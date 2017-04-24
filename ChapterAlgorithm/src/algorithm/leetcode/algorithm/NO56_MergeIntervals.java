package algorithm.leetcode.algorithm;
/*
 * medium
 * NO56_MergeIntervals
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18]. 
 */
import java.util.*;
public class NO56_MergeIntervals {
	public static void main(String[] args) {
		Comparator<Interval> cmp = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			}
		};
	}
	//方法1：
	//先对start排序，然后再一一合并重叠的区域
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0){
            return list;
        }
        Comparator<Interval> cmp = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			}
		};
        Collections.sort(intervals,cmp);
        int start = intervals.get(0).start,end = intervals.get(0).end;
        for(int i = 0 ; i < intervals.size() ; i++){
            Interval tmpI = intervals.get(i);
            if(end < tmpI.start){
                list.add(new Interval(start,end));
                start = tmpI.start;
                end = tmpI.end;
            }else{
                end = Math.max(end,tmpI.end);
            }
        }
        list.add(new Interval(start,end));
        return list;
    }
    
    //方法2：
    //分别对start和end排序，如果是区域尾部的话，start[i]<=end[i],start[i+1]>end[i]
    public List<Interval> merge2(List<Interval> intervals) {
    	// sort start&end
    	int n = intervals.size();
    	int[] starts = new int[n];
    	int[] ends = new int[n];
    	for (int i = 0; i < n; i++) {
    		starts[i] = intervals.get(i).start;
    		ends[i] = intervals.get(i).end;
    	}
    	Arrays.sort(starts);
    	Arrays.sort(ends);
    	// loop through
    	List<Interval> res = new ArrayList<Interval>();
    	for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
    		if (i == n - 1 || starts[i + 1] > ends[i]) {
    			res.add(new Interval(starts[j], ends[i]));
    			j = i + 1;
    		}
    	}
    	return res;
    }
}
