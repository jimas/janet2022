package com.janet.class04;

/**
 * K个节点的组内逆序调整：
 * 给定一个单链表的头节点head，和一个正数K
 * 实现K个节点的小组内部逆序，如果最后一组不够K个就不调整
 * 例子：
 * 调整前：1->2-3->4->5->6->7->8 ，k=3
 * 调整后：3->2->1->6->5->4->7->8
 * Date: 2022/6/17
 * Time: 23:59
 *
 * @author jimas
 */
public class Test03 {
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
        int k = 3;
        NodeList head = new NodeList(1, new NodeList(2, new NodeList(3, new NodeList(4, new NodeList(5, new NodeList(6, new NodeList(7, new NodeList(8, new NodeList(9, null)))))))));
        NodeList newHead = reverseKGroupNode(head, k);
        System.out.println(newHead);
    }

    private static NodeList reverseKGroupNode(NodeList head, int k) {
        //边界条件
        NodeList start = head;
        NodeList end = findKGroupEndNode(start, k);
        if (end == null) {
            return head;
        }
        head = end;
        reverse(start, end);
        NodeList lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = findKGroupEndNode(start, k);
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    /**
     * 链表反转
     *
     * @param start
     * @param end
     */
    private static void reverse(NodeList start, NodeList end) {
        end = end.next;
        NodeList pre = null;
        NodeList next = null;
        NodeList cur = start;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

    /**
     * 获取第K个节点，不足K个返回最后一个
     *
     * @param start
     * @param k
     * @return
     */
    private static NodeList findKGroupEndNode(NodeList start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }
}
