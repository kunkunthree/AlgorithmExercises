package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * medium
 * 34. Search for a Range
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

 similar problems:
278. First Bad Version 
 */
public class NO34_SearchforaRange {
	public static void main(String[] args) {
		int[] nums = new int[]{5,7,7,8,8,10};
		int target = 8;
		System.out.println(Arrays.toString(searchRange2(nums, target)));
	}
	//方法1：O(logN+N),先找到等于target的值的某个下标，再向两边扩散
    public static int[] searchRange(int[] nums, int target) {
        int left = 0,right = nums.length-1,mid;
        while(left <= right){
            mid = left + ((right - left) >> 1);
            if(nums[mid] > target){
                right = mid-1;
            }else if(nums[mid] < target){
                left = mid+1;
            }else{
                left = mid;
                right = mid;
                while(left >= 0 && nums[left] == nums[mid]){
                    left--;
                }
                left++;
                while(right < nums.length && nums[right] == nums[mid]){
                    right++;
                }
                right--;
                break;
            }
        }
        if(left <= right && nums[left] == target && nums[right] == target){
            return new int[]{left,right};
        }
        return new int[]{-1,-1};
    }
    //方法2：先二分搜索第一个大于等于target的值的下标，再二分搜索最后一个小于等于target的值的下标
    //看这两个下标对应的值是否都等于target
    public static int[] searchRange2(int[] nums, int target) {
        int left = 0,right = nums.length-1,mid,start,end;
        while(left < right){
            mid = left + ((right - left) >> 1);
            if(nums[mid] < target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        start = right;
        left = start;
        right = nums.length-1;
        while(left < right){
            mid = right - ((right - left) >> 1);
            if(nums[mid] > target){
                right = mid-1;
            }else{
                left = mid;
            }
        }
        end = left;
        if(start >= 0 && end <= nums.length-1 && start <= end && nums[start] == target && nums[end] == target){
            return new int[]{start,end};
        }
        return new int[]{-1,-1};
    }
    
    //方法3：利用递归实现方法2
    public int[] searchRange3(int[] A, int target) {
        int[] range = {A.length, -1};
        searchRange(A, target, 0, A.length - 1, range);
        if (range[0] > range[1]) range[0] = -1; 
        return range;
    }
    
    public void searchRange(int[] A, int target, int left, int right, int[] range) {
        if (left > right) return;
        int mid = left + (right - left) / 2;
        if (A[mid] == target) {
            if (mid < range[0]) {
                range[0] = mid;
                searchRange(A, target, left, mid - 1, range);
            }
            if (mid > range[1]) {
                range[1] = mid;
                searchRange(A, target, mid + 1, right, range);
            }
        } else if (A[mid] < target) {
            searchRange(A, target, mid + 1, right, range);
        } else {
            searchRange(A, target, left, mid - 1, range);
        }
    }
}
