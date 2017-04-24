package algorithm.leetcode.algorithm;
/*
 * easy
 * 7. Reverse Integer :
 * Reverse digits of an integer.
Example1: x = 123, return 321
Example2: x = -123, return -321 
 */
import java.util.*;
public class NO7_ReverseInteger {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			System.out.println(reverse(in.nextInt()));
		}
	}
    public static int reverse(int x) {
        int num = 0;
        boolean isPositive = true;
        if(x > 0){
            isPositive = true;
        }else if(x < 0){
            isPositive = false;
            x = -x;
        }else{
            return 0;
        }
        while(x!=0){
            if(num > (Integer.MAX_VALUE-x%10)/10){
                return 0;
            }
            num = num * 10 + x%10;
            x/=10;
        }
        return isPositive ? num : -num;
    }
}
