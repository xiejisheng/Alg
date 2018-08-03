package com.xjs.alg;

public class ReOrderArray {
	public static void main(String[] args) {
		String str = "1234567ABCDEFG";
		// String str = "1234567";
		char[] arr = str.toCharArray();
		if (str.length() % 2 == 0) {
			reOrder(arr, 0, str.length());
		} else {
			reOrder(arr, 0, str.length() - 1);
		}

		System.out.println(new String(arr));
	}

	public static void reOrder(char[] arr, int start, int end) {
		if (end - start < 3)
			return;

		int mid = start + (end - start) / 2;
		int preMid = start + (mid - start) / 2;
		int suffixMid = mid + (end - mid) / 2;
		reverse(arr, preMid, mid - 1);
		reverse(arr, mid, suffixMid - 1);
		reverse(arr, preMid, suffixMid - 1);
		mid = preMid + suffixMid - mid;
		reOrder(arr, start, mid);
		reOrder(arr, mid + 1, end);
	}

	public static void reverse(char[] arr, int start, int end) {
		for (; start < end;) {
			char tmp = arr[start];
			arr[start] = arr[end];
			arr[end] = tmp;
			start++;
			end--;
		}
	}

	// 123 4567 ABC DEFG
	// 123 7654 CBA DEFG
	// 123 ABC 4567 DEFG
	// 1 23 A BC
	// 1 32 A BC
	// 1 A 23 BC
	// 1 A 2 3 B C
	// 1 A 2 B 3 C
}
