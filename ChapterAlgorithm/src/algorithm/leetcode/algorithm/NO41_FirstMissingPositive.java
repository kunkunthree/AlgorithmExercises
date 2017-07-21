package algorithm.leetcode.algorithm;
/*
 * hard
 * 41. First Missing Positive 
 *  Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space. 

siimilar problems:
268. Missing Number 
287. Find the Duplicate Number
448. Find All Numbers Disappeared in an Array  
 */
public class NO41_FirstMissingPositive {
	public static void main(String[] args) {
		int[] nums = new int[]{0,1,2};
		System.out.println(firstMissingPositive(nums));
	}
	//方法1：
	//第一次遍历，把所有超出范围的数都赋值为0
	//第二次遍历，把所有范围内的数所对应的下标的数，取非正数
	//第三次遍历，找出第一个非负数
	public static int firstMissingPositive(int[] nums) {
		if(nums == null || nums.length == 0){
            return 1;
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] < 0 || nums[i] > nums.length){
                nums[i] = 0;
            }
        }
        for(int i = 0 ; i < nums.length ; i++){
            int index = i;
            if(nums[index] > 0){
                index = nums[index]-1;
                while(index >= 0){
                    int next = nums[index]-1;
                    if(nums[index] >= 0)nums[index] = -nums[index]-1;
                    index = next;
                }
            }
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] >= 0){
                return i+1;
            }
        }
        return nums.length+1;
    }
	//方法2：
	//把每一次遇到的有效值放到其所对应的位置
	public int firstMissingPositive2(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;	//已经在适当的位置，或者无效值
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);		//放到适当的位置
            else i++;	//已经在适当的位置
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }
    
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
