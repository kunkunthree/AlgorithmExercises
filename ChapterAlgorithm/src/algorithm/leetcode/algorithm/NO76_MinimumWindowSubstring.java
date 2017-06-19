package algorithm.leetcode.algorithm;
/*
 * hard
 * 76. Minimum Window Substring 
 *  Given a string S and a string T, find the minimum window in S which will contain 
 *  all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"

Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique 
minimum window in S. 
 */
public class NO76_MinimumWindowSubstring {
	//方法1：
	//滑动窗口法
	public String minWindow(String s, String t) {
        int[] hash = new int[256];
        for(char ch : t.toCharArray()){
            hash[ch-0]++;
        }
        int left = 0,count = t.length(),index,maxLength = s.length()+1;
        String result = "";
        for(int i = 0 ; i < s.length() ; i++){
            index = s.charAt(i) - 0;
            if(hash[index]-- > 0){
                count--;
            }
            while(count == 0){
                if(i + 1 - left < maxLength){
                    maxLength = i + 1 - left;
                    result = s.substring(left,i+1);
                }
                if(hash[s.charAt(left++) - 0]++ >= 0){
                    count++;
                }
            }
        }
        return result;
    }
	//方法2：
	//方法1的优化，只记录最短的字符串的头坐标和长度
	public String minWindow2(String s, String t) {
        int[] hash = new int[256];
        for(char ch : t.toCharArray()){
            hash[ch-0]++;
        }
        int left = 0,count = t.length(),index,minLength = s.length()+1,head = 0;
        String result = "";
        for(int i = 0 ; i < s.length() ; i++){
            index = s.charAt(i) - 0;
            if(hash[index]-- > 0){
                count--;
            }
            while(count == 0){
                if(i + 1 - left < minLength){
                    head = left;
                    minLength = i + 1 - left;
                }
                if(hash[s.charAt(left++) - 0]++ >= 0){
                    count++;
                }
            }
        }
        return minLength <= s.length() ? s.substring(head,head+minLength) : "";
    }
}
