package algorithm.leetcode.algorithm;
/*
 * medium
 * 494. Target Sum
 *  You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 *   Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:

    1.		The length of the given array is positive and will not exceed 20.
    2.		The sum of elements in the given array will not exceed 1000.
    3.		Your output answer is guaranteed to be fitted in a 32-bit integer.

 */
public class NO494_TargetSum {
	public static void main(String[] args) {
		int[] nums = new int[]{25,29,23,21,45,36,16,35,13,39,44,15,16,14,45,23,50,43,9,15};
		int S = 32;
		System.out.println(findTargetSumWays2(nums, S));
	}
	//方法1：
	//DFS
	private int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays(nums,S,0,nums.length);
    }
    private int findTargetSumWays(int[] nums,int cur,int start,int end) {
        if(start >= end){
            return cur == 0 ? 1 : 0;
        }
        int count = 0;
        count+=findTargetSumWays(nums,cur-nums[start],start+1,end);
        count+=findTargetSumWays(nums,cur+nums[start],start+1,end);
        return count;
    }
    
    public static int findTargetSumWays2(int[] nums, int S) {
    	int n = nums.length,count = 0,sum = 0;
        for(int num : nums){
            sum+=num;
        }
        int[][] dp = new int[n+1][2*sum+1];
        dp[0][sum] = 1;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j <= 2 * sum ; j++){
                if(dp[i-1][j] != 0){
                    dp[i][j+nums[i]] = dp[i-1][j+nums[i]] + dp[i-1][j];
                    dp[i][j-nums[i]] = dp[i-1][j-nums[i]] - dp[i-1][j];
                }
            }
        }
        return dp[n][sum+S];
    }
}
