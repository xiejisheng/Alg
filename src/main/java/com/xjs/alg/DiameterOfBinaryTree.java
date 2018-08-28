package com.xjs.alg;

import java.util.HashMap;
import java.util.Map;

public class DiameterOfBinaryTree {
	
	static Map<TreeNode, Integer> nodeOfDepathMap = new HashMap<>();
	/**
	      1
         / \
        2   3
       / \     
      4   5  
	 */
	public static void main(String[] args) {
		TreeNode node5 = new TreeNode(null, null, 5);
		TreeNode node4 = new TreeNode(null, null, 4);
		TreeNode node3 = new TreeNode(null, null, 3);
		TreeNode node2 = new TreeNode(node4, node5, 2);
		TreeNode root = new TreeNode(node2, node3, 1);
		int res = diameterOfBinaryTree(root);
		System.out.println(res);
	}

//	private static int diameterOfBinaryTree(TreeNode root) {
//		int res = 0;
//		return maxDepath(root, res);
//	}
//
//	private static int maxDepath(TreeNode node, int res) {
//		int res0 = res;
//		if (node == null) 
//			return 0;
//		int left = maxDepath(node.left, res0);
//		int right = maxDepath(node.right, res0);
//		return Math.max(res0, left+right+1);
//	}
	static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        return getHeight(root.left) + getHeight(root.right);
    }
    static int getHeight(TreeNode node) {
        if (node == null) return 0;
        int h = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return h;
    }

}
