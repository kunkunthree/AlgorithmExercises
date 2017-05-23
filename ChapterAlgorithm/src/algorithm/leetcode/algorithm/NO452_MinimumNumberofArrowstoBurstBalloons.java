package algorithm.leetcode.algorithm;
/*
 * medium
 * 452. Minimum Number of Arrows to Burst Balloons
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon,
 *  provided input is the start and end coordinates of the horizontal diameter. 
 *  Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start 
 *  and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. 
A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
There is no limit to the number of arrows that can be shot. 
An arrow once shot keeps travelling up infinitely. 
The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 
(bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).

 */
import java.util.*;
public class NO452_MinimumNumberofArrowstoBurstBalloons {
	public static void main(String[] args) {
		int[][] points = new int[][]{{-2147483648,2147483647}};
		System.out.println(findMinArrowShots(points));
	}
	//方法1：
	//sweep line算法，把点的头部坐标和尾部坐标分开，一起进行从小到大排序，
	//从小到大遍历，计数初始为0
	//如果遇到头节点，则用set把该节点存起来，
	//如果遇到尾节点且其头节点在set中，则把set中所有点清除，计数加1
	public static int findMinArrowShots(int[][] points) {
		int rest = points.length;
        List<int[]> list = new ArrayList<>();
        Set<Integer> preStart = new HashSet<>();
        for(int[] point : points){
            list.add(new int[]{point[0],point[1],0});
            list.add(new int[]{point[0],point[1],1});
        }
        Collections.sort(list,new Comparator<int[]>(){
            public int compare(int[] p1,int[] p2){
                int type1 = p1[2],type2 = p2[2];
                if(p1[type1] == p2[type2]){
                    return type1-type2;
                }
                return p1[type1] < p2[type2] ? -1 : 1;
            }
        });
        int count = 0;
        for(int i = 0 ; i < list.size() ; i++){
            int[] tmp = list.get(i);
            if(tmp[2] == 1){
                if(preStart.contains(tmp[0])){
                    count++;
                    preStart.clear();
                }
            }else{
                preStart.add(tmp[0]);
            }
        }
        return count;
    }
	//方法2：
	//贪婪算法
	//思想和方法1相同，先将原数组进行排序，头部坐标小的优先在前，头部坐标相等的情况下，尾部坐标小的优先在前
	//遍历数组所有区间，记录当前最小的尾部坐标，
	//如果遍历到的区间头部坐标小于等于该尾部坐标，则记录当前最小的尾部坐标
	//如果遍历到的区间头部坐标大于等于该尾部坐标，则计数加1，更新最小尾部坐标为当前区间的尾部坐标
	public int findMinArrowShots2(int[][] points) {
		if(points==null || points.length==0 || points[0].length==0) return 0;
		Arrays.sort(points, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[0]==b[0]) return a[1]-b[1];
				else return a[0]-b[0];
			}
		});
		
		int minArrows = 1;
		int arrowLimit = points[0][1];
		for(int i=1;i<points.length;i++) {
			int[] baloon = points[i];
			if(baloon[0]<=arrowLimit) {
				arrowLimit=Math.min(arrowLimit, baloon[1]);
			} else {
				minArrows++;
				arrowLimit=baloon[1];
			}
		}
		return minArrows;
	}
}
