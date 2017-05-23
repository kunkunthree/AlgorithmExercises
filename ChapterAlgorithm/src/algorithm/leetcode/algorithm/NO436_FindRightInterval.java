package algorithm.leetcode.algorithm;
/*
 * medium
 * 436. Find Right Interval 
 *  Given a set of intervals, for each of the interval i, check if there exists an interval j 
 *  whose start point is bigger than or equal to the end point of the interval i, 
 *  which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, 
which means that the interval j has the minimum start point to build the "right" relationship for interval i. 
If the interval j doesn't exist, store -1 for the interval i. 
Finally, you need output the stored value of each interval as an array.

Note:

    You may assume the interval's end point is always bigger than its start point.
    You may assume none of these intervals have the same start point.

Example 1:
Input: [ [1,2] ]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: [ [3,4], [2,3], [1,2] ]
Output: [-1, 0, 1]
Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.

Example 3:
Input: [ [1,4], [2,3], [3,4] ]
Output: [-1, 2, -1]
Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.

 */
import java.util.*;
import java.util.Map.Entry;
public class NO436_FindRightInterval {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Interval[] intervals = new Interval[n];
		for(int i = 0 ; i < n ; i++){
			intervals[i] = new Interval(in.nextInt(),in.nextInt());
		}
		System.out.println(Arrays.toString(findRightInterval(intervals)));
	}
	//方法1：
	//利用NavigableMap接口
	//ceilingEntry(key):用于获取大于或等于给定key的第一个实体，如果没有则返回null。
	//firstEntry():用于获取Map的第一个实体，如果没有则返回null。
	//floorEntry(key):用于获取小于或等于给定的第一个实体key，如果没有则返回null。
	//higherEntry():用于获取大于给定的key的第一个实体，如果没有则返回null。
	//lastEntry():用于获取Map最后一个实体，如果没有则返回null。
	//lowerEntry(key):用于获取小于给定key的第一个实体，如果没有则返回null。 
	public static int[] findRightInterval(Interval[] intervals) {
        NavigableMap<Integer,Integer> map = new TreeMap<>();
        int n = intervals.length,minStart;
        for(int i = 0 ; i < n ; i++){
            map.put(intervals[i].start,i);
        }
        int[] minIndex = new int[n];
        Arrays.fill(minIndex,-1);
        for(int i = 0 ; i < n ; i++){
            Map.Entry<Integer,Integer> entry = map.ceilingEntry(intervals[i].end);
            minIndex[i] = entry == null ? -1 : entry.getValue();
        }
        return minIndex;
    }
	//方法2：
	//sweep line算法
	//把线段的头节点和尾节点拆开一起从小到大排序，当节点的值相同时，尾节点排在头节点前面
	//遇到一个尾节点，就把这个头节点保存起来
	//遇到一个头节点，则把这个头节点之前所保存的节点所对应的下标的right设置成头节点的的值，清空保存的尾节点
	class Point implements Comparable<Point>{
        int val;
        int flag; //0:start,1:end
        int index;
        Point(int val,int flag,int index){
            this.val = val;
            this.flag = flag;
            this.index = index;
        }
        public int compareTo(Point p){
            if(this.val == p.val){
                return p.flag - this.flag;  //end first
            }
            return this.val-p.val;
        }
    }
    public int[] findRightInterval2(Interval[] intervals) {
        List<Point> list = new ArrayList<>();
        int n = intervals.length,minStart;
        for(int i = 0 ; i < n ; i++){
            list.add(new Point(intervals[i].start,0,i));
            list.add(new Point(intervals[i].end,1,i));
        }
        Collections.sort(list);
        List<Integer> preIndexs = new ArrayList<>();
        int[] minIndex = new int[n];
        Arrays.fill(minIndex,-1);
        for(Point p : list){
            if(p.flag == 0){
                for(int index : preIndexs){
                    minIndex[index] = p.index;
                }
                preIndexs.clear();
            }else{
                preIndexs.add(p.index);
            }
        }
        return minIndex;
    }
    //方法3：
    //先用map把start和index对应起来，保存start列表并从小到大排序，然后通过二分搜索start列表
    //如果搜索到的start比当前节点的end小，那么right为-1，否则，right为start在map中对应的index值
    public int[] findRightInterval3(Interval[] intervals) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> starts = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
            starts.add(intervals[i].start);
        }
        
        Collections.sort(starts);
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i].end;
            int start = binarySearch(starts, end);
            if (start < end) {
                res[i] = -1;
            } else {
                res[i] = map.get(start);
            }
        }
        return res;
    }
    public int binarySearch(List<Integer> list, int x) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < x) { 
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return list.get(left);
    }
}
