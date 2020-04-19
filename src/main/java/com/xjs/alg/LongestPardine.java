package com.xjs.alg;

import org.junit.Test;

public class LongestPardine {

	@Test
	public void testLongestPardine() {
//		String s = "aleelee";
		String s = "babcbabcbaccba";
		System.out.println(longestPardine(s));
		System.out.println(longestPardine1(s));
	}

	private String longestPardine(String s) {
		String sn = "$#";
		for (int i = 0; i < s.length(); i++) {
			sn += s.charAt(i);
			sn += "#";
		}
		
		int[] p = new int[sn.length()];
		int prevCenter = 0, preDiam = 0, mx = 0, id = 0;
		//mx 当前以i为中心的回文的右边界
		//id 当前回文的中心
		//回文特性
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
	
	// 两个问题，只能处理奇数类型的回文
	private String longestPardine1(String s) {
		String sn = "$#";
		for (int i = 0; i < s.length(); i++) {
			sn += s.charAt(i);
			sn += "#";
		}
		sn = sn+";";
		int center = 0;
		int maxlen = 1;
		for (int i = 1; i < sn.length(); i++) {
			int right = i-1;
			int left = i+1;
			while (right >= 0 && left < sn.length()) {
				if (sn.charAt(right) == sn.charAt(left)) {
					right--;left++;
				} else {
					if (maxlen <= left-i) {
						maxlen =  left-i;
						center = i;
					}
					break;
				}
			}
		}
		return s.substring((center-maxlen)/2, (center-maxlen)/2+maxlen-1);
	}
}
