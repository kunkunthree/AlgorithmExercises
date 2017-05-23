package algorithm.leetcode.algorithm;
/*
 * medium
 * 567. Permutation in String 
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 *  In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Note:
    1.	The input strings only contain lower case letters.
    2.	The length of both given strings is in range [1, 10,000].

 */
public class NO567_PermutationinString {
	//滑动窗口法
	//判断字符串s1的字符组成的序列,是否是字符串s2的子字符串，设s1.length() = n
	//只需要用一个长度为n的窗口，当读到某一个下标为i的字符串时，
	//统计下标i的前n个字符含有的s1中字符的个数是否为n
	public boolean checkInclusion(String s1, String s2) {
		if(s1.length() > s2.length()){
            return false;
        }
        int left = 0,right = 0,count = s1.length();
        int[] hash = new int[26];
        for(char c : s1.toCharArray()){
            hash[c-'a']++;
        }
        for(int i = 0;i < s2.length() ; i++){
            if(hash[s2.charAt(i)-'a']-- > 0){
                count--;
            }
            if(count == 0){
                return true;
            }
            //当i+1超过s1.length()时，则要进行窗口的移动，窗口左端右移一个字符，把该字符删除
            if(i+1 >= s1.length() && hash[s2.charAt(i+1-s1.length())-'a']++ >= 0){
                count++;
            }
        }
        return false;
    }
}
