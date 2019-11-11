package com.mfkcel.mynlp.dp;

/**
 * @author mc1381288@163.com
 * @date 2019/11/11 9:54
 *
 * 斐波那契数列
 * f(n) = f(n-1) + f(n-2)
 * f(0) = 0, f(1) = 1
 */
public class Fib {
    public static void main(String[] args) {

    }

    /**
     * 时间复杂度O(n^2)
     * @param n
     * @return
     */
    public int fibUseRecursion(int n) {
        if(n <= 0) return 0;
        else if(n == 1 || n == 2) return 1;
        else return fibUseRecursion(n - 1) * fibUseRecursion(n - 2);
    }

    /**
     * 时间复杂度O(n)
     * @param n
     * @return
     */
    public int fibUseDP(int n) {
        int i = 0;
        int j = 1;
        int total = 0;
        for(int m = 2; m <= n; m++) {
            total = i + j;

            i = j;
            j = total;

        }
        return total;
    }

    /**
     * 时间复杂度O(lgn),最高效准确的
     * @param n
     * @return
     */
    public int fibUseMatrix(int n) {
        return 0;
    }

    /**
     * 直接使用公式，时间复杂度O(1),涉及到无理数但有些不准确
     * @param n
     * @return
     */
    public int fibUseFormula(int n) {
        return 0;
    }
}
