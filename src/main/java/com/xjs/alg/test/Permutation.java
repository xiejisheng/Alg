package com.xjs.alg.test;

import java.util.List;

import avro.shaded.com.google.common.collect.Lists;

public class Permutation {
	public static void main(String[] args) {
		String input = "()()()";
		char[] arr = input.toCharArray();
		List<String> res = Lists.newArrayList();
		int index = 0;
		StringBuilder sb = new StringBuilder();

		permutation1(arr, index, res);
//		compose1(arr, sb, index, res);
		System.out.println(res);
	}

	private static void compose1(char[] arr, StringBuilder sb, int index, List<String> res) {
		res.add(sb.toString());

		for (int i = index; i < arr.length; i++) {
			sb.append(arr[i]);
			compose(arr, sb, i + 1, res);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private static void compose(char[] arr, StringBuilder sb, int index, List<String> res) {
		if (index == arr.length) {
			res.add(sb.toString());
//			sb.setLength(0);
			return;
		}

		sb.append(arr[index]);
		compose(arr, sb, index + 1, res);
		sb.deleteCharAt(sb.length() - 1);
		compose(arr, sb, index + 1, res);
	}

	private static void permutation(char[] arr, StringBuilder sb, int index, List<String> res) {
		if (index == arr.length) {
			res.add(sb.toString());
//			sb.setLength(0);
			return;
		}

		for (int i = index; i < arr.length; i++) {
			sb.append(arr[i]);
			swap(arr, i, index);
			permutation(arr, sb, index + 1, res);
			swap(arr, i, index);
			sb.deleteCharAt(index);
		}
	}
	
	private static void permutation1(char[] arr, int index, List<String> res) {
		if (index == arr.length) {
			res.add(new String(arr));
//			sb.setLength(0);
			return;
		}

		for (int i = index; i < arr.length; i++) {
//			sb.append(arr[i]);
			swap(arr, i, index);
			permutation1(arr, index + 1, res);
			swap(arr, i, index);
//			sb.deleteCharAt(index);
		}
	}

	private static void swap(char[] arr, int i, int index) {
		char tmp = arr[i];
		arr[i] = arr[index];
		arr[index] = tmp;
	}

}
