package com.janet.class01;

/**
 * double num = Math.random()  num 等概率返回[0,1) 之间得数
 * Date: 2022/6/1
 * Time: 22:20
 *
 * @author jimas
 */
public class Code_04_random {

    public static void main(String[] args) {
        int testTimes = 10000000;
        double x = 0.7;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f1() < x) {
                count++;
            }
        }
        System.out.println("[0," + x + ")数出现得概率为：" + (double) count / testTimes);

        System.out.println("=================================");
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f2() < x) {
                count++;
            }
        }
        System.out.println("[0," + x + ")数出现得概率为：" + (double) count / testTimes);
        System.out.println(Math.pow(x, 2));

        System.out.println("=================================");
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (f4() < x) {
                count++;
            }
        }
        System.out.println("[0," + x + ")数出现得概率为：" + (double) count / testTimes);
        System.out.println(1 - Math.pow(1 - x, 2));
        System.out.println("=================================");
        //掷色子
        int k = 6;
        int[] arr = new int[k];
        for (int i = 0; i < testTimes; i++) {
            // (int)f1()*k  就是 [0,k-1] 上等概率出现
            int ans = (int) (f1() * k);
            arr[ans]++;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "数出现的次数为：" + arr[i]);
        }
        System.out.println("=================================");

    }

    /**
     * 做到 返回 [0,x) 的概率为 x,其中x<=1
     *
     * @return
     */
    private static double f1() {
        return Math.random();
    }

    /**
     * 做到 返回 [0,x) 的概率为 x^2,其中x<=1
     */
    private static double f2() {
        //求两次随机数，只能保证两次随机数都必须在[0,x)之间，那么取max值 才能保证结果在[0,x)之间,那就是 x*x 概率
        return Math.max(f1(), f1());
    }

    /**
     * 做到 返回 [0,x) 的概率为 x^3,其中x<=1
     */
    private static double f3() {
        //求两次随机数，只能保证三次随机数都必须在[0,x)之间，那么取max值 才能保证结果在[0,x)之间,那就是 x*x* 概率
        return Math.max(Math.max(f1(), f1()), f1());
    }

    private static double f4() {
        //[0,x)的概率，先求[x-1)的概率，那就只能保证 两次随机都必须在[x-1)之间且其概率为(1-x)^2,则 [0,x)的概率 为1-(1-x)^2。
        return Math.min(f1(), f1());
    }

}
