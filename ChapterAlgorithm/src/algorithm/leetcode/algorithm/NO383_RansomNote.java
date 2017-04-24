package algorithm.leetcode.algorithm;
/*
 * easy
 * 383. Ransom Note
 *  Given an arbitrary ransom note string and another string containing letters from all the magazines, 
 *  write a function that will return true if the ransom note can be constructed from the magazines ; 
 *  otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

 */
public class NO383_RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine.length() < ransomNote.length()){
            return false;
        }
        int[] c = new int[256];
        for(int i = 0 ; i < magazine.length() ; i++){
            c[magazine.charAt(i)-'a']++;
        }
        for(int i = 0 ; i < ransomNote.length() ; i++){
            if(c[ransomNote.charAt(i)-'a'] == 0){
                return false;
            }
            c[ransomNote.charAt(i)-'a']--;
        }
        return true;
    }
}
