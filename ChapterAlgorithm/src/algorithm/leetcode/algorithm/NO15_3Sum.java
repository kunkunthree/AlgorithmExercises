package algorithm.leetcode.algorithm;
/*
 * medium
 * 15. 3Sum
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.
For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 */
import java.util.*;
public class NO15_3Sum {
	public static void main(String[] args) {
		
	}
	//方法1：O(n^2)
	//先对数组排序，然后只从每个数字初次出现的索引开始，假设为 i
	//然后用两个指针从剩余的数组首（j）尾（k）开始，
	//如果j<k，则循环下面三个判断
	//如果nums[i]+nums[i]+nums[k] >0,则说明过大，则k--；
	//如果nums[i]+nums[i]+nums[k] <0,则说明过小，则j++；
	//如果nums[i]+nums[i]+nums[k]  = 0,则说明符合，把这三个数存储起来，
	//则j变为下一个比它大的数的第一个索引，k变为下一个比它小的数的第一个索引
	//循环判断结束后，i变为下一个比它大的非正数的第一个索引
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int i = 0,length = nums.length;
        while(i < length-2 && nums[i] <= 0){
            while(i < length-2 && i > 0 && nums[i] == nums[i-1]){
                i++;
            }
            int j = i+1,k=length-1;
            int target = -nums[i];
            while(j < k){
                if(nums[j] + nums[k] > target){
                    k--;
                }else if(nums[j] + nums[k] < target){
                    j++;
                }else{
                    ll.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    k--;
                    j++;
                    while(j < k && nums[j] == nums[j-1])j++;
                    while(j < k && nums[k] == nums[k+1])k--;
                }
            }
            i++;
        }
        return ll;
    }
}
