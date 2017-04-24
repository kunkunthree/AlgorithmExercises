package algorithm.leetcode.algorithm;
/*
 * easy
 * 507. Perfect Number
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.

Example:

Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14

Note: The input number n will not exceed 100,000,000. (1e8) 
 */
public class NO507_PerfectNumber {
	public static void main(String[] args) {
		System.out.println(checkPerfectNumber(6));
	}
    public static boolean checkPerfectNumber(int num) {
        if(num <= 1){
            return false;
        }
        int sum = 1;
        int x = 1,y = num;
        while(x <= y){
            x++;
            y = num/x;
            if(x <= y && x * y == num){
                if(x == y){
                    sum+=x;
                }else{
                    sum+=x+y;
                }
            }
        }
        return sum == num;
    }
}
