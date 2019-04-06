package com.xjs.alg;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

public class NaturalCompare {
	public static void main(String[] args) {
		String a = "23";
		String b = "34";
		// Set<Character> digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
		// "9"};
		Set<Character> digits = new HashSet<>();
		digits.add('0');
		digits.add('1');
		digits.add('2');
		digits.add('3');
		digits.add('4');
		digits.add('5');
		digits.add('6');
		digits.add('7');
		digits.add('8');
		digits.add('9');
		int naturalCompare = naturalCompare(a, b, digits);
		System.out.println(naturalCompare);
	}

	@SuppressWarnings("deprecation")
	private static int naturalCompare(String a, String b, Set<Character> digits) {
		if (NumberUtils.isNumber(a) && NumberUtils.isNumber(b)) {
			return Integer.parseInt(a) - Integer.parseInt(b);
		} else if (NumberUtils.isNumber(a) && !NumberUtils.isNumber(b)) {
			return -1;
		} else if (!NumberUtils.isNumber(a) && NumberUtils.isNumber(b)) {
			return 1;
		} else {
			char[] arr = a.toCharArray();
			char[] brr = b.toCharArray();
			int sp = 0;
			int len = arr.length < brr.length ? arr.length : brr.length;
			for (int i = 0; i < len; i++) {
				if (!digits.contains(arr[i]) && !digits.contains(brr[i])) {
					if (arr[i] == brr[i]) {
						continue;
					} else {
						return (int) arr[i] - (int) brr[i];
					}
				} else if (!digits.contains(arr[i]) && digits.contains(brr[i])) {
					return 1;
				} else if (digits.contains(arr[i]) && !digits.contains(brr[i])) {
					return -1;
				} else {
					sp = i;
					break;
				}
			}
			if (sp == 0) {
				return arr.length < brr.length ? -1 : 1;
			}
			return Integer.parseInt(a.substring(sp)) - Integer.parseInt(b.substring(sp));
		}
	}
}
