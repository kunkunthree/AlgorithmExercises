package algorithm.leetcode.algorithm;
/*
 * hard
 * 128. Longest Consecutive Sequence 
 *  Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity. 
 */
import java.util.*;
public class NO128_LongestConsecutiveSequence {
	//方法1：
	//用map记录连续区间的边界位置与区间大小
	//如果当前数字未访问，则取其左边相邻区间大小和右边相邻区间大小再加上1，设置到map中，表示已访问
	//并且对左区间的左边界和右区间的右边界进行更新边界大小
	public int longestConsecutive(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for(int num : nums){
            if(!map.containsKey(num)){
                int left = (map.containsKey(num-1) ? map.get(num-1) : 0);
                int right = (map.containsKey(num+1) ? map.get(num+1) : 0);
                int value = 1 + left + right;
                map.put(num,value);
                max = Math.max(max,value);
                map.put(num-left,value);
                map.put(num+right,value);
            }
        }
        return max;
    }
}
