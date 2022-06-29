package com.janet.class04;

/**
 * 两个有序链表的合并:
 * 给定两个有序链表的头节点 head1 与 head2，返回合并之后的大链表，要求依然有序
 * 例子 1->3->5->8  2->3->7->9
 * 返回 1->2->3->3->5->7->8->9
 * <p>
 * Date: 2022/6/17
 * Time: 22:35
 *
 * @author jimas
 */
public class Test01 {
    private static class NodeList {
        private int val;
        private NodeList next;

        @Override
        public String toString() {
            return "NodeList{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }

        public NodeList(int val, NodeList next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        NodeList head1 = new NodeList(3, new NodeList(4, new NodeList(6, new NodeList(9, null))));
        NodeList head2 = new NodeList(1,new NodeList(2, new NodeList(4, new NodeList(16, new NodeList(29, null)))));
        NodeList head = mergeList(head1, head2);
        System.out.println(head);
    }

    private static NodeList mergeList(NodeList head1, NodeList head2) {
        //边界条件
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        //第一步先抓住最小的头节点
        NodeList head = head1.val <= head2.val ? head1 : head2;
        //原来的两个链表当前位置
        NodeList cur1 = head.next;
        NodeList cur2 = head == head1 ? head2 : head1;
        //合并后链表当前位置
        NodeList pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }
}
