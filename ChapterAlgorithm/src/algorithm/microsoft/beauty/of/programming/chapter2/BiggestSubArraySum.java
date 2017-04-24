package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.14	求数组的子数组之和的最大值：
 * 	一个有N个整数元素的一维数组中求子数组的最大和。
 * 子数组是连续的，只求和，不求位置，整数
 */
import java.util.*;
public class BiggestSubArraySum {
	public static void main(String[] args) {
		Random rand = new Random();
		int n = rand.nextInt(10)+10;
		int[] array = new int[n];
		for(int i = 0 ; i < n ;i++){
			array[i] = rand.nextInt(100)-50;
		}
		System.out.println(Arrays.toString(array));
		System.out.println(getMaxSum1(array));
		System.out.println(getMaxSum2(array));
	}
	public static int getMaxSum1(int[] array){
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		for(int i = 0 ; i < array.length ; i++){
			sum = 0;
			for(int j = i ; j < array.length ; j++){
				sum+=array[j];
				if(maxSum < sum){
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}
	/*
	 * 假设和最大的一段为{A[i]....A[j]}
	 * 1.当0 = i = j 时，元素A[0]本身构成和最大的一段
	 * 2.当0 = i < j 时，和最大的一段以A[0]开始
	 * 3.当0 < i时，元素A[0]跟和最大的一段没有关系
	 * 假设已知{A[i]....A[n-1]}中和最大的一段为All[i],并已知{A[i],....A[n-1]}中包含A[i]的和最大的一段为start[i]
	 * start[i]= max(A[i],A[i]+start[i+1])
	 * All[i] = max(start[i],All[i+1])
	 */
	public static int getMaxSum2(int[] array){
		int length = array.length;
		int[] All = new int[length];
		int[] start = new int[length];
		All[length-1] = array[length-1];
		start[length-1] = array[length-1];
		for(int i = array.length-2 ; i >=0 ; i--){
			start[i]= max(array[i],array[i]+start[i+1]);
			All[i] = max(start[i],All[i+1]);
		}
		return All[0];
	}
	//优化
	public static int getMaxSum3(int[] array){
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
