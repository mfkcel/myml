import numpy as np

'''
Numpy 中包含了一些函数用于处理数组，大概可分为以下几类：

修改数组形状
    reshape	不改变数据的条件下修改形状
    flat	数组元素迭代器
    flatten	返回一份数组拷贝，对拷贝所做的修改不会影响原始数组
    ravel	返回展开数组

翻转数组
    transpose	对换数组的维度
    ndarray.T	和 self.transpose() 相同
    rollaxis	向后滚动指定的轴
    swapaxes	对换数组的两个轴
    
修改数组维度

    维度	        描述
    broadcast	产生模仿广播的对象
    broadcast_to	将数组广播到新形状
    expand_dims	扩展数组的形状
    squeeze	从数组的形状中删除一维条目
    
连接数组
    concatenate	连接沿现有轴的数组序列
    stack	沿着新的轴加入一系列数组。
    hstack	水平堆叠序列中的数组（列方向）
    vstack	竖直堆叠序列中的数组（行方向）
    
分割数组
    split	将一个数组分割为多个子数组
    hsplit	将一个数组水平分割为多个子数组（按列）
    vsplit	将一个数组垂直分割为多个子数组（按行）

数组元素的添加与删除
    resize	返回指定形状的新数组
    append	将值添加到数组末尾
    insert	沿指定轴将值插入到指定下标之前
    delete	删掉某个轴的子数组，并返回删除后的新数组
    unique	查找数组内的唯一元素

'''

if __name__ == '__main__':
    '''
    numpy.reshape
    numpy.reshape 函数可以在不改变数据的条件下修改形状，格式如下： numpy.reshape(arr, newshape, order='C')
        arr：要修改形状的数组
        newshape：整数或者整数数组，新的形状应当兼容原有形状
        order：'C' -- 按行，'F' -- 按列，'A' -- 原顺序，'k' -- 元素在内存中的出现顺序。
    '''
    arr1 = np.arange(8)
    print('原始数组:\n')
    print(arr1)
    print('\n')

    arr2 = arr1.reshape(4, 2)
    print('修改后数组:\n')
    print(arr2)


    '''
        numpy.ndarray.flat 是一个数组元素迭代器，
    '''
    arr1 = np.arange(9).reshape(3, 3)
    print('原始数组:\n')
    # 这个是按行输出
    for a in arr1:
        print(a)
        print('-----')

    # 对数组中每个元素都进行处理，可以使用flat属性，该属性是一个数组元素迭代
    print('迭代后的数组:\n')
    for element in arr1.flat:
        print(element)


    '''
        numpy.ndarray.flatten 返回一份数组拷贝，对拷贝所做的修改不会影响原始数组，格式如下：

        ndarray.flatten(order='C')
        参数说明：
        
        order：'C' -- 按行，'F' -- 按列，'A' -- 原顺序，'K' -- 元素在内存中的出现顺序。
    '''
    arr1 = np.arange(8).reshape(2, 4)
    print('原数组:\n')
    print(arr1)
    print('\n')

    print('展开的数组:\n')
    print(arr1.flatten())
    print('\n')
    print('以F风格顺序展开的数组:\n')
    print(arr1.flatten(order='F'))
    print('\n')


    '''
        numpy.ravel
        numpy.ravel() 展平的数组元素，顺序通常是"C风格"，返回的是数组视图（view，有点类似 C/C++引用reference的意味），
        修改会影响原始数组。
        
        该函数接收两个参数：
        
        numpy.ravel(a, order='C')
        参数说明：
        
        order：'C' -- 按行，'F' -- 按列，'A' -- 原顺序，'K' -- 元素在内存中的出现顺序。
    '''
    print()