package algorithm.leetcode.algorithm;

/*
 * easy
 * 14. Longest Common Prefix 
 * Write a function to find the longest common prefix string amongst an array of strings. 
 */
import java.util.*;

public class NO14_LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int arrayLen = strs.length;
		int length = strs[0].length();
		int count = 0;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < arrayLen; j++) {
				if (i >= strs[j].length()
						|| strs[j].charAt(i) != strs[0].charAt(i)) {
					return result.toString();
				}
			}
			result.append(strs[0].charAt(i));
		}
		return result.toString();
	}
	//更优，先排序
	public String longestCommonPrefix2(String[] strs) {
		StringBuilder result = new StringBuilder();
		if (strs != null && strs.length > 0) {
			Arrays.sort(strs);
			char[] a = strs[0].toCharArray();
			char[] b = strs[strs.length - 1].toCharArray();
			for (int i = 0; i < a.length; i++) {
				if (b.length > i && b[i] == a[i]) {
					result.append(b[i]);
				} else {
					return result.toString();
				}
			}
		}
		return result.toString();
	}
}
