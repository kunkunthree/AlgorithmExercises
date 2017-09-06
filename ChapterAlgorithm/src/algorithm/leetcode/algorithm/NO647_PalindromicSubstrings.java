package algorithm.leetcode.algorithm;
/*
 * medium
 * 647. Palindromic Substrings 
 *  Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings 
even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Note:
    1.	The input string length won't exceed 1000.

similar problems：
647. Palindromic Substrings 
516. Longest Palindromic Subsequence 
5. Longest Palindromic Substring 
 */
public class NO647_PalindromicSubstrings {
	//方法1：
	//O(n^2)time,O(n^2)space
	//dp，动态规划,迭代
	//用一个二维数组保存子字符串是否为回文字符串的状态
	public int countSubstrings(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = i ; j >= 0 ; j--){
                if(i == j || j+1 == i && s.charAt(i) == s.charAt(j)){
                    dp[j][i] = true;
                    count++;
                }else if(dp[j+1][i-1] == true && s.charAt(i) == s.charAt(j)){
                    dp[j][i] = true;
                    count++;
                }
            }
        }
        return count;
    }
	//方法2：
	//递归，O(n^2)time,O(1)space
	int count = 0;
    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) return 0;
        
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return count;
    }
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }
}
