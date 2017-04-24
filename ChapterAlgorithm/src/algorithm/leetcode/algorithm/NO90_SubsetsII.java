package algorithm.leetcode.algorithm;
/*
 * medium
 * 90. Subsets II
 *  Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

 */
import java.util.*;
public class NO90_SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        List<Integer> l = new ArrayList<Integer>();
        Arrays.sort(nums);
        subsetsWithDupHelper(nums,ll,l,0,nums.length);
        return ll;
    }
    public void subsetsWithDupHelper(int[] nums,List<List<Integer>> ll , List<Integer> tempList,int begin,int end){
        ll.add(new ArrayList<Integer>(tempList));			//未包含nums[start]的子集，即包含nums[start-1]的子集
        for(int i = begin ; i < end ; i++){
            if(i > begin && nums[i] == nums[i-1])continue;	//避免多个重复的子集出现
            tempList.add(nums[i]);
            subsetsWithDupHelper(nums,ll,tempList,i+1,end);
            tempList.remove(tempList.size()-1);
        }
    }
}
