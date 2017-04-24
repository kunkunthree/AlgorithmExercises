package algorithm.leetcode.algorithm;
/*
 * easy
 * 387. First Unique Character in a String
 *  Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:
s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

Note: You may assume the string contain only lowercase letters. 
 */
public class NO387_FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0){
            return -1;
        }
        int[] c = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            c[s.charAt(i)-'a']++;
        }
        for(int i = 0 ; i < s.length() ; i++){
            if(c[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;
    }
    //效率更高的写法，用两个指针，一个走的快，一个走得慢，
    //走的快的指针用于计数，走得慢的指针用于指向当前只出现一次的字母位置
    //当走的快的指针走到底，且走得慢的指针没超过指定长度时，返回走得慢的指针所指向的位置
    public int firstUniqChar2(String s) {
        if (s==null || s.length()==0) return -1;
        int len = s.length();
        if (len==1) return 0;
        char[] cc = s.toCharArray();
        int slow =0, fast=1;
        int[] count = new int[256];
        count[cc[slow]]++;
        while (fast < len) {
            count[cc[fast]]++;
            // if slow pointer is not a unique character anymore, move to the next unique one
            while (slow < len && count[cc[slow]] > 1) slow++;  
            if (slow >= len) return -1; // no unique character exist
            if (count[cc[slow]]==0) { // not yet visited by the fast pointer
                count[cc[slow]]++; 
                fast=slow; // reset the fast pointer
            }
            fast++;
        }
        return slow;
    }
}
