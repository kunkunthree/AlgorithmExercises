package algorithm.leetcode.algorithm;
/*
 * easy
 * 189. Rotate Array
 * Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

Related problem: Reverse Words in a String II
 */
public class NO189_RotateArray {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k%length;
        reverse(nums,length-k,length-1);
        reverse(nums,0,length-k-1);
        reverse(nums,0,length-1);
    }
    public void reverse(int[] nums ,int start,int end){
        int tmp;
        while(start < end){
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
