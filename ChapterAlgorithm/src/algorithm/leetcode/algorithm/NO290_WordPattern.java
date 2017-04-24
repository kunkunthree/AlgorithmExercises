package algorithm.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

/*
 * easy
 * 290. Word Pattern
 * Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern 
 and a non-empty word in str.

 Examples:

 pattern = "abba", str = "dog cat cat dog" should return true.
 pattern = "abba", str = "dog cat cat fish" should return false.
 pattern = "aaaa", str = "dog cat cat dog" should return false.
 pattern = "abba", str = "dog dog dog dog" should return false.

 Notes:
 You may assume pattern contains only lowercase letters, 
 and str contains lowercase letters separated by a single space. 
 */
public class NO290_WordPattern {
	public static void main(String[] args) {
		HashMap<Character, Boolean> map = new HashMap<Character, Boolean>();
		boolean tmp = map.get(' ');
		System.out.println(tmp);
		// String pattern = "abba";
		// String str = "dog cat cat fish";
		// System.out.println(wordPattern(pattern, str));
	}

	public static boolean wordPattern(String pattern, String str) {
		HashMap<Character, String> map = new HashMap<Character, String>();
		String[] ss = str.split(" ");
		int length = pattern.length();
		if (length != ss.length) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			char c = pattern.charAt(i);
			String s = map.get(c);
			if (s == null) {
				if (map.values().contains(ss[i])) {
					return false;
				}
				map.put(c, ss[i]);
			} else if (!s.equals(ss[i])) {
				return false;
			}
		}
		return true;
	}

	// 更好的方法，利用map.put()的返回值判断是否相等，返回的是上一个该key所赋的值，即该key在的位置是否一致
	public boolean wordPattern2(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length())
			return false;
		Map index = new HashMap();
		for (Integer i = 0; i < words.length; ++i)
			if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
				return false;
		return true;
	}
}
