package algorithm.leetcode.algorithm;
/*
 * easy
 * 9. Palindrome Number:
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
import java.util.*;
public class NO9_PalindromeNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			System.out.println(isPalindrome(in.nextInt()));
		}
	}
    public static boolean isPalindrome(int x) {
        int tmp = x;
        int num = 0;
        if(x<0){
            return false;
        }
        while(tmp != 0){
            num = num*10 + tmp%10;
            tmp/=10;
        }
        System.out.println(num);
        return x == num;
    }
}
