package sparkml.classify

import org.apache.spark.SparkConf
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}
import org.apache.spark.sql.SparkSession

/**
  * 另外一种优化思路:使用训练校验分类
  */
object sparkmllineregressionoptimize {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[4]")
    val spark = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()

    val data = spark.read.format("libsvm").
      load("file:///g:\\studydata\\sample_linear_regression_data.txt")
    val Array(training, test) = data.randomSplit(Array(0.9, 0.1), 12345)

    val lr = new LinearRegression()

    // 怎么确定要设置哪些参数呢，这个就需要了解算法的底层原理了
    // 参数是基于模型的，通过查看参数介绍了解要设置哪些参数,如果不设置的话就是使用默认参数
    // 回归正则化方法（Lasso，Ridge和ElasticNet）在高维和数据集变量之间多重共线性情况下运行良好。
    // 数学上，ElasticNet被定义为L1和L2正则化项的凸组合：
    // 通过适当设置α，ElasticNet包含L1和L2正则化作为特殊情况。
    // 例如，如果用参数α设置为1来训练线性回归模型，则其等价于Lasso模型。
    // 另一方面，如果α被设置为0，则训练的模型简化为ridge回归模型。
    val paramGrid = new ParamGridBuilder().
      addGrid(lr.elasticNetParam, Array(0.0, 0.5, 1.0)).
      addGrid(lr.fitIntercept).
      addGrid(lr.regParam, Array(0.1, 0.01)).
      build()

    val trainValidation = new TrainValidationSplit().
      setEstimator(lr).
      setEstimatorParamMaps(paramGrid).
      setEvaluator(new RegressionEvaluator()).
      setTrainRatio(0.8)

    val model = trainValidation.fit(training)

    model.transform(test).select("features", "label", "prediction").show()
  }
}
