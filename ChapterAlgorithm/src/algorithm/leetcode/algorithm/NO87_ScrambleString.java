package algorithm.leetcode.algorithm;
/*
 * hard
 * 87. Scramble String 
 *  Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t

To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t

We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a

We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. 
 */
public class NO87_ScrambleString {
	//方法1：
	//递归遍历
	public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)){
            return true;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        int[] count = new int[26];
        int n = s1.length();
        for(int i = 0 ; i < n ; i++){
            count[s1.charAt(i)-'a']++;
            count[s2.charAt(i)-'a']--;
        }
        for(int i = 0 ; i < 26 ; i++){
            if(count[i] != 0){
                return false;
            }
        }
        for(int i = 1 ; i <= n-1 ; i++){
            if(isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))){
                return true;
            }
            if(isScramble(s1.substring(0,i),s2.substring(n-i)) && isScramble(s1.substring(i),s2.substring(0,n-i))){
                return true;
            }
        }
        return false;
    }
	//方法2：
	//dp，动态规划
	//dp[i][j][k] 表示isScramble2( s1.substring(i,i+k) , s2.substring(j,j+k) )
	//k == 1, dp[i][j][k] = s1.charAt(i) == s2.charAt(j);
	//for some 1 <= q < k we have:
	//dp[i][j][k] = dp[i][j][q] && dp[i+q][j+q][k-q] || dp[i][j+k-q][q] && dp[i+q][j][k-q];
	public boolean isScramble2(String s1, String s2) {
        if(s1.equals(s2)){
            return true;
        }
        if(s1.length() != s2.length()){
            return false;
        }
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n+1];
        for(int k = 1 ; k <= n ; k++){
            for(int i = 0 ; i + k <= n ; i++){
                for(int j = 0 ; j + k <= n ; j++){
                    if(k == 1){
                        dp[i][j][k] = s1.charAt(i) == s2.charAt(j);
                    }else{
                        for(int q = 1 ; q < k && !dp[i][j][k] ; q++){
                            dp[i][j][k] = dp[i][j][q] && dp[i+q][j+q][k-q] || dp[i][j+k-q][q] && dp[i+q][j][k-q];
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
