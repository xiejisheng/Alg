package com.xjs.onebyone;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

/**
 * @description
 * @auther xjs
 * @date
 */
public class LinkedList {

    static class LinkedNode {
        public int value;
        public LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
            next = null;
        }
    }

    static LinkedNode head;
    static {
        head = new LinkedNode(1);
        head.next = new LinkedNode(2);
        head.next.next = new LinkedNode(3);
        head.next.next.next = new LinkedNode(4);
        head.next.next.next.next = new LinkedNode(5);
        head.next.next.next.next.next = new LinkedNode(6);
        head.next.next.next.next.next.next = new LinkedNode(7);
    }

    static LinkedNode cycleHead;
    static {
        cycleHead = new LinkedNode(1);
        cycleHead.next = new LinkedNode(2);
        cycleHead.next.next = new LinkedNode(3);
        cycleHead.next.next.next = new LinkedNode(4);
        cycleHead.next.next.next.next = new LinkedNode(5);
        cycleHead.next.next.next.next.next = new LinkedNode(6);
        cycleHead.next.next.next.next.next.next = cycleHead.next.next;
    }

    static LinkedNode interestHead1;
    static LinkedNode interestHead2;
    static {
        interestHead1 = new LinkedNode(1);
        interestHead1.next = new LinkedNode(2);
        interestHead1.next.next = new LinkedNode(3);
        interestHead1.next.next.next = new LinkedNode(4);
        interestHead2 = new LinkedNode(5);
        interestHead2.next = interestHead1.next.next;
        interestHead2.next.next = new LinkedNode(6);
    }

    static LinkedNode insertHead;
    static {
        insertHead = new LinkedNode(1);
        insertHead.next = new LinkedNode(3);
        insertHead.next.next = new LinkedNode(5);
    }

    static LinkedNode prtitionHead;
    static {
        prtitionHead = new LinkedNode(5);
        prtitionHead.next = new LinkedNode(4);
        prtitionHead.next.next = new LinkedNode(3);
        prtitionHead.next.next.next = new LinkedNode(2);
        prtitionHead.next.next.next.next = new LinkedNode(1);
        prtitionHead.next.next.next.next.next = new LinkedNode(6);
        prtitionHead.next.next.next.next.next.next = new LinkedNode(7);
    }


    static void print(LinkedNode head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    @Test
    public void testGetMiddleLinkedList() {
        LinkedNode linkedNode = doGetMiddleLinkedList(head);
        System.out.println(linkedNode.value);
    }

    private LinkedNode doGetMiddleLinkedList(LinkedNode head) {
        if (head == null || head.next == null) return null;

        LinkedNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void testReverseLinkedList() {
        LinkedNode linkedNode = doReverseLinkedList(head);
//        LinkedNode linkedNode = doReverseLinkedListByRecursion(head);
        print(linkedNode);
    }

    private LinkedNode doReverseLinkedList(LinkedNode head) {
        if (head == null || head.next == null) return head;

        LinkedNode pre = null, next = null, cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    private LinkedNode doReverseLinkedListByRecursion(LinkedNode head) {
        if (head == null || head.next == null) return head;

        LinkedNode next = head.next;
        LinkedNode newHead = doReverseLinkedListByRecursion(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    @Test
    public void testReverseByGroup() {
        int k = 4;
        LinkedNode newHead = doReverseGroup(head, k);
        print(newHead);
    }

    private LinkedNode doReverseGroup(LinkedNode head, int k) {
        if (head == null || head.next == null) return head;

        LinkedNode pre = null, next = null, cur = head;
        int c = 0;
        while (c < k && cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            c++;
        }

        if (next != null)
            head.next = doReverseGroup(next, k);
        return pre;
    }

    @Test
    public void testFindCycle() {
        LinkedNode linkedNode = doFindCycle(cycleHead);
        System.out.println(linkedNode.value);
    }

    private LinkedNode doFindCycle(LinkedNode head) {
        if (head == null || head.next == null) return null;

        LinkedNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) break;
        }

        if (slow == null)
            return null;

        LinkedNode curr = head, meetNode = slow;
        while (curr != meetNode) {
            curr = curr.next;
            meetNode = meetNode.next;
        }

        return curr;
    }

    @Test
    public void testInterest() {
        LinkedNode linkedNode = doInterest(interestHead1, interestHead2);
        System.out.println(linkedNode.value);

    }

    /**
     * 不用判断
     * @param interestHead1
     * @param interestHead2
     * @return
     */
    private LinkedNode doInterest(LinkedNode interestHead1, LinkedNode interestHead2) {
        if (interestHead1 == null || interestHead2 == null) return null;

        LinkedNode a = interestHead1, b = interestHead2;
        int cycle = 0;
        while (a != b && cycle < 1) {
            a = a.next != null ? a.next : interestHead2;
            b = b.next != null ? b.next : interestHead1;
        }

        return a;
    }

    @Test
    public void testInsert() {
        int value = 6;
        LinkedNode head = doInsert(insertHead, value);
        print(head);
    }

    private LinkedNode doInsert(LinkedNode head, int value) {
        LinkedNode newNode = new LinkedNode(value);
        if (head == null || newNode.value <= head.value) {
            newNode.next = head;
            return newNode;
        }

        LinkedNode pre = head;
        while (pre.next != null && pre.next.value < value) {
            pre = pre.next;
        }

        newNode.next = pre.next;
        pre.next = newNode;
        return head;
    }

    @Test
    public void testReOrder() {
        LinkedNode newHead = doReOrder(head);
        print(newHead);
    }

    private LinkedNode doReOrder(LinkedNode head) {
        if (head == null) return head;

        LinkedNode middle = doGetMiddleLinkedList(head);
        LinkedNode two = doReverseLinkedList(middle.next);
        middle.next = null;

        LinkedNode one = head;
        while (two != null) {
//            cur.next = one;
//            one = one.next;
//            cur = cur.next;
//
//            cur.next = two;
//            two = two.next;
//            cur = cur.next;
            LinkedNode oneNext = one.next;
            one.next = two;
            LinkedNode twoNext = two.next;
            two.next = oneNext;

            one = oneNext;
            two = twoNext;
        }

        return head;
    }

    @Test
    public void testPartition() {
        int t = 3;
        LinkedNode linkedNode = doPartition(head, t);
        print(linkedNode);
    }

    private LinkedNode doPartition(LinkedNode head, int t) {
        if (head == null || head.next == null) return head;

        LinkedNode dummy1 = new LinkedNode(0);
        LinkedNode dummy2 = new LinkedNode(0);
        LinkedNode cur = head, newHead = dummy1, next = dummy2;
        while (cur != null) {
            if (cur.value <= t) {
                dummy1.next = cur;
                dummy1 = dummy1.next;
            } else {
                dummy2.next = cur;
                dummy2 = dummy2.next;
            }
            cur = cur.next;
        }

        dummy1.next = next.next;
        return newHead.next;
    }
}
