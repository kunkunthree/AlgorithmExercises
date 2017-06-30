package algorithm.leetcode.algorithm;
/*
 * hard
 * 407. Trapping Rain Water II 
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
 * compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:
Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

see NO407_TrappingRainWaterII_1.png

The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain. 

see NO407_TrappingRainWaterII_2.png

After the rain, water are trapped between the blocks. The total volume of water trapped is 4. 

similar problems：
42. Trapping Rain Water 
 */
import java.util.*;
public class NO407_TrappingRainWaterII {
	public static void main(String[] args) {
		int[][] heightMap = new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
		System.out.println(trapRainWater(heightMap));
	}
	//方法1：
	//BFS
	//利用PriorityQueue得到当前遍历到的最低的位置，然后进行搜索没遍历的位置，并把新节点加入到queue中
	//注意：加入时，高度必须是Math.max(heightMap[x][y], c.height)，保证最低点高度不会下降
	public static class Cell{
        int x,y;
        int height;
        Cell(int x,int y,int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public static int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0){
            return 0;
        }
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>(1,new Comparator<Cell>(){
            public int compare(Cell c1,Cell c2){
                return c1.height - c2.height;
            }
        });
        int m = heightMap.length,n = heightMap[0].length,result = 0;
        boolean[][] visited = new boolean[m][n];
        
        for(int i = 0 ; i < m ; i++){
            visited[i][0] = true;
            visited[i][n-1] = true;
            queue.offer(new Cell(i,0,heightMap[i][0]));
            queue.offer(new Cell(i,n-1,heightMap[i][n-1]));
        }
        for(int j = 0 ; j < n ; j++){
            visited[0][j] = true;
            visited[m-1][j] = true;
            queue.offer(new Cell(0,j,heightMap[0][j]));
            queue.offer(new Cell(m-1,j,heightMap[m-1][j]));
        }
        Cell c;
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        while(!queue.isEmpty()){
            c = queue.poll();
            for(int[] dir : dirs){
                int x = c.x + dir[0];
                int y = c.y + dir[1];
                if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                	//这里很关键，保持最低处的高度
                	queue.offer(new Cell(x,y,Math.max(heightMap[x][y], c.height)));
                    result += Math.max(0,c.height - heightMap[x][y]);
                    visited[x][y] = true;
                }
            }
        }
        return result;
    }
    //方法2：
    //DFS
    public int trapRainWater2(int[][] heightMap) {
    	//find a pool area
    	//decide element is pool, find min h around it from border element
    	//border's h can pass through linkage.
    	//create a minh[][] matrix, preset border from origin matrix
    	//except border, scan from top, left
    	//decide min h by its value and surrounded element from minh, ignore 0.
    	//pass by: if min h < nearby top, left element's min h, update those element's min h, dfs
    	//when it is done, scan minh to collect water
    	int water = 0;
    	if(heightMap.length<3||heightMap[0].length<3) return water;
    	int[][] minh = new int[heightMap.length][heightMap[0].length];
    	for(int i=0;i<heightMap.length;i++){
    		minh[i][0] = heightMap[i][0];
    		minh[i][heightMap[0].length-1] = heightMap[i][heightMap[0].length-1]; 
    	}
    	for(int j=0;j<heightMap[0].length;j++){
    		minh[0][j] = heightMap[0][j];
    		minh[heightMap.length-1][j] = heightMap[heightMap.length-1][j];    			
    	}
    	for(int i=1;i<heightMap.length-1;i++)
    		for(int j=1;j<heightMap[0].length-1;j++){
    			int min = Integer.MAX_VALUE;
    			if(minh[i-1][j]!=0) min = Math.min(min, minh[i-1][j]);
    			if(minh[i+1][j]!=0) min = Math.min(min, minh[i+1][j]);
    			if(minh[i][j+1]!=0) min = Math.min(min, minh[i][j+1]);
    			if(minh[i][j-1]!=0) min = Math.min(min, minh[i][j-1]);    			
    			minh[i][j] = Math.max(heightMap[i][j], min);
    			//pass by
    			dfs(heightMap, minh, minh[i][j], i-1, j);    	 
    	    	dfs(heightMap, minh, minh[i][j], i, j-1);    	    	
    		}
    	
    	for(int i=1;i<heightMap.length-1;i++)
    		for(int j=1;j<heightMap[0].length-1;j++){
    			water += minh[i][j]-heightMap[i][j];
    		}
    	
    	return water;
    }
    
    public void dfs(int[][] matrix, int[][] minh, int h, int i, int j){
    	if(i==0||i==matrix.length-1||j==0||j==matrix[0].length-1) return;
    	if(minh[i][j]==0) return;
    	if(minh[i][j]>h&&minh[i][j]!=matrix[i][j]){
    		minh[i][j] = Math.max(matrix[i][j], h); 
    		dfs(matrix, minh, minh[i][j], i-1, j);
        	dfs(matrix, minh, minh[i][j], i+1, j);
        	dfs(matrix, minh, minh[i][j], i, j-1);
        	dfs(matrix, minh, minh[i][j], i, j+1);
    	}    	
    }
}
