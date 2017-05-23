package algorithm.leetcode.algorithm;
/*
 * medium
 * 583. Delete Operation for Two Strings 
 *  Given two words word1 and word2, find the minimum number of steps required 
 *  to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:

Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Note:
    1.		The length of given words won't exceed 500.
    2.		Characters in given words can only be lower-case letters.

 */
public class NO583_DeleteOperationforTwoStrings {
	public static void main(String[] args) {
		String word1 = "sea";
		String word2 = "eat";
		System.out.println(minDistance(word1, word2));
	}
	//方法1：
	//dp，求最长公共子序列长度LCS，则需要删除的字符个数为len1+len2-2*LCS
	public static int minDistance(String word1, String word2) {
        int len1 = word1.length(),len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 0 ; i <= len1 ; i++){
            for(int j = 0 ; j <= len2 ; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return len1+len2-2*dp[len1][len2];
    }
	//方法2：
	//dp，求最少需要删除的字符个数
	public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;
        
        // dp[i][j] stands for distance of first i chars of word1 and first j chars of word2
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        for (int j = 0; j <= len2; j++)
            dp[0][j] = j;
            
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))	//不删除字符
                    dp[i][j] = dp[i - 1][j - 1];
                else	//删除字符，删除1个或2个
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
            }
        }
        
        return dp[len1][len2];
    }
}
