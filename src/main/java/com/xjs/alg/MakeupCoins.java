package com.xjs.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class MakeupCoins {
	public static void main(String[] args) {
		int amount = 5;
		Set<Integer> coins = Sets.newHashSet(1, 2, 5);
		ArrayList<ArrayList<Integer>> makeup1 = makeup1(amount, coins);
		System.out.println(makeup1);
	}

	private static ArrayList<ArrayList<Integer>> makeup(int amount, Set<Integer> coins) {
		ArrayList<ArrayList<Integer>> ways = Lists.newArrayList();
		if (amount <= 0) 
			return ways;
		
		for (Integer coin : coins) {
			if (amount - coin > 0) {
				ArrayList<ArrayList<Integer>> tmp = makeup(amount-coin, coins);
				for (ArrayList<Integer> each : tmp) {
					each.add(coin);
				}
				ways.addAll(tmp);
			} else if (amount - coin == 0)
				ways.add(Lists.newArrayList(coin));
		}
		return ways;
	}
	
	private static ArrayList<ArrayList<Integer>> makeup1(int amount, Set<Integer> coins) {
		ArrayList<ArrayList<Integer>> ways = Lists.newArrayList();
		for (int i = 1; i <= amount; i++) {
			for (Integer coin : coins) {
				if (i - coin > 0) {
					for (ArrayList<Integer> each : ways) {
						each.add(coin);
					}
				} else if (i - coin == 0)
					ways.add(Lists.newArrayList(coin));
			}
		}
		return ways;
	}
}
