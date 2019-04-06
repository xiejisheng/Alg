package com.xjs.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class LowestRangeCIDR {

	@Test
	public void ip2CIDR() {
		List<String> cidrs = ipToCIDR("255.0.0.8", 21);
		System.out.println(cidrs);
	}

	public List<String> ipToCIDR(String ip, int range) {
		List<String> res = new ArrayList<>();
		// 11111111 00000000 00000000 00000111
		// 255 * 256^3 + 0 * 256^2 + 0 * 256 + 7 * 1
		String[] strs = ip.split("\\.");
		long decimal = Integer.parseInt(strs[0]) * (256L << 16)
				+ Integer.parseInt(strs[1]) * (256L << 8) 
				+ Integer.parseInt(strs[2]) * (256L << 0) 
				+ Integer.parseInt(strs[3]);
		int lowbits = (int) (decimal & -decimal);
		int step = lowbits;
		int num = 0;
		while (num < range) {
			if (lowbits == 1) {
				res.add(longToIP(decimal, step));
				num++;
				decimal++;
			} else {
				int size  = lowbits;
				while (size > (range-num))
					size >>= 1;
				res.add(longToIP(decimal, size));
				int acc = range-num > size ? size : (range-num);
				decimal += acc;
				num += acc;
			}
			lowbits = (int) (decimal & -decimal);
		}
		
		return res;
	}
	
	public String longToIP(long x, int step) {
		int[] ans = new int[4];
		ans[0] = (int) (x & 255); x >>= 8;
		ans[1] = (int) (x & 255); x >>= 8;
		ans[2] = (int) (x & 255); x >>= 8;
		ans[3] = (int) x;
		int len = 32;  
		while (step > 1) {
			len --;
			step /= 2;
		}
		return ans[3] + "." + ans[2] + "." + ans[1] + "." + ans[0] + "/" + len;
}
	
	@Test
	public void testLowBits() {
		// 1000
		int lowbits = 5 & -5;
		System.out.println(lowbits);
		System.out.println(1 << (Integer.toBinaryString(lowbits).length()-1));
		System.out.println(Integer.highestOneBit(3));
		System.out.println(256L << 24);
		System.out.println(256L * 256 * 256 * 256);
		System.out.println(256L << 16);
		System.out.println(256L * 256 * 256);
		System.out.println(256 << 8);
		System.out.println(256L * 256);
		System.out.println(256 << 0);
		System.out.println(Integer.bitCount(2));
	}

	@Test
	public void solution() {
		Map<Integer, List<List<Integer>>> idAndGroup = Maps.newHashMap();
//		idAndGroup.put(0, 3);
//		idAndGroup.put(1, 3);
//		idAndGroup.put(2, 3);
//		idAndGroup.put(3, 3);
//		idAndGroup.put(4, 3);
//		idAndGroup.put(5, 1);
//		idAndGroup.put(6, 3);
//		ArrayListMultimap<Integer,Integer> invertFrom = Multimaps.invertFrom(Multimaps.forMap(idAndGroup), ArrayListMultimap.create());
//		TreeMap<Integer, Collection<Integer>> treeMap = new TreeMap<>(invertFrom.asMap());
//		NavigableMap<Integer,Collection<Integer>> descendingMap = treeMap.descendingMap();
		idAndGroup.compute(3, (k, v) -> v != null ? v : new ArrayList<>());
//		idAndGroup
//		.values()
//		.stream().flatMap(List::stream)
//		.sorted(Comparator.comparingInt((List<Integer> list) -> list.get(0)))
//		.map(list -> list.stream().map(Object::toString).collect(Collectors.joining(" ")))
//		.forEach(System.out::println);
	}
}
