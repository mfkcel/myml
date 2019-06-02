package sparkml.base.statistic

import org.apache.spark.SparkConf
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.sql.{Row, SparkSession}

/**
  * sparkml 统计模块
  */
object correlationtest {
  val conf = new SparkConf().setMaster("local[4]")
  val ssc = SparkSession.builder().config(conf).getOrCreate()

  def main(args:Array[String]) = {
    val data = Seq(
      Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0),
      Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
    )

    import ssc.implicits._
    // 下面这个语法不太清楚
    val df = data.map(Tuple1.apply).toDF("features")

    // 下面这个语法不太清楚
//    val Row(coeff1:Matrix) =Matrix
  }
}
