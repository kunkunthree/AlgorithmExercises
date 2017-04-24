package algorithm.leetcode.algorithm;
/*
 * easy
 * 504. Base 7
 * Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"

Example 2:
Input: -7
Output: "-10"

Note: The input will be in range of [-1e7, 1e7]. 
 */
public class NO504_Base7 {
    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        String s = "";
        String p = "";
        if(num < 0){
            p = "-";
            num = -num;
        }
        while(num != 0){
            s = num%7 + s;
            num/=7;
        }
        return p+s;
    }
    //递归法
    public String convertToBase7_2(int num) {
        if(num < 0){
            return "-"+convertToBase7_2(-num);
        }
        if(num < 7){
            return ""+num%7;
        }
        return convertToBase7_2(num/7) + num%7;
    }
}
