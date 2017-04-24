package algorithm.leetcode.algorithm;
/*
 * easy
 * 26. Remove Duplicates from Sorted Array 
 *  Given a sorted array, remove the duplicates in place such that each element appear only once 
 *  and return the new length.
 *  Do not allocate extra space for another array, you must do this in place with constant memory.
 *  For example,
 *  Given input array nums = [1,1,2],
 *  Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
 *  It doesn't matter what you leave beyond the new length. 
 */
import java.util.*;
public class NO26_RemoveDuplicatesfromSortedArray {
    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
//        Arrays.sort(nums);
        int count = 1;
        int tmp = nums[0];
        int length = nums.length;
        for(int i = 1 ; i < length ; i++){
            if(tmp != nums[i]){
                tmp = nums[i];
                nums[count] = tmp;
                count++;
            }
        }
        return count;
    }
}
