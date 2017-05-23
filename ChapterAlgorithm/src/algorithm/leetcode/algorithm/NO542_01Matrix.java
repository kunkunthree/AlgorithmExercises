package algorithm.leetcode.algorithm;
/*
 * medium
 * 542. 01 Matrix 
 *  Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input:
0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0

Example 2:
Input:
0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1

Note:
    1.		The number of elements of the given matrix will not exceed 10,000.
    2.		There are at least one 0 in the given matrix.
    3.		The cells are adjacent in only four directions: up, down, left and right.

 */
import java.util.*;
public class NO542_01Matrix {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[][]  matrix = new int[m][n];
		for(int i = 0  ; i < m ; i++){
			for(int j = 0 ; j < n ; j++){
				matrix[i][j] = in.nextInt();
			}
		}
		for(int[] row : updateMatrix2(matrix)){
			System.out.println(Arrays.toString(row));
		}
	}
	//方法1：
	//BFS
	public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return matrix;
        }
        int m = matrix.length,n = matrix[0].length,x,y;
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] > 0){
                    matrix[i][j] = Integer.MAX_VALUE;
                }else{
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int[] cur;
        while(!queue.isEmpty()){
            cur = queue.poll();
            for(int i = 0 ; i < dir.length ; i++){
                x = cur[0]+dir[i][0];
                y = cur[1]+dir[i][1];
                if(x >= 0 && x < m && y >= 0 && y < n && matrix[cur[0]][cur[1]]+1 < matrix[x][y]){
                    matrix[x][y] = matrix[cur[0]][cur[1]]+1;
                    queue.offer(new int[]{x,y});
                }
            }
        }
        return matrix;
    }
	//方法2：
	//DFS，超时
	public static int[][] updateMatrix2(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return matrix;
        }
        int m = matrix.length,n = matrix[0].length,x,y;
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] > 0){
                    matrix[i][j] = Integer.MAX_VALUE;
                }else{
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int[] cur;
        while(!queue.isEmpty()){
            cur = queue.poll();
            dfs(matrix,dir,cur,m,n);
        }
        return matrix;
    }
    private static void dfs(int[][] matrix,int[][] dir,int[] cur,int m,int n){
        int x,y;
        for(int i = 0 ; i < dir.length ; i++){
            x = cur[0]+dir[i][0];
            y = cur[1]+dir[i][1];
            if(x >= 0 && x < m && y >= 0 && y < n && matrix[cur[0]][cur[1]]+1 < matrix[x][y]){
                matrix[x][y] = matrix[cur[0]][cur[1]]+1;
                dfs(matrix,dir,new int[]{x,y},m,n);
            }
        }
    }
    //方法3：
    //dp，O(n)，先考虑左上，再考虑右下
    public int[][] updateMatrix3(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return matrix;
        }
        int m = matrix.length,n = matrix[0].length,x,y;
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] > 0){
                    int left = Integer.MAX_VALUE-1,up = Integer.MAX_VALUE-1;
                    if(i-1 >= 0)up = matrix[i-1][j];
                    if(j-1 >= 0)left = matrix[i][j-1];
                    matrix[i][j] = Math.min(Integer.MAX_VALUE-1,Math.min(left,up)+1);
                }
            }
        }
        for(int i = m-1 ; i >= 0 ; i--){
            for(int j = n-1 ; j >= 0 ; j--){
                if(matrix[i][j] > 1){
                    int right = Integer.MAX_VALUE-1,down = Integer.MAX_VALUE-1;
                    if(i+1 < m)down = matrix[i+1][j];
                    if(j+1 < n)right = matrix[i][j+1];
                    matrix[i][j] = Math.min(matrix[i][j],Math.min(right,down)+1);
                }
            }
        }
        return matrix;
    }
}
