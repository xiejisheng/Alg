package com.xjs.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * DP 
 * @author xjs
 *
 */
public class FewestConis {
	public static void main(String[] args) {
		int amount = 4;
	    Set<Integer> set = new HashSet<Integer>(Arrays.asList(1, 2, 5));
		int fewestCoins = fewestCoins1(set, amount);
		System.out.println(fewestCoins);
	}

	/**
	 * error
	 * @param arr
	 * @param amount
	 * @return
	 */
	@Deprecated
	private static int fewestCoins(Set<Integer> arr, int amount) {
		int M[] = new int[amount + 1];
		for (int item : arr) {
			M[item] = 1;
		}
		for (int i = 0; i <= amount; i++) {
			if (M[i] == 0)
				M[i] = i;
			for (int j = 1; j < i; j++) {
				if (arr.contains(j))
					M[i] = Math.min(M[j] + M[i - j], M[i]);
			}
		}
		return M[amount];
	}


	private static int fewestCoins1(Set<Integer> coins, int amount) {
		int[] M = new int[amount + 1];
		Arrays.fill(M, -1);
		
		for (int i = 1; i < M.length; i++) {
			if (coins.contains(i))
				M[i] = 1;
			
			for (Integer coin : coins) {
				if (i-coin > 0 && M[i-coin] != -1) {
					if (M[i] == -1)
						M[i] = i;
					else 
						M[i] = Math.min(1+M[i-coin], M[i]);
				}
			}
		}
		return M[amount];
	}
}
