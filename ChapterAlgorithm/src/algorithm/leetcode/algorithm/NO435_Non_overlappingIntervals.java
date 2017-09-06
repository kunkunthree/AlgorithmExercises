package algorithm.leetcode.algorithm;
/*
 * medium
 * 435. Non-overlapping Intervals
 *  Given a collection of intervals, find the minimum number of intervals you need to remove to
 *   make the rest of the intervals non-overlapping.
Note:
    You may assume the interval's end point is always bigger than its start point.
    Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]
Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
Input: [ [1,2], [1,2], [1,2] ]
Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
Input: [ [1,2], [2,3] ]
Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

 */
import java.util.*;
public class NO435_Non_overlappingIntervals {
	//方法1：
	//利用PriorityQueue进行排序，再一个一个去掉范围大或有重叠且靠后的区间
	public int eraseOverlapIntervals(Interval[] intervals) {
        PriorityQueue<Interval> queue = new PriorityQueue<Interval>(1,new Comparator<Interval>(){
            public int compare(Interval i1,Interval i2){
                if(i1.start == i2.start){
                    return i1.end-i2.end;
                }else{
                    return i1.start-i2.start;
                }
            } 
        });
        for(Interval interval : intervals){
            queue.offer(interval);
        }
        int count = 0;
        Interval cur = null,pre = null;
        while(!queue.isEmpty()){
            if(pre == null){
                pre = queue.poll();
            }else{
                cur = queue.poll();
                if(cur.start == pre.start){	//例如：[1,2] [1,3]	去掉范围大的
                    count++;
                }else if(cur.start < pre.end && cur.end <= pre.end){ //例如：[1,4] [2,3]	去掉范围大的
                    pre = cur;
                    count++;
                }else if(cur.start < pre.end && cur.end > pre.end){		//例如：	[1,4] [2,5]	//去掉重叠且更靠后的
                    count++;
                }else{			//cur.start >= pre.end 例如：[1,4] [4,5]	不重叠的
                    pre = cur;
                }
            }
        }
        return count;
    }
	//方法2：
	//去掉最少的区间使剩下的区间不重叠《===》得到最多的不重叠区间
	//得到最多的不重叠区间问题：可以先对end从小到大排序，得到最多的不重叠区间的数目
	//所有区间的个数减去最多的不重叠区间的个数就是去掉的区间的最少个数
	public int eraseOverlapIntervals2(Interval[] intervals) {
        Arrays.sort(intervals,new Comparator<Interval>(){
            public int compare(Interval i1,Interval i2){
                return i1.end-i2.end;
            } 
        });
        Interval cur = null,pre = null;
        int count = 0;
        for(Interval interval : intervals){
            if(pre == null){
                pre = interval;
                count++;
            }else if(interval.start >= pre.end){
                pre = interval;
                count++;
            }
        }
        return intervals.length - count;
    }
}
