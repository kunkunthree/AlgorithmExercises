package algorithm.leetcode.algorithm;
/*
 * easy
 * 448. Find All Numbers Disappeared in an Array
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), 
 * some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? 
 * You may assume the returned list does not count as extra space.
Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

similar problems:
41. First Missing Positive
442. Find All Duplicates in an Array  
 */
import java.util.*;
public class NO448_FindAllNumbersDisappearedinanArray {
	public static void main(String[] args) {
		int[] nums = new int[]{4,3,2,7,8,2,3,1};
		System.out.println(findDisappearedNumbers(nums));
	}
	//把当前数绝对值为索引，以该索引为下标的数设置为负数，那么不存在的数为索引的数必然为正
	//把符合和不符合的数用正负性分隔开
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        int n = nums.length;
        for(int i = 0 ; i < n ; i++){
            int j = Math.abs(nums[i]);
            if(j >=1 && j <= n){
                nums[j-1] = -Math.abs(nums[j-1]);
            }
        }
        for(int i = 0 ; i < n ; i++){
            if(nums[i]>0){
                list.add(i+1);
            }
        }
        return list;
    }
}
