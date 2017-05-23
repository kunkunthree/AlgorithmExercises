package algorithm.leetcode.algorithm;
/*
 * easy
 * 594. Longest Harmonious Subsequence 
 * We define a harmonious array is an array where the difference between its maximum value 
 * and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence 
among all its possible subsequences.

Example 1:

Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

Note: The length of the input array will not exceed 20,000. 
 */
import java.util.*;
public class NO594_LongestHarmoniousSubsequence {
	//用map进行计数，
	// max = map.get(num-1) > 0 ? Math.max(max,map.get(num)+map.get(num-1)) : max;
    //	max = map.get(num+1) > 0 ? Math.max(max,map.get(num)+map.get(num+1)) : max;
	public int findLHS(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0,count;
        for(int num : nums){
            if(!map.containsKey(num)){
                map.put(num,0);
            }
            map.put(num,map.get(num)+1);
            if(!map.containsKey(num-1)){
                map.put(num-1,0);
            }
            if(!map.containsKey(num+1)){
                map.put(num+1,0);
            }
            max = map.get(num-1) > 0 ? Math.max(max,map.get(num)+map.get(num-1)) : max;
            max = map.get(num+1) > 0 ? Math.max(max,map.get(num)+map.get(num+1)) : max;
        }
        return max;
    }
	//方法2：
	//先排序，再用两个指针进行右移计数
	public int findLHS2(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        Arrays.sort(nums);
        int min = nums[0],start = 0,nextStart = 1,max = 0;
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] - min > 1){
                start = nextStart++;
                min = nums[start];
                i--;
            }else if(nums[i] - min == 1){
                max = Math.max(max,i-start+1);
                if(nums[i] != nums[i-1]){
                    nextStart = i;
                }
            }
        }
        return max;
    }
}
