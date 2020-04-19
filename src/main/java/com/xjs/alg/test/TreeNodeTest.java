package com.xjs.alg.test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import com.xjs.alg.TreeNode;

public class TreeNodeTest {
	private TreeNode root;

    /**
     *              3
     *          9       20
     *       4    5   2   7
     *
     */
	@Before
	public void init() {
//		TreeNode node7 = new TreeNode(null, null, 7);
//		TreeNode node2 = new TreeNode(null, null, 2);
		TreeNode node5 = new TreeNode(null, null, 5);
		TreeNode node4 = new TreeNode(null, null, 4);
		TreeNode node20 = new TreeNode(null, null, 3);
		TreeNode node9 = new TreeNode(node4, node5, 2);
		root = new TreeNode(node9, node20, 1);
	}

	@Test
	public void outOrder() {
		List<List<Integer>> res = Lists.newArrayList();
		doOutOrder(root, res);
		System.out.println(res);
	}
	public int doOutOrder(TreeNode node, List<List<Integer>> res) {
		if (node == null)
			return -1;
		int height = Math.max(doOutOrder(node.left, res), doOutOrder(node.right, res)) + 1;
		if(height == res.size()) {
			res.add(Lists.newArrayList());
		}
		res.get(height).add(node.val);
		return height;
	}


	@Test
	public void testBinarySearchTree() {
        boolean isBST = doIsBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(isBST);
    }

    private boolean doIsBST(TreeNode node, int min, int max) {
        if (node == null)  return true;

        if (node.val < min || node.val > max)
            return false;

        return doIsBST(node.left, min, node.val) &&
                doIsBST(node.right, node.val, max);
    }

    @Test
	public void verticalOrder() {
	    doVerticalOrder(root);
    }

    private void doVerticalOrder(TreeNode root) {
        int lw = doGetWidth(root.left, true);
        int rw = doGetWidth(root.left, false);
        int w = lw + rw + 1;

        List<List<TreeNode>> res = Lists.newArrayList();
        for (int i = 0; i < w; i++) {
            res.add(Lists.newArrayList());
        }

        addVerticalItem(lw, root, res);
        System.out.println(res);
    }

    private void addVerticalItem(int rootIndex, TreeNode root, List<List<TreeNode>> res) {
	    if (root == null) return;

	    res.get(rootIndex).add(root);
	    addVerticalItem(rootIndex-1, root.left, res);
	    addVerticalItem(rootIndex+1, root.right, res);
    }

    @Test
    public void getHeight() {
        int h = doGetHeight(root);
        System.out.println(h);
    }

    private int doGetHeight(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(doGetHeight(node.left), doGetHeight(node.right));
    }

    @Test
    public void getWidth() {
        int lw = doGetWidth(root.left, true);
        int rw = doGetWidth(root.left, false);
        System.out.println(lw+rw+1);
    }

    private int doGetWidth(TreeNode node, boolean isLeftDirection) {
        int width = 0;
        while (node != null) {
            if (isLeftDirection)
                node = node.left;
            else
                node = node.right;
            width++;
        }

        return width;
    }


    @Test
	public void layerBylayer() {
		doLayerByLayer(root);
	}

	private void doLayerByLayer(TreeNode root) {
		if (root == null) return;

		List<List<TreeNode>> res = Lists.newArrayList();
		List<TreeNode> list = Lists.newArrayList();
        list.add(root);
        while (!list.isEmpty()) {
            List<TreeNode> tmp = Lists.newArrayList();
            for (TreeNode node : list) {
                if (node.left != null)
                    tmp.add(node.left);
                if (node.right != null)
                    tmp.add(node.right);
            }
            res.add(list);
            list = tmp;
        }

        System.out.println(res);

	}

	@Test
	public void preOrder() {
//		doPreOrder(root);
		doPreOrder1(root);
	}

	private void doPreOrder1(TreeNode root) {
		if (root == null) return;

		System.out.println(root.val);
		doPreOrder1(root.left);
		doPreOrder1(root.right);
	}

	private void doPreOrder(TreeNode root) {
		if (root == null) return;

		Deque<TreeNode> deque = new ArrayDeque<>();
		deque.push(root);
		while (!deque.isEmpty()) {
			TreeNode node = deque.pop();
			System.out.println(node.val);
			if (node.right != null)
				deque.push(node.right);
			if (node.left != null)
				deque.push(node.left);
		}
	}

	@Test
	public void inOrder() {
		doInOrder(root);
	}

	private void doInOrder(TreeNode root) {
		// left root right
		if (null == root) return;
		
		Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
		TreeNode curr = root;
		while (curr != null || !deque.isEmpty()) {
			 if (curr != null) {
				 deque.push(curr);
				 curr = curr.left;
			 } else {
				 TreeNode node = deque.pop();
				 System.out.println(node.val);
				 curr = node.right;
			 }
		}
	}

	@Test
	public void postOrder() {
		doPostOrder(root);
	}


	private void doPostOrder(TreeNode root) {
		if (null == root) return;
		
		Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
		boolean flag = false;
		TreeNode prev = null;
		TreeNode curr = root;

		while(prev != root) {
			// 左遍历完
			while (curr != null) {
				deque.push(curr);
				curr = curr.left;
			}
			
			flag = true;
			prev = null;
			
			// 以左为根
			while (flag && !deque.isEmpty()) {
				curr = deque.peek();
				if (curr.right == prev) {
					System.out.println(deque.pop().val);
					prev = curr; // flag = true
				} else {
					flag = false;
					curr = curr.right;
				}
			}
		}
	}
}
