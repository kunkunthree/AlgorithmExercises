package algorithm.leetcode.algorithm;
/*
 * easy
 * 344. Reverse String 
 * Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh". 

similar problems:
345. Reverse Vowels of a String 
541. Reverse String II 
 */
public class NO344_ReverseString {
    public String reverseString(String s) {
        StringBuilder result = new StringBuilder();
        int length = s.length();
        for(int i = length-1 ; i >= 0 ; i--){
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
