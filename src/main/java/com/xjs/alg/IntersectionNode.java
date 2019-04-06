package com.xjs.alg;

/**
 *  很有意思的算法
 *  分析链表的本质和扩展思维
 */
public class IntersectionNode {
	public static void main(String[] args) {
		Node c3 = new Node(null, 9);
		Node c2 = new Node(c3, 8);
		Node c1 = new Node(c2, 7);
		Node b4 = new Node(c1, 6);
		Node b3 = new Node(b4, 5);
		Node b2 = new Node(b3, 4);
		Node b1 = new Node(b2, 3);
		Node a2 = new Node(c1, 2);
		Node a1 = new Node(a2, 1);
		Node intersectionNode = getIntersectionNode(a1, b1);
		System.out.println(intersectionNode);
	}
	public static Node getIntersectionNode(Node headA, Node headB) {
		// boundary check
		if (headA == null || headB == null)
			return null;

		Node a = headA;
		Node b = headB;

		// if a & b have different len, then we will stop the loop after second
		// iteration
		while (a != b) {
			// for the end of first iteration, we just reset the pointer to the head of
			// another linkedlist
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}

		return a;
	}

}
