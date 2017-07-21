package algorithm.leetcode.algorithm;
/*
 * medium
 * 33. Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

similar problems:
81. Search in Rotated Sorted Array II 
153. Find Minimum in Rotated Sorted Array 
 */
public class NO33_SearchinRotatedSortedArray {
	//方法1，先找最小值的索引，找出旋转的位数，然后通过映射当前索引到原来的索引，二分查找出target的位置
    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1,n = nums.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        int rot = left;
        left = 0;
        right = nums.length-1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int realMid = (rot+mid)%n;
            if(nums[realMid] == target){
                return realMid;
            }else if(nums[realMid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
    //方法2：直接通过当前数组经过修正的二分搜索
    public int search2(int[] A, int target) {
        int lo = 0;
        int hi = A.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) return mid;
            
            if (A[lo] <= A[mid]) {
                if (target >= A[lo] && target < A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > A[mid] && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return A[lo] == target ? lo : -1;
    }
}
