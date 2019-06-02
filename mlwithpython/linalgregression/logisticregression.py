import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns # 作为对plt的补充
plt.style.use('fivethirtyeight') #样式美化
import matplotlib.pyplot as plt
# import tensorflow as tf
from sklearn.metrics import classification_report#这个包是评价报告

data = pd.read_csv('ex2data1.txt', names=['exam1', 'exam2', 'admitted'])

sns.set()

sns.lmplot('exam1', 'exam2', hue='admitted', data=data,
           size=6,
           fit_reg=False,
           scatter_kws={"s": 50}
           )
plt.show()#看下数据的样子

def get_X(df):#读取特征
    #     """
    #     use concat to add intersect feature to avoid side effect
    #     not efficient for big dataset though
    #     """
    ones = pd.DataFrame({'ones': np.ones(len(df))})#ones是m行1列的dataframe
    data = pd.concat([ones, df], axis=1)  # 合并数据，根据列合并
    return data.iloc[:, :-1].as_matrix()  # 这个操作返回 ndarray,不是矩阵


def get_y(df):#读取标签
    #     '''assume the last column is the target'''
    return np.array(df.iloc[:, -1])#df.iloc[:, -1]是指df的最后一列


def normalize_feature(df):
    #     """Applies function along input axis(default 0) of DataFrame."""
    return df.apply(lambda column: (column - column.mean()) / column.std())#特征缩放

X = get_X(data)


y = get_y(data)

def sigmoid(z):
    # your code here  (appro ~ 1 lines)
    gz = np.power(np.ones(z.shape[0]).transpose() + np.exp(-1 * z.transpose()), -1)
    #     gz = None
    return gz

fig, ax = plt.subplots(figsize=(8, 6))
ax.plot(np.arange(-10, 10, step=0.01),
        sigmoid(np.arange(-10, 10, step=0.01)))
ax.set_ylim((-0.1,1.1))
ax.set_xlabel('z', fontsize=18)
ax.set_ylabel('g(z)', fontsize=18)
ax.set_title('sigmoid function', fontsize=18)
plt.show()

theta=np.zeros(3) # X(m*n) so theta is n*1

def cost(theta, X, y):
    ''' cost fn is -l(theta) for you to minimize'''
    # your code here  (appro ~ 2 lines)
    x = np.dot(X, theta.transpose())
    result = (np.log(sigmoid(x)) * -1 * y - (1 - y) * np.log(1 - sigmoid(x))) / X.shape[0]
    costf = result
    return costf
# Hint:X @ theta与X.dot(theta)等价

def gradient(theta, X, y):
    # your code here  (appro ~ 2 lines)
    x = np.dot(X, theta.transpose())
    result = (np.dot(X.transpose(), sigmoid(x) - y)) / X.shape[0]
    grad = result
    return grad

import scipy.optimize as opt

# 这个运行报错 The truth value of an array with more than one element is ambiguous. Use a.any() or a.all()
# 这个是咋回事呢???
res = opt.minimize(fun=cost, x0=theta, args=(X, y), method='Newton-CG', jac=gradient)


print(res)