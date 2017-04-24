package algorithm.leetcode.algorithm;
/*
 * easy
 * 136. Single Number
 * Given an array of integers, every element appears twice except for one. Find that single one.
Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 


    0 ^ N = N
    N ^ N = 0
So..... if N is the single number
N1 ^ N1 ^ N2 ^ N2 ^..............^ Nx ^ Nx ^ N
= (N1^N1) ^ (N2^N2) ^..............^ (Nx^Nx) ^ N
= 0 ^ 0 ^ ..........^ 0 ^ N
= N
 */
public class NO136_SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0 ; i < nums.length ; i++){
            result^=nums[i];
        }
        return result;
    }
}
