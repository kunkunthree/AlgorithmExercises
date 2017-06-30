package algorithm.leetcode.algorithm;
/*
 * medium
 * 630. Course Schedule III 
 *  There are n different online courses numbered from 1 to n. 
 *  Each course has some duration(course length) t and closed on dth day.
 *   A course should be taken continuously for t days and must be finished before or on the dth day.
 *    You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses 
that can be taken.

Example:

Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3
Explanation: 
There're totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, 
		and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, 
		and ready to take the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.

Note:
    1.		The integer 1 <= d, t, n <= 10,000.
    2.		You can't take two courses simultaneously.

 */
import java.util.*;
public class NO630_CourseScheduleIII {
	//方法1：
	//贪婪算法
	//先把课程按照截止时间先后顺序排序
	//然后遍历所有课程，用PriorityQueue把每个课程所花时间包存起来，并用降序排列：
	//		1.当前时间+=课程持续时间
	//		2.把当前课程持续时间加入PriorityQueue
	//		3.如果当前时间超出了当前课程截止时间，那么就不断把PriorityQueue中最大的课程时间减去，
	//			直到当前时间小于等于当前课程截止时间
	public int scheduleCourse(int[][] courses) {
        //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        Arrays.sort(courses,new Comparator<int[]>(){
            public int compare(int[] course1,int[] course2){
                return course1[1]-course2[1];
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>(1,new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return b-a;
            }
        });
        int curTime = 0;
        for(int[] course : courses){
            curTime+=course[0];
            // add current course to a priority queue
            queue.offer(course[0]); 
            //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
            while(curTime > course[1])curTime-=queue.poll(); 
        }
        return queue.size();
    }
}
