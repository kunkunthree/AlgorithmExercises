package algorithm.sort;
import java.util.Arrays;

/*
 * 基数排序：
 * 		基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
 * 有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优 先级排序，最后的次序就是高优先级高的在前，
 * 高优先级相同的低优先级高的在前。基数排序基于分别排序，分别收集，
 * 
 * 所以其是稳定的排序算法。
 */
import java.util.*;
public class RadixSort {
	public static final int RADIX = 10;	//基数
	public static void main(String[] args) {
		int[] array = new int[] { 326, 453, 608, 835, 751, 435, 704, 690, 88, 79, 79 };// { 333, 956, 175, 345, 212, 542, 99, 87 }
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < array.length ; i++){
			if(array[i] > max)max = array[i];
		}
		int length = 0;
		while(max !=0){
			length++;
			max/=RADIX;
		}
		radixSort(array, length, RADIX);
		
	}
	//升序
	//d为数据长度
	public static void radixSort(int[] array,int d,int radix){
		for(int i = 0 ; i < d ; i++){
			array = chainRadixSort(array,i,radix);
			printArray(array,i+1,d,radix);
		}
	}
	public static void printArray(int[] array,int num,int length,int radix){
		if(num == length){
			System.out.println("最终结果为：" + Arrays.toString(array));
		}else{
			System.out.println("第"+num+"次排序后结果为：" +Arrays.toString(array));
		}
	}
	//链式基数排序
	public static int[] chainRadixSort(int[] array,int index,int radix){
		ArrayList<ArrayList<Integer>> chainList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0 ; i < radix ; i++){
			chainList.add(new ArrayList<Integer>());
		}
		for(int i = 0 ; i < array.length ; i++){
			int d = getBitData(array[i], index, radix);
			chainList.get(d).add(array[i]);
		}
		int count = 0;
		for(int i = 0 ; i < radix ; i++){
			int j = 0;
			ArrayList<Integer> tmpList = chainList.get(i);
			while(j < chainList.get(i).size()){
				array[count++] = tmpList.get(j++);
			}
		}
		return array;
	}
	//基数排序
	public static int[] countingSort(int[] array,int index,int radix){
		int[] resultArray = new int[array.length];
		int[] count = new int[radix];
		for(int i  = 0 ; i < radix; i++){
			count[i] = 0;
		}
		//统计每个位的个数
		for(int i = 0 ; i < array.length ; i++){
			int d = getBitData(array[i],index,RADIX);
			count[d]++;
		}
		//得到某个位的最后一个数的位置
		for(int i = 1 ; i < radix ; i++){
			count[i]+=count[i-1];
		}
		for(int i = array.length-1 ; i>=0 ; i--){
			int d = getBitData(array[i],index,RADIX);
			resultArray[count[d]-1]=array[i];
			count[d]--;
		}
		return resultArray;
	}
	//index表示从右往左第几位
	public static int getBitData(int data,int index,int radix){
		while(data != 0 && index > 0){
			data/=radix;
			index--;
		}
		data%=radix;
		return data;
	}
}
