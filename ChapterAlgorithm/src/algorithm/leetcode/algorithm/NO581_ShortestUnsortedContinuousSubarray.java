package algorithm.leetcode.algorithm;
/*
 * easy
 * 581. Shortest Unsorted Continuous Subarray
 * Given an integer array, you need to find one continuous subarray that 
 * if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:

Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Note:
    1.		Then length of the input array is in range [1, 10,000].
    2.		The input array may contain duplicates, so ascending order here means <=.

 */
public class NO581_ShortestUnsortedContinuousSubarray {
	//方法1：
	//假设数组的长度为n，则数组区间为[0,n-1]
	//最少需要排序的子数组使得整个数组为升序[start,end]，0 <= start <= end <= n-1,
	//所以区间[0,start-1]和[end+1,n-1]必然是升序的
	//则该子数组中头元素nums[start]必然不是该子数组的最小元素，即[start,end]存在某个元素比nums[start]小
	//因为[end+1,n-1]是升序的且[end+1,n-1]中所有元素必然都大于等于[start,end]中的元素
	//所以nums[start,end]中的最小元素，同样也是[start,n-1]中的最小元素min
	//min < nums[start] ，nums[start]是从右往左数，最后一个比min大的元素
	//同理，nums[end] < max ， max为[0,end]中的最大元素，nums[end]为从左往右数最后一个比max 小的元素
	//O(n)time,O(1)space
	//注意：start和end初始值是为了当数组本身就是升序的情况下使结果正确而设定的
	//				start初始值为-1，end初始值为-2，则end-start+1 = 0，不需要排序
	public int findUnsortedSubarray(int[] nums) {
        int n = nums.length,max = nums[0],min = nums[n-1],start = -1,end = -2;
        for(int i = 1 ; i < n ; i++){
            max = Math.max(max,nums[i]);
            min = Math.min(min,nums[n-i-1]);
            if(nums[i] < max)end = i;
            if(nums[n-i-1] > min)start = n-i-1;
        }
        return end - start + 1;
    }
}
