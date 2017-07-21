package algorithm.leetcode.algorithm;
/*
 * medium
 * 213. House Robber II
 * Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery 
so that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

similar problems:
198. House Robber 
337. House Robber III 
600. Non-negative Integers without Consecutive Ones 
 */
import java.util.*;
public class NO213_HouseRobberII {
	public static void main(String[] args) {
		int[] nums = new int[]{1,1,1,2};
		System.out.println(rob(nums));
	}
	//方法1：
	//由于是环形，所以可以分割成两个问题，[0,n-2],[1,n-1]这样必然不会同时包含0和n-1
	//特殊情况：长度为0时，最大值为0，当长度为1时，最大长度为nums[0]
	public static int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        return Math.max(rob(nums,0,length-2),rob(nums,1,length-1));
    }
    private static int rob(int[] nums,int start,int end){
        int take = 0,notTake = 0,tmpNotTake;
        for(int i = start ; i <= end ; i++){
            tmpNotTake = notTake;
            if(take > notTake){
                notTake = take;
            }
            take = tmpNotTake + nums[i];
        }
        return Math.max(take,notTake);
    }
}
