package algorithm.leetcode.algorithm;
/*
 * medium
 * 523. Continuous Subarray Sum 
 *  Given a list of non-negative numbers and a target integer k, 
 *  write a function to check if the array has a continuous subarray of size at least 2 
 *  that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

Note:

    1.		The length of the array won't exceed 10,000.
    2.		You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

 */
import java.util.*;
public class NO523_ContinuousSubarraySum {
	public static void main(String[] args) {
		int[] nums = new int[]{7,6};
		int k = 7;
//		System.out.println(checkSubarraySum2(nums, k));
	}
	//方法1：
	//dp,O(n)time,O(k)space
	//遍历整个数组，每次访问都计算累加和对k的模，如果k=0，则计算的时累加和
	//用map保留第一次对取得的模及其下标，map需要初始化保存一个(0,-1)对，以免开始两个元素就是k的整数倍
	//只要得到该次和上一次相同模的下标差j-i，如果下标差大于1，即j-i>1，
	//那么说明nums[j]+nums[j-1]+...+nums[i+1]的值为k的整数倍，且中间个数大于等于2
	public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length,sum = 0;
        Integer pre = null;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0 ; i < n ; i++){
            sum+=nums[i];
            if(k != 0){
                sum%=k;
            }
            pre = map.get(sum);
            if(pre == null){
                map.put(sum,i);
            }else if(i-pre > 1){
                return true;
            }
        }
        return false;
    }
	
}
