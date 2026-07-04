package top.hanwg.dsa.list;

import org.junit.jupiter.api.Assertions;

// #19 medium
// Given the head of a linked list, remove the nth node from the end of the list and return its head.
public class RemoveNthNodeFromEnd {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ListNode)) {
                return false;
            }

            ListNode node1 = this;
            ListNode node2 = (ListNode)o;
            while (node1 != null && node2 != null) {
                if (node1.val != node2.val) {
                    return false;
                }

                node1 = node1.next;
                node2 = node2.next;
            }

            return node1 == node2;
        }

        static ListNode build(int... values) {
            if (values.length < 1) {
                return null;
            }

            ListNode head = new ListNode(values[0]);
            ListNode prev = head;
            for (int i = 1; i < values.length; i++) {
                prev.next = new ListNode(values[i]);
                prev = prev.next;
            }
            return head;
        }
    }

    // 2-pointers
    // move first pointer n steps ahead
    // when first pointer reaches the end, remove the node at the second pointer
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
        }

        ListNode prev = null;
        while (p1 != null) {
            p1 = p1.next;
            prev = p2;
            p2 = p2.next;
        }

        if (prev == null) {
            return p2.next;
        }

        prev.next = p2.next;
        return head;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();

        Assertions.assertEquals(ListNode.build(1, 2, 4),
                solution.removeNthFromEnd(ListNode.build(1, 2, 3, 4), 2));

        Assertions.assertEquals(ListNode.build(),
                solution.removeNthFromEnd(ListNode.build(5), 1));

        Assertions.assertEquals(ListNode.build(2),
                solution.removeNthFromEnd(ListNode.build(1, 2), 2));
    }
}
