package algorithm.leetcode.algorithm;
/*
 * medium
 * 162. Find Peak Element 
 * A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 */
public class NO162_FindPeakElement {
	//方法1：
	//直接法，对长度、两边、中间分别进行检测
	public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        int i = 0;
        for(; i < nums.length ; i++){
            if(i == 0 && i+1 < nums.length && nums[i] > nums[i+1])break;
            if(i == nums.length-1 && i-1 >= 0 && nums[i] > nums[i-1])break;
            if(i > 0 && i < nums.length-1 && nums[i] > nums[i-1] && nums[i] > nums[i+1])break;
        }
        return i;
    }
	//方法2：
	//迭代，二分法
	public int findPeakElement2(int[] nums) {
        int left = 0,right = nums.length-1,mid;
        while(left<right){
            mid = (left+right)/2;
            if(nums[mid] > nums[mid+1]){
                right = mid;
            }else if(nums[mid] < nums[mid+1]){
                left = mid+1;
            }
        }
        return left;
    }
	//方法3：
	//递归实现，二分法
	public int findPeakElement3(int[] nums) {
        return helper(nums,0,nums.length-1);
    }
    public int helper(int[] nums,int start,int end){
        if(start == end){
            return start;
        }
        int mid = (start+end)/2;
        if(nums[mid] < nums[mid+1]){
            return helper(nums,mid+1,end);
        }
        return helper(nums,start,mid);
    }
}
