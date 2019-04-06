package com.xjs.alg;

public class MaxCollecutiveRating {
	public static void main(String[] args) {
		int[] arr = new int[]{-3, -7, 4, -1, -2, -5};
		int collecutiveMaxNum = collecutiveMaxNum(arr);
		System.out.println(collecutiveMaxNum);
	}

	/**
	 *    -3    2   4   -1   -2  -5
	 * A   0    -3  2    6    5   4
	 * B   -3   2   6    5    4   -1
	 * M   0    2   6    6    5   4
	 * @param arr
	 * @return
	 */
	private static int collecutiveMaxNum(int[] arr) {
		int[] A = new int[arr.length];
		int[] B = new int[arr.length];
		int[] M = new int[arr.length];
		A[0] = 0;
		B[0] = arr[0];
		M[0] = Math.max(A[0], B[0]);
		for (int i = 1; i < arr.length; i++) {
			A[i] = B[i-1];
			B[i] = M[i-1]+arr[i];
			M[i] = Math.max(A[i], B[i]);
		}
		return M[arr.length-1];
	}
	
	@Deprecated
	private static int collecutiveMaxNum1(int[] arr) {
		int[] M = new int[arr.length];
		int[] B = new int[arr.length];
		if (arr[0] < M[0]) {
			B[0] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			if (B[i-1] == 0) {
				if ((M[i-1]+arr[i]) < M[i-1]) {
					M[i] = M[i-1];
					B[i] = 1;
				} else {
					M[i] = M[i-1] + arr[i];
				}
				
			} else 
				M[i] = M[i-1]+arr[i];
		}
		return M[arr.length-1];
	}
}
