package algorithm.leetcode.algorithm;
/*
 * easy
 * 479. Largest Palindrome Product 
 * Find the largest palindrome made from the product of two n-digit numbers.
Since the result could be very large, you should return the largest palindrome mod 1337.

Example:
Input: 2
Output: 987
Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:
		The range of n is [1,8].
 */
public class NO479_LargestPalindromeProduct {
	//方法1：
	//构造最大的回文数字，然后对前半部分进行递减遍历，不断构造回文数，
	//然后在[lowerBound,upperBound]中进行遍历查找是否存在两个数相乘构成回文数
	public int largestPalindrome(int n) {
        if(n == 1){
            return 9;
        }
        int upperBound = (int)Math.pow(10,n)-1,lowerBound = upperBound/10;
        long maxNum = (long)upperBound * (long)upperBound;
        
        int firstHalf = (int)(maxNum / Math.pow(10,n));
        
        boolean palindromeFound = false;
        long palindrome = 0;
        
        while(!palindromeFound){
            palindrome = getPalindrome(firstHalf);
            
            for (long i = upperBound; upperBound > lowerBound; i--) {
                // if n= 3 none of the factor of palindrom  can be more than 999 or less than square root of assumed palindrom 
                if (palindrome / i > upperBound || i * i < palindrome) {
                    break;
                }
                
                // if two factors found, where both of them are n-digits,
                if (palindrome % i == 0) {
                    palindromeFound = true;
                    break;
                }
            }
            firstHalf--;
        }
        return (int)(palindrome % 1337);
    }
     private long getPalindrome(long num) {
        String str = num + new StringBuilder().append(num).reverse().toString();
        return Long.parseLong(str);
    }
}
