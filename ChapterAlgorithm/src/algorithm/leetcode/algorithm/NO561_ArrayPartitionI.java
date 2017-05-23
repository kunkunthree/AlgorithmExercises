package algorithm.leetcode.algorithm;
/*
 * easy
 * 561. Array Partition I 
 *  Given an array of 2n integers, your task is to group these integers into n pairs of integer, 
 *  say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]
Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4.

Note:
    1.		n is a positive integer, which is in the range of [1, 10000].
    2.		All the integers in the array will be in the range of [-10000, 10000].

 */
import java.util.*;
public class NO561_ArrayPartitionI {
	//方法1：
	//分析：
	//由于最后结果都把数组分成一对一对，取其中所有对的较小的值的和
	//那么数组中最小的值必然会加到最终结果中，那么剩下的数组要删除一个数与该值对应
	//那么只有删除剩余数组的最小值，才能使剩下的结果为最大值
	public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length/2,sum = 0;
        for(int i = 0 ; i < n ; i++){
            sum+=nums[i*2];
        }
        return sum;
    }
}
