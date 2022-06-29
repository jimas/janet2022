package com.janet.class01;

import com.janet.class01.randomarray.Code_RandomArray;

/**
 * 选择排序
 * 0~n-1
 * 1~n-1
 * 2~n-1
 * Date: 2022/5/31
 * Time: 23:37
 * 1，2，3，4，5，6，7
 *
 * @author jimas
 */
public class Code_03_ArraySort {

    public static void main(String[] args) {
        int[] arr = Code_RandomArray.randomArray(10, 20);
        printArray(arr);
        //selectSort(arr);
        bufferSort(arr);
        //insertSort(arr);
        printArray(arr);
    }

    /**
     * 插入排序，类似打牌，手里的都是有序字串，新来的要依次比较大小插入合适的位置
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 1) {
            return;
        }
        //0，0、1，0、1、2，0、1、2、3，0、1、2、3、4，n-1组
        for (int i = 0; i < arr.length; i++) {
            //第i组要比较i次，
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j - 1, j);
                }
            }
        }

    }

    /**
     * 冒泡排序
     * 一个个比较 大数往后移动
     *
     * @param arr
     */
    public static void bufferSort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 1) {
            return;
        }
        //遍历 n 次 （n为数组长度）
        for (int i = arr.length; i > 0; i--) {
            // 0、1， 1、2，2、3，3、4，4、5
            //从位置1开始，与前一个位置比较，把较大数后移，second指针++，依次比较。最终会把最大值放在最后一个位置
            for (int second = 1; second < i; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }
        }
    }

    /**
     * 选择排序，小数往前排，
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 1) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            //假设i位为目前最小值
            //找出0到n-1位中 最小得数 与 i位 交换
            int minValueIndex = i;
            for (int j = i + 1; j < n; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    public static void swap(int[] arr, int i, int minValueIndex) {
        int temp = arr[i];
        arr[i] = arr[minValueIndex];
        arr[minValueIndex] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
