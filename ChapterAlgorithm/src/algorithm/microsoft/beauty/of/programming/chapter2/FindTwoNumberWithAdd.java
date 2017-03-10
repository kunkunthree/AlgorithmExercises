package algorithm.microsoft.beauty.of.programming.chapter2;

import java.util.Arrays;

/*
 * 2.12:	快速寻找符合条件的两个数
 * 能否快速找出一个数组中的两个数，让这两个数字之和等于一个给定的数，为了简化起见，
 * 我们假设这个数组中肯定存在这样一组或以上符合要求的解
 */
import java.util.*;
public class FindTwoNumberWithAdd {
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int length = in.nextInt();
//		int n = in.nextInt();
//		int[] array = new int[length];
//		for(int i = 0; i < length ; i++){
//			array[i] = in.nextInt();
//		}
		int[] array = new int[]{65, 70, 41, 59, 67, 91, 62, 62, 62, 1, 28, 58, 50};
		int n = 112;
		int[] result = getTwoNumFitAdd(array, n);
		System.out.println("数组中符合和为 "+n+" 的是 " +result[0] + "和" + result[1]);
	}
	//只返回一组
	public static int[] getTwoNumFitAdd(int[] array,int n){
		Arrays.sort(array);
		int[] result = new int[2];
		int left = 0;
		int right = array.length-1;
		while(left < right && array[left] < n/2 && array[right] > n/2){
			int tmp = array[left] + array[right];
			if(tmp == n){
				result[0] = array[left];
				result[1] = array[right];
				break;
			}
			else if(tmp > n){
				right--;
			}else{
				left++;
			}
		}
		return result;
	}
}
class NumberSet{
	int a;
	int b;
	public NumberSet(int a,int b) {
		this.a = a;
		this.b = b;
	}
}