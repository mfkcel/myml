package com.mfkcel.mynlp.wordsegmention;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * create by mfkcel on 2019/11/2 22:40
 *
 * 使用枚举来进行分词产生所有可能的结果，然后使用语言模型来选择最好的分词结果(考虑词语上下文间的语义)
 * 思路如下：
 *         wordset  // 分词集合
 *         worddict // 词典
 *         sentence // 待分词句子
 *         for i in 1 to n   //n 为词典中最长的词语
 *              start = 0
 *              for j in i to sentence_len - 1
 *                   word = sentence.substring(start, j)
 *                   if word not in wordset && word in worddict
 *                      wordset.add(word)
 *                      start = j + 1
 *
 *
 *
 * 使用数学公式表达就是:P(x1x2x3...xn)=P(x1)*P(x2)*P(x3)...P(xn)
 *
 * 选择其中结果最大的
 *
 *
 * 分词首先要有基本词典
 * 这个是通过统计可以计算出每个词组的频率--基本词典,一般是通过人工统计
 *
 */
public class WordSegmentUnigram {
    private static HashMap<String, Double> dictMap = new HashMap<>();

    // 词典中最长的词语
    private static final int maxWordLength = 16;
    static {
        InputStream in = null;
        try {
            in = WordSegmentUnigram.class.getClassLoader().getResourceAsStream("chinese_word_freq");
            BufferedReader buffR = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = buffR.readLine()) != null) {
                String[] words = line.split("\t");
                dictMap.put(words[0], Double.parseDouble(words[1]));
            }
        } catch (Exception e) {
            System.out.println("词典加载失败!!!");
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }
    }






    public static void main(String[] args) {
        BigInteger result = getCompose(33, 66);
        System.out.println(result);
        System.out.println(result.longValue());

        System.out.println(getRank(15, 30));

        System.out.println(getRank(15, 15));


    }


    /**
     * 先求所有可能的子串，再用子串去组合原字符串，能产生多少组合就有多少的分词方法
     * @param content
     * @return
     */
    public static Map<Integer, List<String>> enumSegment2(String content) {
        Map<String, List<String>> subStrMap = allSubString(content);
        Map<Integer, List<String>> compose = compose(content, subStrMap);
        return compose;
    }

    /**
     * 每次都产生一个分词结果
     * 5
     *  我爱，我，的，祖，国
     *  我，爱我，的，祖，国
     *  我，爱，我的，祖，国
     *  我，爱，我，的祖，国
     *  我，爱，我，的，祖国
     *
     * 4
     *  我，爱我，的，祖国
     *  我爱，我，的，祖国
     *  我，爱，我的，祖国
     *  我爱，我的，祖，国
     *  我爱，我，的祖，国
     *  我，爱我，的祖，国
     *  我爱我，的，祖，国
     *  我，爱，我的祖，国
     *  我，爱，我，的祖国
     *  我，爱我的，祖，国
     *
     * @param content
     * @return
     */
    public static Map<Integer, List<String>> enumSegment3(String content) {
        Map<Integer, List<String>> wordMap = new HashMap<>();
        int len = content.length();
        for(int i = content.length(); i > 1; i--) {
            // 能产生的词语的最长长度
            int maxWordLen = len - i + 1;
            // 计算在确定最长词语的和长度时计算有多少种分词结果
            // 长度为n的句子，要分成i个单词，那么相当于是要在n-1个位置选i-1个位置放置分隔符
            long total = getCompose(i - 1, len - 1).longValue();

            for(long j = 0; j < total; j++) {
                // 存储本次分词的结果
                List<String> list = new ArrayList<>(i);
                // todo 枚举分词
            }

        }
        return null;
    }

    /**
     * 整数能求的组合范围较小
     *
     */
    private static BigInteger getCompose(int m, int n) {
        //因为在使用中结果需要转型为long,因此只能计算到C66
        if(n > 66) return BigInteger.valueOf(0);
        return getRank(m, n).divide(getRank(m, m));
    }

    private static BigInteger getRank(int m, int n) {

        BigInteger big2 = new BigInteger(n + "");
        BigInteger big3 = new BigInteger(n + "");


        long result = n;
        for(int i = 1; i < m ; i++){
            big3 = big3.multiply(big2.subtract(new BigInteger(i + "")));
        }
        return big3;
    }


    /**
     * 返回字符串所有可能的子串
     * abcde
     *      a, ab, abc, abcd, abcde
     *      b, bc, bcd, bcde
     *      c, cd, cde
     *      d, de
     *      e
     * @param str
     * @return
     */
    public static Map<String, List<String>> allSubString(String str) {
        Map<String, List<String>> subStrMap = new LinkedHashMap<>();
        for(int i = 0; i < str.length(); i++) {
            List<String> list = new ArrayList<>();
            for(int j = i; j < str.length(); j++) {
                // String的求子串是左闭右开,因此endIndex可以等于字符串的长度
                String subStr = str.substring(i, j + 1);
                if(subStr.length() > maxWordLength) break; // 超过词典中最长的词的长度

                //如果使用词典来判断该子字符串是否是一个已在词典中的词，那么就有可能产生分词缺失，因为总有词典没有收纳到的词
                //分词过程中如何处理词典中没有的词
                list.add(subStr);
            }

            subStrMap.put(str.charAt(i) + "", list);
        }
        return subStrMap;
    }



    /**
     * 使用list中的子字符串能产生多少种content的组合
     * 又重新去组合，这个复杂度感觉很高
     * @param content
     * @param subStrMap
     * @return
     */
    public static Map<Integer, List<String>> compose(String content, Map<String, List<String>> subStrMap) {
        StringBuilder builder = new StringBuilder();

        return null;
    }



    /**
     * 下面的这个实现不能满足要求，因为若找到的字都组成词继续去找是否存在更大的词，这样在逻辑上也是行不通的
     * 短词都没有，长词就更可能没有，反而会忽略掉部分短词
     * @param content
     * @return
     */
    public static Map<Integer, List<String>> enumSegment(String content) {

        // 已经产生的分词
        HashSet<String> wordSet = new HashSet<String>();
        // 存储每一次的分词的序列,
        HashMap<Integer, List<String>> wordMap = new LinkedHashMap<>();
        // 将输入的内容拆分成单句,达不到实际效果，中文应该如何将段落划分成一句一句的
//        String[] sentences = content.split("。|| ，|| ！|| ？");


        for(int i = 1; i <= maxWordLength; i++) {
            // i表示该次取次的初始长度
            int start = 0; // 词语从哪里开始截取,也表示已经截取的部分
            List<String> wordList = new ArrayList<>();
            String word = null;
            for(int j = 0; j <= maxWordLength && start < content.length(); ) {
                // j表示每次增加的词语长度
                int end = start + j + 1;
                if(end > content.length()) break;
                word = content.substring(start, end);

                if(dictMap.containsKey(word) && !wordSet.contains(word)) {
                    start = end;
                    wordSet.add(word);
                    wordList.add(word);
                } else {
                    j++;
                }

            }

            wordMap.put(i, wordList);
        }



        return wordMap;
    }


}
