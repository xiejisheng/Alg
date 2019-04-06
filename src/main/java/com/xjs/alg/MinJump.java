package com.xjs.alg;

import java.util.Arrays;

public class MinJump {
	public static void main(String[] args) {
		int[] a = new int[] {2, 1, 1, 0, 2};
		int minJump = minJump(a);
		System.out.println(minJump);
	}

	static int minJump(int[] a) {
		// corner case
		if (a.length == 1) {
			return 0;
		}
		int[] M = new int[a.length];
		Arrays.fill(M, -1);
		M[0] = 0;
		for (int i = 1; i < M.length; i++) {
			for (int j = 0; j < i; j++) {
				if(M[j] != -1 && M[j] + a[j] >= i) {
					if(M[i] == -1) {
						M[i] = M[j] + 1;
					} else {
						M[i] = Math.min(M[i], M[j] + 1);
					}
				}
			}
		}
		return M[a.length - 1];
	}

}
