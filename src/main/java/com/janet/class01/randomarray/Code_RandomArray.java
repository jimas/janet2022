package com.janet.class01.randomarray;

import com.janet.class01.Code_03_ArraySort;

import java.util.Arrays;

/**
 * 随机数组，专门用于验证算法的正确性
 * Date: 2022/6/5
 * Time: 9:28
 *
 * @author jimas
 */
public class Code_RandomArray {
    /**
     * 返回随机数组
     *
     * @param maxLen
     * @param maxVal
     * @return
     */
    public static int[] randomArray(int maxLen, int maxVal) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        return arr;
    }

    /**
     * 校验数组排序
     *
     * @param arr
     */
    public static boolean checkSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return true;
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != copy[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对数器 使用
     * @param args
     */
    public static void main(String[] args) {
        int testTimes = 1000000;
        boolean flag = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(10, 13);
            //Code_03_ArraySort.bufferSort(arr);
            //Code_03_ArraySort.selectSort(arr);
            Code_03_ArraySort.insertSort(arr);
            if (!checkSort(arr)) {
                Code_03_ArraySort.printArray(arr);
                System.out.println("排序出错了!");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Nice!");
        }

    }
}
