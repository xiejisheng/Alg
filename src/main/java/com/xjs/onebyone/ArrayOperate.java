package com.xjs.onebyone;

import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * @description
 * @auther xjs
 * @date
 */
public class ArrayOperate {

    @Test
    public void testMove0ToEnd() {
        int[] a = new int[]{1, 0, 3, 0, 1};
        doMove0ToEnd(a);
        System.out.println(Ints.asList(a));
    }

    private void doMove0ToEnd(int[] a) {
        if (a == null || a.length == 0 || a.length == 1)
            return;

        int i = 0, j = a.length-1;
        while (i < j) {
            if (a[i] == 0 && a[j] != 0)
                swap(a, i, j);
            if (a[i] != 0) i++;
            if (a[j] == 0) j--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void testRainbowSort() {
        int[] a = new int[] {1, 0, 1, -1, 0};
        doRainbowSort(a);
        System.out.println(Ints.asList(a));
    }

    /**
     * 数据三分，0~i, i~j, j~length
     * @param a
     */
    private void doRainbowSort(int[] a) {
        if (a == null || a.length == 0 || a.length == 1)
            return;

        int i = 0, j = 0, k = a.length-1;
        while (j <= k) {
            if (a[j] == -1) {
                swap(a, i++, j++);
            } else if (a[j] == 0) {
                j++;
            } else {
                swap(a, j, k--);
            }
        }

    }
}
