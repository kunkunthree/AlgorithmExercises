package algorithm.leetcode.algorithm;
/*
 * easy
 * 415. Add Strings 
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

1.    The length of both num1 and num2 is < 5100.
2.    Both num1 and num2 contains only digits 0-9.
3.    Both num1 and num2 does not contain any leading zero.
4.    You must not use any built-in BigInteger library or convert the inputs to integer directly.

 */
import java.util.*;
public class NO415_AddStrings {
	public static void main(String[] args) {
		StringBuilder result = new StringBuilder();
	}
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int index1 = num1.length()-1,index2 = num2.length()-1,add = 0;
        while(index1 >= 0 || index2 >= 0){
            int tmp = 0;
            if(index1 >= 0){
                tmp+=num1.charAt(index1--)-'0';
            }
            if(index2 >= 0){
                tmp+=num2.charAt(index2--)-'0';
            }
            tmp+=add;
            add = tmp/10;
            tmp = tmp-add*10;
            result.insert(0,(char)('0'+tmp));
        }
        if(add != 0){
            result.insert(0,'1');
        }
        return result.toString();
    }
}
