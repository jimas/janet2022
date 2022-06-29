package com.janet.bit;

/**
 * 打印 int 值得32位
 * 有符号 int类范围 2^-31~2^31-1  （32位中 最高位 为符号位，0标识正数，1表示负数）
 * 无符号 int类范围 0~2^32
 * -5 = ~5+1 （取反+1）
 * Date: 2022/5/31
 * Time: 23:05
 *
 * @author jimas
 */
public class Code_01_PrintInt32 {
    public static void main(String[] args) {
//        int num = 5;
//        print32(num);
//        //-号 就是 取反+1
//        print32(-num);
//        //~取反
//        print32(~num + 1);
//        System.out.println(~num + 1);

        print32(Integer.MAX_VALUE);
        print32(Integer.MIN_VALUE);
        System.out.println("qwqw" == "qwqw");
    }

    /**
     * 打印一个数得32位二进制值
     * num & (1左移i位) == 0 ?"0":"1"
     *
     * @param num
     */
    public static void print32(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
