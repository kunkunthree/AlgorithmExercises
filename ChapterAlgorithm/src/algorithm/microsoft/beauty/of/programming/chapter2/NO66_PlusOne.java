package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * easy
 * 66. Plus One 
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
import java.util.*;
public class NO66_PlusOne {
	public static void main(String[] args) {
	}
    public int[] plusOne(int[] digits) {
        int add = 0,tmp;
        digits[digits.length-1]++;
        for(int i = digits.length-1 ; i >= 0 ; i--){
            tmp = digits[i] + add;
            add = tmp/10;
            digits[i] = tmp%10;
        }
        if(add == 0){
            return digits;
        }else{
            int[] result = new int[digits.length+1];
            result[0] = add;
            for(int i = result.length-1 ; i >= 1 ; i--){
                result[i] = digits[i-1];
            }
            return result;
        }
    }
}
