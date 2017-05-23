package algorithm.leetcode.algorithm;
/*
 * medium
 * 491. Increasing Subsequences 
 *  Given an integer array, your task is to find all the different possible increasing subsequences of the given array, 
 *  and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

Note:

    1.	The length of the given array will not exceed 15.
    2.	The range of integer in the given array is [-100,100].
    3.	The given array may contain duplicates, and two equal integers should also be considered 
    		as a special case of increasing sequence.

 */
import java.util.*;
public class NO491_IncreasingSubsequences {
	//方法1：
	//修改的全排列算法
	public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ll = new ArrayList<>();
        if(nums == null || nums.length <= 1){
            return ll;
        }
        List<Integer> list = new ArrayList<>();
        helper(nums,ll,list,0,nums.length);
        return ll;
    }
    private void helper(int[] nums,List<List<Integer>> ll,List<Integer> list,int start,int end){
        if(list.size() > 1){
            ll.add(new ArrayList<>(list));
        }
        Set<Integer> set = new HashSet<>();
        for(int i = start ; i < end ; i++){
            if(list.isEmpty() || nums[i] >= list.get(list.size()-1)){
            	//如果该数在前面已经出现过，则跳过
                if(set.contains(nums[i])){
                    continue;
                }
                set.add(nums[i]);
                list.add(nums[i]);
                helper(nums,ll,list,i+1,end);
                list.remove(list.size()-1);
            }
        }
    }
}
