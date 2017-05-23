package algorithm.leetcode.algorithm;

/*
 * medium
 * 131. Palindrome Partitioning
 *  Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
 ["aa","b"],
 ["a","a","b"]
 ]

 */
import java.util.*;
public class NO131_PalindromePartitioning {
	public static void main(String[] args) {
		String s = "aab";
		System.out.println(partition(s));
	}
	public static List<List<String>> partition(String s) {
        List<List<String>> ll = new ArrayList<>();
        List<String> l = new ArrayList<>();
        backTrack(s,ll,l,0);
        return ll;
    }
    public static void backTrack(String s, List<List<String>> ll,List<String> l,int begin){
    	//如果递归遍历到了s.length()时，数组中存在有回文字符串，则符合全部子字符串为回文
        if(l.size() > 0 && begin >= s.length()){
            ll.add(new ArrayList<String>(l));
        }
        for(int i = begin ; i < s.length() ; i++){
            if(isPalindrome(s,begin,i)){
                l.add(s.substring(begin,i+1));
                backTrack(s,ll,l,i+1);
                l.remove(l.size()-1);
            }
        }
    }
    //s字符串中下标begin到下标end(包括)的子字符串是否为回文
    public static boolean isPalindrome(String s,int begin,int end){
        if(begin > end){
            return false;
        }
        while(begin < end){
            if(s.charAt(begin) != s.charAt(end)){
                return false;
            }
            begin++;end--;
        }
        return true;
    }
    
    //方法2：
    //迭代实现
    public List<List<String>> partition2(String s) {
        int length = s.length();
        List<List<String>>[] dp = new List[length+1];	//dp[i]表示0到i-1的字符串中所有子字符串都是回文的组合
        dp[0] = new ArrayList<List<String>>();
        dp[0].add(new ArrayList<String>());
        boolean[][] palindrome = new boolean[length][length];	//palindrome[i][j]表示i到j的子字符串是否时回文
        for(int i = 0 ; i < length ; i++){
            dp[i+1] = new ArrayList<List<String>>();
            for(int left = 0 ; left <= i ; left++){
                if(s.charAt(left) == s.charAt(i) 	//首尾字符相等
                		&& (i-left <= 2 	//如果是单个字符或者相邻字符相等或者3个字符中两端字符相等，即该字符串时回文
                				|| palindrome[left+1][i-1] == true)){	//如果left和i之间的字符串是回文，那么left到i的字符串是回文
                    palindrome[left][i] = true;
                    String str = s.substring(left,i+1);
                    for(List<String> l : dp[left]){
                        List<String> tmpL = new ArrayList<String>(l);
                        tmpL.add(str);
                        dp[i+1].add(tmpL);
                    }
                }
            }
        }
        return dp[length];
    }
}
