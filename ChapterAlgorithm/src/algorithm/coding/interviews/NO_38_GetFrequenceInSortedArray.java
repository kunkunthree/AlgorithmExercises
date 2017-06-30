package algorithm.coding.interviews;

public class NO_38_GetFrequenceInSortedArray {
	public static void main(String[] args) {
		int[] nums = new int[]{1,1,1,2,2,2,2,3,3,3,3};
		int k = 2;
		System.out.println(getFirstK(nums, k));
		System.out.println(getLastK(nums, k));
		System.out.println(getFrequence(nums, k));
		System.out.println(getFrequence(nums, 0));
	}
	public static int getFrequence(int[] nums,int k){
		int first = getFirstK(nums, k);
		int last = getLastK(nums, k);
		if(first >= 0 && last >= 0){
			return last - first + 1;
		}else{
			return 0;
		}
	}
	public static int getFirstK(int[] nums,int k){
		if(nums == null || nums.length == 0){
			return -1;
		}
		int left = 0,right = nums.length-1,mid;
		while(left < right){
			mid = left + (right - left)/2;
			if(nums[mid] >= k){
				right = mid;
			}else{
				left = mid+1;
			}
		}
		return left <= nums.length && nums[left] == k ? left : -1;
	}
	public static int getLastK(int[] nums,int k){
		if(nums == null || nums.length == 0){
			return -1;
		}
		int left = 0,right = nums.length-1,mid;
		while(left < right){
			mid = right - (right - left)/2;
			if(nums[mid] <= k){
				left = mid;
			}else{
				right = mid-1;
			}
		}
		return right >= 0 && nums[right] == k ? right : -1;
	}
}
