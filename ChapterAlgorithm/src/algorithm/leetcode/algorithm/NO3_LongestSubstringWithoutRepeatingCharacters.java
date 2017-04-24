package algorithm.leetcode.algorithm;
import java.io.InputStreamReader;
/*
 * medium
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.
Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, 
"pwke" is a subsequence and not a substring. 
 */
import java.util.*;
public class NO3_LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		while(in.hasNext()){
			s = in.next();
			System.out.println(lengthOfLongestSubstring(s));
		}
	}
	public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        int length = s.length();
        int sum = 1;
        int max = 1;
        int tmpI;
        int start = 0;
        map.put(s.charAt(0), 0);
        for(int i = 1 ; i < length ; i++){
            if(map.containsKey(s.charAt(i))){
                if(sum > max){
                    max = sum;
                }
                tmpI = map.get(s.charAt(i));
                while(start <= tmpI){
                    map.remove(s.charAt(start));
                    start++;
                }
                map.put(s.charAt(i),i);
                sum = map.size();
            }else{
                sum++;
                map.put(s.charAt(i),i);
            }
        }
        if(sum > max){
            max = sum;
        }
        return max;
	}
	//better way：记录最新字符串的开始位置，把每次把字符和位置放入map中，
	//如果该字符在map中已存在，那么更新字符串开始的位置，看该字符之前的位置是否在开始位置之后，
	//若是，则将其设置为开始位置，否则不更新开始位置。
	   public static int lengthOfLongestSubstring2(String s) {
	        if (s.length()==0) return 0;
	        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	        int max=0;
	        for (int i=0, j=0; i<s.length(); ++i){
	            if (map.containsKey(s.charAt(i))){
	                j = Math.max(j,map.get(s.charAt(i))+1);
	            }
	            map.put(s.charAt(i),i);
	            max = Math.max(max,i-j+1);
	        }
	        return max;
	    }
}
