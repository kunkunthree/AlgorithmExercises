package algorithm.leetcode.algorithm;
/*
 * easy
 * 67. Add Binary
 *  Given two binary strings, return their sum (also a binary string).
For example,
a = "11"
b = "1"
Return "100".  
 */
import java.util.*;
public class NO67_AddBinary {
	public static void main(String[] args) {
		StringBuilder s = new StringBuilder("1234");
		s.insert(1, 'a');
		System.out.println(s.toString());
	}
    public String addBinary(String a, String b) {
        int len1 = a.length(),len2 = b.length();
        int add = 0,tmp = 0,i=0;
        StringBuilder result = new StringBuilder();
        while(i < len1 || i < len2){
            tmp = 0;
            if(i < len1){
                tmp += a.charAt(len1-1-i) - '0';
            }
            if(i < len2){
                tmp += b.charAt(len2-1-i) - '0';
            }
            tmp+=add;
            add = tmp/2;
            result.insert(0,tmp&1);
            i++;
        }
        if(add != 0){
            result.insert(0,add);
        }
        return result.toString();
    }
}
