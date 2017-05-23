package algorithm.leetcode.algorithm;
/*
 * medium
 * 522. Longest Uncommon Subsequence II 
 *  Given a list of strings, you need to find the longest uncommon subsequence among them. 
 *  The longest uncommon subsequence is defined as the longest subsequence of one of these strings 
 *  and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters 
without changing the order of the remaining elements. Trivially, any string is a subsequence of itself 
and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. 
If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3

Note:

    1.	 	All the given strings' lengths will not exceed 10.
    2.		The length of the given list will be in the range of [2, 50].

 */
import java.util.*;
public class NO522_LongestUncommonSubsequenceII {
	public static void main(String[] args) {
		String[] strs = new String[]{"aaa","aaa","aa"};
		System.out.println(findLUSlength(strs));
		String s;
	}
	//方法1：
	//暴力破解，完全遍历，判断当前字符串是否是其他字符串的子序列，如果不是，则该字符串是一个非公共子序列
	//将该字符串长度与max比较，更新max
	public static int findLUSlength(String[] strs) {
		int max = -1,n = strs.length;
        for(int i = 0 ; i < n ; i++){
            int j = 0;
            while(j < n){
                if(i != j){
                    if(isSubsequence(strs[i],strs[j])){
                        break;
                    }
                }
                j++;
            }
            if(j == n){
                max = Math.max(max,strs[i].length());
            }
        }
        return max;
    }
    private static boolean isSubsequence(String s1,String s2){
        int i = 0,j = 0;
        while(i < s1.length() && j < s2.length()){
            while(j < s2.length() && s1.charAt(i) != s2.charAt(j)){
                j++;
            }
            if(j < s2.length()){
            	i++;
            	j++;
            }
        }
        return i == s1.length();
    }
    //方法2：
    //方法1的修改，先排序，长度从大到小排，相等长度的按字母序排
    //因为长的字符串肯定是短字符串的非公共子序列
    //相同长度的字符串只要内容不相等，那么这些字符串就互为非公共子序列
    //重复的字符串必然为公共子序列
    //那么只要找出最长的，与比它更长的重复字符串相比，如果该字符串都不是这些重复字符串的子序列
    //那么这个字符串就是最长非公共子序列
    public int findLUSlength2(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2);
                }
                return s2.length() - s1.length();
            }
        });
        int max = -1,n = strs.length;
        for(int i = 0 ; i < n ; i++){
            while(i < n && i-1 >= 0 && strs[i].equals(strs[i-1]) || i >= 0 && i+1 < n && strs[i].equals(strs[i+1])){
                i++;
            }
            int j = 0;
            while(j < i && i < n){
                if(i != j){
                    if(isSubsequence(strs[i],strs[j])){
                        break;
                    }
                }
                j++;
            }
            if(j == i){
                max = Math.max(max,strs[i].length());
            }
        }
        return max;
    }
    
    //方法3：
    //先得到每一个字符串的子序列集
    //用Map统计每一个字符串的子序列集中的子序列在所有子序列集中总共出现的次数
    //由于非公共子序列必然是只出现在数组中的某一个字符串，而不出现在其他字符串中
    //所以最大非公共子序列的出现次数必然是1，所以只要得到出现次数是1的字符串的最大长度即可
    public int findLUSlength3(String[] strs) {
        Map<String,Integer> map = new HashMap<>();
        int max = -1,n = strs.length;
        for(String s : strs){
            for(String subseq : getSubsequences(s)){
                if(!map.containsKey(subseq)){
                    map.put(subseq,0);
                }
                map.put(subseq,map.get(subseq)+1);
            }
        }
        for(String s : map.keySet()){
            if(map.get(s) == 1){
                max = Math.max(max,s.length());
            }
        }
        return max;
    }
    private Set<String> getSubsequences(String s){
        Set<String> result = new HashSet<>();
        if(s.length() == 0){
            result.add("");
            return result;
        }
        Set<String> sub = getSubsequences(s.substring(1));
        result.addAll(sub);
        for(String tmp : sub){
            result.add(s.charAt(0) + tmp);
        }
        return result;
    }
}
