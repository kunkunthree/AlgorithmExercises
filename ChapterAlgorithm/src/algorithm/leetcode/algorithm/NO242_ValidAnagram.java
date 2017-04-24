package algorithm.leetcode.algorithm;
/*
 * easy
 * 242. Valid Anagram
 * Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class NO242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] c = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            c[s.charAt(i)-'a']++;
        }
        for(int i = 0 ; i < t.length() ; i++){
            if(c[t.charAt(i)-'a'] == 0){
                return false;
            }else{
                c[t.charAt(i)-'a']--;
            }
        }
        return true;
    }
}
