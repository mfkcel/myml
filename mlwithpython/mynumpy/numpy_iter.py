import numpy as np


'''
    NumPy 迭代器对象 numpy.nditer 提供了一种灵活访问一个或者多个数组元素的方式。

    迭代器最基本的任务的可以完成对数组元素的访问。

    接下来我们使用 arange() 函数创建一个 2X3 数组，并使用 nditer 对它进行迭代。
'''
if __name__ == '__main__':
    arr1 = np.arange(6).reshape(2, 3)
    print('原始数组:\n')
    print(arr1)

    print('迭代输出元素:\n')
    # 按存储顺序进行迭代
    for x in np.nditer(arr1):
        print(x, end=',')
    print('\n')


    '''
      控制遍历顺序
      for x in np.nditer(a, order='F'):Fortran order，列序优先；
      for x in np.nditer(a.T, order='C'):C order，行序优先；
    '''
    arr1 = np.arange(0, 60, 5).reshape(3, 4)
    print('原始数组:\n')
    print(arr1)
    print('\n')

    print('转置矩阵:\n')
    arr2 = arr1.T
    print(arr2)
    print('\n')

    print('行优先输出(C):\n')
    arr3 = arr2.copy(order='C')
    print(arr3)

    # 下面这样也是可以的
    for x in np.nditer(arr2, order='C'):
        print(x)
    print('\n')

    print('列优先输出(F):\n')
    arr4 = arr2.copy(order='F')
    print(arr4)

    # 下面这样也是可以的
    for x in np.nditer(arr2, order='F'):
        print(x)
    print('\n')

    # 修改数组中元素的值
    print('原始数组:\n')
    print(arr2)
    print('\n')
    for x in np.nditer(arr2, op_flags=['readwrite']):
        x[...] = 2 * x

    print('修改后的值:\n')
    print(arr2)
    print()