package com.xjs.alg;

import java.util.HashSet;

public class RemoveCertainCharacters {
	public static void main(String[] args) {
		String input = "abcd";
		String target = "ab";
		String removeCertainCharacters = removeCertainCharacters(input, target);
		System.out.println(removeCertainCharacters);
	}

	private static String removeCertainCharacters(String input, String target) {
		HashSet<Character> targetSet = new HashSet<>();
		char[] targetArr = target.toCharArray();
		for (char each : targetArr) {
			targetSet.add(each);
		}

		StringBuilder sb = new StringBuilder();
		char[] inputArr = input.toCharArray();
		for (char each : inputArr) {
			if (targetSet.contains(each))
				continue;
			sb.append(each);
		}
		return sb.toString();
	}
}
