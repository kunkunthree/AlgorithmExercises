package algorithm.leetcode.algorithm;
/*
 * easy
 * 219. Contains Duplicate II
 * Given an array of integers and an integer k, 
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
 * and the absolute difference between i and j is at most k. 
 */
import java.util.*;
public class NO219_ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int length = nums.length;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0 ; i < length ; i++){
            if(set.contains(nums[i])){
                return true;
            }else{
                set.add(nums[i]);
                if(i>=k)set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
