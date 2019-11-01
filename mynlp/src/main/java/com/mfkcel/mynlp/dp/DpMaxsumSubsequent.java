package com.mfkcel.mynlp.dp;

/**
 * @author mc1381288@163.com
 * @date 2019/11/1 13:40
 *
 * 问题介绍
 *      假设数组为a[i], 因为最大连续的子序列是在位置0-(n-1)之间的某个位置结束。
 *      那么当循环遍历到第i个位置时，如果其前面的连续子序列和小于等于0，那么以位置
 *      i结尾的最大连续子序列和就是第i个位置a[i],如果其前面的连续子序列和大于0，
 *      则以位置i结尾的最大连续子序列和为b[i] = max{b[i-1] + a[i], a[i]}
 */
public class DpMaxsumSubsequent {
    /**
     * @param arr
     * @return
     */
    public static int solveDP(int[] arr) {
        // 这里需要设置两个临时变量
        // 求连续的子序列的求和
        int sum = Integer.MIN_VALUE;

        // 某个连续子序列的和e
        int maxTemp = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++) {
            if(maxTemp <= 0)
                maxTemp = arr[i];
            else
                maxTemp += arr[i];

            if(maxTemp > sum)
                sum = maxTemp;
        }

        return sum;
    }
}
