package algorithm.leetcode.algorithm;
/*
 * medium
 * 593. Valid Square 
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True

Note:
    1.	All the input integers are in the range [-10000, 10000].
    2.	A valid square has four equal sides with positive length and four equal angles (90-degree angles).
    3.	Input points have no order.

 */
import java.util.*;
public class NO593_ValidSquare {
	//计算每两个点之间的距离，用map进行计数
	//如果距离不是只有2个，或者最大距离的计数不是只有2，则返回false
	//否则返回true
    //1 .There are only two equal longest lenghts.
    //2. The non longest lengths are all equal.
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = new int[][]{p1,p2,p3,p4};
        Map<Integer,Integer> map = new HashMap<>();
        int d,max = 0;
        for(int i = 0 ; i < p.length ; i++){
            for(int j = i+1 ; j < p.length ; j++){
                d = distance(p[i],p[j]);
                max = Math.max(max,d);
                if(!map.containsKey(d)){
                    map.put(d,0);
                }
                map.put(d,map.get(d)+1);
            }
        }
        if(map.keySet().size() != 2 || !map.containsKey(max) || map.get(max)!=2){
            return false;
        }
        return true;
    }
    private int distance(int[] p1,int[] p2){
        return (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]);
    }
}
