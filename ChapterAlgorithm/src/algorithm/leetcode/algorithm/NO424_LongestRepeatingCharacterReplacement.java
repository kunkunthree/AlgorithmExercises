package algorithm.leetcode.algorithm;
/*
 * medium
 * 424. Longest Repeating Character Replacement
 * Given a string that consists of only uppercase English letters, you can replace any letter 
 * in the string with another letter at most k times. 
 * Find the length of a longest substring containing all repeating letters you can get 
 * after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:
Input:
s = "ABAB", k = 2
Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input:
s = "AABABBA", k = 1
Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

 */
public class NO424_LongestRepeatingCharacterReplacement {
	//方法1：
	//滑动窗口法：
	//设置滑动窗口的条件，
	//当满足条件时向右扩展，扩展后检查是否满足滑动窗口的条件，否则窗口左侧缩减
	//缩减直到满足滑动窗口的条件为止，继续向右扩展，每一次扩展后都要检查是否满足滑动窗口的条件
	//本题的条件是，窗口内出现最多的字符的个数maxCount不能小于滑动窗口的大小length减去k
	//即滑动窗口的大小length <= maxCount + k
	public int characterReplacement(String s, int k) {
        int maxLength = 0,maxCount = 0;
        int start = 0,n = s.length();
        int[] count = new int[26];
        for(int end = 0 ; end < n ; end++){
            maxCount = Math.max(maxCount,++count[s.charAt(end)-'A']);
            while(end - start + 1 - maxCount > k){
                count[s.charAt(start++)-'A']--;
            }
            maxLength = Math.max(maxLength,end-start+1);
        }
        return maxLength;
    }
}
