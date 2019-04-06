package com.xjs.alg;

import org.junit.Test;

import com.google.common.primitives.Ints;

public class VarietySort {
	int[] a = new int[]{3, 2, 1, 5, 6, 4, 7, 0, 9};

	

	@Test
	public void insertSort() {
		insertSort(a);
		System.out.println(Ints.asList(a));
	}

	private void insertSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int v = a[i];
			int j = i;
			for (; j > 0 && v < a[j-1]; j--) {
				a[j] = a[j-1];
			}
			a[j] = v;
		}
	}
	
	@Test
	public void mergeSort() {
		mergeSort(a, 0, a.length-1);
		System.out.println(Ints.asList(a));
	}

	private void mergeSort(int[] a, int start, int end) {
		// corner case
		// divide
		// conqure
		if (a == null || a.length == 0) return;
		if (end <= start) return;
		
		int mid = (end-start)/2 + start;
		mergeSort(a, start, mid);
		mergeSort(a, mid+1, end);
		merge(a, start, end, mid);
	}

	private void merge(int[] a, int start, int end, int mid) {
		int[] b = new int[a.length];
		for (int k = start; k <= end; k++) {
			b[k] = a[k];
		}
		int i = start;
		int j = mid+1;
		for (int k = start; k <= end; k++) {
			if (i > mid) a[k] = b[j++];
			else if (j > end) a[k] = b[i++];
			else if (b[i] < b[j]) a[k] = b[i++];
			else a[k] = b[j++];
		}
	}
	
	@Test
	public void quickSort() {
		quickSort(a, 0, a.length-1);
		System.out.println(Ints.asList(a));
	}

	private void quickSort(int[] a, int start, int end) {
		// corner case
		if (end <= start) return;
		int p = a[end];
		int i = start-1;
		int j = end;
		while (true) {
			while (a[++i] < p) if (i == end) break;
			while (a[--j] > p) if (j == start) break;
			if (i >= j) break;
			swap(a, i, j);
		}
		swap(a, i, end);
		quickSort(a, start, i-1);
		quickSort(a, i+1, end);
	}

	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
