package sparkml

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature._
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 使用贝叶斯模型进行分类
  */

object ClassificationMLScalaChinese {

  case class RawDataRecord(category:Int, text:String)

  /**
    * 对要预测的数据进行预处理
    * @param spark
    * @param data
    * @return
    */
  def processData(spark:SparkSession, data:String) = {
    val ssc = spark.sparkContext
    //传入的字符串不要被分开，否则会当作多个文本
    val wordsRdd = ssc.parallelize(Array(data))
    import spark.implicits._
    wordsRdd.toDF("text")
  }


  /**
    * 加载数据,映射为  DataFrame[RawDataRecord[category, words] ]
    * @param spark
    * @param path
    * @return
    */
  def loadFile(spark:SparkSession, path:String):DataFrame = {
    val ssc = spark.sparkContext
    val fileRdd = ssc.textFile(path)

    val docsRdd = fileRdd.mapPartitions(iter => {
      var list:List[(String, String)] = Nil
      for(line <- iter) {
        val kv = line.split("=")
        list = (kv(0), kv(1)) :: list
      }

      list.toIterator
    })
//      .groupByKey()
//      .mapPartitions(iter => {
//        var list:List[(String, String)] = Nil
//        for(category <- iter) {
//          val key = category._1
//          val sb = new StringBuffer()
//          for(v <- category._2) {
//            sb.append(v)
//            sb.append(" ")
//          }
//          sb.deleteCharAt(sb.length() - 1)
//
//          list = (key, sb.toString) :: list
//        }
//        list.toIterator
//      })

    import spark.implicits._
    docsRdd.toDF("label", "text")
  }



  /**
    * 模型的训练
    * @param df
    */
  def trainModel(df:DataFrame, labelIndexer:StringIndexerModel) = {
    //创建模型
    val naiveBayes = new NaiveBayes()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setFeaturesCol("features")

    //将索引标签转换回原始标签
    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictLabel")
      .setLabels(labelIndexer.labels)


    //创建管道
    // 将多个处理阶段组合起来
    val pipeline = new Pipeline().setStages(Array(naiveBayes, labelConverter))

    //计算tf-idf 也可以不需要这个,在需要提取关键词时才需要这个
//    val idf = new IDF().setInputCol("rawfeatures").setOutputCol("features")
//    val idfModel = idf.fit(hashingTFData)
//    val feaData = idfModel.transform(hashingTFData)
    //训练模型
    //df里面一定要含有label标签
    pipeline.fit(df)
  }


  /**
    * 预测
    * @param df
    * @param pm
    * @return
    */
  def predict(df:DataFrame, pm: PipelineModel) = {
    pm.transform(df)
  }


  def main(args:Array[String])={
    val conf = new SparkConf()
      .setMaster("local[4]")
      .setAppName("classification")

    val spark = SparkSession
      .builder()
      .config(conf)
//      .enableHiveSupport()
      .getOrCreate()

    val dataPath = "file:///D:\\myfile\\myml\\data\\total.txt"

    //应该将所有的数据聚合在一起才行

    // 加载数据,映射为<DataFrame[RawDataRecord[category, words]]>
    // 将数据分为训练数据与，测试数据,应该在对数据作处理前向量化处理，这样方便后面处理
    // 避免出现一些重复的步骤
    // 如果数据不是向量化的数据，一定要先进行向量化处理！！！！！！这个很重要

    val data = loadFile(spark, dataPath)
    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(data) // 这里一定要用fit,否则后面的IndexTostring无法调用labeldexer的labels


    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    //计算hashingtf,因为语料已经是关键词了，这里不再进行tf-idf的计算
    val hashingTF = new HashingTF()
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")

    val pipeline = new Pipeline().setStages(Array(labelIndexer, tokenizer, hashingTF))


    val pipelineModel = pipeline.fit(data)
    val td = pipelineModel.transform(data)

    val Array(traindata, testdata) = td.randomSplit(Array(0.7,0.3), 1L)

    val classicalModel = trainModel(traindata, labelIndexer)
//    classicalModel.save("file:///D:\\myfile\\iwork\\model\\classify")


    //评估模型 跑不起来,哪错了
    //错误原因是，输入的数据是文本，应该对数据进行向量化处理后才能使用
    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")

    val predictions = classicalModel.transform(td)
    val predictAccuracy = evaluator.evaluate(predictions)
    predictions.show(1000)
    println("accuracy:" + predictAccuracy) // accuracy:0.25


    // 不能进行批量预测
//    testdata.createOrReplaceTempView("_testdata")
//    val testdata2 = spark.sql("select count(label) from _testdata")
//    var labelList = Nil
//    for(td <- testdata2) {
//      println(td)
//    }
//
//    val predict = classicalModel.transform(testdata.select("text").limit(1))
//
//    val dfPredictioin = predict.select("predictLabel")
//    val dfLabel = testdata.select("label")
//
//    predict.select("*").show()

    // 怎么将两个DataFrame按行合并(a), (b) -> (a, b)
    // union 合并的结果是(a), (b) -> ((a), (b))


//    val test:String = "腾迅 股票 涨停"
//    val testRS = predict(processData(spark, test), classicalModel)
//    testRS.select("prediction")
//    println()

  }
}
