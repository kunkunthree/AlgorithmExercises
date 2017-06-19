package algorithm.leetcode.algorithm;
/*
 * hard
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, 
then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
 */
public class NO45_JumpGameII {
	//方法1：
	//贪婪算法，O(n)time,O(1)space
	//每一步都在当前的最远距离内得到下一个最远距离
	public int jump(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        int n = nums.length,right = nums[0],step = 1,i = 1;
        while(i <= right && right < n-1){
            int max = 0;
            int left = i;
            while(i <= right){	//在[left,right]中找能达到的最远距离
                max = Math.max(max,i+nums[i]);
                i++;
            }
            step++;
            right = Math.max(right,max);
        }
        return step;
    }
	//方法2：
	//原理同上
	public int jump2(int[] A) {
	    int sc = 0;
	    int e = 0;
	    int max = 0;
	    for(int i=0; i<A.length-1; i++) {
	        max = Math.max(max, i+A[i]);
	        if( i == e ) {
	            sc++;
	            e = max;
	        } 
	    }
	    return sc;
	}
}
