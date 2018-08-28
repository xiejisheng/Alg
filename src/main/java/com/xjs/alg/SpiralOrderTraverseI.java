package com.xjs.alg;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseI {
	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		List<Integer> spiralOrder = spiralOrder(matrix);
		System.out.println(spiralOrder);
	}

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		int top = 0;
		int left = 0;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int right = cols - 1;
		int bottom = rows - 1;
		while (left < right-1 || top < bottom-1) {
			// top
			for (int i = left; i <= right; i++) {
				result.add(matrix[left][i]);
			}
			// right
			for (int i = top + 1; i <= bottom - 1; i++) {
				result.add(matrix[i][right]);
			}
			// bottom
			for (int i = right; i >= 0; i--) {
				result.add(matrix[bottom][i]);
			}
			// left
			for (int i = top + 1; i <= bottom - 1; i++) {
				result.add(matrix[i][left]);
			}
			left++;
			top++;
			right--;
			bottom--;
		}
		// one row
		if (top == bottom-1) {
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][left]);
			}
		}
		// one col
		if (left == right-1) {
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
		}
		return result;
	}
}
