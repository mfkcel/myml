import numpy as np


# 数据类型对象 (dtype)
# 数据类型对象是用来描述与数组对应的内存区域如何使用，这依赖如下几个方面：
#
#   1.数据的类型（整数，浮点数或者 Python 对象）
#   2.数据的大小（例如， 整数使用多少个字节存储）
#   3.数据的字节顺序（小端法或大端法）
#   4.在结构化类型的情况下，字段的名称、每个字段的数据类型和每个字段所取的内存块的部分
#   5.如果数据类型是子数组，它的形状和数据类型
#
# 字节顺序是通过对数据类型预先设定"<"或">"来决定的。"<"意味着小端法(最小值存储在最小的地址，即低位组放在最前面)。
# ">"意味着大端法(最重要的字节存储在最小的地址，即高位组放在最前面)。


if __name__ == '__main__':
    #定义结构化数据类型
    student = np.dtype([('name', 'S20'), ('age', 'i1'), ('marks', 'f4')])

    stu = np.array([('abc', 21, 50), ('mfkce', 27, 55)], dtype=student)
    print(student)
    print(stu)