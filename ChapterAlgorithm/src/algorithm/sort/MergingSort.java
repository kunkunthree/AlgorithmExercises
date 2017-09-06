package algorithm.sort;

import java.util.Arrays;

/*
 * 归并排序：
 *		假设初始序列含有n个记录，则可看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，
 *得到n/2个长度为2或1的有序子序列，再两两归并，......，如此重复，直至得到一个长度为n的有序序列为止，
 *这种排序方法成为2-路归并排序。 		
 *
 *归并排序是把序列递归地分成短序列，递归出口是短序列只有1个元素(认为直接有序)或者2个序列(1次比较和交换),
 *然后把各个有序的段序列合并成一个有 序的长序列，不断合并直到原序列全部排好序。
 *可以发现，在1个或2个元素时，1个元素不会交换，2个元素如果大小相等也没有人故意交换，这不会破坏稳定 性。
 *那么，在短的有序序列合并的过程中，稳定是是否受到破坏？
 *没有，合并过程中我们可以保证如果两个当前元素相等时，我们把处在前面的序列的元素保存在结 果序列的前面，
 *这样就保证了稳定性。
 *
 *所以，归并排序也是稳定的排序算法。
 */
public class MergingSort {
	public static void main(String[] args) {
		int[] array = new int[] { 326, 453, 608, 835, 751, 435, 704, 690, 88, 79, 79 };// { 333, 956, 175, 345, 212, 542, 99, 87 }
		System.out.println("排序前：");
		System.out.println(Arrays.toString(array));
		MSort(array, 0, array.length-1);
		System.out.println("排序后：");
		System.out.println(Arrays.toString(array));
	}
	//方法1：
	//归并排序
	public static void Merge(int[] array,int index,int m,int n){
		int start = index;
		int[] tmpArray = new int[n-start+1];
		for(int i = 0 ; i < tmpArray.length ; i++){
			tmpArray[i] = array[i+start];
		}
		int i ,j;
		for(i = index - start,j = m+1 - start;i <= m-start && j <= n-start ; index++){
			if(tmpArray[i] < tmpArray[j]){
				array[index] = tmpArray[i++];
			}else{
				array[index]=tmpArray[j++];
			}
		}
		while(i <= m - start){
			array[index++] = tmpArray[i++];
		}
		while(j <= n - start){
			array[index++] = tmpArray[j++];
		}
	}
	public static void MSort(int[] array,int start,int end){
//		int[] tmpArray = new int[end-start+1];
//		for(int i = 0 ; i < tmpArray.length ; i++){
//			tmpArray[i] = array[i+start];
//		}
		if(start == end){
//			array[start] = tmpArray[0];
		}else{
			int m = (start+end)/2;
			MSort(array,start,m);
			MSort(array, m+1, end);
			Merge(array, start, m, end);
		}
	}
}
