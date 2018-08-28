package com.xjs.alg;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaxProductOfCuttingRope {
	public static void main(String[] args) {
//		int n = 12;
//		Map<Integer, Integer> result = new LinkedHashMap<>(); 
//		int solution = solution(n, result);
//		System.out.println(solution);
//		System.out.println(result);
		System.out.println(maxProduct(12));
	}

	private static int solution(int n, Map<Integer, Integer> result) {
		if (n < 2)
			return n;
		if (n == 2)
			return 2;
		if (n == 3)
			return 3;
		int max = 0;
		for (int i = 1; i < n-2; i++) {
//			int tmp = solution(n-i, result)*solution(i, result);
			int tmp = solution(n-i, result)*i;
			if (tmp > max) {
				max = tmp;
				result.put(n, tmp);
			}
		}
		return max;
	}
	
	public static int maxProduct(int n){
		// Init
		int[] M = new int[n + 1];
		// Basecase
		M[2] = 1;
		// Induction
		for(int i = 3; i < M.length; i++){
			for(int j = 1; j < i; j++){
				int cur = Math.max(M[j], j) * (i - j);
				M[i] = Math.max(M[i], cur);
			}
		}
		return M[n];
	}

}
