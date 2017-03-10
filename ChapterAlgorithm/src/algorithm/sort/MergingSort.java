package algorithm.sort;

import java.util.Arrays;

/*
 * 归并排序：
 *		假设初始序列含有n个记录，则可看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，
 *得到n/2个长度为2或1的有序子序列，再两两归并，......，如此重复，直至得到一个长度为n的有序序列为止，
 *这种排序方法成为2-路归并排序。 		
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
		int[] tmpArray = new int[end-start+1];
		for(int i = 0 ; i < tmpArray.length ; i++){
			tmpArray[i] = array[i+start];
		}
		if(start == end){
			array[start] = tmpArray[0];
		}else{
			int m = (start+end)/2;
			MSort(array,start,m);
			MSort(array, m+1, end);
			Merge(array, start, m, end);
		}
	}
}
