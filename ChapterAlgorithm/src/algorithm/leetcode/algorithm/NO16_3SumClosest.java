package algorithm.leetcode.algorithm;
/*
 * medium
 * 16. 3Sum Closest
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
import java.util.*;
public class NO16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0,length = nums.length,closest = nums[0]+nums[1]+nums[2];
        while(i < length-2){
            int j = i+1,k=length-1;
            while(j < k){
                int sum = nums[i]+nums[j]+nums[k];
                if(Math.abs(sum-target) < Math.abs(closest-target)){
                    closest = sum;
                }
                if(sum > target){
                    k--;
                }else if(sum < target){
                    j++;
                }else{
                    return target;
                }
            }
            i++;
        }
        return closest;
    }
}
