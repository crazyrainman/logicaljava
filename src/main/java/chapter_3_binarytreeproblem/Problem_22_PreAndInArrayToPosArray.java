package chapter_3_binarytreeproblem;

import java.util.HashMap;

public class Problem_22_PreAndInArrayToPosArray {

	public static int[] getPosArray(int[] pre, int[] in) {
		if (pre == null || in == null) {
			return null;
		}
		int len = pre.length;
		int[] pos = new int[len];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			map.put(in[i], i);
		}
		genPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
		return pos;
	}

	public static int genPos(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd,
			int[] pos, int posCurr, HashMap<Integer, Integer> map) {
		if (preStart > preEnd) {
			return posCurr;
		}
		pos[posCurr--] = pre[preStart];
		int i = map.get(pre[preStart]);
		posCurr = genPos(pre, preEnd - inEnd + i + 1, preEnd, in, i + 1, inEnd, pos, posCurr, map);
		return genPos(pre, preStart + 1, preStart + i - inStart, in, inStart, i - 1, pos, posCurr, map);
	}

	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
		int[] in = { 4, 2, 5, 1, 6, 3, 7 };
		int[] pos = getPosArray(pre, in);
		printArray(pos);

	}
}
