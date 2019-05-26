package sparkml.classify

import org.apache.spark.SparkConf
import org.apache.spark.ml.PipelineModel
import org.apache.spark.sql.SparkSession
//import sparkml.classify.ClassificationMLScalaChinese._

/**
  * 加载已经训练好的模型进行预测
  */
@Deprecated
object classifyLoadExistModelScala {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[4]")
      .setAppName("classification")

    val spark = SparkSession
      .builder()
      .config(conf)
      //      .enableHiveSupport()
      .getOrCreate()

    val model = PipelineModel.load("file:///F:\\iwork\\model\\classify")

    val test:String = "大众 宝马 奔驰"

//    val testRS = predict(processData(spark, test), model)
//    testRS.select("*").show()
//    println()
  }
}
