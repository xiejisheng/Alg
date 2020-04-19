package com.xjs.onebyone;

import org.junit.Test;

/**
 * @description
 * @auther xjs
 * @date
 */
public class Strings {
    @Test
    public void testRemoveAdjcent() {
        String s = "abbbbbc";
        doRemoveAdjecent(s.toCharArray());
    }

    private void doRemoveAdjecent(char[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[j-1] != arr[i]) {
                arr[j++] = arr[i];
            }
        }
        System.out.println(new String(arr, 0, j));
    }

    @Test
    public void testRemoveAdjcent1() {
        String s = "abbbaaccz";
        doRemoveAdjecent2(s.toCharArray());
    }

    private void doRemoveAdjecent2(char[] arr) {
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (j == -1 || arr[j] != arr[i]) {
                arr[++j] = arr[i];
            } else {
                j--;
                while (i+1 < arr.length && arr[i] == arr[i+1]) {
                    i++;
                }
            }
        }

        System.out.println(new String(arr, 0, j+1));
    }

    @Test
    public void testRemoveSpace() {
        String s = "   I   love  MTU  ";
        doRemoveSpace(s.toCharArray());
    }

    private void doRemoveSpace(char[] arr) {
        int i = 0;
        while (arr[i] == ' ') {
            i++;
        }
        int j = 0;
        for (; i < arr.length; i++) {
            if (arr[i] != ' ' ||
                    (arr[i] == ' ' && i+1 < arr.length && arr[i+1] != ' ')) {
                arr[j++] = arr[i];
            }
        }
        System.out.println(new String(arr, 0, j));
    }
}
