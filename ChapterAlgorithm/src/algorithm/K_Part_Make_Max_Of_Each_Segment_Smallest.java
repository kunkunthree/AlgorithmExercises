package algorithm;
/*
 * 
 */
public class K_Part_Make_Max_Of_Each_Segment_Smallest {
	public static void main(String[] args) {
		int[] nums = new int[]{100,50,70,90,110};
		int k = 2;
		System.out.println( getMinMaxOfKSegment2(nums, k));
	}
	//方法1：
	//dp，O(nk)time,O(n)space
	public static int getMinMaxOfKSegment(int[] nums,int k){
		int[] sum = new int[nums.length+1];
		for(int i = 0 ; i < nums.length ; i++){
			sum[i+1] = sum[i] + nums[i];
		}
		return helper(sum,Integer.MIN_VALUE,1,sum.length,1,k);
	}
	public static int helper(int[] sum,int curMax,int start,int end,int count,int k){
		int result = Integer.MAX_VALUE;
		if(count == k){
			result = Math.max(curMax, sum[end-1]-sum[start-1]);
		}
		for(int i = start ; count < k && i < end ; i++){
			result = Math.min(result, helper(sum,Math.max(curMax, sum[i]-sum[start-1]),i+1,end,count+1,k));
		}
		return result;
	}
	//方法2：
	//二分法，从数组最大元素的值到数组总和的值
	public static int getMinMaxOfKSegment2(int[] nums,int k){
		int left = Integer.MIN_VALUE,right = 0;
		for(int num : nums){
			left = Math.max(left,num);
			right+=num;
		}
		System.out.println(left + " " + right);
		while(left <= right){
			int mid = left + (right - left)/2;
			int count = getMaxNumOfSegments(nums, mid);
			if(count <= k){
				right = mid-1;
			}else{
				left = mid+1;
			}
		}
		return left;
	}
	public static int getMaxNumOfSegments(int[] nums,int minMax){
		int count = 1,sum = 0;
		for(int i = 0 ; i < nums.length ; i++){
			sum+=nums[i];
			if(sum > minMax){
				count++;
				sum = nums[i];
			}
		}
		return count;
	}
}
