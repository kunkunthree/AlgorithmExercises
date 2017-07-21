package algorithm.leetcode.algorithm;
/*
 * medium
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

similar problems:
33. Search in Rotated Sorted Array 
154. Find Minimum in Rotated Sorted Array II 
 */
public class NO153_FindMinimuminRotatedSortedArray {
	//方法1：
	//二分法
	public int findMin(int[] nums) {
        int left = 0,right = nums.length-1,mid;
        while(left < right){
            mid = (right + left)/2;
            if(nums[mid] > nums[right]){
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }
}
