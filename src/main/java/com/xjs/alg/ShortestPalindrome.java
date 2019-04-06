package com.xjs.alg;

import java.util.HashMap;
import java.util.Map;

public class ShortestPalindrome {
	public static void main(String[] args) {
		String str = "abab"; // "dcbaabcd"
		System.out.println(str.substring(0, 0));
		System.out.println(str.substring(str.length()));
		String shortestPalindrome = shortestPalindrome(str);
		System.out.println(shortestPalindrome);
		Map<Integer, Integer> result = new HashMap<>();
		result.put(0, 0);
		kmp("cccacabc".toCharArray(), result);
		System.out.println(result);
		String reverse = reverse(str);
		String n_new = str + "#" + reverse;
		kmp(n_new.toCharArray(), result);
		System.out.println(result);
		System.out.println(reverse.substring(0, str.length() - result.get(n_new.length() - 1)) + str);
	}

	private static String shortestPalindrome(String str) {
		int n = str.length();
		String reverse = reverse(str);
		for (int i = 0; i < n; i++) {
			if (str.substring(0, n - i).equals(reverse.substring(i)))
				return reverse.substring(0, i) + str;
		}
		return "";
	}

	private static String reverse(String str) {
		char[] charArray = str.toCharArray();
		int i = 0;
		int j = charArray.length - 1;
		while (i <= j) {
			char tmp = charArray[i];
			charArray[i] = charArray[j];
			charArray[j] = tmp;
			i++;
			j--;
		}
		return String.valueOf(charArray);
	}

	static void kmp(char[] b, Map<Integer, Integer> result) {
		for (int i = 1; i < b.length; i++) {
			int t = result.get(i - 1);
			while (t > 0 && b[i] != b[t])
				t = result.get(t - 1);
			if (b[i] == b[t])
				++t;
			result.put(i, t);
		}
	}
}
