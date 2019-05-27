"""
该算法没有向量化处理
"""
def processLine(line):
    """
    line: 读入的内容
    返回单词列表
    需要分隔句子为单个单词，去除单词中的空白符, 去除一些停用词，无效符号
    """
    words = line.split(" ")
    resultWords = []
    for word in words:
        word1 = word.strip()
        if word1.isnumeric():
            continue
        if len(word1) == 1 or len(word1) == 0:
            continue
        if word1[-1] in [',', '!', '.', '?', '(', ')']:
            word1 = word1[:-1]
        if word1[0] in [',', '!', '.', '?', '(',')']:
            word1 = word1[1:]
        # 这里最后是去除停用词，因为没有停用词词典，所有先不去除
        # 去除不需要的词是很重要的，它很大程度是可以影响结果的
        if not word1.isalnum():
            continue
        resultWords.append(word1.lower())
    return resultWords


def countWord(wordDict, file):
    """
    wordDict: 单词字典
    file: 文件
    读入的每行要注意处理，单词后的符号，空白符，还要注意空行的处理
    """
    with open(file, 'r') as f:
        for line in f.readlines():
            words = processLine(line)
            if len(words) == 0:
                continue
            for word in words:
                if word not in wordDict:
                    wordDict[word] = 0
                wordDict[word] += 1


def getWordPercent(files, className):
    wordDict = {}
    for file in files:
        path = className + "/" + file
        countWord(wordDict, path)
    total = 0
    for word in wordDict.keys():
        total += wordDict[word]

    for word in wordDict.keys():
        wordDict[word] = (wordDict[word] + 1) / (total + 1)
    return wordDict

def trainNB(dirs1, dirs2):
    spamPercent = getWordPercent(dirs1, "spam")
    hamPercent = getWordPercent(dirs2, "ham")
    return (spamPercent, hamPercent)

def splitDirs(path):
    import os
    import random
    dirs = os.listdir(path)
    trainPercent = int(len(dirs) * 0.8)
    random.shuffle(dirs)
    trainDirs = dirs[:trainPercent]
    testDirs = dirs[trainPercent:]
    return trainDirs, testDirs

def proccesData(testDataPath):
    wordList = []
    with open(testDataPath, "r") as f:
        for line in f.readlines():
            wordList.extend(processLine(line))
    return wordList

def classify(nbModel, testDataPath):
    """
      为什么这里预测出来是反的呢,原因是两个单词向量不一样
      我取对数的结果是负数，如果我的单词在某个列表里覆盖越多，那么我的和是越小的
      模型词典: 需要存储下所有分类下的所有特征总数，这样才能在分类时，进行一致性处理
                否则会出现因为训练数据的原因导致结果不稳定

    """
    import math
    tdata = proccesData(testDataPath)
    spamPercent = 0.0000000000000000000001
    hamPercent =  0.0000000000000000000001
    for word in tdata:
        if word in nbModel[0]:
            spamPercent += math.log(nbModel[0][word])
        if word in nbModel[1]:
            hamPercent += math.log(nbModel[1][word])
    if spamPercent < hamPercent:
        return "spam"
    else:
        return "ham"

trainDirs1, testDirs1 = splitDirs("spam")
trainDirs2, testDirs2 = splitDirs("ham")
nbModel = trainNB(trainDirs1, trainDirs2)

for file in testDirs1:
    path = "spam/" + file
    result = classify(nbModel, path)
    print(result)