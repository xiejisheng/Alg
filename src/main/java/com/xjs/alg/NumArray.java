package com.xjs.alg;

import org.junit.Test;

public class NumArray {
	private class SegmentTreeNode {
		public int start;
		public int end;
		public int sum;
		public SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.sum = 0;
		}
	}

	private SegmentTreeNode root;

	public NumArray(int[] nums) {
		this.root = buildTree(nums, 0, nums.length - 1);
	}

	public void update(int i, int val) {
		update(root, i, val);
	}

	private void update(SegmentTreeNode node, int position, int val) {
		if (node.start == position && node.end == position) {
			node.sum = val;
			return;
		}
		int mid = node.start + (node.end - node.start) / 2;
		if (position <= mid) {
			update(node.left, position, val);
		} else {
			update(node.right, position, val);
		}
		node.sum = node.left.sum + node.right.sum;
	}

	public int sumRange(int i, int j) {
		return sumRange(root, i, j);
	}

	private int sumRange(SegmentTreeNode node, int lo, int hi) {
		if (node.start == lo && node.end == hi) {
			return node.sum;
		}
		int mid = node.start + (node.end - node.start) / 2;
		if (hi <= mid) {
			return sumRange(node.left, lo, hi);
		} else if (lo > mid) {
			return sumRange(node.right, lo, hi);
		} else {
			return sumRange(node.left, lo, mid) + sumRange(node.right, mid + 1, hi);
		}
	}

	private SegmentTreeNode buildTree(int[] nums, int lo, int hi) {
		if (lo > hi) {
			return null;
		} else {
			SegmentTreeNode node = new SegmentTreeNode(lo, hi);
			if (lo == hi) {
				node.sum = nums[lo];
			} else {
				int mid = lo + (hi - lo) / 2;
				node.left = buildTree(nums, lo, mid);
				node.right = buildTree(nums, mid + 1, hi);
				node.sum = node.left.sum + node.right.sum;
			}
			return node;
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] {1, 3, 5};
		NumArray numArray = new NumArray(nums);
		int sumRange = numArray.sumRange(0, 1);
		System.out.println(sumRange);
		numArray.update(1, 10);
		int sumRange2 = numArray.sumRange(1, 2);
		System.out.println(sumRange2);
	}
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);