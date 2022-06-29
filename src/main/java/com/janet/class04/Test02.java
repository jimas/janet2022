package com.janet.class04;

/**
 * 两个链表相加：
 * 给定两个链表的头节点head1 与 head2
 * 认为从左往右是某个数字从低位到高位，返回相加之后的链表
 * 例子： 4->3->6  2->5->3
 * 返回 6->8->9
 * 解释 634+352=986
 * Date: 2022/6/17
 * Time: 23:27
 *
 * @author jimas
 */
public class Test02 {
    private static class NodeList {
        private int val;
        private NodeList next;

        public NodeList(int val, NodeList next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "NodeList{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        NodeList head1 = new NodeList(3, new NodeList(5, new NodeList(6, null)));
        NodeList head2 = new NodeList(8, new NodeList(4, new NodeList(8, null)));
        NodeList head = addLinked(head1, head2);
        System.out.println(head);
    }

    private static NodeList addLinked(NodeList head1, NodeList head2) {

        int length1 = lengthNodeList(head1);
        int length2 = lengthNodeList(head2);
        //新head 抓住最长链表的head
        NodeList head = length1 >= length2 ? head1 : head2;
        //区分出长短链表当前位置
        NodeList curL = head;
        NodeList curS = head == head1 ? head2 : head1;
        //记录下个节点  用于最高位产生进位时 抓出进位的新节点
        NodeList last = head;
        //进位 默认为0
        int carry = 0;
        //两个链表 都不为空阶段
        while (curS != null) {
            int val = (curL.val + curS.val + carry) % 10;
            carry = (curL.val + curS.val + carry) / 10;
            curL.val = val;
            last = curL;
            curS = curS.next;
            curL = curL.next;

        }
        //长链表不为空
        while (curL != null) {
            int val = (curL.val + carry) % 10;
            carry = (curL.val + carry) / 10;
            curL.val = val;
            last = curL;
            curL = curL.next;

        }
        //进位不为 0，创建新节点
        if (carry != 0) {
            last.next = new NodeList(1, null);
        }
        return head;
    }

    private static int lengthNodeList(NodeList head) {
        int length = 0;
        NodeList pre = head;
        while (pre != null) {
            length++;
            pre = pre.next;
        }
        return length;
    }
}
