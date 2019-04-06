package com.xjs.alg;

public class RemoveAdjacentRepeatedCharacters {
	public static void main(String[] args) {
		char[] arr = "aabccdc".toCharArray();
		String removeAdjacentRepeatedCharacters = removeAdjacentRepeatedCharacters(arr);
		System.out.println();
		System.out.println(removeAdjacentRepeatedCharacters);
	}
	
	public static String removeAdjacentRepeatedCharacters(char[] arr) {
		int j = -1;
		for (int i = 0; i < arr.length; i++) {
			if (j == -1 || arr[j] != arr[i]) {
				arr[++j] = arr[i];
			} else {
				j--;
				while(i + 1 < arr.length && arr[i] == arr[i + 1]){
					i++;
				}
			}
		}
		return new String(arr, 0, j+1);
	}
}
