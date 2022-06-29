package com.janet.bit;

/**
 * Date: 2022/6/29
 * Time: 23:22
 *
 * @author jimas
 */
public class BitMap01 {
    public static class BitMap {
        private long[] bits;

        public BitMap(int max) {
            //>> 6 就是 除64
            bits = new long[(max + 64) >> 6];
        }
        //34359738368  103079215104 240518168576 515396075520
        public void add(int num) {
            //num % 64 就是 num & 63
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {

            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        final BitMap bitMap = new BitMap(101);
        bitMap.add(98);
        bitMap.add(99);
        bitMap.add(100);
        bitMap.add(101);
        boolean contains = bitMap.contains(98);
        System.out.println(contains);
        bitMap.delete(98);
        contains = bitMap.contains(100);
        System.out.println(contains);
    }
}
