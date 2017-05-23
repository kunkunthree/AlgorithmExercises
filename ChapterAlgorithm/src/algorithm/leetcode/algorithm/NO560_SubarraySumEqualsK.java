package algorithm.leetcode.algorithm;
/*
 * medium
 * 560. Subarray Sum Equals K 
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays
 *  whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

Note:
    1.		The length of the array is in range [1, 20,000].
    2.		The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 */
import java.util.*;
public class NO560_SubarraySumEqualsK {
	//方法1：
	//遍历整个数组，计算从第一个元素开始累加的和
	//用一个map记录遍历过程中出现的累加和的出现次数
	//如果sum-k在map中存在，那么连续子数组的和为k的个数增加map.get(sum-k)
	//map中sum对应的值+1，继续下一个元素
	public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0,count = 0;
        for(int num : nums){
            sum+=num;
            if(map.containsKey(sum-k)){
                count+=map.get(sum-k);
            }
            if(!map.containsKey(sum)){
                map.put(sum,0);
            }
            map.put(sum,map.get(sum)+1);
        }
        return count;
    }
}
