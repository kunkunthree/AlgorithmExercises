package algorithm.leetcode.algorithm;
/*
 * hard
 * 546. Remove Boxes 
 * Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. 
Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), 
remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]

Output:

23

Explanation:

[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)

Note: The number of boxes n would not exceed 100. 
 */
public class NO546_RemoveBoxes {
	//dp，动态规划：
	//dp[i][j][k]表示子数组[i,j]前面带着k个连续的和boxes[i]相等的箱子时，所能得到的最大分数
	//1.		dp[i][i][k] = (k+1)*(k+1);
	//2.		dp[i][j][k] = 0  if i > j
	//3.		(1)将dp[i][j][k]分成两部分，先除去[i+1,m-1]部分(boxes[i] == boxes[m])，然后再除去剩余的部分， i+1 <= m <= j
	//			dp[i][j][k] = Math.max(dp[i][j][k],dp[i+1][m-1][0]+dp[m][j][k+1]);
	//			(2)直接去掉前面带着的k个元素和boxes[i]，则
	//			dp[i][j][k] = Math.max(dp[i][j][k],(k+1)*(k+1)+dp[i+1][j][0]);
	
	//方法1：
	//dp，动态规划，迭代，自下而上
	public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        for(int i = 0 ; i < n ; i++){
            for(int k = 0 ; k < n ; k++){
                dp[i][i][k] = (k+1)*(k+1);
            }
        }
        for(int len = 1 ; len < n ; len++){
            for(int j = len ; j < n ; j++){
                int i = j-len;
                for(int k = 0 ; k <= i ; k++){
                    int result = (k+1)*(k+1) + dp[i+1][j][0];
                    
                    for(int m = i+1 ; m <= j ; m++){
                        if(boxes[i] == boxes[m]){
                            result = Math.max(result,dp[i+1][m-1][0]+dp[m][j][k+1]);
                        }
                    }
                    dp[i][j][k] = result;
                }
            }
        }
        return (n == 0 ? 0 : dp[0][n-1][0]);
    }
	//方法2：
	//dp，动态规划，递归，自上而下
	public int removeBoxes2(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return (n == 0 ? 0 : removeBoxes(boxes,0,n-1,0,dp));
    }
    private int removeBoxes(int[] boxes,int i,int j,int k,int[][][] dp){
        if(i > j){
            return 0;
        }
        if(dp[i][j][k] > 0){
            return dp[i][j][k];
        }
        int result = (k+1)*(k+1) + removeBoxes(boxes,i+1,j,0,dp);
        for(int m = i+1 ; m <= j ; m++){
            if(boxes[i] == boxes[m]){
                result = Math.max(result,removeBoxes(boxes,i+1,m-1,0,dp)+removeBoxes(boxes,m,j,k+1,dp));
            }
        }
        dp[i][j][k] = result;
        return result;
    }
}
