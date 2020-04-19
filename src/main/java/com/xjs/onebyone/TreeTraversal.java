package com.xjs.onebyone;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.xjs.alg.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @description
 * @auther xjs
 * @date
 */
public class TreeTraversal {
    static TreeNode root;
    static {
//        TreeNode node7 = new TreeNode(null, null, 7);
//		TreeNode node2 = new TreeNode(null, null, 2);
        TreeNode node5 = new TreeNode(null, null, 5);
        TreeNode node4 = new TreeNode(null, null, 4);
        TreeNode node20 = new TreeNode(null, null, 20);
        TreeNode node9 = new TreeNode(node4, node5, 9);
        root = new TreeNode(node9, node20, 1);
    }
    @Test
    public void preTraversal() {
//        doPreTraveralRecursive(root);
        doPreTraversalForeach(root);
    }

    private void doPreTraveralRecursive(TreeNode root) {
        if (root == null) return;

        System.out.println(root.val);
        doPreTraveralRecursive(root.left);
        doPreTraveralRecursive(root.right);
    }

    private void doPreTraversalForeach(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> tmp = new ArrayDeque<>();
        tmp.add(root);
        while (tmp.size() > 0) {
            TreeNode poll = tmp.pop();
            System.out.println(poll.val);
            if (poll.right != null)
                tmp.push(poll.right);
            if (poll.left != null)
                tmp.push(poll.left);
        }
    }

    @Test
    public void testInTraversal() {
//        doInTraversalRecursive(root);
        doInTraversalForeach(root);
    }

    private void doInTraversalRecursive(TreeNode root) {
        if (root == null) return;

        doInTraversalRecursive(root.left);
        System.out.println(root.val);
        doInTraversalRecursive(root.right);
    }

    private void doInTraversalForeach(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        TreeNode cur = root;
        while (deque.size() > 0) {
            if (cur.left != null) {
                deque.push(cur.left);
                cur = cur.left;
            } else {
                TreeNode pop = deque.pop();
                System.out.println(pop.val);
                if (pop.right != null) {
                    deque.push(pop.right);
                }
            }
        }
    }

    @Test
    public void testPostReversal() {
//        doPostReversalRecusive(root);
        doPostReversalForeach(root);
    }

    private void doPostReversalRecusive(TreeNode root) {
        if (root == null) return;

        doPostReversalRecusive(root.left);
        doPostReversalRecusive(root.right);
        System.out.println(root.val);
    }

    // TODO弄明白
    private void doPostReversalForeach(TreeNode root) {
        if (root == null) return;

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

    @Test
    public void testGetHeight() {
        int h = doGetHeight(root);
        System.out.println(h);
    }

    private int doGetHeight(TreeNode root) {
        if (root == null) return 0;

        int left = doGetHeight(root.left);
        int right = doGetHeight(root.right);

        return Math.max(left, right)+1;
    }

    @Test
    public void testLayerByLayer() {
        doLayerByLayer(root);
    }

    private void doLayerByLayer(TreeNode root) {
        if (root == null) return;

        List<List<Integer>> res = Lists.newArrayList();
        Queue<TreeNode> queue = Queues.newArrayDeque();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> subRes = Lists.newArrayList();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tn = queue.poll();
                if (tn.left != null)
                    queue.offer(tn.left);
                if (tn.right != null)
                    queue.offer(tn.right);

                subRes.add(tn.val);
            }
            res.add(subRes);
        }

        System.out.println(res);
    }

    @Test
    public void testVertical() {
        doVertical(root);
    }

    private void doVertical(TreeNode root) {
        if (root == null) return;

        int wl = getWidth(root.left, true);
        int wr = getWidth(root.right, false);
        int w = wl + wr + 1;
        System.out.println(w + "," + wl + "," + wr);

        List<List<Integer>> res = Lists.newArrayListWithCapacity(w);
        for (int i = 0; i < w; i++) {
            res.add(Lists.newArrayList());
        }
        doVerticalTraversal(root, wl, res);
        System.out.println(res);
    }

    private void doVerticalTraversal(TreeNode root, int len, List<List<Integer>> res) {
        if (root == null) return;

        TreeNode cur = root;
        res.get(len).add(cur.val);
        if (cur.left != null) {
            doVerticalTraversal(cur.left, len-1, res);
        }
        if (cur.right != null) {
            doVerticalTraversal(cur.right, len+1, res);
        }
    }

    private int getWidth(TreeNode root, boolean isLeft) {
        int c = 0;
        TreeNode cur = root;
        while (cur != null) {
            if (isLeft) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            c += 1;
        }

        return c;
    }
}
