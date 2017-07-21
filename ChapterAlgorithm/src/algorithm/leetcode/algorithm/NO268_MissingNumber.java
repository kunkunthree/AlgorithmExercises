package algorithm.leetcode.algorithm;
/*
 * easy
 * 268. Missing Number
 *  Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. 
Could you implement it using only constant extra space complexity? 

similar problems:
41. First Missing Positive 
136. Single Number 
287. Find the Duplicate Number 
 */
public class NO268_MissingNumber {
	//可以先求和再求差，最终结果就是没有的那个数
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int result = n*(n+1)/2;
        for(int i = 0 ; i < n ; i++){
            result-=nums[i];
        }
        return result;
    }
    //也可以用异或运算
    public int missingNumber2(int[] nums) {

        int xor = 0, i = 0;
    	for (i = 0; i < nums.length; i++) {
    		xor = xor ^ i ^ nums[i];
    	}

    	return xor ^ i;
    }
}
