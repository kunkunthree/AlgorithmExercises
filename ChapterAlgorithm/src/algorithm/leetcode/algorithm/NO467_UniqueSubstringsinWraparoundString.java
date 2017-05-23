package algorithm.leetcode.algorithm;
/*
 * medium
 * 467. Unique Substrings in Wraparound String 
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", 
 * so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. 
In particular, your input is the string p and you need to output the number of different non-empty substrings of p
 in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.

Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.

Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.

 */
public class NO467_UniqueSubstringsinWraparoundString {
	//方法1：
	//dp，动态规划 
	//每个字母只考虑以其结尾的最长连续字符串的长度，因为所有以该字母结尾的子字符串都包含在里面
	//如“abcdbcd”的d字母，以d字母结尾的最长子字符串为“abcd”，长度为4，“bcd”已经包含在这长度4里面
	//所以如果有重叠的子字符串，只考虑最长的子字符串长度
	//最后把所有字符的以其结尾的最长字符串长度相加起来，就是所有唯一的子字符串个数
	public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int length = p.length(),maxLength = 0;
        for(int i = 0 ; i < length ; i++){
            if(i > 0 && (p.charAt(i) - p.charAt(i-1) == 1 || p.charAt(i-1) - p.charAt(i) == 25)){
                maxLength++;
            }else{
                maxLength = 1;
            }
            int index = p.charAt(i)-'a';
            count[index] = Math.max(maxLength,count[index]);
        }
        int result = 0;
        for(int i = 0 ; i < 26 ; i++){
            result+=count[i];
        } 
        return result;
    }
}
