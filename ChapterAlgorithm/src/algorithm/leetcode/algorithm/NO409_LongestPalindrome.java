package algorithm.leetcode.algorithm;

/*
 * easy
 * Given a string which consists of lowercase or uppercase letters, 
 * find the length of the longest palindromes that can be built with those letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.

 */
import java.util.*;

public class NO409_LongestPalindrome {
	public int longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] c = new int[26];
		int[] C = new int[26];
		int add = 0, length = s.length(), result = 0;
		for (int i = 0; i < length; i++) {
			char a = s.charAt(i);
			if (a >= 'a' && a <= 'z') {
				c[a - 'a']++;
			}
			if (a >= 'A' && a <= 'Z') {
				C[a - 'A']++;
			}
		}
		for (int i = 0; i < 26; i++) {
			result += c[i] + C[i];
			if ((c[i] & 1) == 1) {
				result--;
				add = 1;
			}
			if ((C[i] & 1) == 1) {
				result--;
				add = 1;
			}
		}
		return result + add;
	}

	// 改进上面写法，通过判断最后长度与原来长度是否一致来确定是否加1，不一致说明有多余的字符则加1，一致则不加1
	public int longestPalindrome2(String s) {
		int[] lowercase = new int[26];
		int[] uppercase = new int[26];
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			char temp = s.charAt(i);
			if (temp >= 97)
				lowercase[temp - 'a']++;
			else
				uppercase[temp - 'A']++;
		}
		for (int i = 0; i < 26; i++) {
			res += (lowercase[i] / 2) * 2;
			res += (uppercase[i] / 2) * 2;
		}
		return res == s.length() ? res : res + 1;

	}

	// 利用HashSet来判断是否重复出现，重复出现后移除，最后判断HashSet是否为空来确定是否加1
	public int longestPalindrome3(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashSet<Character> hs = new HashSet<Character>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (hs.contains(s.charAt(i))) {
				hs.remove(s.charAt(i));
				count++;
			} else {
				hs.add(s.charAt(i));
			}
		}
		if (!hs.isEmpty())
			return count * 2 + 1;
		return count * 2;
	}
}
