package algorithm.leetcode.algorithm;
/*
 * medium
 * 139. Word Break
 *  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 *  determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
 *  You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). 
Please reload the code definition to get the latest changes. 
 */
import java.util.*;
public class NO139_WordBreak {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("bb");
		list.add("b");
		list.add("c");
		String s = "abbc";
		System.out.println(wordBreak(s,list));
	}
	//方法1：
	//递归，如果字典中包含字符串s，则返回true，
	//否则对1到s.length()进行遍历，如果0到i(不包含i)的子字符串是
	//超时，不符合
	public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<String>(wordDict);
        return wordBreakHelper(s,set);
    }
    public static boolean wordBreakHelper(String s, Set<String> wordDict) {
        if(wordDict.contains(s) || s.length() == 0){
            return true;
        }
        for(int i = 1 ; i < s.length() ; i++){
            if(wordDict.contains(s.substring(0,i)) && wordBreakHelper(s.substring(i,s.length()),wordDict)){
                return true;
            }
        }
        return false;
    }
    //方法2：
    //动态规划，dp，O(n^2)space
    public boolean wordBreak2(String s, List<String> wordDict) {
        int length = s.length();
        boolean[][] dp = new boolean[length+1][length+1];//dp[i][j]表示s.substring(i,j)是否在wordDict中
        Set<String> set = new HashSet<String>(wordDict);
        for(int i = 0 ; i <= length ; i++){
            for(int j = 0 ; j <= i ; j++){
                if(i == j){
                    dp[i][j] = true;
                }else if(dp[0][j] == true && set.contains(s.substring(j,i))){
                    dp[0][i] = true;
                    break;
                }
            }
        }
        return dp[0][length];
    }
    
    //方法3：
    //动态规划，dp，O(n)space
    public boolean wordBreak3(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length+1];//dp[i][j]表示s.substring(i,j)是否在wordDict中
        dp[0] = true;
        Set<String> set = new HashSet<String>(wordDict);
        for(int i = 0 ; i <= length ; i++){
            for(int j = 0 ; j < i ; j++){
                if(dp[j] == true && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }
}
