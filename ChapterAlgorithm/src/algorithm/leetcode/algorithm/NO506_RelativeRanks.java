package algorithm.leetcode.algorithm;

import java.util.Comparator;

/*
 * easy
 * 506. Relative Ranks
 *  Given scores of N athletes, find their relative ranks and the people with the top three highest scores, 
 *  who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.

Note:
    N is a positive integer and won't exceed 10,000.
    All the scores of athletes are guaranteed to be unique.

 */
import java.util.*;
class Point{
    int index;
    int val;
    public Point(int i,int v){
        index = i;
        val =v;
    }
}
public class NO506_RelativeRanks {
	public static void main(String[] args) {
		
	}
    public String[] findRelativeRanks(int[] nums) {
        String[] ss = new String[nums.length];
        if(nums == null || nums.length == 0){
            return ss;
        }
        Point[] p = new Point[nums.length];
        Comparator<Point> cmp = new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return p2.val - p1.val;
			}
		};
        for(int i = 0 ; i < nums.length ; i++){
            p[i] = new Point(i,nums[i]);
        }
        Arrays.sort(p,cmp);
        for(int i = 0 ; i < nums.length ; i++){
            if(i == 0){
                ss[p[i].index] = "Gold Medal";
            }else if(i == 1){
                ss[p[i].index] = "Silver Medal";
            }else if(i == 2){
                ss[p[i].index] = "Bronze Medal";
            }else{
                ss[p[i].index] = (i+1)+"";
            }
        }
        return ss;
    }
    //先利用HashMap映射索引，再排序
    public String[] findRelativeRanks2(int[] nums) {
        String[] ss = new String[nums.length];
        if(nums == null || nums.length == 0){
            return ss;
        }
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0 ; i < nums.length ; i++){
            map.put(nums[i],i);
        }
        Arrays.sort(nums);
        for(int i = 0 ; nums.length-1-i>=0 ; i++){
            if(i == 0){
                ss[map.get(nums[nums.length-1-i])] = "Gold Medal";
            }else if(i == 1){
                ss[map.get(nums[nums.length-1-i])] = "Silver Medal";
            }else if(i == 2){
                ss[map.get(nums[nums.length-1-i])] = "Bronze Medal";
            }else{
                ss[map.get(nums[nums.length-1-i])] = (i+1)+"";
            }
        }
        return ss;
    }
}
