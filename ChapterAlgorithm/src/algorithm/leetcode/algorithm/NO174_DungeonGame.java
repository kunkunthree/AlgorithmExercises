package algorithm.leetcode.algorithm;
/*
 * hard
 * 174. Dungeon Game 
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned 
 * in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops 
to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering 
these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health 
(positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or 
downward in each step.

Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows 
the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K) 	-3 	3
-5 	-10 	1
10 	30 	-5 (P)

Notes:
    The knight's health has no upper bound.
    Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

 */
import java.util.*;
public class NO174_DungeonGame {
	public static void main(String[] args) {
		int[][] dungeon = new int[][]{{1,-3,3},{0,-2,0},{-3,-3,-3}};
		System.out.println(calculateMinimumHP(dungeon));
	}
	//方法1：
	//dp，动态规划，记录到达从右下角到左上角中途某个点所需的最小生命值
	//O(m*n)time,O(m*n)space
	public static int calculateMinimumHP(int[][] dungeon) {
		if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0){
            return 1;
        }
        int m = dungeon.length,n = dungeon[0].length;
        int[][] health = new int[m][n];
        for(int i = m-1 ; i >= 0 ; i--){
            for(int j = n-1 ; j >= 0 ; j--){
                if(i == m-1 && j == n-1){
                    health[m-1][n-1] = Math.max(1-dungeon[m-1][n-1],1);
                }else if(i == m-1){
                    health[i][j] = Math.max(health[i][j+1]-dungeon[i][j],1);
                }else if(j == n-1){
                    health[i][j] = Math.max(health[i+1][j]-dungeon[i][j],1);
                }else{
                    int down = Math.max(health[i+1][j]-dungeon[i][j],1);
                    int right = Math.max(health[i][j+1]-dungeon[i][j],1);
                    health[i][j] = Math.min(right,down);
                }
            }
        }
        return health[0][0];
    }
	//方法2：
	//dp，动态规划，假设初始生命值是1，开始从[m-1,n-1]开始向[0,0]走，记录中途所需要的最小生命值
	//O(m*n)time,O(m*n)space
	public int calculateMinimumHP2(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0){
            return 1;
        }
        int m = dungeon.length,n = dungeon[0].length;
        int[][] health = new int[m+1][n+1];
        for(int[] row : health){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        health[m][n-1] = 1;
        health[m-1][n] = 1;
        for(int i = m-1 ; i >= 0 ; i--){
            for(int j = n-1 ; j >= 0 ; j--){
                int need = Math.min(health[i + 1][j], health[i][j + 1]) - dungeon[i][j];
                health[i][j] = need <= 0 ? 1 : need;
            }
        }
        return health[0][0];
    }
	//方法3：
	//O(m*n)time,O(1)space
	public int calculateMinimumHP3(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0){
            return 1;
        }
        int m = dungeon.length,n = dungeon[0].length;
        for(int i = m-1 ; i >= 0 ; i--){
            for(int j = n-1 ; j >= 0 ; j--){
                if(i == m-1 && j == n-1){
                    dungeon[m-1][n-1] = Math.max(1-dungeon[m-1][n-1],1);
                }else if(i == m-1){
                    dungeon[i][j] = Math.max(dungeon[i][j+1]-dungeon[i][j],1);
                }else if(j == n-1){
                    dungeon[i][j] = Math.max(dungeon[i+1][j]-dungeon[i][j],1);
                }else{
                    int down = Math.max(dungeon[i+1][j]-dungeon[i][j],1);
                    int right = Math.max(dungeon[i][j+1]-dungeon[i][j],1);
                    dungeon[i][j] = Math.min(right,down);
                }
            }
        }
        return dungeon[0][0];
    }
	//方法4：
	//dfs+cache
	private int result = Integer.MAX_VALUE;
    private int[][] dirs = {{1,0}, {0,1}};
    public int calculateMinimumHP4(int[][] dungeon) {
        int[][] cache = new int[dungeon.length][dungeon[0].length];
        int res = dfs(dungeon, 0, 0, cache);
        return res > 0 ? 1 : ((-1)*res+1);
        
    }
    private int dfs(int[][] dungeon, int x, int y, int[][] cache) {
        if(x == dungeon.length-1 && y == dungeon[0].length-1) {
            return dungeon[x][y] > 0 ? 0 : dungeon[x][y];
        }

        if(cache[x][y] != 0) {
            return cache[x][y] == -1 ? 0 : cache[x][y];
        }
        
        int result = Integer.MIN_VALUE;
        for(int[] dir : dirs) {
            int x0 = x + dir[0];
            int y0 = y + dir[1];

            if(x0 >= dungeon.length || y0 >= dungeon[0].length) {
                continue;
            }
            result = Math.max(result, dfs(dungeon, x0, y0, cache));
        }
        
        cache[x][y] = (result+dungeon[x][y]) > 0 ? -1 : (result+dungeon[x][y]);
        return (result+dungeon[x][y]) > 0 ? 0 : (result+dungeon[x][y]);
    }
}
