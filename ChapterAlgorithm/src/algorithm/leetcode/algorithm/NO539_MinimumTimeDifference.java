package algorithm.leetcode.algorithm;
/*
 * medium
 * 539. Minimum Time Difference 
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, 
 * find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1

Note:
    1.	The number of time points in the given list is at least 2 and won't exceed 20000.
    2.	The input time is legal and ranges from 00:00 to 23:59.

 */
import java.util.*;
public class NO539_MinimumTimeDifference {
	public static void main(String[] args) {
		
	}
	//方法1：
	//先排序，再计算相邻的最小时间差，再得到最小的时间差
	//注意，要计算最后一个和第一个的最小时间差
	//O(nlogn)time,O(1)space
	public int findMinDifference(List<String> timePoints) {
        if(timePoints == null || timePoints.size() <= 1){
            return 0;
        }
        Collections.sort(timePoints,new Comparator<String>(){
            public int compare(String s1,String s2){
                return getMinuteDifference(s1,s2);
            }
        });
        int minDifference = 24*60,n = timePoints.size();
        for(int i = 0 ; i < n ; i++){
            int tmp = getMinuteDifference(timePoints.get((i+1)%n),timePoints.get(i));
            if(tmp < 0){
                tmp+=24*60;
            }
            minDifference = Math.min(minDifference,tmp);
        }
        return minDifference;
    }
	//第一种计算时间差方法
    private int getMinuteDifference(String s1,String s2){
        String[] time = s1.split(":");
        int[] time1 = new int[2];
        time1[0] = Integer.parseInt(time[0]);
        time1[1] = Integer.parseInt(time[1]);
        time = s2.split(":");
        int[] time2 = new int[2];
        time2[0] = Integer.parseInt(time[0]);
        time2[1] = Integer.parseInt(time[1]);
        return (time1[0]-time2[0])*60+(time1[1]-time2[1]); 
    }
  //第二种计算时间差方法
    private int getMinuteDifference2(String s1,String s2){
        int time1 = Integer.valueOf(s1.substring(0, 2))*60;
        time1+=Integer.valueOf(s1.substring(3, 5));
        int time2 = Integer.valueOf(s2.substring(0, 2))*60;
        time2+=Integer.valueOf(s2.substring(3, 5));
        return time1-time2; 
    }
    //方法2：
    //利用bucket得到所有时间点
    public int findMinDifference2(List<String> timePoints) {
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) return 0;
            mark[h * 60 + m] = true;
        }
        
        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (mark[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        
        min = Math.min(min, (24 * 60 - last + first));
        
        return min;
    }
}
