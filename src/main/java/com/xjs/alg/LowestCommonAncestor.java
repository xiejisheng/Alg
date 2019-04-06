package com.xjs.alg;

import org.junit.Test;
public class LowestCommonAncestor {

	/**
	    1
	   / \
	  2   3
	 / \     
	4   5  
	*/
	@Test
	public void testLowestCommonAncestor() {
		TreeNode node5 = new TreeNode(null, null, 5);
		TreeNode node4 = new TreeNode(null, null, 4);
		TreeNode node3 = new TreeNode(null, null, 3);
		TreeNode node2 = new TreeNode(node4, node5, 2);
		TreeNode root = new TreeNode(node2, node3, 1); 
		TreeNode ancestor = doLowestCommonAncestor(root, node5, node3);
		System.out.println(ancestor.val);
	}

	private TreeNode doLowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null || root == one || root == two)
			return root;
		
		TreeNode left = doLowestCommonAncestor(root.left, one, two);
		TreeNode right = doLowestCommonAncestor(root.right, one, two);
		
		if (left != null && right != null)
			return root;
		
		return left != null ? left : right;
	}
	
	@Test
	public void testIsheightBT() {
		TreeNode node6 = new TreeNode(null, null, 6);
		TreeNode node5 = new TreeNode(null, null, 5);
		TreeNode node4 = new TreeNode(node6, null, 4);
		TreeNode node3 = new TreeNode(null, null, 3);
		TreeNode node2 = new TreeNode(node4, node5, 2);
		TreeNode root = new TreeNode(node2, node3, 1); 
		boolean isHeightBT = isHeightBT(root);
		System.err.println(isHeightBT);
	}

	private boolean isHeightBT(TreeNode root) {
		int max = getHeight(root, false);
		int min = getHeight(root, true);
		return max-min <= 1;
	}

	private int getHeight(TreeNode root, boolean isMin) {
		if (root == null)
			return 0;
		int left = getHeight(root.left, isMin)+1;
		int right = getHeight(root.right, isMin)+1;
		return isMin ? Math.min(left, right) : Math.max(left, right);
	}
}
