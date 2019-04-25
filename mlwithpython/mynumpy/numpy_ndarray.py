import numpy as np


'''
ndarray 的各种创建方法
'''
if __name__ == '__main__':
    # 使用np 创建一维数组, 从已有的数组
    arr1 = np.array([1, 2, 3, 4])

    # 在创建多维数组时，每一维长度应该相同，否则会转化成list
    arr2 = np.array([[1, 2, 3], [1, 2, 3], [1, 2, 3]])

    # 创建时指定最小维度, 如果传入的没有达到最小维度，它会自动转化
    # 它定义的实质是一个矩阵
    arr3 = np.array([1, 2, 3], ndmin=2)

    # 创建时指定参数类型，如果传入的参数不是该类型，它会自动转化
    arr4 = np.array([1, 2, 3], dtype=complex)

    # numpy.empty 方法用来创建一个指定形状（shape）、数据类型（dtype）且未初始化的数组：
    # numpy.empty(shape, dtype = float, order = 'C')
    # 参数说明：
    #
    # 参数	描述
    # shape	数组形状
    # dtype	数据类型，可选
    # order	有"C"和"F"两个选项,分别代表，行优先和列优先，在计算机内存中的存储元素的顺序。
    # 使用随机值填充每个位置
    x = np.empty([3, 2], dtype=int)



    # numpy.zeros
    # 创建指定大小的数组，数组元素以 0 来填充：
    #
    # numpy.zeros(shape, dtype = float, order = 'C')
    # 参数说明：
    #
    # 参数	描述
    # shape	数组形状
    # dtype	数据类型，可选
    # order	'C' 用于 C 的行数组，或者 'F' 用于 FORTRAN 的列数组
    x1 = np.zeros([3, 4], dtype=int)



    # numpy.ones
    # 创建指定形状的数组，数组元素以 1 来填充：
    #
    # numpy.ones(shape, dtype = None, order = 'C')
    # 参数说明：
    #
    # 参数	描述
    # shape	数组形状
    # dtype	数据类型，可选
    # order	'C' 用于 C 的行数组，或者 'F' 用于 FORTRAN 的列数组
    x2 = np.ones([3, 4], dtype=int)

    # 创建 randn(size) 服从 X~N(0,1) 的正态分布随机数组
    x3 = np.random.randn(3, 4)


    # Numpy 创建随机分布整数型数组。
    #
    # 利用 randint([low,high],size) 创建一个整数型指定范围在 [low.high] 之间的数组：
    x4 = np.random.randint(10, 100, (3, 4))

    #  arange(start, end, step, dtype) 创建一维数组
    x5 = np.arange(3, 15, 2)

    # eye 创建对角矩阵数组
    x6 = np.eye(5)


    # NumPy 从已有的数组创建数组
    #
    # numpy.asarray
    # numpy.asarray 类似 numpy.array，但 numpy.asarray 只有三个，比 numpy.array 少两个。
    #
    # numpy.asarray(a, dtype = None, order = None)
    # 参数说明：
    #
    # 参数	描述
    # a	任意形式的输入参数，可以是，列表, 列表的元组, 元组, 元组的元组, 元组的列表，多维数组
    # dtype	数据类型，可选
    # order	可选，有"C"和"F"两个选项,分别代表，行优先和列优先，在计算机内存中的存储元素的顺序。
    a1 = [1, 2, 3]
    ar1 = np.asarray(x)

    t1 = (1, 3, 4)
    ar1 = np.asarray(t1)

    t2 = [(1, 2, 4), (8, 9)]
    ar1 = np.asarray(t1, dtype=float)


    # numpy.frombuffer
    # numpy.frombuffer 用于实现动态数组。
    #
    # numpy.frombuffer 接受 buffer 输入参数，以流的形式读入转化成 ndarray 对象。
    #
    # numpy.frombuffer(buffer, dtype = float, count = -1, offset = 0)
    # 注意：buffer 是字符串的时候，Python3 默认 str 是 Unicode 类型，所以要转成 bytestring 在原 str 前加上 b。
    #
    # 参数说明：
    #
    # 参数	描述
    # buffer	可以是任意对象，会以流的形式读入。
    # dtype	返回数组的数据类型，可选
    # count	读取的数据数量，默认为-1，读取所有数据。
    # offset	读取的起始位置，默认为0。
    str1 = b'the people\'s republic of china'
    ar1 = np.frombuffer(str1, dtype='S1')


    # numpy.fromiter
    # numpy.fromiter 方法从可迭代对象中建立 ndarray 对象，返回一维数组。
    #
    # numpy.fromiter(iterable, dtype, count=-1)
    # 参数	描述
    # iterable	可迭代对象
    # dtype	返回数组的数据类型
    # count	读取的数据数量，默认为-1，读取所有数据
    list1 = range(5)
    it = iter(list1)
    ar1 = np.fromiter(it, dtype=float)


    # numpy.linspace
    # numpy.linspace 函数用于创建一个一维数组，数组是一个等差数列构成的，格式如下：
    #
    # np.linspace(start, stop, num=50, endpoint=True, retstep=False, dtype=None)
    # 参数说明：
    #
    # 参数	描述
    # start	序列的起始值
    # stop	序列的终止值，如果endpoint为true，该值包含于数列中
    # num	要生成的等步长的样本数量，默认为50
    # endpoint	该值为 ture 时，数列中中包含stop值，反之不包含，默认是True。
    # retstep	如果为 True 时，生成的数组中会显示间距，反之不显示。
    # dtype	ndarray 的数据类型
    ar1 = np.linspace(1, 10, 10)


    # 要构建一个多维数组，那么一维数组的元素个数一定要是后面多维数组元素个数
    # 如果不匹配会报错
    ar1 = np.linspace(1, 10, 25).reshape([5, 5])


    # numpy.logspace
    # numpy.logspace 函数用于创建一个于等比数列。格式如下：
    #
    # np.logspace(start, stop, num=50, endpoint=True, base=10.0, dtype=None)
    # base 参数意思是取对数的时候 log 的下标。
    #
    # 参数	描述
    # start	序列的起始值为：base ** start
    # stop	序列的终止值为：base ** stop。如果endpoint为true，该值包含于数列中
    # num	要生成的等步长的样本数量，默认为50
    # endpoint	该值为 ture 时，数列中中包含stop值，反之不包含，默认是True。
    # base	对数 log 的底数。
    # dtype	ndarray 的数据类型
    # 每个值是怎样算的 开始与终点间均分后值的底数的乘方

    # 默认底数是10
    ar1 = np.logspace(1, 2, num=10, base=5)
    # 输出
    # [ 5.          5.97906587  7.14984574  8.54987973 10.22405883 12.22606424
    #  14.62008869 17.48289467 20.90627577 25.        ]
    print(ar1)

