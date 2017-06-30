package algorithm.leetcode.algorithm;
/*
 * easy
 * 624. Maximum Distance in Arrays 
 *  Given m arrays, and each array is sorted in ascending order. 
 *  Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. 
 *  We define the distance between two integers a and b to be their absolute difference |a-b|. 
 *  Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Note:
    1.		Each given array will have at least 1 number. There will be at least two non-empty arrays.
    2.		The total number of the integers in all the m arrays will be in the range of [2, 10000].
    3.		The integers in the m arrays will be in the range of [-10000, 10000].

 */
import java.util.*;
public class NO624_MaximumDistanceInArrays {
	//方法1：
	//O(n)time,O(1)space
	//result初始化为0
	//i=1开始遍历
	//将array[0]~arrays[i-1]的最大值与arrays[i]的最小值的差与result比较，result取较大值
	//将arrays[i]的最大值与array[0]~arrays[i-1]的最小值的差与result比较，result取较大值
	public int maxDistance(List<List<Integer>> arrays) {
        if(arrays == null || arrays.size() <= 1){
            return 0;
        }
        int min = arrays.get(0).get(0),max = arrays.get(0).get(arrays.get(0).size()-1);
        int result = 0;
        for(int i = 1 ; i < arrays.size() ; i++){
            List<Integer> list = arrays.get(i);
            if(list != null && list.size() >= 1){
                result = Math.max(result,max - list.get(0));
                result = Math.max(result,list.get(list.size()-1) - min);
                min = Math.min(min,list.get(0));
                max = Math.max(max,list.get(list.size()-1));
            }
        }
        return result;
    }
}
