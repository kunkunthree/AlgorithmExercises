package algorithm.leetcode.algorithm;
/*
 * medium
 * 200. Number of Islands
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 *  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 *  You may assume all four edges of the grid are all surrounded by water.

Example 1:
11110
11010
11000
00000
Answer: 1

Example 2:
11000
11000
00100
00011
Answer: 3
 */
import java.util.*;
public class NO200_NumberofIslands {
	public static void main(String[] args) {
//		System.out.println((int)'0');
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		char[][] grid = new char[m][];
		for(int i = 0 ; i < m ; i++){
			grid[i] = in.next().toCharArray();
		}
		System.out.println(numIslands(grid));
	}
	public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0){
            return 0;
        }
        int m = grid.length,n = grid[0].length;
        int count = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == '1'){
                    count++;
                    searchAndFill(grid,i,j);
                }
            }
        }
        return count;
    }
    public static void searchAndFill(char[][] grid,int x,int y){
        grid[x][y] = '0';
        if(x > 0 && grid[x-1][y] == '1'){
            searchAndFill(grid,x-1,y);
        }
        if(y > 0 && grid[x][y-1] == '1'){
            searchAndFill(grid,x,y-1);
        }
        if(x < grid.length-1 && grid[x+1][y] == '1'){
            searchAndFill(grid,x+1,y);
        }
        if(y < grid[0].length-1 && grid[x][y+1] == '1'){
            searchAndFill(grid,x,y+1);
        }
    }
}
