package algorithm.leetcode.algorithm;

/*
 * easy
 * 459. Repeated Substring Pattern
 * Given a non-empty string check if it can be constructed by taking a substring of it 
 * and appending multiple copies of the substring together.
 *  You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 Example 1:
 Input: "abab"
 Output: True

 Explanation: It's the substring "ab" twice.

 Example 2:
 Input: "aba"
 Output: False

 Example 3:
 Input: "abcabcabcabc"
 Output: True

 Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

 */
public class NO459_RepeatedSubstringPattern {
	public static void main(String[] args) {
		System.out.println(repeatedSubstringPattern("ab"));
	}

	public static boolean repeatedSubstringPattern(String s) {
		if (s == null || s.length() <= 1) {
			return false;
		}
		int count = 1;
		int i = count;
		while (i + count <= s.length()) {
			int j;
			for (j = 0; j < count; j++) {
				if (s.charAt(i + j) != s.charAt(j)) {
					break;
				}
			}
			if (j != count) {
				count++;
				i = count;
			} else {
				i += count;
			}
		}
		if (count == s.length() || s.length() % count != 0) {
			return false;
		} else {
			return true;
		}
	}

	// 其他解法
	// 从一半长度开始递减，寻找能够组成原字符串的子字符串
	public boolean repeatedSubstringPattern2(String str) {
		int l = str.length();
		for (int i = l / 2; i >= 1; i--) {
			if (l % i == 0) {
				int m = l / i;
				String subS = str.substring(0, i);
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < m; j++) {
					sb.append(subS);
				}
				if (sb.toString().equals(str))
					return true;
			}
		}
		return false;
	}

	// 其他解法
	public boolean repeatedSubstringPattern3(String str) {
		// This is the kmp issue
		int[] prefix = kmp(str);
		int len = prefix[str.length() - 1];
		int n = str.length();
		return (len > 0 && n % (n - len) == 0);
	}

	private int[] kmp(String s) {
		int len = s.length();
		int[] res = new int[len];
		char[] ch = s.toCharArray();
		int i = 0, j = 1;
		res[0] = 0;
		while (i < ch.length && j < ch.length) {
			if (ch[j] == ch[i]) {
				res[j] = i + 1;
				i++;
				j++;
			} else {
				if (i == 0) {
					res[j] = 0;
					j++;
				} else {
					i = res[i - 1];
				}
			}
		}
		return res;
	}
}
