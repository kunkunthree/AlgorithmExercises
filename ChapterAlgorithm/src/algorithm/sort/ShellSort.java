package algorithm.sort;
/*
 * 希尔排序：
 * 		Shell排序是DL. Shell于1959年针对直接插入排序算法改进提出的，属于插入排序的范畴，
 * 是对直接插入排序算法的改进。直接插入排序在基本有序时效率较高，并且在序列规模不是很大时效率也很高，
 * Shell排序就是针对这两点进行改进。核心思想是：待排序列有n个元素，先取一个小于n的整数h1作为第一个增量，
 * 把待排序列以间隔h1分成若干子序列，子序列内使用插入排序；然后取第二个增量h2(< h1)，重复上述的划分和排序
 * ，直至所取的增量hl = 1 (h1 > h2 > ... > hl)。

        这样不管序列多么庞大，在先前较大步长分组下每个子序列规模都不是很大，用直接插入效率很高；
        后面步长变小，子序列变大，但由于整体有序性越来越明显，排序效率依然很高，大大提高了时间效率。
 */
import java.util.*;
public class ShellSort {
	public static void main(String[] args) {
		int[] array = new int[]{65, 70, 41, 59, 67, 91, 62, 62, 62, 1, 28, 58, 50};
		int n = (int)Math.floor(Math.log(array.length)/Math.log(2));
		int length = array.length/2;
		int[] delta = new int[n];
		for(int i = 0 ; i < n ; i++){
			delta[i] = length;
			length/=2;
		}
		System.out.println(array.length + "   " + n);
		System.out.println(Arrays.toString(delta));
		System.out.println("排序前：");
		System.out.println(Arrays.toString(array));
		shellSort(array, delta);
		System.out.println("排序后：");
		System.out.println(Arrays.toString(array));
	}
	//升序
	public static void shellInsert(int[] array,int dk){
		for(int i = dk ; i<array.length ; i++){
			if(array[i] < array[i-dk]){
				int tmp = array[i];
				int j ;
				for(j = i - dk; j >= 0 && tmp < array[j] ; j-=dk){
					array[j + dk] = array[j];
				}
				array[j+dk] = tmp;
			}
		}
	}
	public static void shellSort(int[] array,int[] delta){
		//按增量序列对顺序表array进行希尔排序
		for(int i = 0 ; i < delta.length ; i++){
			shellInsert(array, delta[i]);
		}
	}
}
