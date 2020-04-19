package com.xjs.onebyone;

import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * @description
 * @auther xjs
 * @date
 */
public class Sort {
    @Test
    public void testSelectionSort() {
        int[] arr = new int[]{-3, 1, 2, 4, 6};
        doSelectionSort(arr);
        System.out.println(Ints.asList(arr));
    }

    private void doSelectionSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1)
            return;

        int l = arr.length;
        for (int i = 0; i < l; i++) {
            int min = i;
            for (int j = i + 1; j < l; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            if (min != i)
                swap(arr, i, min);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void testMergeSort() {
        int[] arr = new int[]{4, 2, -3, 6, 1};
        doMergeSort(arr);
        System.out.println(Ints.asList(arr));
    }

    private void doMergeSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1)
            return;

        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        int[] b = new int[a.length];
        System.arraycopy(a, lo, b, lo, hi - lo + 1);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = b[j++];
            else if (j > hi) a[k] = b[i++];
            else if (b[i] < b[j]) a[k] = b[i++];
            else a[k] = b[j++];
        }
    }

    @Test
    public void testQuickSort() {
        int[] a = new int[]{4, 2, -3, 6, 1};

        doQuickSort(a);
        System.out.println(Ints.asList(a));
    }

    private void doQuickSort(int[] a) {
        if (a == null || a.length == 0 || a.length == 1)
            return;
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(int[] a, int lo, int hi) {
        if (lo > hi) return;

        int mid = partition(a, lo, hi);
        quickSort(a, lo, mid - 1);
        quickSort(a, mid + 1, hi);
    }

    private int partition(int[] a, int lo, int hi) {
        int pivot = hi, j = hi - 1, i = lo;
        while (i <= j) {
            if (a[i] < a[pivot]) i++;
            else if (a[j] >= a[pivot]) j--;
            else swap(a, i++, j--);
        }
        swap(a, i, pivot);
        return i;
    }
}
