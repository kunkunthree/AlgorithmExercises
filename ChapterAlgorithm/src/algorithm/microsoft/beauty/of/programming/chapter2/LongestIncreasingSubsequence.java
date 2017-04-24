package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.16	求最长递增子序列：
 * 		写一个时间复杂度尽可能低的程序，求一个一维数组中的最长递增子序列的长度
 */
import java.util.*;
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] array = new int[n];
		for(int i = 0 ; i < n  ; i++){
			array[i] = in.nextInt();
		}
		System.out.println("最大递增子序列长度为：" +getLonggestIncreasingSubsequence(array));
	}
	//动态规划：
	//设LIS[i]为前i个元素中以第i个元素结尾的最长递增子序列,i = 0,1,....,n-1
	//则若array[k] > array[i]，则LIS[k]=max{LIS[i],i = 0,1,....,k-1} + 1。
	//则LIS数组中最大的值则为原数组array的最大递增子序列长度
	//复杂度为O(N^2)
	public static int getLonggestIncreasingSubsequence(int[] array){
		int[] LIS = new int[array.length];
		int[] last = new int[array.length];
		for(int i = 0 ; i < array.length ; i++){
			last[i] = -1;
		}
		LIS[0] = 1;
		for(int i = 1 ;  i < array.length ; i++){
			for(int j = 0 ; j < i ; j++){
				if(array[i] > array[j] && LIS[j] + 1 > LIS[i]){
					LIS[i] = LIS[j] + 1;
					last[i] = j;
				}
			}
		}
		int maxLength = 0;
		int index = 0;
		for(int i = 0 ; i < array.length ; i++){
			if(LIS[i] > maxLength){
				index = i;
				maxLength = LIS[i];
			}
		}
		StringBuilder result = new StringBuilder();
		while(index >= 0){
			result.insert(0, array[index]+" ");
			index = last[index];
		}
		System.out.println("最长递增子序列为：" + result.toString());
		return maxLength;
	}
	
}
