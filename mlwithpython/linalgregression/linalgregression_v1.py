import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

def computeCost(X, y, theta):
    # your code here  (appro ~ 2 lines)
    # 计算假设函数 与真实值的差值的矩阵
    m = y.shape[0]
    temp = np.dot(X, theta.transpose()) - y
    # 将每个元素平方后求和
    total = (1/(2*m)) * np.sum(np.power(temp, 2), axis=0)
    return total


def gradientDescent(X, y, theta, alpha, iters):
    # 梯度下降
    # 这个是有问题的，不能很好的拟合数据，在对Numpy熟练后再来看看 2019.4.25
    # 关键是给的答案跑不起来
    temp = np.matrix(np.zeros(theta.shape[1]))
    parameters = int(theta.ravel().shape[1])
    cost = np.zeros(iters)

    for i in range(iters):
        # your code here  (appro ~ 1 lines)
        #temp = np.matrix(np.zeros(theta.shape))
        for j in range(parameters):
            temp2 = np.dot(X, theta.transpose()) - y
            if j == 0:
                temp[0,j] = theta[0,j] - np.sum(temp2) * alpha / X.shape[0]
            else:
                temp[0,j] = theta[0,j] - np.sum(np.multiply(temp2, X[:,1])) * alpha / X.shape[0]
        # your code here  (appro ~ 2 lines)
        theta = temp
        cost[i] = computeCost(X, y, theta)
    return theta, cost


# 单变量线性回归
path =  'ex1data1.txt'
data = pd.read_csv(path, header=None, names=['Population', 'Profit'])

data.plot(kind='scatter', x='Population', y='Profit', figsize=(12,8))

data.insert(0, 'Ones', 1)

# set X (training data) and y (target variable)
cols = data.shape[1]
X = data.iloc[:,0:cols-1] #X是所有行，去掉最后一列
y = data.iloc[:,cols-1:cols] # X是所有行，最后一列


X = np.matrix(X.values)
y = np.matrix(y.values)
# your code here  (appro ~ 1 lines)
theta = np.matrix(np.array([
    0, 0
]))

alpha = 0.01
iters = 1000

g, cost = gradientDescent(X, y, theta, alpha, iters)

computeCost(X, y, g)

x = np.linspace(data.Population.min(), data.Population.max(), 100)
f = g[0, 0] + (g[0, 1] * x)

fig, ax = plt.subplots(figsize=(12,8))
ax.plot(x, f, 'r', label='Prediction')
ax.scatter(data.Population, data.Profit, label='Traning Data')
ax.legend(loc=2)
ax.set_xlabel('Population')
ax.set_ylabel('Profit')
ax.set_title('Predicted Profit vs. Population Size')
plt.show()

fig, ax = plt.subplots(figsize=(12,8))
ax.plot(np.arange(iters), cost, 'r')
ax.set_xlabel('Iterations')
ax.set_ylabel('Cost')
ax.set_title('Error vs. Training Epoch')
plt.show()



# 多变量线性回归
path =  'ex1data2.txt'
data2 = pd.read_csv(path, header=None, names=['Size', 'Bedrooms', 'Price'])

data2 = (data2 - data2.mean()) / data2.std()

# add ones column
data2.insert(0, 'Ones', 1)

# set X (training data) and y (target variable)
cols = data2.shape[1]
X2 = data2.iloc[:,0:cols-1]
y2 = data2.iloc[:,cols-1:cols]

# convert to matrices and initialize theta
X2 = np.matrix(X2.values)
y2 = np.matrix(y2.values)
theta2 = np.matrix(np.array([0,0,0]))

# perform linear regression on the data set
g2, cost2 = gradientDescent(X2, y2, theta2, alpha, iters)

# get the cost (error) of the model
computeCost(X2, y2, g2)

fig, ax = plt.subplots(figsize=(12,8))
ax.plot(np.arange(iters), cost2, 'r')
ax.set_xlabel('Iterations')
ax.set_ylabel('Cost')
ax.set_title('Error vs. Training Epoch')
plt.show()