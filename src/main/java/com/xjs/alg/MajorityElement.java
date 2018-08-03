package com.xjs.alg;

public class MajorityElement {
	public static void main(String[] args) {
		int[] arr = new int[] {1,2,2,3,3,4,4,4,4,4,4,4,4,4,4,4,7,8,9,10,10};
		System.out.println(majorityElement(arr));
	}

	public static int majorityElement(int[] num) {
		int major = num[0], count = 1;
		for (int i = 1; i < num.length; i++) {
			if (count == 0) {
				count++;
				major = num[i];
			} else if (major == num[i]) {
				count++;
			} else
				count--;

		}
		return major;
	}
}
