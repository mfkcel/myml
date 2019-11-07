package com.mfkcel.mynlp.util.numformat;

import org.junit.Test;

/**
 * @author mc1381288@163.com
 * @date 2019/11/7 17:53
 */
public class TestNumFormat {
    // 将字符串表示的数字转换为long
    @Test
    public void test1() {
        String numStr = "00237692";
        System.out.println(Long.parseLong(numStr));
    }
}
