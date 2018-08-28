package com.xjs.alg;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		String str = "bcdfbdbbbbacdefbg";
		char[] arr = str.toCharArray();
		int longestSubstr = longestSubstr(arr);
		System.out.println(longestSubstr);
	}

	private static int longestSubstr(char[] arr) {
		Set<Character> set = new HashSet<>();
		int i = 0, j = 0;
		int max = 0;
		for (; j < arr.length; j++) {
			if (set.contains(arr[j])) {
				if (set.size() > max) {
					max = set.size();
				}
				while (arr[i] != arr[j]) {
					set.remove(arr[i]);
					i++;
				}
				set.remove(arr[i]);
			} else {
				set.add(arr[j]);
			}
		}

		return set.size() > max ? set.size() : max;
	}
}
