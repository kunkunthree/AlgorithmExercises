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
