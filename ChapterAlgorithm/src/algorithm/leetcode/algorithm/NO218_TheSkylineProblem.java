package algorithm.leetcode.algorithm;
/*
 * hard
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city 
 * when viewed from a distance. Now suppose you are given the locations and height of all the buildings 
 * as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings 
 * collectively (Figure B).
 * see NO218_TheSkylineProblem_1.png
 * see NO218_TheSkylineProblem_2.png
 * 
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, 
 * and Hi is its height. It is guaranteed that 0 ? Li, Ri ? INT_MAX, 0 < Hi ? INT_MAX, and Ri - Li > 0. 
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: 
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] 
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, is merely used to mark the termination
 of the skyline, and always has zero height. 
 Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:
    1.		The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    2.		The input list is already sorted in ascending order by the left x position Li.
    3.		The output list must be sorted by the x position.
    4.		There must be no consecutive horizontal lines of equal height in the output skyline. 
    		For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
    			the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
    			
解析：
https://briangordon.github.io/2014/08/the-skyline-problem.html
 */
import java.util.*;
public class NO218_TheSkylineProblem {
	//方法1：
	//O(nlogn)time,O(n)space
	//1.将所有矩形的左边和右边都按照从左往右，从大到小的顺序排序
	//		注意：左边为了排在右边的前面且高的优先，则左边以其高度的相反数作为排序依据
	//2.用一个PriorityQueue保存当前坐标点的最大高度
	//3.按照排序后的顺序遍历所有边：
	//		3.1	如果是左边，则将该边的高度（非负）加入队列中
	//				如果是右边，则将队列中对应的高度去除
	//		3.2	以队列的头为当前最大高度cur，如果当前最大高度cur与上一个最大高度pre不相等，
	//				那么把当前横坐标和当前最大高度cur加入到结果集
	//				否则进行下一次循环3
	public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if(buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0){
            return result;
        }
        List<int[]> heights = new ArrayList<>();
        for(int[] b : buildings){
        	// start point has negative height value
        	//起始点高度用负数表示
            heights.add(new int[]{b[0],-b[2]});
            //终止点高度用正常值表示
            // end point has normal height value
            heights.add(new int[]{b[1],b[2]});
        }
        // sort $height, based on the first value, if necessary, use the second to break ties
        //根据横坐标从小到大排序，横坐标相等时，根据高度从小到大排序
        Collections.sort(heights,new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            }
        });
        // Use a maxHeap to store possible heights
        //用最大堆存储可能的高度
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(1,new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });
        // Provide a initial value to make it more consistent
        //提供一个初始值，表示没有矩形时的高度
        queue.offer(0);
        // Before starting, the previous max height is 0;
        //开始前，设置上一个高度为0
        int pre = 0,cur;
        // visit all points in order
        //按照顺序遍历所有点
        for(int[] h : heights){
            if(h[1] < 0){		// a start point, add height	如果是一个起始点，则其高度进入队列
                queue.offer(-h[1]);
            }else{				// a end point, remove height	如果是一个终止点，则将其高度从队列中删除
                queue.remove(h[1]);
            }
            // current max height;
            //得到当前最大高度
            cur = queue.peek();
            
            // compare current max height with previous max height, update result and 
            // previous max height if necessary
            //比较当前最大高度和上一个最大高度，如果两个高度不相等，则将当前横坐标和当前最大高度加入结果集
            if(cur != pre){
                result.add(new int[]{h[0],cur});
                pre = cur;
            }
        }
        return result;
    }
	//方法2：
	//O(n^2)time,O(n)space
	//	for each rectangle r:
	//	    for each critical point c:
	//	        if c.x >= r.left && c.x < r.right:
	//	            c.y gets the max of r.height and the previous value of c.y
	public List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if(buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0){
            return result;
        }
        List<int[]> heights = new ArrayList<>();
        for(int[] b : buildings){
            heights.add(new int[]{b[0],-b[2]});
            heights.add(new int[]{b[1],b[2]});
        }
        Map<Integer,Integer> map = new TreeMap<>();
        Set<Integer> criticals = new HashSet<Integer>();
        for(int[] b : buildings){
            criticals.add(b[0]);
            criticals.add(b[1]);
        }
        for(int[] b : buildings){
            if(!map.containsKey(b[0])){
                map.put(b[0],0);
            }
            if(!map.containsKey(b[1])){
                map.put(b[1],0);
            }
            for(int x : criticals){
                if(x >= b[0] && x < b[1]){
                    if(!map.containsKey(x)){
                        map.put(x,b[2]);
                    }else{
                        map.put(x,Math.max(b[2],map.get(x)));
                    }
                }
            }
        }
        for(Integer x : map.keySet()){
            if(result.isEmpty() || result.get(result.size()-1)[1] != map.get(x)){
                result.add(new int[]{x,map.get(x)});
            }
        }
        return result;
    }
	//方法3：
	//O(n^2)time,O(n)space
	//	for each critical point c:
	//	    for each rectangle r:
	//	        if c.x >= r.left && c.x < r.right:
	//	            c.y gets the max of r.height and the previous value of c.y
	public List<int[]> getSkyline3(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if(buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0){
            return result;
        }
        List<int[]> heights = new ArrayList<>();
        for(int[] b : buildings){
            heights.add(new int[]{b[0],-b[2]});
            heights.add(new int[]{b[1],b[2]});
        }
        Map<Integer,Integer> map = new TreeMap<>();
        Set<Integer> criticals = new HashSet<Integer>();
        for(int[] b : buildings){
            criticals.add(b[0]);
            criticals.add(b[1]);
        }
        for(int x : criticals){
            for(int[] b : buildings){
                if(x >= b[0] && x < b[1]){
                    if(!map.containsKey(x)){
                        map.put(x,b[2]);
                    }else{
                        map.put(x,Math.max(b[2],map.get(x)));
                    }
                }
                if(!map.containsKey(x)){
                    map.put(x,0);
                }
            }
        }
        for(Integer x : map.keySet()){
            if(result.isEmpty() || result.get(result.size()-1)[1] != map.get(x)){
                result.add(new int[]{x,map.get(x)});
            }
        }
        return result;
    }
}
