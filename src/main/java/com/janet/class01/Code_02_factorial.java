package com.janet.class01;

/**
 * 求一个数阶乘和 1!+2!+3!+4!+5!+……+n!
 * 4! = 4*3*2*1 = 4*3!
 * Date: 2022/5/31
 * Time: 23:11
 * 1!+2!+3!+4! = 1+2+6+24
 *
 * @author jimas
 */
public class Code_02_factorial {

    public static void main(String[] args) {
        int num = 4;
        m1(num);
        m2(num);
    }

    /**
     * 比较好得做法
     * 4!= 4*3!
     *
     * @param num
     */
    public static void m2(int num) {
        int sum = 0;
        int next = 1;
        for (int i = 1; i <= num; i++) {
            next = next * i;
            sum += next;
        }
        System.out.println(sum);
    }

    /**
     * 通俗做法
     * 4! = 4*3*2*1
     *
     * @param num
     */
    public static void m1(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += m1_01(i);
        }
        System.out.println(sum);
    }

    //1*2*3
    private static int m1_01(int i) {
        int m1_num = 1;
        for (int j = 1; j <= i; j++) {
            m1_num *= j;
        }
        return m1_num;
    }

}
