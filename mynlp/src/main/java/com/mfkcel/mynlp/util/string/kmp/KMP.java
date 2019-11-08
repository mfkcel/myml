package com.mfkcel.mynlp.util.string.kmp;

/**
 * @author mc1381288@163.com
 * @date 2019/11/8 18:51
 */
public class KMP {
    public static int[] getNext(String str) {
        int[] next = new int[str.length()];

        for(int i = 0, j = i + 1; i < j && j < str.length();) {
            if(str.charAt(i) == str.charAt(j)) {
                i++;
                next[j] = next[i] + 1;
            } else {
                j++;
            }
        }

        return next;
    }
}
