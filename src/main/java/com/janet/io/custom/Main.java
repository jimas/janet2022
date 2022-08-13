package com.janet.io.custom;

/**
 * Date: 2022/7/26
 * Time: 21:27
 *
 * @author jimas
 */
public class Main {

    public static void main(String[] args) {
        final SelectorThreadGroup threadGroup = new SelectorThreadGroup(3);
        threadGroup.bind(9999);
    }
}
