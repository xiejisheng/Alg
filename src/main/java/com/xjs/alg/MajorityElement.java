package com.xjs.alg;

public class MajorityElement {
	public static void main(String[] args) {
		int[] arr = new int[] {1,2,2,3,3,4,4,4,4,4,4,4,4,4,4,4,7,8,9,10,10};
		System.out.println(majorityElement(arr));
		System.out.println(longestSubArray(new int[] {8,2,7,1,5,3}));
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
	
	public static int longestSubArray(int[] a){
		int max = 0;
		int count = 1;
		for(int i = 1; i < a.length; i++){
			if(a[i] > a[i - 1]){
				count++;
				max = Math.max(max, count);
			}else{
				count = 1;
			}
		}
		return max;
	}

}
