package algorithm.leetcode.algorithm;
/*
 * medium
 * 525. Contiguous Array 
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:

Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:

Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000. 
 */
import java.util.*;
public class NO525_ContiguousArray {
	//方法1：
	//遍历整个数组，计算当前下标为止0的个数与1的个数的差，diffZeroOne = countZero - countOne
	//如果当前的差在map已存在，则计算当前下标与map中该差对应的下标的差值，如果比max大，则更新max
	//如果当前的差在map中不存在，则把第一次出现的差及其下标存入map中
	public int findMaxLength(int[] nums) {
        int diffZeroOne = 0,max = 0,n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0 ; i < n ; i++){
            if(nums[i] == 0){
                diffZeroOne++;
            }else{
                diffZeroOne--;
            }
            if(map.containsKey(diffZeroOne)){
                max = Math.max(max,i - map.get(diffZeroOne));
            }else{
                map.put(diffZeroOne,i);
            }
        }
        return max;
    }
}
