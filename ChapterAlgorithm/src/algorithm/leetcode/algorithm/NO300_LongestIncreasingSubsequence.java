package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * medium
 * 300. Longest Increasing Subsequence
 *  Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity? 
 */
public class NO300_LongestIncreasingSubsequence {
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
		System.out.println(lengthOfLIS(nums));
	}
	//方法1：
	//动态规划：
	//设LIS[i]为前i个元素中以第i个元素结尾的最长递增子序列,i = 0,1,....,n-1
	//则若array[k] > array[i]，则LIS[k]=max{LIS[i],i = 0,1,....,k-1} + 1。
	//则LIS数组中最大的值则为原数组array的最大递增子序列长度
	//复杂度为O(N^2)
	public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length,max = 0;
        int[] dp = new int[n];
        for(int i = 0 ; i < n ; i++){
            for(int j = i-1 ; j >= 0 ; j--){
                if(nums[i] > nums[j] && dp[i] < dp[j]+1){
                    dp[i] = dp[j]+1;
                    max = Math.max(max,dp[i]);
                }
            }
        }
        return max+1;
    }
	//方法2：
		//O(nlog n)time,dp
		public int lengthOfLIS2(int[] nums) {            
	        int[] dp = new int[nums.length];
	        int len = 0;

	        for(int x : nums) {
	            int i = Arrays.binarySearch(dp, 0, len, x);
	            if(i < 0) i = -(i + 1);
	            dp[i] = x;
	            if(i == len) len++;
	        }

	        return len;
	    }
}
