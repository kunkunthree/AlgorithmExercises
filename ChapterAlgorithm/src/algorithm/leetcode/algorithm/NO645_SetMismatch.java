package algorithm.leetcode.algorithm;
/*
 * easy
 * 645. Set Mismatch 
 *  The set S originally contains numbers from 1 to n. But unfortunately, 
 *  due to the data error, one of the numbers in the set got duplicated to another number in the set, 
 *  which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. 
Your task is to firstly find the number occurs twice and then find the number that is missing. 
Return them in the form of an array.

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]

Note:
    1.	The given array size will in the range [2, 10000].
    2.	The given array's numbers won't have any order.

similar problems:
287. Find the Duplicate Number 
 */
public class NO645_SetMismatch {
	//方法1：
	//两次遍历数组
	//第一次遍历，使当前遍历到的数在数组中对应的位置设为负数，如果对应的位置已经为负数，则该数为重复的数
	//第二次遍历，如果某个位置为正，说明该位置就是缺少的那个数
	public int[] findErrorNums(int[] nums) {
        int[] result = new int[]{-1,-1};
        for(int i = 0 ; i < nums.length ; i++){
            int index = Math.abs(nums[i])-1;
            if(nums[index] < 0){
                result[0] = index+1;
            }else{
                nums[index] = - nums[index];
            }
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] > 0){
                result[1] = i+1;
            }
        }
        return result;
    }
}
