package algorithm.leetcode.algorithm;
/*
 * easy
 * 434. Number of Segments in a String
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of 
 * non-space characters.

Please note that the string does not contain any non-printable characters.

Example:
Input: "Hello, my name is John"
Output: 5

 */
import java.util.*;
public class NO434_NumberofSegmentsinaString {
	public static void main(String[] args) {
		System.out.println("a     b".split(" ").length);
	}
    public int countSegments(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        s+=" ";
        int count = 0,length = s.length();
        for(int i = 0 ; i < length-1 ; i++){
            if(s.charAt(i) != ' ' && s.charAt(i+1) == ' '){
                count++;
            }
        }
        return count;
    }
    //O(n)time,O(1)space
    public int countSegments2(String s) {
        int res=0;
        for(int i=0; i<s.length(); i++)
            if(s.charAt(i)!=' ' && (i==0 || s.charAt(i-1)==' '))
                res++;        
        return res;
    }
}
