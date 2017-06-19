package algorithm.leetcode.algorithm;
/*
 * hard
 * 214. Shortest Palindrome 
 *  Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
 *  Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".
 */
public class NO214_ShortestPalindrome {
	public static void main(String[] args) {
		String s ="aaaaaaaa";
		System.out.println(shortestPalindrome(s));
	}
	//方法1：
	//暴力破解法，找出以0开始的最长的回文子字符串
	//O(n^2)time，效率太低，不可取
	public static String shortestPalindrome(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        int left,right;
        StringBuilder result = new StringBuilder();
        for(int i = s.length()-1 ; i > 0 ; i--){
            left = 0;
            right = i;
            while(left < right){
                if(s.charAt(right) == s.charAt(left)){
                    left++;
                    right--;
                }else{
                    break;
                }
            }
            if(right <= left){
                result.append(s.substring(i+1,s.length()));
                result = result.reverse();
                result.append(s);
                return result.toString();
            }
        }
        result.append(s.substring(1,s.length()));
        result = result.reverse();
        result.append(s);
        return result.toString();
    }
	//方法2：
	//kmp字符串匹配算法：
	//先构造一个字符串，s+"#"+s.reverse()，这样可以求出s的前缀和s.reverse()的后缀的最大公共长度
	//再对这个字符串求kmp的table
	//那么就能table最后一项就是以0开始的最长的回文子字符串的长度
	//最后就直接构造回文字符串即可
	//O(n)time
	public String shortestPalindrome2(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        String tmp = s+"#"+ new StringBuilder(s).reverse();
        int[] table = getTable(tmp);
        return new StringBuilder(s.substring(table[table.length-1])).reverse().toString() + s;
    }
    private int[] getTable(String s){
        int[] table = new int[s.length()];
        table[0] = 0;
        int j = 0;
        for(int i = 1 ; i < s.length() ; i++){
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = table[j-1];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            table[i] = j;
        }
        return table;
    }
}
