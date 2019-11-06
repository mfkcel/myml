package com.mfkcel.mynlp.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mc1381288@163.com
 * @date 2019/11/2 11:18
 *
 * 人民的面额总共有:100、50、20、10、5、1、0.5、0.1
 *
 * 当输入找零总额时，计算并输出最好的找零方案
 *
 * 在学习算法时要注意，分析问题的思想过程
 *
 * 假设找零为x, 那么最终的结果为f(x) = x1 + x2 + x3 + ... + xn
 *
 * 假设含有的零钱类别为, changes[c1, c2, c3, c4, c5]
 *
 * 那么对应的最优解为:
 *     f(c1) = 1, f(c2) = 1, f(c3) = 1, f(c4) = 1, f(c5) = 1, f(0.5) = 1, f(0.1) = 1, f(0) = 0(设0这个数是为了使逻辑连续)
 *
 * 状态转换为
 *      min{f(cn)}
 *
 *  f(99) = f(99 - 50) + 1
 *        = f(44 - 20) + 1
 *        = f(22 - 20) + 1
 *        = f(2 - 1) + 1
 *        = f(1)
 *
 */
public class DpChange {
    private static double[] changes = {100, 50, 20, 10, 5, 1, 0.5, 0.1};

    public static void main(String[] args) {
        Map<Double, Integer> doubleIntegerMap = solveDp(199.5);
        System.out.println(doubleIntegerMap);
    }

    // 这个只计算了一种最优方案，不能计算找零的所有组合
    public static Map<Double, Integer> solveDp(double change) {
        Map<Double, Integer> resultMap = new HashMap<Double, Integer>();
        for(int i = 0; i < changes.length; i++) {
            int count = 0;
            while(change - changes[i] >= 0) {
                change = change - changes[i];
                count++;
            }

            if(count == 0) continue;
            resultMap.put(changes[i], count);
        }

        return resultMap;
    }
}
