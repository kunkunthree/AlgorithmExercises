package algorithm.leetcode.algorithm;

import javax.activation.DataSource;

/*
 * hard
 * 576. Out of Boundary Paths 
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

Example 1:

Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:
see NO576_OutOfBoundaryPaths.png

Example 2:

Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:
see NO576_OutOfBoundaryPaths_2.png

Note:
    Once you move the ball out of boundary, you cannot move it back.
    The length and height of the grid is in range [1,50].
    N is in range [0,50].

 */
public class NO576_OutOfBoundaryPaths {
	public static void main(String[] args) {
	}
	//方法1：
	//dp，动态规划
	public int findPaths(int m, int n, int N, int i, int j) {
        int MOD = 1000000007;
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int result = 0;
        int[][] count = new int[m][n];
        count[i][j] = 1;
        for(int k = 0 ; k < N ; k++){
            int[][] tmp = new int[m][n];
            for(int r = 0 ; r < m ; r++){
                for(int c = 0 ; c < n ; c++){
                    if(count[r][c] > 0){
                        for(int[] dir : dirs){
                            int x = r+dir[0];
                            int y = c+dir[1];
                            if(x < 0 || x >= m || y < 0 || y >= n){
                                result = (result + count[r][c]) % MOD;
                            }else{
                                tmp[x][y] = (tmp[x][y] + count[r][c])%MOD;
                            }
                        }
                    }
                }
            }
            count = tmp;
        }
        return result;
    }
}
