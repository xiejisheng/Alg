package com.xjs.alg.test;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DPTest {

    /**
     * max = Math.max(Cn+F(n-2), F(n-1))
     */
    @Test
    public void coinRow() {
        int[] coins = new int[] {5, 1, 2, 10, 6, 2};
        int index = coins.length-1;
//        int max = doCoinRow(coins, index);
        int max = doCoinRow(coins);
        System.out.println(max);
    }

    private int doCoinRow(int[] coins) {
        if (coins == null)
            return -1;
        if (coins.length == 1)
            return coins[0];
        if (coins.length == 2)
            return Math.max(coins[0], coins[1]);

        int[] M = new int[coins.length+1];
        M[1] =coins[0];
        for (int i = 1; i < coins.length; i++) {
            M[i+1] = Math.max(coins[i] + M[i+1-2], M[i+1-1]);
        }

        System.out.println(Ints.asList(M));
        return M[coins.length];
    }

    private int doCoinRow(int[] coins, int index) {
        if (index == 0)
            return coins[index];
        if (index == 1)
            return Math.max(coins[0], coins[1]);

        return Math.max(doCoinRow(coins,index-1), coins[index] + doCoinRow(coins, index-2));
    }

    @Test
    public void bag() {
        int[] wn = new int[]{1, 8, 5, 4, 3, 2};
        int target = 10;
        List<List<Integer>> res = Lists.newArrayList();
        List<Integer> curr = Lists.newArrayList();
        int index = 0;
        doBag(index, target, wn, curr, res);
        System.out.println(res);
    }

    private void doBag(int index, int target, int[] wn, List<Integer> curr, List<List<Integer>> res) {
        if (index == wn.length || target <= 0) {
            if (target == 0) {
                res.add(Lists.newArrayList(curr));
                return ;
            } else {
                return ;
            }
        }

        curr.add(wn[index]);
        doBag(index+1,  target-wn[index], wn, curr, res);
        curr.remove(curr.size()-1);
        doBag(index+1, target, wn, curr, res);
    }

    @Test
    public void minJump() {
        int[] a = new int[]{2, 1, 1, 0, 2};
//        int[] a = new int[]{1, 3, 2, 0, 3};
        int minJump = doMinJump(a);
        System.out.println(minJump);
    }
    private int doMinJump(int[] a) {
        // corner case
        if (a.length == 1) {
            return 0;
        }
        int[] M = new int[a.length];
        // Arrays.fill(M, -1);
        // M[0] = 0;
        for (int i = 1; i < a.length; i++) {
            M[i] = - 1;
            for (int j = 0; j < i; j++) {
                if(M[j] != -1 && a[j] >= i-j) {
                    if(M[i] == -1) {
                        M[i] = M[j] + 1;
                    } else {
                        M[i] = Math.min(M[i], M[j] + 1);
                    }
                }
            }
        }
        System.out.println(Ints.asList(M));
        return M[a.length - 1];
    }


    @Test
    public void longestSqure() {
        int[][] matrix = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1}
        };
        int longestSqure = doLongestSqure(matrix);
        System.out.println(longestSqure);
    }

    private int doLongestSqure(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] M = new int[m][n];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    M[i][j] = matrix[i][j];
                } else {
                    if (matrix[i][j] == 0) {
                        M[i][j] = 0;
                    } else {
                        int tmp = Math.min(M[i-1][j], M[i][j-1]);
                        M[i][j] = Math.min(tmp, M[i-1][j-1])+1;
                        max = Math.max(max, M[i][j]);
                    }

                }
            }
        }

        return max;
    }

    @Test
    public void coinChangeP() {
        int[] coins = new int[]{10, 5, 3, 2, 1};
        int amount = 25;
        int index = 0;
        List<Integer> curr = Lists.newArrayList();
        List<List<Integer>> res = Lists.newArrayList();
        doCoinChangeP(index, amount, coins, curr, res);
        System.out.println(res);
    }

    private void doCoinChangeP(int index, int target, int[] coins, List<Integer> curr, List<List<Integer>> res) {
        // corner case
        if (index == coins.length-1) {
            if (target%coins[index] == 0) {
                curr.add(target/coins[index]);
                res.add(Lists.newArrayList(curr));
                curr.remove(curr.size() - 1);
            }
            return;
        }

        if (coins[index] > target) {
            curr.add(0);
            doCoinChangeP(index+1, target, coins, curr, res);
            curr.remove(curr.size()-1);
        } else {
            for (int i = 0; i <= target / coins[index]; i++) {
                curr.add(i);
                doCoinChangeP(index + 1, target - coins[index] * i, coins, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    @Test
    public void coinChange(){
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        int c = doCoinChange(coins, amount);
        System.out.println(c);
    }

    private int doCoinChange(int[] coins, int amount) {
        int[] M = new int[amount+1];
        Arrays.fill(M, -1);

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                M[coin] = 1;
                if (i-coin > 0 && M[i-coin] != -1) {
                    if (M[i] == -1) {
                        M[i] = 1 + M[i-coin];
                    } else {
                        M[i] = Math.min(1 + M[i-coin], M[i]);
                    }
                }
            }
        }

        return M[amount];
    }

    @Test
    public void collecutiveMaxNum() {
//        int[] arr = new int[]{-3, 2, 4, -1, -2, -5};
        int[] arr = new int[]{5, 1, 2, 10, 6, 2};
        doCollecutiveMaxNum(arr);
    }

    private int doCollecutiveMaxNum(int[] arr) {
        int[] A = new int[arr.length];
        int[] B = new int[arr.length];
        int[] M = new int[arr.length];
        A[0] = 0;
        B[0] = arr[0];
        M[0] = Math.max(A[0], B[0]);
        for (int i = 1; i < arr.length; i++) {
            B[i] = M[i-1] + arr[i];
            A[i] = B[i-1];
            M[i] = Math.max(A[i], B[i]);
        }
        System.out.println(Ints.asList(A));
        System.out.println(Ints.asList(B));
        System.out.println(Ints.asList(M));
        return M[arr.length-1];
    }

    @Test
    public void traverseMatrix() {
        int m = 4;
        int n = 4;
        int[][] matrix = new int[m][n];
        doTraverseMatrix(matrix, m, n);
        int k = 3;
        System.out.println(matrix[m-1][k]);
    }

    private void doTraverseMatrix(int[][] matrix, int m, int n) {
        matrix[0][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    matrix[i][j] = matrix[i-1][j] + matrix[i-1][j+1];
                } else if (j == n-1) {
                    matrix[i][j] = matrix[i-1][j] + matrix[i-1][j-1];
                } else {
                    matrix[i][j] = matrix[i-1][j] + matrix[i-1][j-1] + matrix[i-1][j+1];
                }
            }
        }
    }

    @Test
    public void dictWord() {
        Set<String> dict = ImmutableSet.of("rob", "ro", "bobn");
        String word = "";
        int[] ints = doDictWord(dict, word);
        System.out.println(ints[word.length()]);
    }

    private int[] doDictWord(Set<String> dict, String word) {
        if (StringUtils.isBlank(word))
            return new int[0];

        int[] M = new int[word.length()+1];
        M[0] = 1;
        for (int i = 0; i <= word.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (dict.contains(word.substring(j,i))) {
                    M[i] = M[j] == 0 ? 0 : 1;
                }
            }
        }

        System.out.println(Ints.asList(M));
        return M;
    }

    @Test
    public void curRope() {
        int n = 12;
        int i = doCutRope(n);
        System.out.println(i);
    }

    private int doCutRope(int n) {
        int[] M = new int[n+1];
        M[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int curr = Math.max(M[j], j)*(i-j);
                M[i] = Math.max(M[i], curr);
            }
        }
        System.out.println(Ints.asList(M));
        return M[n];
    }
}
