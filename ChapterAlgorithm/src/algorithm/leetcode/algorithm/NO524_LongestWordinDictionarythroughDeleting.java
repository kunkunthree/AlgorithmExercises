package algorithm.leetcode.algorithm;
/*
 * medium
 * 524. Longest Word in Dictionary through Deleting 
 *  Given a string and a string dictionary, find the longest string in the dictionary
 *   that can be formed by deleting some characters of the given string.
 *    If there are more than one possible results, return the longest word with the smallest lexicographical order.
 *     If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]
Output: 
"apple"

Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]
Output: 
"a"

Note:
    1.		All the strings in the input will only contain lower-case letters.
    2.		The size of the dictionary won't exceed 1,000.
    3.		The length of all the strings in the input won't exceed 1,000.

 */
import java.util.*;
public class NO524_LongestWordinDictionarythroughDeleting {
	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> d= Arrays.asList("ale","apple","monkey","plea");
		System.out.println(findLongestWord(s, d));
	}
	//方法1：
	//直接法，判断ds中每一个字符串是否时s的子序列，是则与当前result比较长度和字母序
	public static String findLongestWord(String s, List<String> d) {
        String result = "";
        for(String ds : d){
            if(isSubsequence(ds,s)){
                if(result.length() < ds.length() 
                    || result.length() == ds.length() && ds.compareTo(result) < 0){
                        result = ds;
                }
            }
        }
        return result;
    }
    public static boolean isSubsequence(String s1,String s2){
        int m = s1.length(),n = s2.length(),i = 0,j = 0;
        while(i < m && j < n){
            while(j < n && s1.charAt(i) != s2.charAt(j)){
                j++;
            }
            if(j < n){
                i++;
                j++;
            }
        }
        return i == m;
    }
    //方法2：
    //先按长度从大到小排序，相同长度的按字母顺序排，然后找第一个子序列
    public String findLongestWord2(String s, List<String> d) {
        Collections.sort(d,new Comparator<String>(){
            public int compare(String s1,String s2){
                return s1.length() == s2.length() ? s1.compareTo(s2) : s2.length() - s1.length();
            }
        });
        for(String ds : d){
            if(isSubsequence(ds,s)){
                return ds;
            }
        }
        return "";
    }
}
