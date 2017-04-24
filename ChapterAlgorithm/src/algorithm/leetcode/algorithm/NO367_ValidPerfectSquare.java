package algorithm.leetcode.algorithm;
/*
 * easy
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True

Example 2:

Input: 14
Returns: False

 */
public class NO367_ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if(num == 0 || num ==1){
            return true;
        }
        for(int i = 2 ; i <= num/2 ; i++){
            if(i * i == num){
                return true;
            }
        }
        return false;
    }
    //A square number is 1+3+5+7+..., JAVA code
    public boolean isPerfectSquare2(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
// The time complexity is O(sqrt(n)), a more efficient one using binary search whose time complexity is O(log(n)):
    public boolean isPerfectSquare3(int num) {
        int low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }
    //One thing to note is that we have to use long for mid to avoid mid*mid from overflow. 
    //Also, you can use long type for low and high to avoid type casting for mid from long to int.
    //And a third way is to use Newton Method to calculate the square root or num, refer to Newton Method for details.
    public boolean isPerfectSquare4(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }
}
