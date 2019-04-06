package com.xjs.alg;

import org.junit.Test;

public class LongestPardine {

	@Test
	public void testLongestPardine() {
		String s = "aleelee";
		System.out.println(longestPardine(s));
	}

	private String longestPardine(String s) {
		String sn = "$#";
		for (int i = 0; i < s.length(); i++) {
			sn += s.charAt(i);
			sn += "#";
		}
		
		int[] p = new int[sn.length()];
		int prevCenter = 0, preDiam = 0, mx = 0, id = 0;
		for (int i = 1; i < sn.length(); i++) {
			p[i] = mx > i ? Math.min(p[id*2-i], mx-i) : 1;
			while (i+p[i] < sn.length() && sn.charAt(i+p[i]) == sn.charAt(i-p[i])) {
				++p[i];
			}
			
			if (preDiam < p[i]) {
				prevCenter = i;
				preDiam = p[i];
			}
			
			if (mx < i+p[i]) {
				mx = i+p[i];
				id = i;
			}
		}
		return s.substring((prevCenter-preDiam)/2, (prevCenter-preDiam)/2+preDiam-1);
	}
}
