package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 寻找最大的k个数:
 *【解法一】
			对N个数进行排序，然后选出最大的K个数。可以使用快速排序或堆排序，时间复杂度为O(NlogN)。
	因为这里N的数目可能非常大，即N>>K，而前N-K个数可以不进行排序，使用可以部分排序的算法，
	如选择排序或交换排序，时间复杂度为O(NK)。
 */
import static algorithm.sort.QuickSort.quickSort;
import static xie.util.Print.println;

import java.math.*;
import java.util.Arrays;
public class GetBiggestK {
	public static void main(String[] args) {
		int[] array = new int[]{65, 70, 41, 59, 67, 91, 62, 62, 62, 1, 28, 58, 50};
		int[] result = getBiggestK2(array, 4,0,array.length-1);
		System.out.println(Arrays.toString(result));
		println("sort before:");
		println(Arrays.toString(array));
		quickSort(array, 0, array.length-1);
		println("sort after:");
		println(Arrays.toString(array));
	}
	public static int[] getBiggestK1(int[] array,int K){
		if(K <= 0){
			return new int[]{};
		}
		if(Math.log(array.length)/Math.log(2) < K){
			quickSort(array, 0, array.length-1);
			return Arrays.copyOfRange(array, array.length-K+1, array.length);
		}else{
			int[] tmpArray = new int[K];
			for(int i = 0 ; i < K ; i++){
				tmpArray[i] = Integer.MIN_VALUE;
			}
			for(int i = 0 ; i < array.length ; i++){
				int j = 0;
				while(j < K){
					if(array[i] > tmpArray[j]){
						for(int t = K-1 ; t > j ; t--){
							tmpArray[t] = tmpArray[t-1];
						}
						tmpArray[j++] = array[i];
						break;
					}
					j++;
				}
			}
			return tmpArray;
		}
	}
	public static int[] getBiggestK2(int[] array,int K,int low,int high){
		if(K <= 0){
			return new int[]{};
		}
		if(high-low+1 <= K){
			return Arrays.copyOfRange(array, low,high+1);
		}
		//以数组第一个元素作为分割点，把数组分割为两边，左边大于该元素，右边小于等于该元素
		int tmp = array[low];
		int left = low,right = high;
		while(left < right){
			while(left< right && array[right] <= tmp){
				right--;
			}
			array[left] = array[right];
			while(left < right && array[left] > tmp){
				left++;
			}
			array[right] = array[left];
		}
		array[left] = tmp;
		if(left-low+1 >= K){
			return getBiggestK2(array, K, low, left);
		}else{//left-low+1 < K 即[low,left]的个数小于K
			int[] result = new int[K];
			//把[low,left]
			for(int i = 0 ; i < left-low+1 ; i++){
				result[i] = array[low+i];
			}
			int add[] = getBiggestK2(array, K-(left-low+1), left+1, high);
			for(int i = 0 ; i < add.length ; i++){
				result[i+left-low+1] = add[i];
			}
			return result;
		}
	}
}
