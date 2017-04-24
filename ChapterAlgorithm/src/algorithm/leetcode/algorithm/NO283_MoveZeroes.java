package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * easy
 * 283. Move Zeroes
 *  Given an array nums, write a function to move all 0's to the end of it 
 *  while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:

 1.    You must do this in-place without making a copy of the array.
 2.    Minimize the total number of operations.

 */
public class NO283_MoveZeroes {
	public static void main(String[] args) {
		int[] x = new int[] { 4,2,4,0,0,3,0,5,1,0 };
		System.out.println(Arrays.toString(x));
		moveZeroes(x);
		System.out.println(Arrays.toString(x));
	}

	public static void moveZeroes(int[] nums) {
        int zeroIndex = 0,nonZeroIndex = 0,length = nums.length,tmp;
        while(zeroIndex < length && nums[zeroIndex] != 0){
                zeroIndex++;
        }
        nonZeroIndex = zeroIndex+1;
        while(nonZeroIndex < length){
            while(nonZeroIndex < length  && nums[zeroIndex] != 0){
                zeroIndex++;
            }
            while(nonZeroIndex < length  && nums[nonZeroIndex] == 0){
                nonZeroIndex++;
            }
            if(nonZeroIndex < length){
                tmp = nums[nonZeroIndex];
                nums[nonZeroIndex] = nums[zeroIndex];
                nums[zeroIndex] = tmp;
            }
        }
    }
	//更简单的方法，先把非0项设置在前面，然后把后面全部置零，O(n)time,O(1)space
	public void moveZeroes2(int[] nums) {
		if (nums == null || nums.length == 0) return;        
		
		int insertPos = 0;
		for (int num: nums) {
			if (num != 0) nums[insertPos++] = num;
		}        
		while (insertPos < nums.length) {
			nums[insertPos++] = 0;
		}
	}
}
