package algorithm.sort;
import java.util.*;
import static xie.util.Print.*;
/*
 * 冒泡排序：
 */
public class BubbleSort {
	//升序
	public static void bubbleSort(int[] array){
		int tmp = 0;
		for(int i = 0 ; i < array.length ; i++){
			for(int j = 0 ; j < array.length - i - 1 ; j++){
				if(array[j] > array[j+1]){
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
	}
	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[rand.nextInt(20)];
		for(int i = 0 ; i < array.length ; i++){
			array[i] = rand.nextInt(100);
		}
		println("sort before:");
		println(Arrays.toString(array));
		bubbleSort(array);
		println("sort after:");
		println(Arrays.toString(array));
	}
}
