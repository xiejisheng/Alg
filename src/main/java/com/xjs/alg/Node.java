package com.xjs.alg;

public class Node {
	public Node next;
	public Integer val;
	
	public Node(Node next, Integer val) {
		this.next = next;
		this.val = val;
	}
	
	@Override
	public String toString() {
		return val.toString();
	}
}
