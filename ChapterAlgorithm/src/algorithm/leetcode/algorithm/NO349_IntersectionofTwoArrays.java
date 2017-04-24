package algorithm.leetcode.algorithm;
/*
 * easy
 * 349. Intersection of Two Arrays
 *  Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:

    Each element in the result must be unique.
    The result can be in any order.

 */
import java.util.*;
public class NO349_IntersectionofTwoArrays {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
	}
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for(int i = 0 ; i < nums1.length ; i++){
            set1.add(nums1[i]);
        }
        for(int i = 0 ; i < nums2.length ; i++){
            if(set1.contains(nums2[i]))set2.add(nums2[i]);
        }
        int[] result = new int[set2.size()];
        int i = 0;
        for(int e : set2){
            result[i++] = e;
        }
        return result;
    }
}
