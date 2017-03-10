package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.10 寻找数组中的最大值和最小值：
 * 1）扫描一遍数组，找出最大的数和最小的数
 * 2）按顺序将相邻两个数分在1组，比较同一组中奇数下标位数字和偶数下标位数组，
 * 大的放偶数下标位，小的放奇数下标位，然后在分别在偶数位下标中找最大值，在奇数位下标中找最小值
 * 3）利用分治的思想：在N个数中的最大值和最小值就是，求出前后N/2个数的最大值和最小值中最大值的
 * 较大值和最小值的较小值
 */
import java.util.*;
public class GetBiggestAndSmallestNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] array = new int[n];
		for(int i = 0 ; i < n ; i ++){
			array[i] = in.nextInt();
		}
		int[] result = getBASN3(array,0,array.length-1);
		System.out.println("max:"+result[0] + "  , min:"+result[1]);
	}
	public static int[] getBASN1(int[] array){
		if(array == null || array.length <0){
			return null;
		}
		int[] result = new int[2];	//result[0]为最大值，result[1]为最小值
		result[0] = array[0];
		result[1] = array[0];
		for(int i = 0 ; i < array.length ; i++){
			if(array[i] > result[0]){
				result[0] = array[i];
			}else if(array[i] < result[1]){
				result[1] = array[i];
			}
		}
		return result;
	}
	public static int[] getBASN2(int[] array){
		if(array == null || array.length <0){
			return null;
		}
		int[] result = new int[2];	//result[0]为最大值，result[1]为最小值
		result[0] = array[0];
		result[1] = array[0];
		for(int i = 0 ; i <= array.length/ 2 -1 ; i++){
//			System.out.println(2*i + " " + (2*i+1));
			if(array[2*i] > array[2*i+1]){
				if(array[2*i] > result[0]){
					result[0] = array[2*i];
				}else if(array[2*i+1] < result[1]){
					result[1] = array[2*i+1];
				}
			}else{
				if(array[2*i+1] > result[0]){
					result[0] = array[2*i+1];
				}else if(array[2*i] < result[1]){
					result[1] = array[2*i];
				}
			}
		}
		return result;
	}
	public static int[] getBASN3(int[] array,int start,int end){
		if(array == null || array.length <0){
			return null;
		}
		if(end-start <=1){
			if(array[start] > array[end]){
				return new int[]{array[start],array[end]};
			}
			else{
				return new int[]{array[end],array[start]};
			}
		}
		int[] left = getBASN3(array,start,(start+end)/2);
		int[] right = getBASN3(array, (start+end)/2 +1, end);
		int[] result = new int[2];	//result[0]为最大值，result[1]为最小值
		result[0] = left[0] > right[0] ? left[0] : right [0];
		result[1] = left[1] < right[1] ? left[1] : right [1];
		return result;
	}
	
}
