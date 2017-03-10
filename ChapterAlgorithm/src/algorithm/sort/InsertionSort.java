package algorithm.sort;
import static xie.util.Print.*;

import java.util.*;
/*
 * 插入排序：
 */
public class InsertionSort {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[rand.nextInt(20)];
		for(int i = 0 ; i < array.length ; i++){
			array[i] = rand.nextInt(100);
		}
		println("sort before:");
		println(Arrays.toString(array));
		insertSort(array);
		println("sort after:");
		println(Arrays.toString(array));
	}
	//升序
	public static void insertSort(int[] array){
		for(int i = 0 ; i < array.length ; i++){	//遍历数组，从1开始
			int j;				
			int tmp = array[i];								//临时存储当前的数字，把位置空出来
			for(j = i ; j > 0; j--){								//遍历i之前的数字
				if(array[j-1] > tmp){						//如果前面的数大于临时存储的数，则这个数往后移动一个位置
					array[j] = array[j-1]; 
				}
				else{
					//如果当前的数小于等于临时存储的数，则停止遍历，说明前面的数都小于等于临时存储的数。
					//退出当前循环比较
					break;
				}
			}
			array[j] = tmp;		//填补空缺位置为临时存储的数
		}
	}
}
