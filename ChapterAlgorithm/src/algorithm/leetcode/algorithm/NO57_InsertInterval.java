package algorithm.leetcode.algorithm;
/*
 * hard
 * 57. Insert Interval 
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]. 
 */
import java.util.*;
public class NO57_InsertInterval {
	//方法1：
	//先找到重叠部分，然后合并，再把剩下非重叠的加上
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        Interval interval;
        int i = 0;
        while(i < intervals.size()){
            interval = intervals.get(i);
            if(interval.end < newInterval.start){
                result.add(interval);
            }else{
                if(interval.start > newInterval.end){
                    break;
                }else{
                    newInterval.start = Math.min(newInterval.start,interval.start);
                    newInterval.end  = Math.max(newInterval.end,interval.end);
                }
            }
            i++;
        }
        result.add(newInterval);
        while(i < intervals.size()){
            result.add(intervals.get(i));
            i++;
        }
        return result;
    }
}
