package com.mfkcel.mynlp.util.string.match;

/**
 * @author mc1381288@163.com
 * @date 2019/11/9 16:49
 */
public class BM {

    public static void main(String[] args) {

    }

    /**
     * 使用简版的bm算法确定字符串str中是否有模式串pattern
     * 只实现了坏字符匹配表
     * @param str
     * @param pattern
     * @return
     */
    public static boolean find(String str, String pattern) {
        int[] badMatchArr = getBadMatch(pattern);

        return false;
    }


    private static int[] getBadMatch(String str) {
        int[] bad = new int[str.length()];

        for(int i = 1; i < str.length(); i++) {


            bad[i] = str.substring(0, i - 1).lastIndexOf(str.charAt(i) + "");
        }

        return bad;
    }


}
