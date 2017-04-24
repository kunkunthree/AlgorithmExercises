package algorithm.leetcode.algorithm;
/*
 * medium
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
 * which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */
public class NO64_MinimumPathSum {
	//方法1：
	//动态规划，dp，节省空间写法
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = 0;
        for(int j = 1 ; j < n ; j++){
            dp[j] = Integer.MAX_VALUE;
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(j > 0){
                    dp[j] = grid[i][j] + Math.min(dp[j],dp[j-1]);
                }else{
                    dp[j]+=grid[i][j];
                }
            }
        }
        return dp[n-1];
    }
    //方法2：
    //不用额外空间存储，直接从原来矩阵操作
    public int minPathSum2(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        for(int i = 1 ; i < m ; i++){
            grid[i][0] += grid[i-1][0];
        }
        for(int j = 1 ; j < n ; j++){
            grid[0][j] += grid[0][j-1];
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                grid[i][j] = grid[i][j] + Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }
}
