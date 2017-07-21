package algorithm.leetcode.algorithm;
/*
 * easy
 * 643. Maximum Average Subarray I 
 *  Given an array consisting of n integers, find the contiguous subarray of given length k 
 *  that has the maximum average value. And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

Note:
    1.		1 <= k <= n <= 30,000.
    2.		Elements of the given array will be in the range [-10,000, 10,000].
    
similar problems：
644. Maximum Average Subarray II 
 */
public class NO643_MaximumAverageSubarrayI {
	//方法1：
	//滑动窗口法：
	public double findMaxAverage(int[] nums, int k) {
        long maxSum = Integer.MIN_VALUE,curSum = 0;
        if(nums == null || k <= 0 || nums.length <= k-1){
            return 0.0;
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(i < k){
                curSum+=nums[i];
            }else{
                curSum+=nums[i]-nums[i-k];
            }
            if(i>=k-1)maxSum = Math.max(maxSum,curSum);
        }
        return (double)maxSum/k;
    }
}
