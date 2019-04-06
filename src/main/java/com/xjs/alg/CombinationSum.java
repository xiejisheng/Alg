package com.xjs.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	public static void main(String[] args) {
		int[] candidates = new int[] { 2, 3, 6, 7 };
		int target = 7;
		System.out.println(combinationSum(candidates, target));
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(candidates);
		backtrack(list, new ArrayList<>(), candidates, target, 0);

		return list;
	}

	public static boolean backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain,
			int start) {
		if (remain < 0) // if remain is 0 or less than 0, meaning the rest
						// numbers are even greater
			return false; // therefore, no need to continue the loop, return
							// false
		else if (remain == 0) {
			list.add(new ArrayList<>(tempList));
			return false;
		} else {
			for (int i = start; i < nums.length; i++) {
				boolean flag;
				tempList.add(nums[i]);
				flag = backtrack(list, tempList, nums, remain - nums[i], i); // not
																				// i
																				// +
																				// 1
																				// because
																				// we
																				// can
																				// use
																				// same
																				// number.
				tempList.remove(tempList.size() - 1);

				if (!flag) // if find a sum or fail to find a sum, there is no
							// need to continue
					break;// because it is a sorted array with no duplicates,
							// the rest numbers are even greater.
			}

			return true; // return true because previous tempList didn't find a
							// sum or fail a sum
		}
	}
}