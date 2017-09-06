package algorithm.sort;
import static xie.util.Print.*;

import java.util.*;
/*
 * 插入排序：
 * 插入排序是在一个已经有序的小序列的基础上，一次插入一个元素。
 * 当然，刚开始这个有序的小序列只有1个元素，就是第一个元素。
 * 比较是从有序序列的末尾开 始，也就是想要插入的元素和已经有序的最大者开始比起，
 * 如果比它大则直接插入在其后面，否则一直往前找直到找到它该插入的位置。
 * 如果碰见一个和插入元素相 等的，那么插入元素把想插入的元素放在相等元素的后面。
 * 所以，相等元素的前后顺序没有改变，从原无序序列出去的顺序就是排好序后的顺序，
 * 
 * 所以插入排序是稳 定的。
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
