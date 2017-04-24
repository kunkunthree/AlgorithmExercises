package algorithm.leetcode.algorithm;
/*
 * easy
 * 303. Range Sum Query - Immutable 
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

Note:
    You may assume that the array does not change.
    There are many calls to sumRange function.

 */
public class NO303_RangeSumQuery_Immutable {
	class NumArray {
	    public int[] sum,nums;
	    public NumArray(int[] nums) {
	        this.nums = nums;
	        int length = nums.length;
	        sum = new int[length];
	        int tmpSum = 0;
	        for(int i = 0 ; i < length ; i++){
	            tmpSum+=nums[i];
	            sum[i] = tmpSum;
	        }
	    }
	    
	    public int sumRange(int i, int j) {
	        return sum[j]-sum[i]+nums[i];
	    }
	}
}
