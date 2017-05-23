package algorithm.leetcode.algorithm;
/*
 * medium
 * 334. Increasing Triplet Subsequence
 *  Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

    Return true if there exists i, j, k
    such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false. 

Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false. 
 */
public class NO334_IncreasingTripletSubsequence {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,5,7,8,9,10};
//		System.out.println(increasingTriplet2(nums));
		int target = 6;
		int left = 0;
		int right = nums.length-1;
		while(left < right){
			int mid = right -  (right-left)/2;
			if(target >= nums[mid]){
				left = mid;
			}else{
				right = mid -1;
			}
		}
		System.out.println(left + " " + nums[left]);
	}
	//方法1：
	//dp，求最大增长子序列的长度是否大于等于2
	public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for(int i = 0 ; i < n ; i++){
            for(int j = i-1 ; j >= 0 ; j--){
                if(nums[i] > nums[j] && dp[i] < dp[j]+1){
                    dp[i] = dp[j]+1;
                    if(dp[i] == 2){
                        return true;
                    }
                }
            }
        }
        return false;
    }
	//方法2：
	//O(n)time
	public static boolean increasingTriplet2(int[] nums) {
        int n = nums.length,length = 0;
        int left,right,mid;
        for(int i = 0 ; i < n ; i++){
            left = 0;
            right = length;
            while(left < right){
                mid = left + (right - left)/2;
                if(nums[i] > nums[mid]){
                    left = mid + 1;
                }else if(nums[i] <= nums[mid]){
                    right = mid;
                }
            }
            if(left == length){
                if(length == 2){
                    return true;
                }
                length++;
            }
            nums[left] = nums[i];
        }
        return false;
    }
	//方法3：
	//保存当前最小的一个值和一个比这个当前最小的值要大的最小值，如果遇到比这两个数都要大的数，那么就返回true
	public boolean increasingTriplet3(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) { small = n; } // update small if n is smaller than both
            else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }
}
