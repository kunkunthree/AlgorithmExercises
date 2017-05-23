package algorithm.leetcode.algorithm;
/*
 * medium
 * 304. Range Sum Query 2D - Immutable
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 *  (row1, col1) and lower right corner (row2, col2).
 *  
 *  see NO304_RangeSumQuery2D_Immutable.png
 *  
 *  The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

 */
import java.util.*;
public class NO304_RangeSumQuery2D_Immutable {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int [][] matrix = new int[m][n];
		for(int i = 0 ; i < m ; i++){
			for(int j = 0 ; j < n ; j++){
				matrix[i][j] = in.nextInt();
			}
		}
		NumMatrix nm = new NumMatrix(matrix);
		System.out.println(nm.sumRegion(2, 1, 4, 3));
		System.out.println(nm.sumRegion(1, 1, 2, 2));
		System.out.println(nm.sumRegion(1, 2, 2, 4));
	}
}
//方法1：
//dp，把左上角所有元素的和存起来，dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
//获得某个区域的所有元素和：return dp[x2+1][y2+1] - dp[x1][y2+1] - dp[x2+1][y1] + dp[x1][y1];
class NumMatrix {
    private int[][] dp;
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return;
        }
        int m = matrix.length,n = matrix[0].length;
        dp = new int[m+1][n+1];
        for(int i = 1 ; i <= m ; i++){
            for(int j = 1 ; j <= n ; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int x1 = Math.min(row1,row2);
        int x2 = Math.max(row1,row2);
        int y1 = Math.min(col1,col2);
        int y2 = Math.max(col1,col2);
        return dp[x2+1][y2+1] - dp[x1][y2+1] - dp[x2+1][y1] + dp[x1][y1];
    }
}
