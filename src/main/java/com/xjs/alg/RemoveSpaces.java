package com.xjs.alg;

public class RemoveSpaces {
	public static void main(String[] args) {
		String str = " l    love   u";
		char[] arr = str.toCharArray();
		int lastIndex = removeSpaces(arr);
		System.out.println(new String(arr, 0, lastIndex));
	}

	private static int removeSpaces(char[] arr) {
		int j = 0, i = 0;
		while (arr[i] == ' ') {
			i++;
		}
		for (; i < arr.length; i++) {
			if (arr[i] != ' ' || (arr[i] == ' ' && i + 1 < arr.length && arr[i + 1] != ' ')) {
				arr[j++] = arr[i];
			}
		}
		return j;
	}
}
