package algorithm.coding.interviews;

import java.util.Arrays;

/*
 * 数组中的逆转对
 */
public class NO_37_InversePairs {
	public static void main(String[] args) {
		int[] nums = new int[]{7,5,6,4};
		System.out.println(inversePairs(nums));
	}
	public static int inversePairs(int[] nums){
		if(nums == null || nums.length <= 1){
			return 0;
		}
		int[] copy = Arrays.copyOf(nums, nums.length);
		return inversePairsHelper(nums,copy, 0, nums.length-1);
	}
	private static int inversePairsHelper(int[] nums,int[] copy,int start,int end){
		if(start == end){
			return 0;
		}
		int mid = start + (end - start)/2;
		int leftCount = inversePairsHelper(nums,copy, start, mid);
		int rightCount = inversePairsHelper(nums,copy, mid+1, end);
		int curCount = 0;
		int left = start,right = mid+1,index = start;
		while(left <= mid && right <= end){
			if(nums[left] > nums[right]){
				curCount+=mid - left+1;
				copy[index++] = nums[right++];
			}else{
				copy[index++] = nums[left++];
			}
		}
		while(left <= mid){
			copy[index++] = nums[left++];
		}
		while(right <= end){
			copy[index++] = nums[right++];
		}
		for(int i = start ; i <= end ; i++){
			nums[i] = copy[i];
		}
		return leftCount+rightCount+curCount;
	}
}
