package com.mfkcel.mynlp.util.dict;

/**
 * create by mfkcel on 2019/11/7 23:21
 */
public class Test {
    public static void main(String[] args) {
        String content = "我爱我的祖国";
        String[] split = content.split(",||\\.||\\?");
        System.out.println(split.length);
        for(String str : split) {
            System.out.println(str);
        }

    }
}
