package algorithm.leetcode.algorithm;
/*
 * hard
 * 10. Regular Expression Matching 
 * Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

 */
public class NO10_RegularExpressionMatching {
	public static void main(String[] args) {
		String s = "a";
		String p = "ab*";
		System.out.println(isMatch(s, p));
	}
	//方法1：
	//dp，动态规划
	//0. If p.charAt(i) == '*' && dp[0][i-1] = true :		dp[0][i+1] = true;
	//1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	//2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	//3, If p.charAt(j) == '*': 
	//   here are two sub conditions:
   //				1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
   //				2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
   //				              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
   //							or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
   //							or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	public static boolean isMatch(String s, String p) {
        int m = s.length(),n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i = 0 ; i < n ; i++){
            if(p.charAt(i) == '*' && dp[0][i-1]){
                dp[0][i+1] = true;
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
                    dp[i+1][j+1] = dp[i][j];
                }else if(p.charAt(j) == '*'){
                    if(s.charAt(i) != p.charAt(j-1) && p.charAt(j-1) != '.'){
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }else{
                        dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j] || dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[m][n];
    }
	//方法2：
	//递归
	public boolean isMatch2(String s, String p) {
        return isMatch(s,p,0,0);
    }
    private boolean isMatch(String s, String p , int i,int j){
    	//匹配字符完毕
        if(j == p.length()){
        	//判断是否匹配完全
            return i == s.length();
        }
        //遇到带*字符
        if(j+1 < p.length() && p.charAt(j+1) == '*'){
        	//匹配0个带*字符
            if(isMatch(s,p,i,j+2)){
                return true;
            }
            //匹配所有1个或多个带*字符
            while(i < s.length() && isMatchChar(s.charAt(i),p.charAt(j))){
                if(isMatch(s,p,i+1,j+2)){
                    return true;
                }
                i++;
            }
        }else if(i < s.length() && isMatchChar(s.charAt(i),p.charAt(j))){	//与非带*字符匹配
            return isMatch(s,p,i+1,j+1);
        }
        //不匹配
        return false;
    }
    private boolean isMatchChar(char c1,char c2){
        if(c1 == '.' || c2 == '.'){
            return true;
        }
        return c1 == c2;
    }
}
