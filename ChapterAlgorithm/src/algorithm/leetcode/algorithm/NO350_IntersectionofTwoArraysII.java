package algorithm.leetcode.algorithm;
/*
 * easy
 * 350. Intersection of Two Arrays II
 *  Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:

    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.

Follow up:

    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited 
    such that you cannot load all elements into the memory at once?
    

    If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, 
    read chunks of array that fit into the memory, and record the intersections.
    If both nums1 and nums2 are so huge that neither fit into the memory, 
    sort them individually (external sort), then read 2 elements from each array at a time in memory,
     record intersections.
 */
import java.util.*;
public class NO350_IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < nums1.length ; i++){
            if(map.containsKey(nums1[i])){
                map.put(nums1[i],map.get(nums1[i])+1);
            }else{
                map.put(nums1[i],1);
            }
        }
        for(int i = 0 ; i < nums2.length ; i++){
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0){
                list.add(nums2[i]);
                map.put(nums2[i],map.get(nums2[i])-1);
            }
        }
        int[] result = new int[list.size()];
        int i = 0;
        for(int e : list){
            result[i++] = e;
        }
        return result;
    }
}
