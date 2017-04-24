package algorithm.leetcode.algorithm;
/*
 * easy
 * 35. Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0  
 */
public class NO35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        for(int i = 0 ; i < length ; i++){
            if(nums[i]>=target){
                return i;
            }
        }
        return length;
    }
    //二分法
    public int searchInsert2(int[] nums, int target) {
        int left = 0,right = nums.length-1,mid;
        while(left <= right){
            mid = (left + right)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return left;
    }
}
