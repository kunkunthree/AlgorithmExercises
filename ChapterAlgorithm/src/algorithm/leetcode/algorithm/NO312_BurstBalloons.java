package algorithm.leetcode.algorithm;
/*
 * hard
 * 312. Burst Balloons 
 *  Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
 *  You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] 
 *  coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:
Given [3, 1, 5, 8]
Return 167
    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

 */
import java.util.*;
public class NO312_BurstBalloons {
	public static void main(String[] args) {
		Integer[] nums = new Integer[]{1};
		List<Integer> list = Arrays.asList(nums);
	}
	//方法1：
	//DFS，分治，O(n^3)time
	//逆向思维，先找到最后一个被击中的气球，再反推回第一个被击中的气球
	public int maxCoins(int[] nums) {
		int[] newNums = new int[nums.length + 2];
	    int n = 1;
	    //先把等于0的气球去掉
	    for (int x : nums) if (x > 0) newNums[n++] = x;
	    newNums[0] = newNums[n++] = 1;
        int[][] memo = new int[n][n];
        return burst(memo,newNums,0,n-1);
    }
    public int burst(int[][] memo,int[] nums,int left,int right){
        if(left + 1 == right){
            return 0;
        }
        if(memo[left][right] > 0){
            return memo[left][right];
        }
        int result = 0;
        for(int i = left+1 ; i < right ; i++){
            result = Math.max(result,nums[left]*nums[i]*nums[right] 
                    + burst(memo,nums,left,i) + burst(memo,nums,i,right));
        }
        memo[left][right] = result;
        return result;
    }
    //方法2：
    //DP，动态规划，O(n^3)time,O(n^2)space
    //逆向思维，先找到最后一个被击中的气球，再反推回第一个被击中的气球
    //n为气球总数+2
    //dp[left][right]表示[left+1,right-1]范围内射击气球所能获得的最多硬币数，0 <= left < right < n 
    //当left>=right-1时，dp[left][right] = 0;
    //dp[left][right] = Math.max(dp[left][right],newNums[left]*newNums[i]*newNums[right] + dp[left][i] + dp[i][right]);
    public int maxCoins2(int[] nums) {
		int[] newNums = new int[nums.length + 2];
	    int n = 1;
	    //先把等于0的气球去掉
	    for (int x : nums) if (x > 0) newNums[n++] = x;
	    newNums[0] = newNums[n++] = 1;
        int[][] dp = new int[n][n];
        for(int k = 2; k < n ; k++){
            for(int left = 0 ; left < n-k ; left++){
                int right = left+k;
                for(int i = left+1 ; i < right ; i++){
                    dp[left][right] = Math.max(dp[left][right],
                                            newNums[left]*newNums[i]*newNums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n-1];
    }
}
