package com.janet.class01;

/**
 * 已知 f1() 函数 等概率返回1~5之间的整数，求等概率返回1~7之间的 函数
 * Date: 2022/6/1
 * Time: 22:20
 *
 * @author jimas
 */
public class Code_05_random {
    /**
     * x 固定为5
     */
    private static int x = 5;

    public static void main(String[] args) {
        int testTimes = 100000000;
        int count = 0;
        int[] arr = new int[x + 1];
        for (int i = 0; i < testTimes; i++) {
            int ans = f1();
            arr[ans]++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "数出现的次数为：" + arr[i]);
        }
        System.out.println("=======================");
        int[] counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int ans = f4();
            counts[ans]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + "数出现的次数为：" + counts[i]);
        }
    }

    /**
     * [1,x] 等概率返回 1、2、3、……、x-1、x
     * 假设为 lib 包中的方法不能动.
     */
    private static int f1() {
        return (int) (Math.random() * x) + 1;
    }

    /**
     * 根据 f1() 函数 构造出等概率返回 0\1的函数
     * f1()返回1、2时为0,f1()返回4、5时为1，f1()返回3时，继续循环 直到返回1、2、4、5
     *
     * @return
     */
    private static int f2() {
        int ans = 0;
        do {
            ans = f1();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * 因为f2() 得到的是 0 或者 1，故需要看几个二进制位能标识0--7，需要三个二进制位，也就是f2 三次，每次分别左移 2、1、0位
     * 得到 000  ~ 111 等概率返回0、1、2、3、4、5、6、7
     *
     * @return
     */
    private static int f3() {
        return (f2() << 2) + (f2() << 1) + f2();
    }

    /**
     * 等概率返回1、2、3、4、5、6、7
     *
     * @return
     */
    private static int f4() {
        int ans;
        do {
            ans = f3();
        } while (ans == 0);
        return ans;
    }
}
