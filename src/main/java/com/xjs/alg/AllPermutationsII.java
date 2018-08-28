package com.xjs.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AllPermutationsII {
	public static void main(String[] args) {
		int index = 0;
		char[] arr = "abc".toCharArray();
		List<String> result = new ArrayList<>();
		dfs(index, arr, result);
		System.out.println(new HashSet<>(result));
	}
	
	public static void dfs(int index, char[] arr, List<String> result){
		// Terminate
		if(index == arr.length - 1){
			result.add(new String(arr));
			System.out.println(result);
			return;
		}
		/**
		    			abc
		   			/
		  	a(bc)
		  /      \
		ab(c)    ac(b)
		 |		  |
		abc		 acb
		 */
		// Recursion Rule <0,1,2>
		for(int i = index; i < arr.length; i++){
			swap(arr, index, i);
			dfs(index + 1, arr, result);
			swap(arr, index, i);
		}
	}

	public static void swap(char[] arr, int index, int i) {
		char tmp = arr[index];
		arr[index] = arr[i];
		arr[i] = tmp;
	}
	
}
