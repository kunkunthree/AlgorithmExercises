package algorithm.leetcode.algorithm;
/*
 * medium
 * 516. Longest Palindromic Subsequence
 *  Given a string s, find the longest palindromic subsequence's length in s. 
 *  You may assume that the maximum length of s is 1000.

Example 1:
Input:
"bbbab"
Output:
4

One possible longest palindromic subsequence is "bbbb".

Example 2:
Input:
"cbbd"
Output:
2

One possible longest palindromic subsequence is "bb". 
 */
public class NO516_LongestPalindromicSubsequence {
	//方法1：
	//dp，O(n^2)time,O(n^2)space
	//dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
	//State transition:
	//dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
	//otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
	//Initialization: dp[i][i] = 1 ，
	//dp[i][j] = 0 , i > j
	public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = n-1 ; i >= 0 ; i--){
            for(int j = i ; j < n ; j++){
                if(i == j){
                    dp[i][j] = 1;
                }else if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
	//方法2：
	//DFS
	public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return helper(s,dp,0,n-1);
    }
    private int helper(String s,int[][] dp,int start ,int end){
        if(start > end){
            return dp[start][end];
        }
        if(dp[start][end] > 0){
            return dp[start][end];
        }
        int result = 0;
        if(start == end){
            result = 1;
        }else if(s.charAt(start) == s.charAt(end)){
            result =  helper(s,dp,start+1,end-1)+2;
        }else{
            result = Math.max(helper(s,dp,start+1,end),helper(s,dp,start,end-1));
        }
        dp[start][end] = result;
        return result;
    }
}
