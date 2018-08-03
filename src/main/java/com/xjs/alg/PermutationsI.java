package com.xjs.alg;

import java.util.ArrayList;
import java.util.List;

public class PermutationsI {
	public static void main(String[] args) {
		String str = "abc";
		char[] arr = str.toCharArray();
		List<String> result = new ArrayList<>();
		permutation(arr, result);
		System.out.println(result);
	}

	private static void permutation(char[] arr, List<String> result) {
		for (int i = 0; i < arr.length; i++) {
			if (result.isEmpty()) {
				result.add(Character.toString(arr[i]));
				continue;
			}
			
			List<String> tmp = new ArrayList<>();
			for (String each : result) {
				int length = 0;
				while (length <  each.length()+1) {
					StringBuilder sb = new StringBuilder(each);
					sb.insert(length, arr[i]);
					length++;
					tmp.add(sb.toString());
				}
			}
			result.clear();
			result.addAll(tmp);
		}
	}
}
