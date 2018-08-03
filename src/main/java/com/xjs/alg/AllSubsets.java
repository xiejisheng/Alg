package com.xjs.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class AllSubsets {
	static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) {
		char[] set = "ABC".toCharArray();
		StringBuilder sb = new StringBuilder();
		List<String> result = new ArrayList<>();
		bfs(set, sb, 0 , result);
//		allSubsets.helperII(set, sb, 0, result);
		
		result.stream().forEach(x -> {
			System.out.print(x + " ");
		});
	}
	
	public static void  bfs(char[] set, StringBuilder sb, int index, List<String> result){
		if(index == set.length){
			result.add(sb.toString());
			return;
		}
		sb.append(set[index]);  // add
		bfs(set, sb, index + 1,result);
		sb.deleteCharAt(sb.length() - 1); // not add 
		bfs(set, sb, index + 1,result);	
	}
	
	public static void helperII(char[] set, StringBuilder sb, int index, List<String> result) {
		result.add(sb.toString());
		for (int i = index; i < set.length; i++) {
			sb.append(set[i]);
			System.out.println("序号：" + atomicInteger.getAndIncrement()
			+ ", sb：" + sb.toString()
			+ ", i：" + i
			+ ", set[i]：" + set[i]
			+ ", index：" + index
			+ ", result：" + Objects.toString(result));
			helperII(set, sb, i+1, result);
			sb.deleteCharAt(sb.length()-1);
		}
	}

}
