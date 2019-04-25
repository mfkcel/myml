'''
python 文件的操作
'''

# input 函数可以获取输入
# str1 = input('请输入你的姓名:')
# print(str1)

'''
python 文件的操作
Python 提供了必要的函数和方法进行默认情况下的文件基本操作。你可以用 file 对象做大部分的文件操作。

open 函数
你必须先用Python内置的open()函数打开一个文件，创建一个file对象，相关的方法才可以调用它进行读写。
如果需要使用python来操作系统目录，需要使用os模块提供的功能


完整的语法格式为：

open(file, mode='r', buffering=-1, encoding=None, errors=None, newline=None, closefd=True, opener=None)
参数说明:

file: 必需，文件路径（相对或者绝对路径）。
mode: 可选，文件打开模式
buffering: 设置缓冲
encoding: 一般使用utf8
errors: 报错级别
newline: 区分换行符
closefd: 传入的file参数类型
opener:
          
File对象的属性
一个文件被打开后，你有一个file对象，你可以得到有关该文件的各种信息。


file 对象
file 对象使用 open 函数来创建，下表列出了 file 对象常用的函数：

序号	方法及描述
1	
file.close()

关闭文件。关闭后文件不能再进行读写操作。

2	
file.flush()

刷新文件内部缓冲，直接把内部缓冲区的数据立刻写入文件, 而不是被动的等待输出缓冲区写入。

3	
file.fileno()

返回一个整型的文件描述符(file descriptor FD 整型), 可以用在如os模块的read方法等一些底层操作上。

4	
file.isatty()

如果文件连接到一个终端设备返回 True，否则返回 False。

5	
file.next()

返回文件下一行。

6	
file.read([size])

从文件读取指定的字节数，如果未给定或为负则读取所有。

7	
file.readline([size])

读取整行，包括 "\n" 字符。

8	
file.readlines([sizeint])

读取所有行并返回列表，若给定sizeint>0，则是设置一次读多少字节，这是为了减轻读取压力。

9	
file.seek(offset[, whence])

设置文件当前位置

10	
file.tell()

返回文件当前位置。

11	
file.truncate([size])

截取文件，截取的字节通过size指定，默认为当前文件位置。

12	
file.write(str)

将字符串写入文件，返回的是写入的字符长度。

13	
file.writelines(sequence)

向文件写入一个序列字符串列表，如果需要换行则要自己加入每行的换行符。
'''

f1 = open('D:\myfile\\test.txt', 'w')
print('文件名(应该说是路径):', f1.name)
print('是否已关闭:', f1.closed)
print('访问模式:', f1.mode)

# 在打开文件进行，如果遇错进行相应的处理
try:
    f = open('/path/to/file', 'r')
    print(f.read())
finally:
    if f:
        f.close()

# 还可以如下面这样写
with open('/path/to/file', 'r') as f:
    print(f.read())

