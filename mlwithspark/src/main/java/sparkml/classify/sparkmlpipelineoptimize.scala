package sparkml.classify

import org.apache.spark.SparkConf
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder}
import org.apache.spark.sql.SparkSession

/**
  * 一种调优的方法:使用交叉验证器
  */
object sparkmlpipelineoptimize {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[4]")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()
    val training = spark.createDataFrame(Seq(
      (0L, "a b c d e spark", 1.0),
      (1L, "b d", 0.0),
      (2L, "spark f g h", 1.0),
      (3L, "hadoop mapreduce", 0.0),
      (4L, "b spark who", 1.0),
      (5L, "g d a y", 0.0),
      (6L, "spark fly", 1.0),
      (7L, "was mapreduce", 0.0),
      (8L, "e spark program", 1.0),
      (9L, "a e c l", 0.0),
      (10L, "spark compile", 1.0),
      (11L, "hadoop software", 0.0)
    )).toDF("id", "text", "label")

    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    val hashingTF = new HashingTF()
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")

    val lr = new LogisticRegression()
      .setMaxIter(10)

    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTF, lr))

    // 设置网格参数
    val paramGrid = new ParamGridBuilder()
      .addGrid(hashingTF.numFeatures, Array(10, 100, 1000))
      .addGrid(lr.regParam, Array(0.1, 0.01))
      .build()

    // 设置交叉验证器
    // 交叉验证器需要设置三个参数
    // 1.定义好的pipeline
    // 2.一个评估器参数集合
    // 3.一个交叉验证器
    val cv = new CrossValidator()
      .setEstimator(pipeline)
      .setEstimatorParamMaps(paramGrid)
      .setEvaluator(new BinaryClassificationEvaluator())
      .setNumFolds(2) // 这个参数默认是3但至少要>=2, 它是把数据拆分成多份，来进行计算

    val CVModel = cv.fit(training)

    val test = spark.createDataFrame(Seq(
      (12L, "spark h d e"),
      (13L, "a f c"),
      (14L, "mapreduce spark"),
      (15L, "apache hadoop")
    )).toDF("id", "text")

    CVModel.transform(test).select("id", "text", "probability", "prediction").collect().foreach(println(_))
  }
}
