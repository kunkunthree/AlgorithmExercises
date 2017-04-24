package algorithm.leetcode.algorithm;
/*
 * easy
 * 172. Factorial Trailing Zeroes 
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */
public class NO172_FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        if(n < 5){
            return 0;
        }
        return n/5+trailingZeroes(n/5);
    }
}
