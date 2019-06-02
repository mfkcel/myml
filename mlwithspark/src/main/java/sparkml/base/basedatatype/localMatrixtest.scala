package sparkml.base.basedatatype

import org.apache.spark.ml.linalg.Matrices


/**LocalMatrix 本地矩阵
  *
  * 本地矩阵具有整型的行、列索引值和双精度浮点型的元素值，它存储在单机上。
  * MLlib支持稠密矩阵DenseMatrix和稀疏矩阵Sparse Matrix两种本地矩阵，
  *
  * 稠密矩阵将所有元素的值存储在一个列优先（Column-major）的双精度型数组中，
  *
  * 而稀疏矩阵则将非零元素以列优先的CSC（Compressed Sparse Column）模式进行存储，
  * 关于CSC等稀疏矩阵存储方式的具体实现，可以参看Sparse Matrix Compression Formats一文。
  *
  * 本地矩阵的基类是org.apache.spark.mllib.linalg.Matrix，DenseMatrix和SparseMatrix均是它的实现类，
  * 和本地向量类似，MLlib也为本地矩阵提供了相应的工具类Matrices，调用工厂方法即可创建实例
  *
  * 稠密矩阵与稀疏矩阵详见
  * https://www.cnblogs.com/xbinworld/p/4273506.html?utm_source=tuicool&utm_medium=referral
  */

object localMatrixtest {
  def main(args:Array[String]) = {
    // 创建一个3行2列的稠密矩阵 [ [1.0, 2.0], [3.0, 4.0], [5.0, 6.0] ]
    // 数组参数列优先
    val dm = Matrices.dense(3, 2, Array(1.0, 3.0, 5.0, 2.0, 4.0, 6.0))

    // 创建一个3行2列的稀疏矩阵[ [9.0,0.0], [0.0,8.0], [0.0,6.0]]
    // 第一个数组参数表示列指针，即每一列元素的开始索引值，最后一个元素值表示value的大小
    // 第二个数组参数表示行索引，即对应的元素是属于哪一行
    // 第三个数组即是按列先序排列的所有非零元素，通过列指针和行索引即可判断每个元素所在的位置
    val dm1 = Matrices.sparse(3, 2, Array(0, 1, 3), Array(0, 2, 1), Array(9, 6, 8))
    println(dm1)
  }
}
