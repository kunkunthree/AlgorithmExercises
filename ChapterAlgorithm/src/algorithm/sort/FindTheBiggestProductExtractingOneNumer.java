package algorithm.sort;
/*
 * 2.13 子数组的最大乘积:
 * 给定一个长度为N的数组，只许用乘法，不许用除法，计算任意（N-1）个数的组合中乘积最大的一个组，
 * 并写出算法的时间复杂度。
 */
import java.util.*;
public class FindTheBiggestProductExtractingOneNumer {
	public static void main(String[] args) {
		Random rand = new Random();
		int n = rand.nextInt(10) + 10;
		int[] array = new int[n];
		for(int i = 0 ; i < array.length ; i++){
			int x = rand.nextInt(3)-1;
			while(x == 0){
				x = rand.nextInt(3)-1;
			}
			array[i] = rand.nextInt(50) * x;
		}
		System.out.println(Arrays.toString(array));
		System.out.println("去除一个数字后最大乘积是:" + getTheBiggestProductExtractingOneNumer(array));
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
	}
	//时间复杂度 O(N)
	public static Long getTheBiggestProductExtractingOneNumer(int[] array){
		long result = 1;
		int negativeCount = 0;
		int zeroCount = 0;
		int zeroIndex = -1;
		int positiveCount = 0;
		int minPositiveNum = Integer.MAX_VALUE;
		int minPositiveIndex = -1;
		int maxNegativeIndex = -1;
		int maxNegativeNum = Integer.MIN_VALUE;
		for(int i = 0 ; i < array.length ; i++){
			if(array[i] > 0){
				positiveCount++;
				if(array[i] < minPositiveNum){
					minPositiveNum = array[i];
					minPositiveIndex = i;
				}
			}else if(array[i] < 0){
				negativeCount++;
				if(array[i] > maxNegativeNum){
					maxNegativeNum = array[i];
					maxNegativeIndex = i;
				}
			}else{
				zeroCount++;
				if(zeroCount == 1){
					zeroIndex = i;
				}
			}
		}
		System.out.println("负数个数：" + negativeCount + " , 0的个数：" + zeroCount + " , 正数个数：" + positiveCount);
		int index = -1;	//出去的数字的位置
		if(zeroCount ==1){
			if((negativeCount & 1) == 1){
				return 0L;
			}
			index = zeroIndex;
		}else if(zeroCount == 0){
			if((negativeCount & 1) == 1){
				index = maxNegativeIndex;
			}else{
				index = minPositiveIndex;
			}
		}else{	//zeroCount > 0
			return 0L;
		}
		for(int i = 0 ; i < array.length ; i++){
			if(i != index){
				result*=array[i];
			}else{
				System.out.println("去除的数字是：" +  array[i]);
			}
		}
		return result;
	}
}
