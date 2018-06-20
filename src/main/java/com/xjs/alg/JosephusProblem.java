package com.xjs.alg;

public class JosephusProblem {
	public static void main(String[] args) {
		Integer n = 15;
		System.out.println(Integer.toBinaryString(n));
		System.out.println((n >>> (Integer.toBinaryString(15).length()-1)) << (Integer.toBinaryString(15).length()-1) );
	}
}
