package com.xjs.alg.test;

import com.xjs.alg.Node;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    Node head = null;

    @Before
    public void init() {
        Node node7 = new Node(null, 1);
        Node node6 = new Node(node7, 8);
        Node node5 = new Node(node6, 6);
        Node node4 = new Node(node5, 2);
        Node node3 = new Node(node4, 7);
        Node node2 = new Node(node3, 3);
        Node node1 = new Node(node2, 5);
        node7.next = node4;
        head = node1;
    }

    @Test
    public void locationCycleNode() {
        Node node = doLocationCycleNode(head);
        System.out.println(node.val);
    }

    /**
     * 1、假设node1是成环的节点
     * 2、node6是快慢指针相遇的节点
     * 3、node1到node6的距离为s1
     * 4、node6到node1的距离为s2
     * 5、环的长度为s1+s2
     * @param head
     * @return
     */
    private Node doLocationCycleNode(Node head) {
        Node node = cycleMeetNode(head);
        if (node == null)
            return null;

        Node curr = head, meetNode = node.next;
        while (curr != meetNode) {
            curr = curr.next;
            meetNode = meetNode.next;
        }

        return curr;
    }

    private Node cycleMeetNode(Node head) {
        if (head == null || head.next == null)
            return null;

        Node curr = head, next = head.next;
        while (curr != null && next != null && next.next != null) {
            if (curr == next)
                return curr;
            else {
                curr = curr.next;
                next = next.next.next;
            }
        }
        return null;
    }

    @Test
    public void hasCycle() {
        boolean hasCycle = doHasCycle(head);
        System.out.println(hasCycle);
    }

    private boolean doHasCycle(Node head) {
        if (head == null || head.next == null)
            return false;

        Node curr = head, next = head.next;
        while (curr != null && next != null && next.next != null) {
            if (curr == next)
                return true;
            else {
                curr = curr.next;
                next = next.next.next;
            }
        }
        return false;
    }

    @Test
    public void findMiddle() {
        Node middle = doFindMiddle(head);
        System.out.println(middle.val);
    }

    private Node doFindMiddle(Node head) {
        if (head == null || head.next == null)
            return head;

        Node curr = head, next = curr;
        while (curr != null && next != null && next.next != null) {
            curr = curr.next;
            next = next.next.next;
        }
        return curr;
    }

    @Test
    public void reversePair() {
        int pair = 3;
        Node newHead = doReverseGroup(head, pair);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    private Node doReverseGroup(Node head, int k) {
        if (head == null || head.next == null) return head;

        Node current = head;
        Node next = null;
        Node prev = null;

        int count = 0;

        /* Reverse first k nodes of linked list */
        while (count < k && current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

       /* next is now a pointer to (k+1)th node
          Recursively call for the list starting from current.
          And make rest of the list as next of first node */
        if (next != null)
            head.next = doReverseGroup(next, k);

        // prev is now head of input list
        return prev;

    }

    /**
     * 递归的方式
     * @param head
     * @return
     */
    public Node reverseIter(Node head) {
        if (head == null || head.next == null)
            return head;

        Node t = head.next;
        head.next = reverseIter(head.next);
        t.next = head;
        return t;
    }

    @Test
    public void reverse() {
//        Node newHead = doReverse(head);
        Node newHead = reverseIter(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    private Node doReverse(Node head) {
        if (head == null || head.next == null) return head;

        Node prev = null, curr = head, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
    
}
