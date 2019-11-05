package com.mfkcel.mynlp.sort;

/**
 * @author mc1381288@163.com
 * @date 2019/11/5 13:48
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {10, 9};

        sort(arr);
        for(int i : arr) {
            System.out.print(i + ", ");
        }
    }


    public static void sort(int[] arr) {
         sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high){
        if(low >= high) return ;
        int mid = (low + high)/2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    /**
     * 更改原数组内容的合并算法
     * @param arr
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[arr.length];
        for(int i = low; i <= high; i++) {
            temp[i] = arr[i];
        }

        int i = low;
        int j1 = low;
        int j2 = mid + 1;

        for(; i <= high; i++){
            if(j1 <= mid  && j2 <= high) {
                if(temp[j1] <= temp[j2]){
                    arr[i] = temp[j1++];
                } else {
                    arr[i] = temp[j2++];
                }
            } else {
                break;
            }
        }

        if(j1 > mid) {
            for(;i <= high && j2 <= high; i++, j2++) {
                arr[i] = temp[j2];
            }
        } else if(j2 > high) {
            for(; i <= high && j1 <= mid; i++, j1++) {
                arr[i] = temp[j1];
            }
        }
    }


    /**
     * 这个合并只能应用于两个不同的升序排序数组，不能应用于使用原空间排序的数组
     *
     * 如果在使用原位置的归并排序中使用这个合并算法，那么最终的结果是数组的长度会成倍的增长
     * 因为它内部在分配数组时是以传入的两个数组长度为基础的，而在原地归并排序中传入是两个原数组
     * 这样不公不能得到正确的排序结果，所返回的结果还是原来数组长度的数倍
     * @param arr1
     * @param arr2
     * @return
     */
    private static int[] merge(int[] arr1, int[] arr2) {
        int[] temp = new int[arr1.length + arr2.length];
        int i = 0;
        int j1 = 0;
        int j2 = 0;

        for(; i < temp.length; i++) {
            if(j1 < arr1.length && j2 < arr2.length) {
                if(arr1[j1] < arr2[j2]) {
                    temp[i] = arr1[j1++];
                }
                else if(arr1[j1] >= arr2[j2]) {
                    temp[i] = arr2[j2++];
                }
            } else {
                break;
            }
        }


        if(j1 >= arr1.length) {
            for(; i < temp.length && j2 < arr2.length; i++, j2++) {
                temp[i] = arr2[j2];
            }
        } else if(j2 >= arr2.length) {
            for(; i < temp.length && j1 < arr1.length; i++, j1++) {
                temp[i] = arr1[j1];
            }
        }


        return temp;
    }
}
