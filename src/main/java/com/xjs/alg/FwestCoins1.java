package com.xjs.alg;

import java.util.Arrays;
import java.util.List;

public class FwestCoins1 {
	public static void main(String[] args) {
		int target = 11;
		int[] coins = new int[]{5,2,1};
		
	}
	static void dfs(int index, int[] coins, int target, List<int[]> res, int[] cur) {
		if(index == coins.length - 1) {
			cur[index] = target;
			res.add(Arrays.copyOf(cur, cur.length));
			return;
		}
		// [5, 2, 1]
		for(int i = 0; i < target / coins[index]; i++) {
			cur[index] = i;
			dfs(index + 1, coins, target - coins[index] * i, res, cur);		
		}
	}

}
