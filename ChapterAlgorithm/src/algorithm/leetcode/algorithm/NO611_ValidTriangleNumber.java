package algorithm.leetcode.algorithm;
/*
 * medium
 * 611. Valid Triangle Number 
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen 
 * from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:

Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Note:
    1.		The length of the given array won't exceed 1000.
    2.		The integers in the given array are in the range of [0, 1000].

 */
import java.util.*;
public class NO611_ValidTriangleNumber {
	//方法1：
	//O(n^2)
	//先排序，然后从n-1~2作为最长边
	//然后用两个指针从左右两边开始向两边靠拢
	public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for(int i = nums.length-1 ; i >= 2 ; i--){
            int left = 0,right = i-1;
            while(left < right){
                if(nums[left]+nums[right] > nums[i]){
                    count+=right-left;
                    right--;
                }else{
                    left++;
                }
            }
        }
        return count;
    }
}
