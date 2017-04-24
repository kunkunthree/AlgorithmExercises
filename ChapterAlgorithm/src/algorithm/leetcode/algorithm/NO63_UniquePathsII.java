package algorithm.leetcode.algorithm;
/*
 * medium
 * 63. Unique Paths II
 * Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]

The total number of unique paths is 2.

Note: m and n will be at most 100.
 */
public class NO63_UniquePathsII {
	public static void main(String[] args) {
		int[][] obstacleGrid = new int[][]{{0}};
		System.out.println(uniquePathsWithObstacles(obstacleGrid));
	}
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1^obstacleGrid[0][0];
        for(int i = 1; i < m ; i++){
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }else{
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int j = 1; j < n ; j++){
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }else{
                dp[0][j] = dp[0][j-1];
            }
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
    //方法2：
    //方法1的优化，节省额外空间
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}
