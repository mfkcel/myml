package sparkml.classify

import org.apache.spark.SparkConf
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.sql.SparkSession

// spark mllib 逻辑回归练习
// ml库与mllib库的区别,它们不能吗？
// ml和mllib都是Spark中的机器学习库，目前常用的机器学习功能2个库都能满足需求。
// spark官方推荐使用ml, 因为ml功能更全面更灵活，未来会主要支持ml，mllib很有可能会被废弃(据说可能是在spark3.0中deprecated）。
// ml主要操作的是DataFrame, 而mllib操作的是RDD，也就是说二者面向的数据集不一样。
//     相比于mllib在RDD提供的基础操作，ml在DataFrame上的抽象级别更高，数据和操作耦合度更低。
//DataFrame和RDD什么关系？DataFrame是Dataset的子集，也就是Dataset[Row], 而DataSet是对RDD的封装，对SQL之类的操作做了很多优化。
//
// 相比于mllib在RDD提供的基础操作，ml在DataFrame上的抽象级别更高，数据和操作耦合度更低。
//ml中的操作可以使用pipeline, 跟sklearn一样，可以把很多操作(算法/特征提取/特征转换)以管道的形式串起来，然后让数据在这个管道中流动。
// 大家可以脑补一下Linux管道在做任务组合时有多么方便。

// ml中无论是什么模型，都提供了统一的算法操作接口，比如模型训练都是fit；
//
// 不像mllib中不同模型会有各种各样的trainXXX。
//mllib在spark2.0之后进入维护状态, 这个状态通常只修复BUG不增加新功能。


/**
  * 逻辑回归
  *
  */
object sparkmlloggression {
  def main(args:Array[String]) = {
    val conf = new SparkConf().setMaster("local[4]").setAppName("loggression")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    // 数据准备
    val training = spark.createDataFrame(Seq(
      (1.0, Vectors.dense(0.0, 1.1, 0.1)),
      (0.0, Vectors.dense(2.0, 1.0, -1.0)),
      (0.0, Vectors.dense(2.0, 1.3, 1.0)),
      (1.0, Vectors.dense(0.0, 1.2, -0.5))
    )).toDF("label", "features")

    //建立模型,会有默认的参数
    val lr = new LogisticRegression()

    // 设置自己的参数 lr.explainParam 查看参数解释
    // 这是方法一
    // 正则化
    lr.setMaxIter(10).setRegParam(0.01)

    // 训练模型
    val model1 = lr.fit(training)

    // 方法二，通过ParamMap
    val paramMap = ParamMap(lr.maxIter -> 20)
      .put(lr.maxIter, 30)
      .put(lr.regParam -> 0.1, lr.threshold -> 0.55)

    val paramMap2 = ParamMap(lr.probabilityCol -> "myprobability")
    // 联合两个参数
    val paramMapCombined = paramMap ++ paramMap2
    val model2 = lr.fit(training, paramMapCombined)

    // 测试数据
    val test = spark.createDataFrame(Seq(
      (1.0, Vectors.dense(-1.0, 1.5, 0.1)),
      (0.0, Vectors.dense(3.0, 2.0, -0.1)),
      (1.0, Vectors.dense(0.0, 2.2, -1.5))
    )).toDF("label", "features")

    // 测试模型
    model1.transform(test)
      .select("label", "features", "probability", "prediction")
      .collect()
        .foreach(println(_))
    print()
  }
}
