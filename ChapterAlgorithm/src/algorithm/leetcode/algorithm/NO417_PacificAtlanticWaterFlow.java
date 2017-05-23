package algorithm.leetcode.algorithm;
/*
 * medium
 * 417. Pacific Atlantic Water Flow
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right 
 * and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

 */
import java.util.*;
public class NO417_PacificAtlanticWaterFlow {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[][] matrix = new int[m][n];
		for(int i = 0 ; i < m ; i++){
			for(int j = 0 ; j < n ; j ++){
				matrix[i][j] = in.nextInt();
			}
		}
		for(int[] x :pacificAtlantic2(matrix)){
			System.out.println(x[0] + " , " + x[1]);
		}
	}
	//方法1：
	//BFS
	public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return list;
        }
        int m = matrix.length,n = matrix[0].length;
        int[][] flow = new int[m][n];
        Queue<int[]> poQueue = new LinkedList<int[]>();
        Queue<int[]> aoQueue = new LinkedList<int[]>();
        //初始化
        for(int i = 0 ; i < m ; i++){
            // flow[i][0] |= 2;        //Pacific ocean
            poQueue.add(new int[]{i,0});
            // flow[i][n-1] |= 1;      //Atlantic ocean
            aoQueue.add(new int[]{i,n-1});
        }
        for(int j = 0 ; j < n ; j++){
            // flow[0][j] |= 2;        //Pacific ocean
            poQueue.add(new int[]{0,j});
            // flow[m-1][j] |= 1;      //Atlantic ocean
            aoQueue.add(new int[]{m-1,j});
        }
        while(!poQueue.isEmpty()){
            int[] cur = poQueue.poll();
            flow[cur[0]][cur[1]] |= 2;
            //up
            if(cur[0]-1 >= 0 
                && (flow[cur[0]-1][cur[1]]&2) == 0 
                && matrix[cur[0]-1][cur[1]] >= matrix[cur[0]][cur[1]]){
                poQueue.offer(new int[]{cur[0]-1,cur[1]});
            }
            //down
            if(cur[0]+1 < m 
                && (flow[cur[0]+1][cur[1]]&2) == 0 
                && matrix[cur[0]+1][cur[1]] >= matrix[cur[0]][cur[1]]){
                poQueue.offer(new int[]{cur[0]+1,cur[1]});
            }
            //left
            if(cur[1]-1 >= 0 
                && (flow[cur[0]][cur[1]-1]&2) == 0 
                && matrix[cur[0]][cur[1]-1] >= matrix[cur[0]][cur[1]]){
                poQueue.offer(new int[]{cur[0],cur[1]-1});
            }
            //right
            if(cur[1]+1 < n 
                && (flow[cur[0]][cur[1]+1]&2) == 0 
                && matrix[cur[0]][cur[1]+1] >= matrix[cur[0]][cur[1]]){
                poQueue.offer(new int[]{cur[0],cur[1]+1});
            }
        }
        while(!aoQueue.isEmpty()){
            int[] cur = aoQueue.poll();
            if((flow[cur[0]][cur[1]]&1) == 1){
                continue;
            }
            flow[cur[0]][cur[1]] |= 1;
            //up
            if(cur[0]-1 >= 0 
                && (flow[cur[0]-1][cur[1]]&1) == 0 
                && matrix[cur[0]-1][cur[1]] >= matrix[cur[0]][cur[1]]){
                aoQueue.offer(new int[]{cur[0]-1,cur[1]});
            }
            //down
            if(cur[0]+1 < m 
                && (flow[cur[0]+1][cur[1]]&1) == 0 
                && matrix[cur[0]+1][cur[1]] >= matrix[cur[0]][cur[1]]){
                aoQueue.offer(new int[]{cur[0]+1,cur[1]});
            }
            //left
            if(cur[1]-1 >= 0 
                && (flow[cur[0]][cur[1]-1]&1) == 0 
                && matrix[cur[0]][cur[1]-1] >= matrix[cur[0]][cur[1]]){
                aoQueue.offer(new int[]{cur[0],cur[1]-1});
            }
            //right
            if(cur[1]+1 < n 
                && (flow[cur[0]][cur[1]+1]&1) == 0 
                && matrix[cur[0]][cur[1]+1] >= matrix[cur[0]][cur[1]]){
                aoQueue.offer(new int[]{cur[0],cur[1]+1});
            }
            if(flow[cur[0]][cur[1]] == 3){
                list.add(cur);
            }
        }
        return list;
    }
	//方法2：
	//方法1的简化写法
	public static List<int[]> pacificAtlantic2(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return list;
        }
        int m = matrix.length,n = matrix[0].length;
        int[][] flow = new int[m][n];
        Queue<int[]> poQueue = new LinkedList<int[]>();
        Queue<int[]> aoQueue = new LinkedList<int[]>();
        //初始化
        for(int i = 0 ; i < m ; i++){
            // flow[i][0] |= 2;        //Pacific ocean
            poQueue.add(new int[]{i,0});
            // flow[i][n-1] |= 1;      //Atlantic ocean
            aoQueue.add(new int[]{i,n-1});
        }
        for(int j = 0 ; j < n ; j++){
            // flow[0][j] |= 2;        //Pacific ocean
            poQueue.add(new int[]{0,j});
            // flow[m-1][j] |= 1;      //Atlantic ocean
            aoQueue.add(new int[]{m-1,j});
        }
        while(!poQueue.isEmpty()){
            int[] cur = poQueue.peek();
            if((flow[cur[0]][cur[1]]&2) == 2){
                poQueue.poll();
                continue;
            }
            bfs(poQueue,matrix,flow,2);
        }
        while(!aoQueue.isEmpty()){
            int[] cur = aoQueue.peek();
            if((flow[cur[0]][cur[1]]&1) == 1){
                aoQueue.poll();
                continue;
            }
            bfs(aoQueue,matrix,flow,1);
            if(flow[cur[0]][cur[1]] == 3){
                list.add(cur);
            }
        }
        return list;
    }
    private static void bfs(Queue<int[]> queue,int[][] matrix,int[][] flow,int mask){
        int[] cur = queue.poll();
        flow[cur[0]][cur[1]] |= mask;
        //up
        if(cur[0]-1 >= 0 
            && (flow[cur[0]-1][cur[1]]&mask) == 0 
            && matrix[cur[0]-1][cur[1]] >= matrix[cur[0]][cur[1]]){
            queue.offer(new int[]{cur[0]-1,cur[1]});
        }
        //down
        if(cur[0]+1 < matrix.length 
            && (flow[cur[0]+1][cur[1]]&mask) == 0 
            && matrix[cur[0]+1][cur[1]] >= matrix[cur[0]][cur[1]]){
            queue.offer(new int[]{cur[0]+1,cur[1]});
        }
        //left
        if(cur[1]-1 >= 0 
            && (flow[cur[0]][cur[1]-1]&mask) == 0 
            && matrix[cur[0]][cur[1]-1] >= matrix[cur[0]][cur[1]]){
            queue.offer(new int[]{cur[0],cur[1]-1});
        }
        //right
        if(cur[1]+1 < matrix[0].length 
            && (flow[cur[0]][cur[1]+1]&mask) == 0 
            && matrix[cur[0]][cur[1]+1] >= matrix[cur[0]][cur[1]]){
            queue.offer(new int[]{cur[0],cur[1]+1});
        }
    }
    //方法3：
    //DFS
    public List<int[]> pacificAtlantic3(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return list;
        }
        int m = matrix.length,n = matrix[0].length;
        int[][] flow = new int[m][n];
        Queue<int[]> poQueue = new LinkedList<int[]>();
        Queue<int[]> aoQueue = new LinkedList<int[]>();
        //Pacific ocean
        for(int i = 0 ; i < m ; i++){
            dfs(matrix,flow,2,new int[]{i,0},list);
        }
        for(int j = 0 ; j < n ; j++){
            dfs(matrix,flow,2,new int[]{0,j},list);
        }
        //Atlantic ocean
        for(int i = 0 ; i < m ; i++){
            dfs(matrix,flow,1,new int[]{i,n-1},list);
        }
        for(int j = 0 ; j < n ; j++){
            dfs(matrix,flow,1,new int[]{m-1,j},list);
        }
        return list;
    }
    private void dfs(int[][] matrix,int[][] flow,int mask,int[] cur,List<int[]> list){
        if((flow[cur[0]][cur[1]]&mask) == mask){
            return;
        }
        flow[cur[0]][cur[1]] |= mask;
        //up
        if(cur[0]-1 >= 0 
            && (flow[cur[0]-1][cur[1]]&mask) == 0 
            && matrix[cur[0]-1][cur[1]] >= matrix[cur[0]][cur[1]]){
            dfs(matrix,flow,mask,new int[]{cur[0]-1,cur[1]},list);
        }
        //down
        if(cur[0]+1 < matrix.length 
            && (flow[cur[0]+1][cur[1]]&mask) == 0 
            && matrix[cur[0]+1][cur[1]] >= matrix[cur[0]][cur[1]]){
            dfs(matrix,flow,mask,new int[]{cur[0]+1,cur[1]},list);
        }
        //left
        if(cur[1]-1 >= 0 
            && (flow[cur[0]][cur[1]-1]&mask) == 0 
            && matrix[cur[0]][cur[1]-1] >= matrix[cur[0]][cur[1]]){
            dfs(matrix,flow,mask,new int[]{cur[0],cur[1]-1},list);
        }
        //right
        if(cur[1]+1 < matrix[0].length 
            && (flow[cur[0]][cur[1]+1]&mask) == 0 
            && matrix[cur[0]][cur[1]+1] >= matrix[cur[0]][cur[1]]){
            dfs(matrix,flow,mask,new int[]{cur[0],cur[1]+1},list);
        }
        if(flow[cur[0]][cur[1]] == 3){
            list.add(cur);
        }
    }
}
