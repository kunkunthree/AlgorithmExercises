package algorithm.leetcode.algorithm;
/*
 * easy
 * 1. Two Sum 
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

similar problems:

 */
import java.util.*;

import algorithm.TwoKindOfSortingMethod;
public class NO1_TwoSum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int target = in.nextInt();
		int[] nums = new int[n];
		for(int i = 0 ; i < n ; i++){
			nums[i] = in.nextInt();
		}
		System.out.println(Arrays.toString(twoSum2(nums, target)));
	}
	//方法1：
	//O(n^2)time，O(1)space
	//两重循环
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for(int i = 0 ; i < length-1 ; i++){
            for(int j = i+1 ; j < length ; j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    
    //方法2
    //O(n)time，O(n)space
    //用一个map记录值对应的下标
    public static int[] twoSum2(int[] nums, int target) {
    	int length = nums.length;
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < length ; i++){
            if(map.containsKey(target-nums[i])){
                result[1] = i;
                result[0] = map.get(target-nums[i]);
                break;
            }
            map.put(nums[i],i);
        }
        return result;
    }
}
