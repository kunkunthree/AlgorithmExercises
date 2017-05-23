package algorithm.leetcode.algorithm;
/*
 * medium
 * 395. Longest Substring with At Least K Repeating Characters
 *  Find the length of the longest substring T of a given string (consists of lowercase letters only) 
 *  such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2
Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

 */
public class NO395_LongestSubstringwithAtLeastKRepeatingCharacters {
	//方法1：
	//递归，用当前字符串中存在且小于k次的字符把当前字符串分割开来，先从整个字符串开始，逐渐分割，
	//获取符合题意的字符串的最大值
	public int longestSubstring(String s, int k) {
        return helper(s,0,s.length(),k);
    }
    private int helper(String s,int start,int end,int k){
        int[] c = new int[26];
        for(int i = start ; i < end ; i++){
            c[s.charAt(i)-'a']++;
        }
        for(int i = 0 ; i < 26 ; i++){
            if(c[i] < k && c [i] > 0){  //存在且小于k次的字符
                for(int j = start ; j < end ; j++){
                    if(s.charAt(j) == i+'a'){   //用字符串中存在且小于k次的字符把字符串分割开来
                        int left = helper(s,start,j,k);
                        int right = helper(s,j+1,end,k);
                        return Math.max(left,right);
                    }
                }
            }
        }
        return end - start;
    }
    //方法2：
    //迭代，用两个指针表示字符串的头和尾，通过mask标识当前字符串是否符合所有字符个数大于等于k
    //当mask = 0,时，表示符合，当mask！=0时，表示不符合
    //mask中的从右到左1~26位，分别对应26个英文字母，
    //当某一位为1时，则表示该位字母存在且出现次数小于k
    //当某一位为0时，则表示该位字母不存在或出现次数大于等于k
    //所以只有当26位都为0时，才符合所有存在的字母的出现次数都大于等于k
}
