package algorithm.leetcode.algorithm;
/*
 * easy
 * 27. Remove Element 
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 * 
 * similar problems:
 * 26. Remove Duplicates from Sorted Array 
 * 283. Move Zeroes 
 * 203. Remove Linked List Elements 
 */
import java.util.*;
public class NO27_RemoveElement {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			int[] array = new int[n];
			for(int i = 0 ; i < n ; i++){
				array[i] = in.nextInt();
			}
			int val = in.nextInt();
			System.out.println(removeElement(array, val));
		}
	}
	//方法1：
	//迭代，用两个指针分别指向头和尾，向中间靠拢
	//把尾部遇到的不等于val的值设置到头部指针所指向的等于val的值
    public static  int removeElement(int[] nums, int val) {
            int length = nums.length;
            int count = 0,left = 0,right = length-1;
            for(int i = 0 ; i < length ; i++){
			if (nums[i] != val) {
				count++;
			}
		}
		while (left < right) {
			while (left < right && nums[left] != val) {
				left++;
			}
			while (left < right && nums[right] == val) {
				right--;
			}
			if (left < right) {
				nums[left] = nums[right];
				left++;
				right--;
			}
		}
		return count;
	}
    //方法2：
    //简洁写法
    public int removeElement2(int[] A, int elem) {
        int len = A.length;
        for (int i = 0 ; i< len; ++i){
            while (A[i]==elem && i< len) {
                A[i]=A[--len];
            }
        }
        return len;
    }
}
