package algorithm.leetcode.algorithm;
/*
 * medium
 * 486. Predict the Winner
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:

Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5,
 then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.

Example 2:

Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. 
No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.

Note:

    1.		1 <= length of the array <= 20.
    2.		Any scores in the given array are non-negative integers and will not exceed 10,000,000.
    3.		If the scores of both players are equal, then player 1 is still the winner.

 */
import java.util.*;
public class NO486_PredicttheWinner {
	public static void main(String[] args) {
		int[] nums = new int[]{1,5,2};
		System.out.println(PredictTheWinner4(nums));
	}
	//方法1：
	//DFS+dp
	public static boolean PredictTheWinner(int[] nums) {
		int n = nums.length,tmpSum = 0;
        int[] sum = new int[n+1];
        int[][] dp = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i = 0 ; i < n ; i++){
            sum[i+1] = tmpSum + nums[i];
            tmpSum = sum[i+1];
            dp[i][i] = nums[i];
        }
        return biggestResult(nums,dp,sum,0,n-1)*2 >= sum[n];
    }
	//biggestResult(int[] nums,int[][] dp,int[] sum,int start,int end) 表示在[start,end]区间内能得到的最大结果
    private static int biggestResult(int[] nums,int[][] dp,int[] sum,int start,int end){
    	if(dp[start][end] != -1){
            return dp[start][end];
        }
        int left = sum[end+1] - sum[start] - biggestResult(nums,dp,sum,start+1,end);
        int right = sum[end+1] - sum[start] - biggestResult(nums,dp,sum,start,end-1);
        dp[start][end] = Math.max(left,right);
        return dp[start][end];
    }
    //方法2：
    //DFS+dp，辅助方法和方法1不一样
    public boolean PredictTheWinner2(int[] nums) {
        return helper(nums, 0, nums.length-1, new Integer[nums.length][nums.length])>=0;
    }
    //helper得到的时[s,e]取得的最大值和剩余值的差
    private int helper(int[] nums, int s, int e, Integer[][] mem){    
        if(mem[s][e]==null)
            mem[s][e] = s==e ? nums[e] : Math.max(nums[e]-helper(nums,s,e-1,mem),nums[s]-helper(nums,s+1,e,mem));
        return mem[s][e];
    }
    
    //方法3：
    //dp,迭代，O(n^2)space,O(n^2)time
    public boolean PredictTheWinner3(int[] nums) {
        int n = nums.length,tmpSum = 0;
        int[][] dp = new int[n][n];
        for(int len = 0 ; len < n ; len++){
            for(int i = 0 ; i < n-len ; i++){
                int j = i+len;
                if(i == j){
                    dp[i][j] = nums[i];
                }else{
                    dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1] >= 0;
    }
    public static boolean PredictTheWinner4(int[] nums) {
        int n = nums.length,tmpSum = 0;
        int[] dp = new int[n];	//dp[j]表示当前[j,i]的最大值与其他值的差
        for(int i = 0 ; i < n ; i++){
            for(int j = i ; j >= 0 ; j--){
                if(i == j){
                    dp[j] = nums[i];
                }else{
                    dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j+1]);
                }
            }
        }
        return dp[0] >= 0;
    }
}
