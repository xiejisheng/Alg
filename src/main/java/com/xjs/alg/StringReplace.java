package com.xjs.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringReplace {
	public static void main(String[] args) {
		String pattern = "apple";
		char[] patternArr = pattern.toCharArray();
		Map<String, Integer> knuth = knuth(pattern);
		String target = "applecatappleqweqweqappleqweqwercs";
		char[] targetArr = target.toCharArray();
		List<Integer> matchIndex = new ArrayList<>();
		for (int i = 0, j = 0; i < targetArr.length; ) {
			if (j == pattern.length()-1) {
				matchIndex.add(i);
				j = 0;
			}
			if (patternArr[j] != targetArr[i]) {
				String patternSub = pattern.substring(0, j);
				if (patternSub.isEmpty())
					j = 0;
				else {
					Integer step = knuth.get(patternSub);
					j = step;
				}
			} else {
				j++;
			}
			i++;
		}
		if (!matchIndex.isEmpty()) {
			for (Integer each : matchIndex) {
				System.out.println(each);
				System.out.println(target.substring(each+1-pattern.length(), each+1));
			}
		}
	}

	private static Map<String, Integer> knuth(String pattern) {
		Map<String, Integer> backupModel = new LinkedHashMap<>();
		char[] arr = pattern.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			int backupStep = calBackupStep(sb.toString());
			backupModel.put(sb.toString(), backupStep);
		}
		return backupModel;
	}

	private static int calBackupStep(String str) {
		if (str.length() == 1)
			return 0;
		String prev = str.substring(0, str.length()-1);
		Set<String> prevSet = new HashSet<>();
		for (int i = 1; i <= prev.length(); i++) {
			prevSet.add(prev.substring(0, i));
		}
		
		String suffix = str.substring(1, str.length());
		Set<String> suffixSet = new HashSet<>();
		for (int i = 0; i < prev.length(); i++) {
			suffixSet.add(suffix.substring(i, suffix.length()));
		}
		
		int backupModel = 0;
		for (String each : prevSet) {
			if (each.equals(""))
				continue;
			if (suffixSet.contains(each)) {
				if (backupModel <= each.length()) {
					backupModel = each.length();
				}
			}
		}
		return backupModel;
	}
}
