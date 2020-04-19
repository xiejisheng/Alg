package com.xjs.alg.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IntersectionOfTwoArrayII {
	
	@Test
	public int[] solution(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		List<Integer> res = new ArrayList<>();
		for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
			if (nums1[i] == nums2[j]) {
				res.add(nums1[i]);
				i++; j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		int[] arr = new int[res.size()];
		for (int i = 0; i < res.size(); i++) {
			arr[i] = res.get(i);
		}
		return arr;
	}

}
