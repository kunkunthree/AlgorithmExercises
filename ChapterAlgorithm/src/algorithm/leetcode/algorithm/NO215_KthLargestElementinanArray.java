package algorithm.leetcode.algorithm;
/*
 * medium
 * 215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
import java.util.*;
public class NO215_KthLargestElementinanArray {
	//方法1：
	//O(n*k)time,O(k)space
	public int findKthLargest(int[] nums, int k) {
        Integer[] max = new Integer[k];
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = 0 ; j < max.length ; j++){
                if(max[j] == null || nums[i] > max[j]){
                    for(int m = max.length-1 ; m > j ; m--){
                        max[m] = max[m-1];
                    }
                    max[j] = nums[i];
                    break;
                }
            }
        }
        return max[k-1] == null ? max[0] : max[k-1];
    }
	//方法2：
	//快速选择，quick select，最快O(n),最慢O(n^n)
	public static int findKthLargest2(int[] nums, int k) {
        return findKthLargestHelper(nums,0,nums.length-1,k-1);
    }
    public static int findKthLargestHelper(int[] nums,int start,int end, int k){
        if(start > end){
            return Integer.MAX_VALUE;
        }
        int pivot = nums[start];
        int left = start;
        for(int i = start+1 ; i <= end ; i++){
            if(nums[i]>pivot){
                swap(nums,i,++left);
            }
        }
        swap(nums,start,left);
        if(left == k){
            return nums[left];
        }else if(left > k){
            return findKthLargestHelper(nums,start,left-1,k);
        }else{
            return findKthLargestHelper(nums,left+1,end,k);
        }
    }
    private static void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    //方法3：
    //先排序，后选择
    public static int findKthLargest3(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
