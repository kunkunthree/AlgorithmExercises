package algorithm.leetcode.algorithm;
/*
 * medium
 * 416. Partition Equal Subset Sum
 * Given a non-empty array containing only positive integers, 
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

    Each of the array element will not exceed 100.
    The array size will not exceed 200.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

 */
public class NO416_PartitionEqualSubsetSum {
	public static void main(String[] args) {
		int[] nums = {1,2,3,5};
		System.out.println(canPartition(nums));
	}
	//方法1：
	//0-1背包问题，能不能达到总和的一半
	public static boolean canPartition(int[] nums) {
        int sum = 0,n = nums.length;
        for(int i = 0 ; i < n ; i++){
            sum+=nums[i];
        }
        if((sum&1) == 1){
            return false;
        }
        int halfSum = sum/2,last;
        boolean[] dp = new boolean[halfSum+1];
        dp[0] = true;
        for(int i = 0 ; i < n ; i++){
            for(int j = halfSum ; j >= nums[i] ; j--){
                if(dp[j-nums[i]] == true){
                    dp[j] = true;
                }
            }
        }
        return dp[halfSum];
    }
}
