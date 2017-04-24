package algorithm.leetcode.algorithm;
/*
 * easy
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them is that 
 * adjacent houses have security system connected and it will automatically contact the police 
 * if two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class NO198_HouseRobber {
	public static void main(String[] args) {
		int[] nums = new int[]{104,209,137,52,158,67,213,86,141,110,151,127,238,147,169,138,240,185,246,225,147,203,83,83,131,227,54,78,165,180,214,151,111,161,233,147,124,143};
		System.out.println(rob(nums));
	}
	//O(n)space,O(n)time
    public static  int rob(int[] nums) {
        int[][] dp = new int[nums.length+1][2];
        for(int i = 1 ; i < nums.length+1 ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = dp[i-1][0]+nums[i-1];
        }
        return Math.max(dp[nums.length][0],dp[nums.length][1]);
    }
    //O(1)space,O(n)time
    public int rob2(int[] nums) {
        int[][] dp = new int[nums.length+1][2];
        int take=0,notTake=0,tmpNotTake=0;
        for(int i = 1 ; i < nums.length+1 ; i++){
            tmpNotTake = notTake;
            notTake = Math.max(tmpNotTake,take);
            take = tmpNotTake+nums[i-1];
        }
        return Math.max(take,notTake);
    }
    //O(n)time，时间更快
    public int rob3(int nums[]) {
    	int n = nums.length;
        int a = 0;
        int b = 0;
        for (int i=0; i<n; i++)
        {
            if (i%2==0)
            {
                a = Math.max(a+nums[i], b);
            }
            else
            {
                b = Math.max(a, b+nums[i]);
            }
        }
        return Math.max(a, b);
    }
}
