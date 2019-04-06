package com.xjs.alg;

import com.google.common.collect.Lists;

import java.util.*;

public class Vertical {

	public static void main(String[] args) {
		TreeNode node7 = new TreeNode(null, null, 7);
		TreeNode node2 = new TreeNode(null, null, 2);
		TreeNode node5 = new TreeNode(null, null, 5);
		TreeNode node4 = new TreeNode(null, null, 4);
		TreeNode node20 = new TreeNode(node2, node7, 20);
		TreeNode node9 = new TreeNode(node4, node5, 9);
		TreeNode root = new TreeNode(node9, node20, 3);
//		helper(root);
		List<List<Integer>> lists = helper1(root);
		System.out.println(lists);
	}

	public static void helper(TreeNode root) {
		Queue<ColTreeNode> q = new ArrayDeque();
		List<ColTreeNode> inplacelist = new ArrayList<>();
		q.add(new ColTreeNode(0, root));
		while (!q.isEmpty()) {
			ColTreeNode cur = q.poll();
			inplacelist.add(cur);
			if (cur.node.left != null) {
				q.offer(new ColTreeNode(cur.col - 1, cur.node.left));
			}
			if (cur.node.right != null) {
				q.offer(new ColTreeNode(cur.col + 1, cur.node.right));
			}
		}
		Collections.sort(inplacelist, (o1, o2) -> o1.col - o2.col);
		for (ColTreeNode colTreeNode : inplacelist) {
			System.out.println(colTreeNode.node.val);
		}
	}

	/**
	 * 把树压平，存入数组，问题就解决了
	 * 命令式：告诉程序怎么把树压平，具体细节
	 * 函数式：这个数组符合怎样的规则
	 * @param root
	 */
	public static List<List<Integer>> helper1(TreeNode root) {
		int left = getWidth(root, true);
		int right = getWidth(root, false);
		List<List<Integer>> list = Lists.newArrayList();
		for (int i = 0; i < left + right -1; i++) {
			list.add(Lists.newArrayList());
		}
		int index = left;
		recurive(root, index, list);
		return list;
	}

	private static void recurive(TreeNode root, int index, List<List<Integer>> list) {
		if (root == null) {
			return;
		}
		list.get(index).add(root.val);
		recurive(root.left, index - 1, list);
		recurive(root.right, index + 1, list);
	}

	public static int getWidth(TreeNode node, boolean isLeftDirection) {
		int count = 0;
		TreeNode curr = node;
		while (curr != null) {
			count++;
			if (isLeftDirection) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		return count;
	}
	static private class ColTreeNode {
		int col;
		TreeNode node;
		public ColTreeNode(int col, TreeNode node) {
			this.col = col;
			this.node = node;
		}
	}
	
}

