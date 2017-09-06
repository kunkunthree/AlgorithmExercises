package algorithm.leetcode.algorithm;
/*
 * hard
 * 664. Strange Printer 
 *  There is a strange printer with the following two special requirements:
    1.	The printer can only print a sequence of the same character each time.
    2.	At each turn, the printer can print new characters starting from and ending at any places, 
    	and will cover the original existing characters.

Given a string consists of lower English letters only, 
your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".

Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, 
which will cover the existing character 'a'.

Hint: Length of the given string will not exceed 100.
 */
public class NO664_StrangePrinter {
	//方法1：
	//dp，动态规划
	//dp[i][j]表示打印i-j所需要的最少次数
	//当i>j时，dp[i][j]=0
	//当i==j时，dp[i][j]=0;
	//当i < j时，dp[i][j] = Math.min(j-i+1,dp[i+1[k-1]+dp[k][j])，i < k <= j 且s[k] == s[i]
	//						因为k和i在i+1和k-1的两边，k和i可以同时打印，所以可以忽略i
	public int strangePrinter(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i = 0 ; i < n ; i++){
            dp[i][i] = 1;
        }
        for(int i = n-1 ; i >= 0 ; i--){
            for(int j = i ; j < n ; j++){
                dp[i][j] = 1+dp[i+1][j];
                for(int k = i+1 ; k <= j ; k++){
                    if(s.charAt(i) == s.charAt(k)){
                        dp[i][j] = Math.min(dp[i][j],dp[i+1][k-1]+dp[k][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
