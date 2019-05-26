package sparkml

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{HashingTF, IndexToString, StringIndexer, Tokenizer}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}


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
    * string to index
    * 可以使用StringIndexer
    * @param name
    * @return
    */
  def getLabel(name:String) = name match {
    case "体育" => 0
    case "娱乐" => 1
    case "教育" => 2
    case "时尚" => 3
    case "时政" => 4
    case "汽车" => 5
    case "游戏" => 6
    case "社会" => 7
    case "科技" => 8
    case "财经" => 9
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
    }).groupByKey()
      .mapPartitions(iter => {
        var list:List[(String, String)] = Nil
        for(category <- iter) {
          val key = category._1
          val sb = new StringBuffer()
          for(v <- category._2) {
            sb.append(v)
            sb.append(" ")
          }
          sb.deleteCharAt(sb.length() - 1)

          list = (key, sb.toString) :: list
        }
        list.toIterator
      })

    import spark.implicits._
    docsRdd.toDF("label", "text")
  }



  /**
    * 模型的训练
    * @param df
    */
  def trainModel(df:DataFrame) = {
    //将字符串标签映射为indexer,也可以手动映射，但会不太方便
    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(df)

    //对输入的str使用空格进行分词
    //输入的df要是如下的格式("label", "text"), label:Int, text:String
    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    //计算hashingtf,因为语料已经是关键词了，这里不再进行tf-idf的计算
    val hashingTF = new HashingTF()
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")

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
    val pipeline = new Pipeline().setStages(Array(labelIndexer, tokenizer, hashingTF, naiveBayes, labelConverter))

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

    val dataPath = "file:///D:\\myfile\\iwork\\testdata\\data\\total.txt"

    //应该将所有的数据聚合在一起才行

    // 加载数据,映射为<DataFrame[RawDataRecord[category, words]]>
    // 将数据分为训练数据与，测试数据
    val Array(traindata, testdata) = loadFile(spark, dataPath).randomSplit(Array(0.8,0.2))



    val classicalModel = trainModel(traindata)
//    classicalModel.save("file:///D:\\myfile\\iwork\\model\\classify")


    //评估模型 跑不起来,哪错了
//    val evaluator = new MulticlassClassificationEvaluator()
//      .setLabelCol("indexedLabel")
//      .setPredictionCol("prediction")
//


//    val predictAccuracy = evaluator.evaluate(classicalModel.transform(testdata))
//    println(predictAccuracy)


    val test:String = "腾迅 股票 涨停"
    val testRS = predict(processData(spark, test), classicalModel)
    testRS.select("*").show()
    println()
  }
}
