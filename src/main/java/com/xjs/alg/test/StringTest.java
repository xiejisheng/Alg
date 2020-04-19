package com.xjs.alg.test;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StringTest {

    @Test
    public void longestSubstr() {
        String str = "bcdfbdbbbbacdefbg";
        char[] arr = str.toCharArray();
        String s = doLongestSubstr(arr);
        System.out.println(s);
    }


    private String doLongestSubstr(char[] arr) {
        HashMap<Character, Integer> map = Maps.newHashMap();

        int i = 0, j = 0, max = 0, start = 0, end = 0;
        for (; j < arr.length; j++) {
            if (map.containsKey(arr[j])) {
                while (arr[i] != arr[j]) {
                    i = delete(arr, map, i);
                }
                i = delete(arr, map, i);
            } else {
                map.put(arr[j], !map.containsKey(arr[j]) ? 1 : map.get(arr[j])+1);
                if(size(map) > max){
                    start = i;
                    end = j;
                    max = size(map);
                }
            }
        }

//        return new int[]{i, i+max};
        return new String(arr, start, end-1);
    }

    private int delete(char[] arr, HashMap<Character, Integer> map, int i) {
        Integer c = map.get(arr[i]);

        if (c == 1) {
            map.remove(arr[i++]);
        } else {
            map.put(arr[i++], c-1);
        }
        return i;
    }

    private int size(HashMap<Character, Integer> map) {
        int res = 0;
        Collection<Integer> values = map.values();
        for (Integer val : values) {
            res += val;
        }
        return res;
    }


    @Test
    public void removeEmpty() {
        String s = " i   love u  ";
        String s1 = doRemoveEmpty(s);
        System.out.println(s1);
    }

    private String doRemoveEmpty(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = 0;
        while (arr[i] == ' ') {
            i++;
        }
        for (; i < arr.length; i++) {
            if (arr[i] != ' '
                    || (arr[i] == ' ' && i+1 < arr.length && arr[i+1] != ' ')) {
                arr[j++] = arr[i];
            }
        }
        return new String(arr, 0, j);
    }

    @Test
    public void removeAdjacentIV() {
        String s = "abbbaccz";
        String doRemoveAdjacentIV = doRemoveAdjacentIV(s);
        System.out.println(doRemoveAdjacentIV);
    }

    private String doRemoveAdjacentIV(String s) {
        char[] arr = s.toCharArray();
        int j = -1;
        for (int i = 0; i < arr.length; i++) {
            if (j == -1 || arr[j] != arr[i]) {
                arr[++j] = arr[i];
            } else {
                j--;
                while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                    i++;
                }
            }
        }

        return new String(arr, 0, j + 1);

    }

    @Test
    public void removeAdjacent() {
        String s = "aaabbbccc";
        String doRemoveAdjacent = doRemoveAdjacent(s);
        System.out.println(doRemoveAdjacent);
    }

    private String doRemoveAdjacent(String s) {
        char[] arr = s.toCharArray();
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[j - 1] != arr[i]) {
                arr[j++] = arr[i];
            }
        }
        return new String(arr, 0, j);

    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
}
