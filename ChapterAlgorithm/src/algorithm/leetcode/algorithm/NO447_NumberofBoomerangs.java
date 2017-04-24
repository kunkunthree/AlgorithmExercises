package algorithm.leetcode.algorithm;
/*
 * easy
 * 447. Number of Boomerangs
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) 
 * such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * Find the number of boomerangs. 
 * You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:

Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

 */
import java.util.*;
public class NO447_NumberofBoomerangs {
	public static void main(String[] args) {
		System.out.println(numberOfBoomerangs(new int[][]{{0,0},{1,0},{2,0}}));
	}
	//统计以每个顶点为起点的距离相等的顶点个数，并计算排列方式
    public static int numberOfBoomerangs(int[][] points) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int count = 0;
        for(int i = 0 ; i < points.length ; i++){
            for(int j = 0 ; j < points.length ; j++){
                if(i != j){
                    int x = points[j][0]-points[i][0];
                    int y = points[j][1]-points[i][1];
                    int d = x*x+y*y;
                    if(map.containsKey(d)){
                        map.put(d,map.get(d)+1);
                    }else{
                        map.put(d,1);
                    }
                }
            }
            for(int n : map.values()){
                count+=n*(n-1);
            }
            map.clear();
        }
        return count;
    }
}
