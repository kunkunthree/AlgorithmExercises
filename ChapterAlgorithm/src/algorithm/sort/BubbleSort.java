package algorithm.sort;
import java.util.*;
import static xie.util.Print.*;
/*
 * 冒泡排序：
 * 		冒泡排序就是把小的元素往前调或者把大的元素往后调。
 * 		比较是相邻的两个元素比较，交换也发生在这两个元素之间。
 * 		所以，如果两个元素相等，我想你是不会再无 聊地把他们俩交换一下的；如果两个相等的元素没有相邻，
 * 		那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，所以相同元素的前后顺序并没有改 变，
 * 
 * 		所以冒泡排序是一种稳定排序算法。
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
