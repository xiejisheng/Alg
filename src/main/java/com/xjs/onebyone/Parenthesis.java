package com.xjs.onebyone;

import org.junit.Test;

/**
 * @description
 * @auther xjs
 * @date
 */
public class Parenthesis {

    @Test
    public void testBrackets() {
        brackets(3,0, "");
    }

    private void brackets(int openStock, int closeStock, String s) {
        if (openStock == 0 && closeStock == 0) {
            System.out.println(s);
        }
        if (openStock > 0) {
            brackets(openStock-1, closeStock+1, s + "(");
        }
        if (closeStock > 0) {
            brackets(openStock, closeStock-1, s + ")");
        }
    }

    public static void main(String[] args) {
        brackets(4);
    }
    static void brackets(final int N) {
        brackets(N, 0, 0, new char[N * 2]);
    }
    static void brackets(int openStock, int closeStock, int index, char[] arr) {
        while (closeStock >= 0) {
            if (openStock > 0) {
                arr[index] = '<';
                brackets(openStock-1, closeStock+1, index+1, arr);
            }
            if (closeStock-- > 0) {
                arr[index++] = '>';
                if (index == arr.length) {
                    System.out.println(arr);
                }
            }
        }
    }
}
