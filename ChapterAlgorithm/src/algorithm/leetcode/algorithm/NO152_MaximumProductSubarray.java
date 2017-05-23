package algorithm.leetcode.algorithm;
/*
 * medium
 * 152. Maximum Product Subarray
 *  Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6. 
 */
public class NO152_MaximumProductSubarray {
	//方法1：
	//动态规划，计算出所有以nums[i]结尾的乘积最大值和最小值
	public int maxProduct(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length+1][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int i = 0 ; i < length ; i++){
            if(nums[i] > 0){
                dp[i+1][0] = Math.max(dp[i][0] * nums[i],nums[i]);
                dp[i+1][1] = Math.min(dp[i][1] * nums[i],nums[i]);
            }else{
                dp[i+1][0] = Math.max(dp[i][1] * nums[i],nums[i]);
                dp[i+1][1] = Math.min(dp[i][0] * nums[i],nums[i]);
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < length ; i++){
            if(dp[i+1][0] > max){
                max = dp[i+1][0];
            }
        }
        return max;
    }
	//方法2：
	//一边求以当前数字结尾的乘积表达式的最大值，一边比较目前为止的最大值，节省额外空间
	public int maxProduct2(int[] nums) {
        int length = nums.length;
        int curMax = 1,curMin = 1,tmp = 0,max = Integer.MIN_VALUE;
        for(int i = 0 ; i < length ; i++){
            tmp = Math.max(Math.max(curMax * nums[i],curMin * nums[i]),nums[i]);
            curMin = Math.min(Math.min(curMax * nums[i],curMin * nums[i]),nums[i]);
            curMax = tmp;
            max = Math.max(max,curMax);
        }
        return max;
    }
}
