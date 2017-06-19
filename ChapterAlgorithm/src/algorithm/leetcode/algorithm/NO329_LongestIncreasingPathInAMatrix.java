package algorithm.leetcode.algorithm;
/*
 * hard
 * 329. Longest Increasing Path in a Matrix 
 * Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]

Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]

Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
import java.util.*;
public class NO329_LongestIncreasingPathInAMatrix {
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
		NO329_LongestIncreasingPathInAMatrix test = new NO329_LongestIncreasingPathInAMatrix();
		System.out.println(test.longestIncreasingPath(matrix));
	}
	//方法1：
	//DFS+cache
	public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length,n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] len = new int[m][n];
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int  max = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
            	max = Math.max(max,dfs(matrix,len,dirs,i,j));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix,int[][] len,int[][] dirs,int x,int y){
        if(len[x][y] > 0){
            return len[x][y];
        }
        int path = 0;
        for(int[] dir : dirs){
            int nextX = x+dir[0];
            int nextY = y+dir[1];
            if(isInBoundary(matrix,nextX,nextY) 
                && matrix[nextX][nextY] > matrix[x][y]){
                path = Math.max(path,dfs(matrix,len,dirs,nextX,nextY));
            }
        }
        len[x][y] = path+1;
        return len[x][y];
    }
    private boolean isInBoundary(int[][] matrix,int x,int y){
        return x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length;
    }
}
