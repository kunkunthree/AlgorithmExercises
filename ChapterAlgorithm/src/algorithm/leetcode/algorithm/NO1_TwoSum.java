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
		System.out.println(Arrays.toString(twoSum(nums, target)));
	}
	//O(n^2)
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
  //O(n)
    public static int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }
}
