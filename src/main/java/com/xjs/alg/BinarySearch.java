package com.xjs.alg;

public class BinarySearch {
	public static void main(String[] args) {
		// 12233
		int[] arr = new int[]{1,2,2,4,4,4,4,4,5,6,7,7};
		int target = 4;
		int index = binarySearch(arr, target);
		System.out.println(index);
	}

	private static int binarySearch(int[] arr, int target) {
		int low = 0;
		int high = arr.length-1;
		int mid = low + (high-low)/2;
		while (low < high-1) {
			if (arr[mid] == target) {
				high = mid;
			} else if (arr[mid] > target) {
				high = mid-1;
			} else {
				low = mid+1;
			}
			mid = low + (high-low)/2;
		}
		if(arr[high] == target){
			return high;
		}else if(arr[low] == target){
			return low;
		}
//		if(arr[low] == target){
//			return low;
//		}else if(arr[high] == target){
//			return high;
//		}
		return -1;
	}
}
