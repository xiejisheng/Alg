package com.xjs.alg;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public Integer val;
	
	public TreeNode(TreeNode left, TreeNode right, Integer val) {
		this.left = left;
		this.right = right;
		this.val = val;
	}
	
	@Override
	public String toString() {
		return val.toString();
	}
	
	public static void main(String[] args) {
		int n = 10;
		int step = step(n);
		System.out.println(step);
		System.out.println(fib(5));
	}

	private static int step(int n) {
		if (n == 1)
			return 1;
		if (n == 2) 
			return 2;
		return step(n-1) + step(n-2);
	}
	
	static int fib(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fib(n-2) + fib(n-1);
	}

}
