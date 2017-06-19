package algorithm.leetcode.algorithm;
/*
 * hard
 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false. 
 */
public class NO97_InterleavingString {
	//方法1：
	//dp，动态规划,O(m*n)time,O(m*n)space
	public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(),len2 = s2.length(),len3 = s3.length();
        if(len1 + len2 != len3){
            return false;
        }
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for(int i = 0 ; i < len1 ; i++){
            if(s1.charAt(i) == s3.charAt(i)){
                dp[i+1][0] = dp[i][0];
            }
        }
        for(int j = 0 ; j < len2 ; j++){
            if(s2.charAt(j) == s3.charAt(j)){
                dp[0][j+1] = dp[0][j];
            }
        }
        for(int i = 0 ; i < len1 ; i++){
            for(int j = 0 ; j < len2 ; j++){
                if(s1.charAt(i) == s3.charAt(i+j+1)){
                    dp[i+1][j+1] |= dp[i][j+1];
                }
                if(s2.charAt(j) == s3.charAt(i+j+1)){
                    dp[i+1][j+1] |= dp[i+1][j];
                }
            }
        }
        return dp[len1][len2];
    }
	//方法1.1：
	//方法1的简化写法
	public boolean isInterleave1(String s1, String s2, String s3) {
        int len1 = s1.length(),len2 = s2.length(),len3 = s3.length();
        if(len1 + len2 != len3){
            return false;
        }
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for(int i = 0 ; i <= len1 ; i++){
            for(int j = 0 ; j <= len2 ; j++){
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[len1][len2];
    }
	//方法2：
	//递归，剪枝，用一个boolean型二维数组记录已经遍历过的路径
	//O(m*n)time,O(m*n)space
	public boolean isInterleave2(String s1, String s2, String s3) {
        boolean[][] visited = new boolean[s1.length()+1][s2.length()+1];
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        return isInterleave(s1,0,s2,0,s3,0,visited);
    }
    private boolean isInterleave(String s1,int index1,String s2,int index2,String s3,int index3,boolean[][] visited){
        if(index3 == s3.length()){
            return true;
        }
        if(visited[index1][index2] == true){
            return false;
        }
        if(index1 < s1.length() && s1.charAt(index1) == s3.charAt(index3) && isInterleave(s1,index1+1,s2,index2,s3,index3+1,visited)){
            return true;
        }
        if(index2 < s2.length() && s2.charAt(index2) == s3.charAt(index3) && isInterleave(s1,index1,s2,index2+1,s3,index3+1,visited)){
            return true;
        }
        visited[index1][index2] = true;
        return false;
    }
}
