package com.xjs.alg.test;

import avro.shaded.com.google.common.primitives.Ints;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Kmp {
	public static void main(String[] args) {
		String input = "abcab";
		char[] arr = input.toCharArray();
		int[] res = new int[arr.length];
		kmp(arr, res);
		System.out.println(Ints.asList(res));
	}

	// 类回文 dp
	static void kmp(char[] b, int[] result) {
		for (int i = 1; i < b.length; i++) {
			int t = result[i - 1];
			while (t > 0 && b[i] != b[t])
				t = result[t - 1];
			if (b[i] == b[t])
				++t;
			result[i] = t;
		}
	}
}
