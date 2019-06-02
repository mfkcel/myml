package sparkml.base.basedatatype

import org.apache.spark.ml.linalg.Vectors

/**
  * Local Vector
  *本地向量存储在单机上，其拥有整型、从0开始的索引值以及浮点型的元素值。
  * MLlib提供了两种类型的本地向量，稠密向量DenseVector和稀疏向量SparseVector。
  * 稠密向量使用一个双精度浮点型数组来表示其中每一维元素，而稀疏向量则是基于一个整型索引数组和一个双精度浮点型的值数组
  *
  * 向量(1.0, 0.0, 3.0)的
  * 稠密向量表示形式是[1.0,0.0,3.0]
  * 而稀疏向量形式则是(3, [0,2], [1.0, 3.0])，
  * 其中，3是向量的长度，[0,2]是向量中非0维度的索引值，表示位置为0、2的两个元素为非零值，而[1.0, 3.0]则是按索引排列的数组元素值
  *
  * 所有本地向量都以org.apache.spark.mllib.linalg.Vector为基类，
  * DenseVector和SparseVector分别是它的两个实现类，
  * 故推荐使用Vectors工具类下定义的工厂方法来创建本地向量
  */
object vectorstest {
  def main(args:Array[String]) = {
    // 构建稠密向量
    val dv = Vectors.dense(Array(1.0, 0, 3.0))

    // 构建稀疏向量
    // 方法1(索引), (数值)
    val sv = Vectors.sparse(3, Array(0, 2), Array(1, 3))
    // 方法2(索引， 数值)
    val sv1 = Vectors.sparse(3, Array(0, 1), Array(2, 3))

    println(dv)
    println(sv.toDense)
  }
}
