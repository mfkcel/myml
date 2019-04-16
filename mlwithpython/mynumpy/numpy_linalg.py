import numpy as np
import numpy.linalg
import numpy.matlib

'''
NumPy 提供了线性代数函数库 linalg，该库包含了线性代数所需的所有功能，

    函数	描述
    dot	两个数组的点积，即元素对应相乘。
    vdot	两个向量的点积
    inner	两个数组的内积
    matmul	两个数组的矩阵积
    determinant	数组的行列式
    solve	求解线性矩阵方程
    inv	计算矩阵的乘法逆矩阵
'''

if __name__ == '__main__':

    # numpy.dot()
    # numpy.dot() 对于两个一维的数组，计算的是这两个数组对应下标元素的乘积和(数学上称之为内积)；
    # 对于二维数组，计算的是两个数组的矩阵乘积；对于多维数组，
    # 它的通用计算公式如下，即结果数组中的每个元素都是：
    # 数组a的最后一维上的所有元素与数组b的倒数第二位上的所有元素的乘积和：
    #       dot(a, b)[i,j,k,m] = sum(a[i,j,:] * b[k,:,m])。
    #
    # numpy.dot(a, b, out=None)
    # 参数说明：
    #
    # a : ndarray 数组
    # b : ndarray 数组
    # out : ndarray, 可选，用来保存dot()的计算结果
    a = np.array([[1,2],
                  [3,4]])

    b = np.array([[11,12],
                  [13,14]])
    print(np.dot(a, b))
    # [[37 40]
    #  [85 92]]


    # numpy.vdot()
    # numpy.vdot() 函数是两个向量的点积。
    # 如果第一个参数是复数，那么它的共轭复数会用于计算。 如果参数是多维数组，它会被展开。
    a = np.arange(4)
    b = np.arange(4)
    # 计算式: 0*0 + 1*1 + 2*2 + 3*3
    print(np.vdot(a, b))


    # numpy.inner()
    # numpy.inner() 函数返回一维数组的向量内积。对于更高的维度，它返回最后一个轴上的和的乘积。
    # 数组的列数需相同
    a = np.arange(4)
    b = np.arange(4)
    print(np.inner(a, b))
    # 计算式: 0*0 + 1*1 + 2*2 + 3*3


    # numpy.matmul
    # numpy.matmul 函数返回两个数组的矩阵乘积。 虽然它返回二维数组的正常乘积，但如果任一参数的维数大于2，则将其视为存在于最后两个索引的矩阵的栈，并进行相应广播。
    #
    # 另一方面，如果任一参数是一维数组，则通过在其维度上附加 1 来将其提升为矩阵，并在乘法之后被去除。
    #
    # 对于二维数组，它就是矩阵乘法 要注意矩阵乘法的条件
    a = np.array([
        [1, 2, 3],
        [2, 5, 8]
    ])

    b = np.array([
        [3, 4],
        [8, 9],
        [-2, 5]
    ])
    print(np.matmul(a, b))

    # numpy.linalg.det()
    # numpy.linalg.det() 函数计算输入矩阵的行列式。
    #
    # 行列式在线性代数中是非常有用的值。 它从方阵的对角元素计算。 对于 2×2 矩阵，它是左上和右下元素的乘积与其他两个的乘积的差。
    #
    # 换句话说，对于矩阵[[a，b]，[c，d]]，行列式计算为 ad-bc。 较大的方阵被认为是 2×2 矩阵的组合。

    print(np.linalg.det(np.array([
        [2, 4, 5],
        [3, 8, 9],
        [-2, 4, 12]
    ])))


    # numpy.linalg.solve()
    # numpy.linalg.solve() 函数给出了矩阵形式的线性方程的解。
    #
    # 考虑以下线性方程：
    #
    # x + y + z = 6
    #
    # 2y + 5z = -4
    #
    # 2x + 5y - z = 27
    # A * X = B
    # X = A^(-1)*B
    a = np.array([
        [1, 1, 1],
        [0, 2, 5],
        [2, 5, -1]
    ])
    b = np.array([
        6, -4, 27
    ]).T
    # [ 5.  3. -2.]
    print(np.linalg.solve(a, b))



    # numpy.linalg.inv()
    # numpy.linalg.inv() 函数计算矩阵的乘法逆矩阵。
    #
    # 逆矩阵（inverse matrix）：设A是数域上的一个n阶矩阵，若在相同数域上存在另一个n阶矩阵B，
    # 使得： AB=BA=E ，则我们称B是A的逆矩阵，而A则被称为可逆矩阵。注：E为单位矩阵。

    x = np.array([
        [1, 2],
        [3, 4]
    ])
    y = np.linalg.inv(x)
    # print(y)
    print(np.matmul(y, x))
    print()