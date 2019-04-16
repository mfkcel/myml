import numpy as np

'''
广播(Broadcast)是 numpy 对不同形状(shape)的数组进行数值计算的方式， 对数组的算术运算通常在相应的元素上进行。

如果两个数组 a 和 b 形状相同，即满足 a.shape == b.shape，那么 a*b 的结果就是 a 与 b 数组对应位相乘。
这要求维数相同，且各维度的长度相同。
'''
if __name__ == '__main__':

    arr1 = np.array([1, 2, 3, 4])
    arr2 = np.array([4, 5, 6, 7])
    # 两个数组的对应位相乘
    arr3 = arr1 * arr2

    # 当运算中的 2 个数组的形状不同时，numpy 将自动触发广播机制。
    '''
        广播的规则:

            让所有输入数组都向其中形状最长的数组看齐，形状中不足的部分都通过在前面加 1 补齐。
            输出数组的形状是输入数组形状的各个维度上的最大值。
            如果输入数组的某个维度和输出数组的对应维度的长度相同或者其长度为 1 时，这个数组能够用来计算，否则出错。
            当输入数组的某个维度的长度为 1 时，沿着此维度运算时都用此维度上的第一组值。
            
        简单理解：对两个数组，分别比较他们的每一个维度（若其中一个数组没有当前维度则忽略），满足：
            数组拥有相同形状。
            当前维度的值相等。
            当前维度的值有一个是 1。
            若条件不满足，抛出 "ValueError: frames are not aligned" 异常。
    '''

    arr4 = np.array([1, 2, 4])
    # 报错: ValueError: operands could not be broadcast together with shapes (4,) (2,)
    # print(arr1 * arr4)

    arr5 = np.array([
        [1, 2, 3],
        [4, 2, 5]
    ])

    # 这个是可以正确运算的，若arr4=[3, 4, 5, 6] 或 arr4=[1, 2]则报错
    print(arr4 * arr5)
    print()