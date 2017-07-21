package algorithm.leetcode.algorithm;
/*
 * medium
 * 377. Combination Sum IV
 *  Given an integer array with all positive numbers and no duplicates, find the number of possible combinations
 *   that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers? 

similar problems:
39. Combination Sum
40. Combination Sum II
216. Combination Sum III
377. Combination Sum IV
 */
import java.util.*;
public class NO377_CombinationSumIV {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		int target = 4;
		System.out.println(combinationSum4(nums, target));
	}
	//方法1：
	//dp，O(target)space,O(target*n)time
	public static int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 1 ; i <= target ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i - nums[j] >= 0){
                    dp[i]+=dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
	//方法2：
	//DFS，递归，用一个hashMap记录中间结果，节省运算时间
	Map<Integer, Integer> map = new HashMap<>();
    public int combinationSum4_2(int[] nums, int target) {
        int count = 0;
        if (nums == null || nums.length ==0 || target < 0 ) return 0;
        if ( target ==0 ) return 1;
        if (map.containsKey(target)) return map.get(target);
        for (int num: nums){
            count += combinationSum4_2(nums, target-num);
        }
        map.put(target, count);
        return count;
    }
}
