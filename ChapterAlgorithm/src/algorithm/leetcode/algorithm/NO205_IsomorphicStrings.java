package algorithm.leetcode.algorithm;
/*
 * easy
 * 205. Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. 
 * No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
 */
import java.util.*;
public class NO205_IsomorphicStrings {
	public static void main(String[] args) {
		System.out.println(isIsomorphic2("abcdd", "abcdc"));
	}
    public static boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()){
            return false;
        }
        int length = s.length(),count = 0;
        Map<Character,Integer> map1 = new HashMap<Character,Integer>();
        Map<Character,Integer> map2 = new HashMap<Character,Integer>();
        char c1,c2;
        for(int i = 0 ; i < length ; i++){
            c1 = s.charAt(i);
            c2 = t.charAt(i);
            if(map1.containsKey(c1) && map2.containsKey(c2)){
                if(map1.get(c1) != map2.get(c2)){
                    return false;
                }
            }else if(!map1.containsKey(c1) && !map2.containsKey(c2)){
                count++;
                map1.put(c1,count);
                map2.put(c2,count);
            }else{
                return false;
            }
        }
        return true;
    }
    //不用map，用数组代替map，计算每个字母出现的次数，
    //当前位置的字母上一次出现的位置如果不一样则说明结构不同
    public static boolean isIsomorphic2(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
        }
        return true;
    }
}
