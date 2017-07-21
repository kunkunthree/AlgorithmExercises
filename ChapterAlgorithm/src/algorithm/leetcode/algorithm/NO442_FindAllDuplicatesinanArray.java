package algorithm.leetcode.algorithm;
/*
 * medium
 * 442. Find All Duplicates in an Array
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[2,3]

similar problems:
287. Find the Duplicate Number 
448. Find All Numbers Disappeared in an Array 
 */
import java.util.*;
public class NO442_FindAllDuplicatesinanArray {
	//方法1：
	//因为所有元素都是只出现1次或两次且都为正数，
	//那么以当前访问到的数值-1为下标，访问到的数值如果为正，则将该访问到的值置为相反数，
	//如果为负，则说明之前已经访问过了，该数出现了2次
	//O(n)time,O(1)space
	public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];
        }
        return res;
    }
}
