package algorithm.leetcode.algorithm;
/*
 * hard
 * 115. Distinct Subsequences 
 *  Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) 
of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3. 
 */
public class NO115_DistinctSubsequences {
	public int numDistinct(String s, String t) {
        int m = s.length(),n = t.length();
        int[][] dp = new int[m+1][n+1];
        //dp[i+1][j+1]表示s[0,i]中能和t[0,j]匹配的子序列个数
        //任意字符串的子序列都有1个空字符串
        for(int i = 0 ; i <= m ; i++){
            dp[i][0] = 1;
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(s.charAt(i) == t.charAt(j)){
                	//如果不用s[i]和t[j]匹配，则用s[i-1]和t[j]可能匹配的情况,即增加dp[i][j+1]个
                    //如果用s[i]和t[j]匹配，则增加dp[i][j]个
                    dp[i+1][j+1] = dp[i][j] + dp[i][j+1];
                }else{
                    dp[i+1][j+1] = dp[i][j+1];
                }
            }
        }
        return dp[m][n];
    }
}
