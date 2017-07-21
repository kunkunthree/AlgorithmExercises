package algorithm.leetcode.algorithm;
/*
 * easy
 * 53. Maximum Subarray :
 *  Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6. 

similar problems:
121. Best Time to Buy and Sell Stock 
152. Maximum Product Subarray 
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
	
	public int maxSubArray2(int[] A) {
	    int max = Integer.MIN_VALUE, sum = 0;
	    for (int i = 0; i < A.length; i++) {
	    	//如果累加和出现小于0的情况，  
	        //则和最大的子序列肯定不可能包含前面的元素，  
	        //这时将累加和置0，从下个元素重新开始累加
	        if (sum < 0) 
	            sum = A[i];
	        else 
	            sum += A[i];
	        if (sum > max)
	            max = sum;
	    }
	    return max;
	}

}
