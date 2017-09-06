package algorithm.leetcode.algorithm;
/*
 * easy
 * 665. Non-decreasing Array 
 *  Given an array with n integers, your task is to check if it could become non-decreasing by modifying 
 *  at most 1 element.
We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 
4
 to 
1
 to get a non-decreasing array.

Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.

Note: The n belongs to [1, 10,000]. 
 */
public class NO665_Non_decreasingArray {
	//方法1：
	//贪婪算法
	//如果下一个数比当前数小，那么count++
	//如果当前数下标大于0且下一个数比上一个数小，那么将下一个数设置为当前数
	//否则将当前数设置为下一个数
	//保证当前数必然大于等于前面遍历过的数
	public boolean checkPossibility(int[] nums) {
        int count = 0;
        for(int i = 0 ; i < nums.length-1 ; i++){
            if(nums[i] > nums[i+1]){
                count++;
                if(i > 0 && nums[i+1] < nums[i-1]){
                    nums[i+1] = nums[i];
                }else{
                    nums[i] = nums[i+1];
                }
            }
        }
        return count <= 1;
    }
}
