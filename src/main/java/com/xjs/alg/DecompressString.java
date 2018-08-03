package com.xjs.alg;

public class DecompressString {
	public static void main(String[] args) {
		String compressStr = "a1c0b2c4";
		String decompressStr = decompress(compressStr);
		System.out.println(decompressStr);
	}

	private static String decompress(String compressStr) {
		if (compressStr == null || compressStr.length() == 0)
			return "";
		char[] arr = compressStr.toCharArray();
		StringBuilder sb = new StringBuilder(arr.length*9);
		for (int i = 0; i < arr.length; i += 2) {
			int repeat = arr[i+1] - '0';
			if (repeat == 0)
				continue;
			for (int j = 0; j < repeat; j++) {
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}
}
