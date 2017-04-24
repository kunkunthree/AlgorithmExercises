package algorithm.leetcode.algorithm;
/*
 * easy
 * 463. Island Perimeter
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
NO463_IslandPerimeter.png
 */
public class NO463_IslandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        int count = 0,add = 0;
        boolean down = false,up = false;
        //水平方向上映射
        for(int i = 0 ; i < grid.length ; i ++){
            add = 0;
            down = false;
            for(int j = 0 ; j < grid[i].length ; j++){
                if(grid[i][j] > 0){
                    add = 2;
                }
                //映射是否被覆盖，填补覆盖的部分
                if(j < grid[i].length-1){
                    if(down == false){
                        if(grid[i][j] > grid[i][j+1]){    //凹下
                            down = true;
                        }
                    }else{
                        if(grid[i][j] < grid[i][j+1]){    //凹下后凸起
                            count+=2;
                            down = false;
                        }
                    }
                }
            }
            count+=add;
        }
        //垂直方向上映射
        for(int i = 0 ; i < grid[0].length ; i++){
            add = 0;
            down = false;
            for(int j = 0 ; j < grid.length ; j++){
                if(grid[j][i] > 0){
                    add = 2;
                }
                //映射是否被覆盖，填补覆盖的部分
                if(j < grid.length-1){
                    if(down == false){
                        if(grid[j][i] > grid[j+1][i]){    //凹下
                            down = true;
                        }
                    }else{
                        if(grid[j][i] < grid[j+1][i]){    //凹下后凸起
                            count+=2;
                            down = false;
                        }
                    }
                }
            }
            count+=add;
        }
        return count;
    }
    //更简洁写法，直接判断某个方格的左边或上边是否存在
    //每存在一格陆地，加4，每个陆地如果左边或上边都存在陆地，即说明相邻，内部相接的两条边合并不计，则减2。
    public static int islandPerimeter2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i-1][j] == 1) result -= 2;
                    if (j > 0 && grid[i][j-1] == 1) result -= 2;
                }
            }
        }
        return result;
    }
}
