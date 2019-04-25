'''
python的一些基本语法
'''
# while 循环
i = 0
while i < 10:
    i += 1
    print(i)

# 循环list
list1 = [1, 2, 3, 4, 5, 6]
for i in list1:
    print(i)

# 循环 dict
dict1 = {'name':'mfkcel', 'age':25, 'sex':'male', 'address':'suzhou'}
for k in dict1:
    print(k,'-',dict1[k])

age = int(input('输入你的年龄:'))
if age < 18:
    print('你是祖国的未来')
elif age < 25:
    print('你是祖国的活力')
elif age < 45:
    print('你是祖国的顶梁')
elif age < 55:
    print('你是中年晚期')
else:
    print('你儿孙满堂了')