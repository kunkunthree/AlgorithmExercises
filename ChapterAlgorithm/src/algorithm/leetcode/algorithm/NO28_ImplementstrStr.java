package algorithm.leetcode.algorithm;
/*
 * easy
 * 28. Implement strStr() 
 *  Implement strStr().
 *  Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack. 
 *  
 *  similar problems:
 *  214. Shortest Palindrome 
 *  459. Repeated Substring Pattern 
 */
public class NO28_ImplementstrStr {
	public static void main(String[] args) {
		String haystack = "mississippi";
		String needle = "issip";
		System.out.println(strStr2(haystack, needle));
	}
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
    public static int strStr2(String haystack, String needle) {
        if(haystack == null || needle == null || haystack.length() < needle.length()){
            return -1;
        }
        if(needle.length() == 0){
            return 0;
        }
        int j = 0;
        int[] next = getNext(needle);
        for(int i = 0 ; i < haystack.length() ; i++){
            while(j > 0 && haystack.charAt(i) != needle.charAt(j)){
                j = next[j];
            }
            if(haystack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if(j == needle.length()){
                return i-j+1;
            }
        }
        return -1;
    }
    private static int[] getNext(String s){
        int len = s.length();
        int j = 0;
        int[] next = new int[len+1];
        //next[0] = next[1] = 0;
        
        for(int i = 1 ; i < len ; i++){
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            next[i+1] = j;
        }
        return next;
    }
}
