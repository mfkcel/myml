from matplotlib import pyplot as plt
import matplotlib
import numpy as np
'''
Matplotlib 是 Python 的绘图库。 它可与 NumPy 一起使用，提供了一种有效的 MatLab 开源替代方案。
默认是不显示中文的
'''

if __name__ == '__main__':
    x = np.arange(1, 11)
    y = 2 * x + 5
    plt.title('Matplotlib demos')
    plt.xlabel('x axis caption')
    plt.xlabel('y axis caption')
    plt.plot(x, y)
    # plt.show()

    '''
        这个没有测试
        要显示中文，需要作如下处理
        首先下载字体（注意系统）：https://www.fontpalace.com/font-details/SimHei/

        SimHei.ttf 文件放在当前执行的代码文件中：
    '''
    # a = sorted([f.name for f in matplotlib])
    # for i in a:
    #     print(i)