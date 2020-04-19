package com.xjs.onebyone;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description
 * @auther xjs
 * @date
 */
public class Combination {

    @Test
    public void testAllSubsets() {
        String s = "abc";
        StringBuilder sb = new StringBuilder();
        List<String> res = Lists.newArrayList();
        int index = 0;
//        doAllSubSets(s.toCharArray(), sb, index, res);
        dfs(s.toCharArray(), sb, index, res);
        System.out.println(res);
    }

    private void doAllSubSets(char[] a, StringBuilder sb, int index, List<String> res) {
        res.add(sb.toString());

        for (int i = index; i < a.length; i++) {
            sb.append(a[i]);
            doAllSubSets(a, sb, i+1, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private void dfs(char[] a, StringBuilder sb, int index, List<String> res) {
        if (index == a.length) {
            res.add(sb.toString());
            return;
        }

        sb.append(a[index]);
        dfs(a, sb, index+1, res);
        sb.deleteCharAt(sb.length()-1);
        dfs(a, sb, index+1, res);
    }

    @Test
    public void testPermutation() {
        String s = "abc";
        List<String> res = Lists.newArrayList();
        doPermutation(s.toCharArray(), 0, res);
        System.out.println(res);
    }

    private void doPermutation(char[] a, int index, List<String> res) {
        if (index == a.length) {
            res.add(new String(a));
        }

        for (int i = index; i < a.length; i++) {
            swap(a, index, i);
            doPermutation(a, index+1, res);
            swap(a, index, i);
        }
    }

    private void swap(char[] a, int index, int i) {
        char tmp = a[index];
        a[index] = a[i];
        a[i] = tmp;
    }

    @Test
    public void testCombinationCoins() {
        int[] coins = new int[]{3, 2};
        int t = 5;
        int index = 0;
        List<List<Integer>> res = Lists.newArrayList();
        List<Integer> cur = Lists.newArrayList();
        doCombinationCoins(index, coins, t, cur, res);
        System.out.println(res);
    }

    private void doCombinationCoins(int index, int[] coins, int t, List<Integer> cur, List<List<Integer>> res) {
        if (t == 0) {
//            cur.add(t);
            res.add(new ArrayList<Integer>(cur));
//            cur.remove(cur.size() - 1);
            return;
        }

        if (index == coins.length) return;

        for (int i = 0; i <= t/coins[index]; i++) {
            cur.add(i);
            doCombinationCoins(index+1, coins, t-coins[index]*i, cur, res);
            cur.remove(cur.size()-1);
        }
    }

    @Test
    public void testPermutation2() {
        String str = "abc";
        char[] arr = str.toCharArray(); 
        List<String> result = new ArrayList<>(); 
        doPermutation2(arr, result); 
        System.out.println(result);
    }

    private void doPermutation2(char[] arr, List<String> result) {
        for (int i = 0; i < arr.length; i++) {
            if (result.isEmpty()) {
                result.add(arr[i]+"");
                continue;
            }
            List<String> tmp = Lists.newArrayList();
            for (String each : result) {
                int length = 0;
                while (length < each.length()+1) {
                    StringBuilder sb = new StringBuilder(each);
                    sb.insert(length, arr[i]);
                    length++;
                    tmp.add(sb.toString());
                }
            }
            result.clear();
            result.addAll(tmp);
        }
    }

    @Test
    public void testGetMondayOfOffsetWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.WEEK_OF_MONTH, c.get(Calendar.WEEK_OF_MONTH) + 0);
        int i = (int) (c.getTime().getTime() / 1000l);
        System.out.println(i);
        System.out.println(Long.valueOf(new Date().getTime() / 1000).intValue());

    }
    @Test
    public void testGetSundayOfOffsetWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 7);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.WEEK_OF_MONTH, c.get(Calendar.WEEK_OF_MONTH) + 0);
        int i = (int) (c.getTime().getTime() / 1000);
        System.out.println(i);

        System.out.println(Long.valueOf(new Date().getTime() / 1000).intValue());
    }
}
