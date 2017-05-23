package algorithm.leetcode.algorithm;
/*
 * medium
 * 384. Shuffle an Array 
 * Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

 */
import java.util.*;
public class NO384_ShuffleanArray {
	//方法1：
	//随机排列，前面的数随机与后面的数交换，递归实现
	public class Solution {
	    int[] original;
	    int[] copy;
	    Random rand;
	    public Solution(int[] nums) {
	        original = nums;
	        rand = new Random();
	        copy = new int[nums.length];
	        for(int i = 0 ; i < nums.length ; i++){
	            copy[i] = nums[i];
	        }
	    }
	    
	    private void helper(int[] nums,int start,int end){
	        if(start == end){
	            return;
	        }
	        int i = rand.nextInt(end-start) + start;
	        swap(nums,i,start);
	        helper(nums,start+1,end);
	    }
	    private void swap(int[] nums,int i ,int j){
	        int tmp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = tmp;
	    }
	    /** Resets the array to its original configuration and return it. */
	    public int[] reset() {
	        int[] result = new int[original.length];
	        for(int i = 0 ; i < original.length ; i++){
	            result[i] = original[i];
	        }
	        return result;
	    }
	    
	    /** Returns a random shuffling of the array. */
	    public int[] shuffle() {
	        helper(copy,0,copy.length);
	        return copy;
	    }
	}
	//方法2：
	//迭代实现
	//随机排列，后面的数随机与前面的数交换
	public class Solution2 {
	    private int[] nums;
	    private Random random;

	    public Solution2(int[] nums) {
	        this.nums = nums;
	        random = new Random();
	    }
	    
	    /** Resets the array to its original configuration and return it. */
	    public int[] reset() {
	        return nums;
	    }
	    
	    /** Returns a random shuffling of the array. */
	    public int[] shuffle() {
	        if(nums == null) return null;
	        int[] a = nums.clone();
	        for(int j = 1; j < a.length; j++) {
	            int i = random.nextInt(j + 1);
	            swap(a, i, j);
	        }
	        return a;
	    }
	    
	    private void swap(int[] a, int i, int j) {
	        int t = a[i];
	        a[i] = a[j];
	        a[j] = t;
	    }
	}
}
