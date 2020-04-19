package com.xjs.alg.test;

import java.util.List;

import org.junit.Test;

import avro.shaded.com.google.common.collect.Lists;

public class PermutationAndCombination {
	
	@Test
	public void generateParentheses() {
		int n = 2;
		List<String> res = Lists.newArrayList();
		StringBuilder sb = new StringBuilder();
		int count = 0;
		doGenerateParentheses(count, n, sb, res);
		System.out.println(res);
	}

	private void doGenerateParentheses(int count, int n, StringBuilder sb, List<String> res) {
		if (n <= 0) 
			return;
		
		if (sb.length() == n*2) {
			if (count == 0) {
				res.add(sb.toString());
			}
			return;
		}
		
		if (count < n) {
			sb.append('(');
			doGenerateParentheses(count+1, n, sb, res);
			sb.deleteCharAt(sb.length()-1);
		}
		if (count > 0) {
			sb.append(')');
			doGenerateParentheses(count-1, n, sb, res);
			sb.deleteCharAt(sb.length()-1);
		}
	}

	@Test
	public void chargeCoins() {
		int target = 10;
		int[] coins = new int[] {25, 10, 5, 2 ,1};
		List<List<Integer>> res = Lists.newArrayList();
		List<Integer> list = Lists.newArrayList();
		int index = 0;
		doChargeCoins(index, target, coins, list, res);
		System.out.println(res);
	}

	private void doChargeCoins(int index, int target, int[] coins, List<Integer> list, List<List<Integer>> res) {
		if (index == coins.length-1) {
			list.add(target);
			res.add(Lists.newArrayList(list));
			list.remove(list.size()-1);
			return;
		}
		
		if (coins[index] > target) {
			list.add(0);
			doChargeCoins(index+1, target, coins, list, res);
			list.remove(list.size()-1);
		} else {
			for (int i = 0; i <= target/coins[index]; i++) {
				list.add(i);
				doChargeCoins(index+1, target-coins[index]*i, coins, list, res);
				list.remove(list.size()-1);
			}
		}
	}
}
