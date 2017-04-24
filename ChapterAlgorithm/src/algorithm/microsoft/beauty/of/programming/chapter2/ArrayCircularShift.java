package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.17	数组循环移位：
 * 		设计一个算法，把一个含有N个元素的数组循环右移K位，要求时间复杂度为O(N)，且只允许使用两个附加变量。
 */
import java.util.*;
public class ArrayCircularShift {
	public static void main(String[] args) {
		Random rand = new Random();
		int n = rand.nextInt(10) + 10;
		int K = rand.nextInt(n);
		System.out.println(n + "  " + K);
		int[] array = new int[n];
		for(int i = 0 ; i < n ; i++){
			array[i] = rand.nextInt(100);
		}
		System.out.println(Arrays.toString(array));
		arrayCircularShift3(array,K);
		System.out.println(Arrays.toString(array));
	}
	//O(K*N)
	public static void arrayCircularShift1(int[] array,int K){
		while(K-- > 0){
			int t = array[array.length-1];
			for(int i = array.length -1; i >0 ; i--){
				array[i] = array[i-1];
			}
			array[0] = t;
		}
	}
	//O(N^2)
	public static void arrayCircularShift2(int[] array,int K){
		K %= array.length;
		while(K-- > 0){
			int t = array[array.length-1];
			for(int i = array.length -1; i >0 ; i--){
				array[i] = array[i-1];
			}
			array[0] = t;
		}
	}
	//O(N)
	public static void arrayCircularShift3(int[] array,int K){
		K %= array.length;
		if(K == 0){
			return;
		}
		reverse(array,0,array.length - K -1);
		reverse(array,array.length-K,array.length-1);
		reverse(array,0,array.length-1);
	}
	public static void reverse(int[] array,int start,int end){
		int tmp;
		if(start > end){
			tmp = end;
			end = start;
			start = tmp;
		}
		while(start < end){
			tmp = array[start];
			array[start] = array[end];
			array[end] = tmp;
			start++;
			end--;
		}
	}
}
