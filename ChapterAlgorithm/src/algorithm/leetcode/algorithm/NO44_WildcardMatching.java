package algorithm.leetcode.algorithm;
/*
 * hard
 * 44. Wildcard Matching 
 * Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

similar problem: 
 */
public class NO44_WildcardMatching {
	//方法1：
	//dp，动态规划
	//0. If p.charAt(i) == '*' && dp[0][i-1] = true :		dp[0][i+1] = true;
	//1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	//2, If p.charAt(j) == '?' : dp[i][j] = dp[i-1][j-1];
	//3, If p.charAt(j) == '*': dp[i][j] = dp[i-1][j] || dp[i][j-1] || dp[i][j] = dp[i-1][j-1];
   //				              dp[i][j] = dp[i-1][j]    //in this case, * counts as multiple characters 
   //							or dp[i][j] = dp[i][j-1]   // in this case, * counts as empty
   //							or dp[i][j] = dp[i-1][j-1]   // in this case, * counts as one character
	public boolean isMatch(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i = 0 ; i < n ; i++){
            if(p.charAt(i) == '*')dp[0][i+1] = dp[0][i];
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    dp[i+1][j+1] = dp[i][j];
                }else if(p.charAt(j) == '*'){
                    dp[i+1][j+1] = dp[i][j] || dp[i+1][j] || dp[i][j+1];
                }
            }
        }
        return dp[m][n];
    }
}
