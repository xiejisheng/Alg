package com.xjs.alg;

public class ReverseLink {
	public static void main(String[] args) {
		Node node6 = new Node(null, 6);
		Node node5 = new Node(node6, 5);
		Node node4 = new Node(node5, 4);
		Node node3 = new Node(node4, 3);
		Node node2 = new Node(node3, 2);
		Node node1 = new Node(node2, 1);
		Node newHead = reverse1(node1);
		Node curr = newHead;
		while(curr != null) {
			System.out.println(curr.val);
			curr = curr.next;
		}
	}

	private static Node reverse(Node head) {
		Node curr = head;
		Node pre = null, next = null;
		while(curr != null) {
			next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}
		return pre;
	}
	
	public static Node reverse1(Node head){
		// terminate
		if(head == null || head.next == null){
			return head;
		}

		Node nextNode = head.next;
		Node newHead = reverse1(nextNode);
		
		nextNode.next = head;
		head.next = null;
		return newHead;
	}
	
	public static Node reversePair2(Node head){
		// terminate
		if(head == null || head.next == null){
			return head;
		}

		Node nextNode = head.next;
		Node next2 = head.next.next;
		Node newHead = reverse1(nextNode);
		
		nextNode.next = head;
		head.next = next2;
		return newHead;
	}

	private static Node reversePair(Node head) {
		if (head == null || head.next == null)
			return head;
		Node curr = head;
		Node pre = null;
		int i = 0;
		while(curr != null && curr.next != null) {
			Node next2 = curr.next.next;
			Node next = curr.next;
			next.next = curr;
			curr.next = next2;
			if (i == 0) {
				head = next;
				++i;
			}
			if (pre != null) {
				pre.next = next;
			}
			pre = curr;
			curr = next2;
		}
		return head;
	}
}
