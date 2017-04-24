package algorithm.leetcode.algorithm;
/*
 * easy
 * 28. Implement strStr() 
 *  Implement strStr().
 *  Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack. 
 */
public class NO28_ImplementstrStr {
    public int strStr(String haystack, String needle) {
        for(int i = 0 ; i < haystack.length() - needle.length() + 1; i++){
            int j = 0;
            for(; j < needle.length() ; j++){
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    break;
                }
            }
            if(j == needle.length()){
                return i;
            }
        }
        return -1;
    }
}
