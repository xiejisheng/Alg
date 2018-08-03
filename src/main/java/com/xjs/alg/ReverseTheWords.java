package com.xjs.alg;

public class ReverseTheWords {
	public static void main(String[] args) {
		String str = "I love Google";
		System.out.println(reverseStr(str.toCharArray(), 0, str.length()-1));
		
		String reverseStr = reverseStr(str.toCharArray(), 0, str.length()-1);
		
		char[] arr = reverseStr.toCharArray();
		int start = 0;
		int end = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ') {
				start = end;
				end = i;
				reverseStr(arr, start, end-1);
				end++;
			}
		}
		
		System.out.println(new String(arr));
	}
	
	static String reverseStr(char[] arr, int start, int end) {
		int i = start;
		int j = end;
		while (i < j) {
			swap(arr, i++, j--);
		}
		return new String(arr, 0, arr.length);
	}

	static void swap(char[] arr, int i, int j) {
			char tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
	}

}
