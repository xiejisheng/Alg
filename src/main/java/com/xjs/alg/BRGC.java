package com.xjs.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * 二进制反射格雷码
 * 
 * @author xjs
 *
 */
public class BRGC {
	private int N;

	public BRGC(int n) {
		this.N = n;
	}

	public List<String> brgc() {
		return brgc(N);
	}

	private List<String> brgc(int n) {
		if (n == 1) {
			List<String> l0 = Lists.newArrayList();
			l0.add("0");
			l0.add("1");
			return l0;
		} else {
			List<String> l1 = brgc(n - 1);
			List<String> l2 = Lists.newArrayList();

			for (int i = 0; i < l1.size(); i++) {
				l2.add(l1.get(i));
				l1.set(i, Joiner.on("").join("0", l1.get(i)));
			}
			Collections.sort(l2, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1);
				}
			});
			for (int i = 0; i < l2.size(); i++) {
				l2.set(i, Joiner.on("").join("1", l2.get(i)));
			}
			List<String> l = Lists.newArrayList();
			l.addAll(l1);
			l.addAll(l2);
			return l;
		}
	}

	public static void main(String[] args) {
		List<String> brgc = new BRGC(4).brgc();
		for (String each : brgc) {
			System.out.println(each);
		}
		Queue<Map.Entry<String, Integer>> q = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {

		      @Override
		      public int compare(Map.Entry<String, Integer> o1,
		          Map.Entry<String, Integer> o2) {
		        return o1.getValue() - o2.getValue();
		      }
		    });
	}
}
