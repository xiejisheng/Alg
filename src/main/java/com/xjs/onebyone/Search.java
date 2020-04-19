package com.xjs.onebyone;

import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * @description
 * @auther xjs
 * @date
 */
public class Search {
    @Test
    public void testClassicBinarySearch() {
        int[] a = new int[]{1,2,2,2,7,9};
        int t = 2;
        int index = doClassicBinarySearch(a, t);
        System.out.println(index);
    }

    private int doClassicBinarySearch(int[] a, int t) {
        if (a == null) return -1;
        if (a.length == 1) {
            if (a[0] == t) return 0;
            else return -1;
        }

        int lo = 0, hi = a.length-1, mid = lo + (hi-lo)/2;
        while (lo <= hi) {
            if (a[mid] == t) return mid;
            else if (a[mid] < t) lo = mid+1;
            else hi = mid-1;

            mid = lo + (hi-lo)/2;
        }
        return -1;
    }

    @Test
    public void testFirstOccBinarySearch() {
        int[] a = new int[]{1,2,2,2,7,9};
        int t = 2;
        int index = doFirstOccBinarySearch(a, t);
        System.out.println(index);
    }

    private int doFirstOccBinarySearch(int[] a, int t) {
        if (a == null) return -1;
        if (a.length == 1) {
            if (a[0] == t) return 0;
            else return -1;
        }

        int lo = 0, hi = a.length-1, mid = lo + (hi-lo)/2;
        while (lo < hi) {
            if (a[mid] == t) hi = mid;
            else if (a[mid] < t) lo = mid+1;
            else hi = mid-1;

            mid = lo + (hi-lo)/2;
        }

        if (a[lo] == t) return lo;
        else if (a[hi] == t) return hi;
        else return -1;
    }

    @Test
    public void testLastOccBinarySearch() {
        int[] a = new int[]{1,2,2,2,7,9};
        int t = 2;
        int index = doLastOccBinarySearch(a, t);
        System.out.println(index);
    }

    private int doLastOccBinarySearch(int[] a, int t) {
        if (a == null) return -1;
        if (a.length == 1) {
            if (a[0] == t) return 0;
            else return -1;
        }

        int lo = 0, hi = a.length-1, mid = lo + (hi-lo)/2;
        while (lo < hi) {
            if (a[mid] == t) lo = mid;
            else if (a[mid] < t) lo = mid+1;
            else hi = mid-1;

            mid = lo + (hi-lo)/2;
        }

        if (a[hi] == t) return lo;
        else if (a[lo] == t) return hi;
        else return -1;
    }

    @Test
    public void testCloestInSortedArray() {
        int[] a = new int[]{1, 3, 3, 4};
        int t = 2;
        int index = doCloestInSortedArray(a, t);
        System.out.println(index);
    }

    private int doCloestInSortedArray(int[] a, int t) {
        if (a == null || a.length == 0) return -1;
        if (a.length == 1) return 0;

        int lo = 0, hi = a.length-1, mid = lo+(hi-lo)/2;
        while (lo+1 < hi) {
            if (a[mid] == t) return mid;
            else if (a[mid] > t) hi = mid;
            else lo = mid;

            mid = lo+(hi-lo)/2;
        }
        if (Math.abs(a[lo]-t) > Math.abs(a[hi]-t)) return hi;
        else return lo;
    }

    @Test
    public void testKCloestInSortedArray() {
        int[] a = new int[]{1, 4, 6, 8};
        int t = 4;
        int k = 2;
        int[] c = doKCloestInSortedArray(a, t, k);
        System.out.println(Ints.asList(c));
    }

    private int[] doKCloestInSortedArray(int[] a, int t, int k) {
        if (a == null || a.length == 0) return new int[]{};
        if (a.length <= k) return a;

        int[] res = new int[k];
        int closet = doCloestInSortedArray(a, t);

        int i = closet-1, j = closet+1;
        res[0] = a[closet];
        for (int m = 1; m < k; m++) {
            if(i >= 0 && j < a.length){
                if (Math.abs(a[i]-a[closet]) <= Math.abs(a[j]-a[closet])) {
                    res[m] = a[i];
                    i--;
                } else {
                    res[m] = a[j];
                    j++;
                }
            }else if (i < 0) {
                res[m] = a[j];
                j++;
            } else {
                res[m] = a[i];
                i--;
            }
        }

        return res;
    }

    @Test
    public void testSortedMatrixSearch() {
        int[][] matrix = new int[][]{{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int t = 9;
        int[] index = doSortedMatrixSearch(matrix, t);
        System.out.println(Ints.asList(index));
    }

    /**
     * 主要是如何表示
     * @param matrix
     * @param t
     * @return
     */
    private int[] doSortedMatrixSearch(int[][] matrix, int t) {
        if (matrix == null) return new int[]{-1, -1};
        if (matrix.length == 1 && matrix[0].length == 0) return new int[]{-1, -1};
        if (matrix.length == 1 && matrix[0].length == 1) return new int[]{1, 1};

        int rows = matrix.length, cols = matrix[0].length;
        int lo = 0, hi = rows*cols-1;
        while (lo <= hi) {
            int mid = lo+(hi-lo)/2, row = mid/cols, col = mid%cols;
            if (matrix[row][col] == t) return new int[]{row, col};
            else if (matrix[row][col] > t) hi = mid-1;
            else lo = mid+1;
        }
        return new int[]{-1, -1};
    }
}
