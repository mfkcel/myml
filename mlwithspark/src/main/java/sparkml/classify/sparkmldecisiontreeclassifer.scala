package sparkml.classify

import org.apache.spark.SparkConf
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{DecisionTreeClassificationModel, DecisionTreeClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession

/**
  * 决策树的基本使用
  */
@Deprecated
object sparkmldecisiontreeclassifer {
  def main(args:Array[String]) = {
    val conf = new SparkConf().setMaster("local[4]").setAppName("decisiontreeclassifer")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()

    val path = "file:///g:\\studydata\\sample_multiclass_classification_data.txt"
    val data = spark.read.format("libsvm").load(path)

    // 分类标签，将元数据添加到标签列中
    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(data)

    // 自动识别分类的特征，并对它们进行索引
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(data)

    val Array(training, test) = data.randomSplit(Array(0.8, 0.2), 12345)

    // 构建模型
    val dt = new DecisionTreeClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeatures")
      .setImpurity("entropy") //不纯度
      .setMaxBins(100) //离散化"连续特征"的最大划分数
      .setMaxDepth(5) // 树的最大深度
      .setMinInfoGain(0.01) // 一个节点分裂的最小信息增益，值为[0,1]
      .setMinInstancesPerNode(10) // 每个节点包含的最小样本数
      .setSeed(123456)

    // 将索引标签转换回原始标签
    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labels)

    val pipeline = new Pipeline()
      .setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

    val model = pipeline.fit(training)

    val predictions = model.transform(test)
    predictions.select("predictedLabel", "label", "features").show()

    // 计算测试误差
    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")

    val accuracy = evaluator.evaluate(predictions)
    println("Test Error = " + (1.0 - accuracy))

    val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    println("learned classification tree model:\n" + treeModel.toDebugString)
  }
}
