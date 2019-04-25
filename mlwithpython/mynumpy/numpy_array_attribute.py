import numpy as np

# NumPy 数组属性
# 本章节我们将来了解 NumPy 数组的一些基本属性。
#
# NumPy 数组的维数称为秩（rank），一维数组的秩为 1，二维数组的秩为 2，以此类推。
#
# 在 NumPy中，每一个线性的数组称为是一个轴（axis），也就是维度（dimensions）。
# 比如说，二维数组相当于是两个一维数组，其中第一个一维数组中每个元素又是一个一维数组。所以一维数组就是 NumPy 中的轴（axis），
# 第一个轴相当于是底层数组，第二个轴是底层数组里的数组。而轴的数量——秩，就是数组的维数。
#
# 很多时候可以声明 axis。axis=0，表示沿着第 0 轴进行操作，即对每一列进行操作；axis=1，表示沿着第1轴进行操作，即对每一行进行操作。
#
# NumPy 的数组中比较重要 ndarray 对象属性有：
#
# 属性	            说明
# ndarray.ndim	    秩，即轴的数量或维度的数量
# ndarray.shape	    数组的维度，对于矩阵，n 行 m 列
# ndarray.size	    数组元素的总个数，相当于 .shape 中 n*m 的值
# ndarray.dtype	    ndarray 对象的元素类型
# ndarray.itemsize	ndarray 对象中每个元素的大小，以字节为单位
# ndarray.flags	    ndarray 对象的内存信息
# ndarray.real	    ndarray元素的实部
# ndarray.imag	    ndarray 元素的虚部
# ndarray.data	    包含实际数组元素的缓冲区，由于一般通过数组的索引获取元素，所以通常不需要使用这个属性。


if __name__ == '__main__':
    arr = np.array([[1, 2, 3], [2, 3, 4]])

    #维度数量
    print(arr.ndim)

    #数组的元素个数总数以元组的形式给出(n, m)
    print(arr.shape)

    # 数组的元素个数对象类型
    print(arr.dtype)

    # 数组的元素个数总数
    print(arr.size)

    # 数组中每个元素的大小(以字节为单位)
    print(arr.itemsize)
