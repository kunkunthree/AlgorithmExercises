package algorithm.leetcode.algorithm;
/*
 * hard
 * 154. Find Minimum in Rotated Sorted Array II 
 * 
    Follow up for "Find Minimum in Rotated Sorted Array":
    What if duplicates are allowed?

    Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
 */
public class NO154_FindMinimumInRotatedSortedArrayII {
	//方法1：
	//O(n)time,O(1)space
	//二分法
	public int findMin(int[] nums) {
        int left = 0,right = nums.length-1,mid;
        while(left < right){
            mid = (right+left)/2;
            if(nums[mid] > nums[right]){
                left = mid+1;
            }else if(nums[mid] < nums[right]){
                right = mid;
            }else{
                right--;
            }
        }
        return nums[left];
    }
}
