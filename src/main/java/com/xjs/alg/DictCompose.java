package com.xjs.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class DictCompose {

	private int n;
	private long rankSize = 1;
	public DictCompose(int n) {
		this.n = n;
		calRankSize();
	}
	
	private void calRankSize() {
		for (int i = n; i >= 1; i--) {
			rankSize *= i;
		}
	}

	public List<int[]> dictCompose() {
		List<int[]> result = Lists.newArrayList();
		int[] f = initFirstRank();
		result.add(f);
		// 如何知道还有剩余的排列 [排列为单调递减， 没有逆序对]
		// 如何证明单调递减的排列数据最大
//		for ( ; result.size() < rankSize; ) {
		for ( ; existReverse(result.get(result.size()-1)); ) {
			int[] c = result.get(result.size()-1);
			int[] d = deepcopy(c);
			int maxi = findMaxAscIndexFormRightToLeft(d);
			int minj =  findGTMaxiMinIndex(maxi, d);
			cmp(maxi, minj, d);
			sortAsc(maxi+1, d);
			result.add(d);
		}
		return result;
	}

	private boolean existReverse(int[] c) {
		for (int i = 0; i < n-1; i++) {
			if (c[i] < c[i+1]) 
				return true;
		}
		return false;
	}

	private void sortAsc(int i, int[] d) {
		List<Integer> rest = Lists.newArrayList();
		for (int k = i; k < n; k++) {
			rest.add(d[k]);
		}
		Collections.sort(rest);
		int index = i;
		for (Integer each : rest) {
			d[index] = each;
			index++;
		}
	}

	private int[] deepcopy(int[] c) {
		int[] d = new int[c.length];
		for (int i = 0; i < c.length; i++) {
			d[i] = c[i];
		}
		return d;
	}

	private void cmp(int maxi, int minj, int[] c) {
		int tmp = c[maxi];
		c[maxi] = c[minj];
		c[minj] = tmp;
	}

	private int findGTMaxiMinIndex(int maxi, int[] c) {
		List<Map<Integer, Integer>> rest = Lists.newArrayList();
		for (int k = maxi + 1 ; k < n; k++) {
			rest.add(ImmutableMap.of(c[k], k));
		}
		Collections.sort(rest, new Comparator<Map<Integer, Integer>>() {

			@Override
			public int compare(Map<Integer, Integer> o1, Map<Integer, Integer> o2) {
				return o1.keySet().iterator().next() - o2.keySet().iterator().next();
			}
		});
		int minj = 0;
		for (Map<Integer, Integer> each : rest) {
			if (c[maxi] < each.keySet().iterator().next()) {
				minj = each.values().iterator().next();
				break;
			}
		}
		return minj;
	}

	private int findMaxAscIndexFormRightToLeft(int[] d) {
		if (d == null) throw new RuntimeException();
		int l = d.length - 1;
		int i = l-1;
		while (d[i] > d[l] && i >= 0) {
			l--;
			i--;
		}
		return i;
	}

	private int[] initFirstRank() {
		int[] f = new int[n];
		for (int i = 0; i < n; i++) {
			f[i] = i+1;
		}
		return f;
	}
	
	public static void main(String[] args) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		List<int[]> dictComposes = new DictCompose(6).dictCompose();
		for (int[] each : dictComposes) {
			System.out.println(ToStringBuilder.reflectionToString(each, ToStringStyle.SIMPLE_STYLE));
		}
		System.err.println(dictComposes.size());
		System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}
}
