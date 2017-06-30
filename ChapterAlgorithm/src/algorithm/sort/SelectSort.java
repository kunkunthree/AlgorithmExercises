package algorithm.sort;

import java.util.Arrays;

/*
 * 直接选择排序：
 * 	原理：选择一个值array[0]作为标杆，然后循环找到除这个值外最小的值（查找小于标杆的最小值），
 * 交换这两个值，这时最小值就被放到了array[0]上，然后再将array[1]作为标杆，从剩下未排序的值中找到最小值，
 * 并交换这两个值。
 * 时间复杂度：O(N^2)，与冒泡排序相比减少了数组交换的次数
 *时间复杂度与初始顺序无关
 */
public class SelectSort {
	public static void main(String[] args) {
		int[] nums = new int[]{1,5,3,2,6,8,3,5,8,10,4};
		System.out.println(Arrays.toString(nums));
		selectSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	public static void selectSort(int[] nums){
		if(nums == null || nums.length <= 1){
			return;
		}
		for(int i = 0 ; i < nums.length ; i++){
			int index = i;
			for(int j = i+1 ; j < nums.length ; j++){
				if(nums[j] < nums[index]){
					index = j;
				}
			}
			if(index != i){
				int tmp = nums[i];
				nums[i] = nums[index];
				nums[index] = tmp;
			}
		}
	}
}
