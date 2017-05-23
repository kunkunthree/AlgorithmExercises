package algorithm.leetcode.algorithm;
/*
 * medium
 * 376. Wiggle Subsequence
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly 
 * alternate between positive and negative. The first difference (if one exists) may be either positive or negative. 
 * A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are 
alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, 
the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
  leaving the remaining elements in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2

Follow up:
Can you do it in O(n) time? 
 */
public class NO376_WiggleSubsequence {
	//方法1：
	//dp，O(n^2)time,O(n)space
	public int wiggleMaxLength(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        //dp[0][i]表示以nums[i]为末尾下降的最长子序列长度
        //dp[1][i]表示以nums[i]为末尾上升的最长子序列长度
        int[][] dp = new int[2][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = i ; j >= 0 ; j--){
                if(nums[i] > nums[j]){
                    dp[1][i] = Math.max(dp[1][i],dp[0][j]+1);
                }else if(nums[i] < nums[j]){
                    dp[0][i] = Math.max(dp[0][i],dp[1][j]+1);
                }
            }
        }
        return Math.max(dp[0][n-1],dp[1][n-1])+1;
    }
	//方法2：
	//统计交叉起伏的次数，用两个标志up和down表示上升还是下降，如果up和down都为false，
	//则表示从头到尾都没有起伏，所有元素相等
	//O(1)space,O(n)time
	public int wiggleMaxLength2(int[] nums) {
		if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int count = 1;
        boolean up = false,down = false;
        for(int i = 1 ; i < n ; i++){
            if(nums[i] > nums[i-1]){
                if(down == true || up == false){  //前面的趋势是下降或者没有起伏过
                    count++;
                }
                down = false;
                up = true;
            }
            if(nums[i] < nums[i-1]){
                if(up == true || down == false){  //前面的趋势是上升或者没有起伏过
                    count++;
                }
                down = true;
                up = false;
            }
        }
        return count;
    }
	//方法3：
	//O(n)time，O(1)space，更简单的方法
	//只需要记录前一次上升和下降的序列的最长长度即可
	//则如果遇到num[i]>nums[i-1]，则此时以nums[i]结尾的上升的浮动序列的最长长度为上一次下降的最长长度+1
	//即，若nums[i]>nums[i-1]，则up = down+1;
	//同理，若nums[i]<nums[i-1]，则down = up+1;
	//则最终只要比较两个序列哪个长度比较长即可
	public int wiggleMaxLength3(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int up = 1,down = 1;
        for(int i = 1 ; i < n ; i++){
            if(nums[i] > nums[i-1]){
                up = down+1;
            }else if(nums[i] < nums[i-1]){
                down = up+1;
            }
        }
        return Math.max(up,down);
    }
}
