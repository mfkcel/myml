package com.mfkcel.mynlp.wordsegmention;

/**
 * create by mfkcel on 2019/11/2 22:40
 *
 * 使用枚举来进行分词产生所有可能的结果，然后使用语言模型来选择最好的分词结果(考虑词语上下文间的语义)
 *
 * 使用数学公式表达就是:P(x1x2x3...xn)=P(x1)*P(x2)*P(x3)...P(xn)
 *
 * 选择其中结果最大的
 *
 *
 * 分词首先要有基本词典
 * 这个是通过统计可以计算出每个词组的频率--基本词典,一般是通过人工统计
 */
public class WordSegmentUnigram {

}