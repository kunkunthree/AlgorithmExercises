package algorithm.leetcode.algorithm;
/*
 * medium
 * 554. Brick Wall 
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. 
 * The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom 
 * and cross the least bricks.

The brick wall is represented by a list of rows. 
Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. 
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, 
in which case the line will obviously cross no bricks.

Example:

Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
Explanation: 
see NO554_BrickWall.png

Note:
    1.	The width sum of bricks in different rows are the same and won't exceed INT_MAX.
    2.	The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. 
    		Total number of bricks of the wall won't exceed 20,000.

 */
import java.util.*;
public class NO554_BrickWall {
	public static void main(String[] args) {
		List<List<Integer>> wall = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		int n = Integer.valueOf(in.nextLine());
		for(int i = 0 ; i < n ; i++){
			List<Integer> row = new ArrayList<Integer>();
			String[] ss = in.nextLine().split(" ");
			for(String s : ss){
				row.add(Integer.valueOf(s));
			}
			wall.add(row);
		}
		System.out.println(leastBricks(wall));
	}
	//方法1：
	//每行用一个指针，每次指向最低的那个缝隙，看该缝隙所在为止的缝隙的个数，得到最大个数maxCount
	//最终结果为行数n-maxCount
	//超时
	public static int leastBricks(List<List<Integer>> wall) {
        if(wall == null || wall.size() == 0){
            return 0;
        }
        int n = wall.size(),width = 0,maxCount = 0,tmpMin = 0,preMin;
        for(int brick : wall.get(0)){
            width+=brick;
        }
        int[] index = new int[n];
        int[] sum = new int[n];
        List<Integer> row;
        while(tmpMin < width){
            preMin = tmpMin;
            tmpMin = Integer.MAX_VALUE;
            for(int i = 0 ; i < n ; i++){
                row = wall.get(i);
                if(sum[i] > preMin){
                    tmpMin = Math.min(tmpMin,sum[i]);
                }else if(index[i] < row.size()){
                    tmpMin = Math.min(tmpMin,sum[i]+row.get(index[i]));
                }
            }
            int tmpCount = 0;
            if(tmpMin < width){
	            for(int i = 0 ; i < n ; i++){
	                row = wall.get(i);
	                while(sum[i] < tmpMin){
	                    sum[i]+=row.get(index[i]++);
	                }
	                if(sum[i] == tmpMin){
	                    tmpCount++;
	                }
            	}
            }
            maxCount = Math.max(maxCount,tmpCount);
        }
        return n-maxCount;
    }
	
	//方法2：
	//用map记录每一行的每一个中间缝隙的位置出现的次数
	public int leastBricks2(List<List<Integer>> wall) {
        if(wall == null || wall.size() == 0){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();
        int n = wall.size(),width = 0,maxCount = 0;
        for(int brick : wall.get(0)){
            width+=brick;
        }
        List<Integer> row;
        for(int i = 0 ; i < n ; i++){
            row = wall.get(i);
            int sum = 0;
            for(int j = 0 ; j < row.size()-1; j++){
                sum+=row.get(j);
                if(!map.containsKey(sum)){
                    map.put(sum,0);
                }
                map.put(sum,map.get(sum)+1);
                maxCount = Math.max(maxCount,map.get(sum));
            }
        }
        return n-maxCount;
    }
}
