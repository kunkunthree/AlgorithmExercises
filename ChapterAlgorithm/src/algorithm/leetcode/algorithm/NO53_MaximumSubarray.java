package algorithm.leetcode.algorithm;
/*
 * easy
 * 53. Maximum Subarray :
 *  Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6. 
 */
public class NO53_MaximumSubarray {
	/*
	 * 假设和最大的一段为{A[i]....A[j]}
	 * 1.当0 = i = j 时，元素A[0]本身构成和最大的一段
	 * 2.当0 = i < j 时，和最大的一段以A[0]开始
	 * 3.当0 < i时，元素A[0]跟和最大的一段没有关系
	 * 假设已知{A[i]....A[n-1]}中和最大的一段为All[i],并已知{A[i],....A[n-1]}中包含A[i]的和最大的一段为start[i]
	 * start[i]= max(A[i],A[i]+start[i+1])
	 * All[i] = max(start[i],All[i+1])
	 */
	public static int maxSubArray(int[] array){
		int length = array.length;
		int start = array[length-1];
		int All = array[length-1];
		for(int i = array.length-2 ; i >=0 ; i--){
			start= max(array[i],array[i]+start);
			All = max(start,All);
		}
		return All;
	}
	public static int max(int x,int y){
		return x > y ? x : y;
	}
}
