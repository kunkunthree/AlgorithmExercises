package algorithm.leetcode.algorithm;
/*
 * hard
 * 149. Max Points on a Line 
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
import java.util.*;
public class NO149_MaxPointsOnALine {
	public static void main(String[] args) {
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,-1);
		Point p3 = new Point(0,2);
		Point[] points = new Point[]{p1,p2,p3};
		System.out.println(maxPoints(points));
	}
	//方法1：
	//O(n^2)time
	//两重循环
	//用一个两层的map记录斜率，同时每次内部循环统计与该点相同的点的个数
	public static int maxPoints(Point[] points) {
		Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        int max = points.length == 0 ? 0 : 1;
        for(int i = 0 ; i < points.length ; i++){
            int tmpMax = 1;
            int count = 0;
            map.clear();
            for(int j = i+1 ; j < points.length ; j++){
                int x = points[j].x-points[i].x;
                int y = points[j].y-points[i].y;
                if(x == 0 && y == 0){
                    count++;
                }else{
                    int gcd = getGCD(x,y);
                    x = x/gcd;
                    y = y/gcd;
                    if (!map.containsKey(x)){
                        Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        				map.put(x, m);
        			}
    				if (!map.get(x).containsKey(y)){
    				    map.get(x).put(y, 1);
    				}
    				map.get(x).put(y, map.get(x).get(y)+1);
    				tmpMax = Math.max(tmpMax,map.get(x).get(y));
                }
            }
            max = Math.max(max,tmpMax+count);
        }
        return max;
    }
	private static int getGCD(int a,int b){
    	if (b==0) return a;
    	else return getGCD(b,a%b);
    }
	//方法2：
	//三重循环，不用map，速度快
	public int maxPoints2(Point[] points) {
        if(points == null){
            return 0;
        }
        int len = points.length;
        if(len < 3){
            return len;
        }
        int res = 2;
        for(int i = 0; i < len - 1; i++){
            int dup = 1;
            for(int j = i + 1; j < len; j++){
                int count = 0;
                int dx = points[j].x - points[i].x;
                int dy = points[j].y - points[i].y;
                if(dx == 0 && dy == 0){
                    dup++;
                }
                else{
                    count = 1;
                    for(int k = j + 1; k < len; k++){
                        int deltaX = points[k].x - points[i].x;
                        int deltaY = points[k].y - points[i].y;
                        if((long)deltaX * dy == (long)deltaY * dx){
                            count++;
                        }
                    }
                }
                res = Math.max(res, dup + count);
            }
        }
        return res;
    }
}
