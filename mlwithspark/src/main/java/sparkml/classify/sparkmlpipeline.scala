package sparkml.classify

import org.apache.spark.SparkConf
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.sql.SparkSession

object sparkmlpipeline {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[4]")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    // 为什么这就直接使用中文，而在使用java时就不行???
    val training = spark.createDataFrame(Seq(
      (0L, "商业模式 观察 中国制造2025 定位 财富 双11 金融", 1.0),
      (1L, "杂志 财富 学区房 图书馆 哈佛 北京市 加班 养车 成功人士 思维方式 500强 土豪 朋友圈", 0.0),
      (2L, "阿里巴巴 快讯通财经 纽交所 圆通速递 摩根士丹利 顺丰 中通 中通快递 融资 路演 纽约证券交易所", 1.0),
      (3L, "宝马 奔驰 腾讯新闻 劳斯莱斯幻影 内饰 私家车 马云 私人定制 自媒体 企业家 客户端", 0.0)
    )).toDF("id", "text", "label")

    // 分词
    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    // 提取特征值
    val hashingTF = new HashingTF()
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")
      .setNumFeatures(1000) //如果文章词很多一定要设置

    // 构建转换器
    val lr = new LogisticRegression().setMaxIter(10).setRegParam(0.01)

    //创建模型, 并设置相应的stage
    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTF, lr))

    // 训练模型，或者说安装数据
    val model = pipeline.fit(training)
    // 保存管道
//    pipeline.save("")
//    model.save("")  //模型保存
//    PipelineModel.load("") //可以加载前面保存的模型

    val test = spark.createDataFrame(Seq(
      (4L, "美女 胸衣 大美腿 大咪咪"),
      (5L, "中国 美国 政府"),
      (6L, "路虎 奇瑞"),
      (7L, "apache hadoop")
    )).toDF("id", "text")

    model.transform(test).select("id", "text", "probability", "prediction").collect().foreach(println(_))
  }
}
