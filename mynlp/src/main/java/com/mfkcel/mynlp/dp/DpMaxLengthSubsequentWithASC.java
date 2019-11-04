package com.mfkcel.mynlp.dp;

import java.util.Arrays;

/**
 * @author mc1381288@163.com
 * @date 2019/11/1 15:24
 *
 * 问题描述
 *      给定一个长度为N的数组，找出一个最长的单调递增子序列，子序列不一定连续，但初始顺序不能乱。
 *
 * 例如：给定一个长度为6的数组A{4， 5， 7， 1，3， 9}，则其最长的单调递增子序列为{4，5，7，9}，长度为4。
 *
 * 动态规划思路：
 *
 * 记d[i]为以任意一个A[i]为末尾元素组成的最长递增子序列的长度，找出所有位于i之前且比A[i]小的元素A[j]，此时可
 *
 * 出现两种情况：
 *
 * （1）若找到，例如i = 2，此时A[i] = 7，比A[i]小的元素为A[0] = 4，A[1] = 5，取所有比A[i]小的元素中A[j]中，对应的d[j]最
 *
 * 大的加1，即d[i] = max{ d[j] }，其中j < i 且 A[j] < A[i]；
 *
 * （2）若没有找到，例如i = 3，此时A[i] = 1，i之前不存在比1小的元素，此时A[3] = 1独自构成一个递增子序列，d[i] = 1。
 *
 * 实现过程：
 *
 * 当i = 0时，此时A[0] = 4，只有一个元素独自构成子序列，此时d[0] = 1；
 *
 * 当i = 1时，此时A[1] = 5，比A[1]小的元素为A[0] = 4，
 *
 *                    即i = 1对应最长递增子序列长度应加1，即d[1] = d[0] + 1 = 2；
 *
 * 当i = 2时，此时A[2] = 7，比A[2]小的元素分别为j = 0，1，对应A[0] = 4 及 A[1] = 5，取对应的d[j]最大的d[1] = 2，
 *
 *                    即i = 2时对应最长递增子序列长度应为d[2] = d[1] + 1 = 3；
 *
 * 当i = 3时，此时A[3] = 1，i = 3 之前不存在比1小的元素，
 *
 *                    即d[3] = 1；
 *
 * 当i = 4时，此时A[4] = 3，比A[4]小的元素为j = 3， A[3] = 1，此时对应的d[3] = 1，只有一个元素，当然为对应d[j]最大，
 *
 *                    即i = 4对应最长递增子序列长度应为d[3] 加1，即d[4] = d[3] + 1 = 2；
 *
 * 当i = 5时，此时A[5] = 9，前5个元素均比A[5]小，取对应最大的d[j]为当j = 2时，此时d[2] = 3，
 *
 *                    即i = 5时对应的最长的递增子序列长度为d[5] = d[2] + 1 = 4。
 *
 *
 */
public class DpMaxLengthSubsequentWithASC {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -8, 12, 6, 5, 7, 9, 10, -7, -6, -5, -4 };
        System.out.println(solveDp(arr));
    }

    /**
     * 以第arr[i]为最后一个元素的子序列是前面的最大子序列+1
     * @param arr
     * @return
     */
    public static int solveDp(int[] arr) {
        int[] subSeqs = new int[arr.length];
        int max = 0;

        for(int i = 0; i < arr.length; i++) {
            subSeqs[i] = 1;
            max = 0;
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && arr[j] > arr[max]) {
                    max = j;
                }
                subSeqs[i] = subSeqs[max] + 1;
            }
        }

        max = Integer.MIN_VALUE;
        for(int i = 0; i < subSeqs.length; i++) {
            if(max < subSeqs[i])
                max = subSeqs[i];
        }
        return max;
    }
}
