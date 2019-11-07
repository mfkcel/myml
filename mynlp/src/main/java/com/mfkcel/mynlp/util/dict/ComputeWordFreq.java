package com.mfkcel.mynlp.util.dict;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mc1381288@163.com
 * @date 2019/11/7 18:14
 *
 * 最长的词是16
 */
public class ComputeWordFreq {
    // 文件全全部写入缓存后，一定要flush，不然写出的结果是不全的，因为还有部分内容在缓存中
    public static void main(String[] args) throws Exception {
        BufferedReader buffR = new BufferedReader(new FileReader("chinese_word.dict"));
        String line = null;
        HashMap<String, Double> wordMap = new HashMap<>();
        double total = 0;
        int count = 0;
        int maxWordLength = 0;
        while((line = buffR.readLine()) != null) {
            String[] words = line.split("\t");
            double freq = Double.parseDouble(words[1]);
            if(freq == 0.0) {
                freq = 50;
            }
            total += freq;
            if(wordMap.containsKey(words[0])) {
                wordMap.put(words[0], wordMap.get(words[0]) + freq);
            } else {
                wordMap.put(words[0], freq);
            }

            if(words[0].length() > maxWordLength) maxWordLength = words[0].length();
            count++;
        }

        BufferedWriter buffW = new BufferedWriter(new FileWriter("chinese_word_freq"));
        int count2 = 0;
        for(Map.Entry<String, Double> entry : wordMap.entrySet()) {
            double freq = entry.getValue() / total;
            buffW.write(entry.getKey() + "\t" + freq);
            buffW.newLine();
            count2++;
        }
        buffW.flush();
        System.out.println(wordMap.size());
        System.out.println("end! " + count);
        System.out.println(wordMap.entrySet().size());
        System.out.println(maxWordLength);
        System.out.println("count2 " + count2);

    }
}
