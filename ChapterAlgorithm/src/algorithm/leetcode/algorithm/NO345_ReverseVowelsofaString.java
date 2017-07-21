package algorithm.leetcode.algorithm;
/*
 * easy
 * 345. Reverse Vowels of a String 
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y". 

similar problems:
344. Reverse String 
 */
public class NO345_ReverseVowelsofaString {
    public String reverseVowels(String s) {
        if(s == null || s.length() <= 1){
            return s;
        }
        char[] str = s.toCharArray();
        int left = 0,right = s.length()-1;
        char tmp;
        while(left < right){
            while(left < right && !isVowels(str[left])){
                left++;
            }
            while(left < right && !isVowels(str[right])){
                right--;
            }
            if(left < right){
                tmp = str[left];
                str[left] = str[right];
                str[right] = tmp;
                left++;
                right--;
            }
        }
        return new String(str);
    }
    public boolean isVowels(char c){
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
        || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
            return true;
        }
        return false;
    }
}
