package com.xjs.alg;

public class KClosetInSortedArray {
	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 6, 8};
		int[] kCloset = kCloset(a, 3, 3);
		for (int each : kCloset) {
			System.out.println(each);
		}
	}

	public static int closestInSortedArray(int[] a, int target) {
		// Corner case
		// Init
		int i = 0, j = a.length - 1, mid;
		// binary Search
		while (i < j - 1) {
			mid = i + (j - i) / 2;
			if (target == a[mid]) {
				return mid;
			} else if (target < a[mid]) {
				j = mid;
			} else {
				i = mid;
			}
		}

		if (Math.abs(a[i] - target) <= Math.abs(a[j] - target)) {
			return i;
		}
		return j;
	}

	public static int[] kCloset(int[] a, int target, int k) {
		// Corner Case

		// init
		int[] result = new int[k];
		// closet -> index
		int closet = closestInSortedArray(a, target);
		int i = closet - 1;
		int j = closet + 1;
		result[0] = a[closet];
		for (int m = 1; m < k; m++) {
			if (i >= 0 && j <= a.length && Math.abs(a[i] - target) <= Math.abs(a[j] - target)) {
				result[m] = a[i];
				i--;
			} else if (i >= 0 && j <= a.length && Math.abs(a[i] - target) >= Math.abs(a[j] - target)) {
				result[m] = a[j];
				j++;
			} else if (i >= 0 && j > a.length) {
				result[m] = a[i];
				i--;
			} else if (i < 0 && j <= a.length) {
				result[m] = a[j];
				j++;
			} 
		}
		return result;
	}

}
