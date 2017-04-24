package algorithm.leetcode.algorithm;
/*
 * easy
 * 38. Count and Say 
 * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.
Note: The sequence of integers will be represented as a string. 
 */
import java.util.*;
public class NO38_CountandSay {
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while(in.hasNext()){
		for(int i = 1 ; i <= 20 ; i++){
//			int n = in.nextInt();
			System.out.println(countAndSay(i));
		}
	}
    public static String countAndSay(int n) {
            StringBuilder s = new StringBuilder(1+"");
            String tmp;
            for(int i = 1 ; i < n ; i++){
                tmp = s.toString();
                s = new StringBuilder();
                int count = 1;
                char c = tmp.charAt(0);
                for(int j = 1 ; j < tmp.length(); j++){
                    if(c != tmp.charAt(j)){
                        s.append(count+""+c);
                        count = 1;
                        c = tmp.charAt(j);
                    }else{
                        count++;
                    }
                }
                s.append(count+""+c);
            }
            return s.toString();
        }
}
