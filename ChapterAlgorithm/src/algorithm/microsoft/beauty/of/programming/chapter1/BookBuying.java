package algorithm.microsoft.beauty.of.programming.chapter1;
/*
 * 买书问题：
 * 		《哈利波特》系列一共有五卷，每一卷售价均8欧元。同时买不同的卷（各一本）有折扣，具体如下表所示。
购买方案 	折扣
	2卷 			5%
	3卷 			10%
	4卷 			20%
	5卷 			25%
在一份订单中，根据购买的卷数及本数，可以有多种不同折扣规则。但一本书只能应用一个折扣规则。
设计一个算法，计算购书组合，使得所购买的一批书花费最少。
 */
import java.util.*;
public class BookBuying {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] array = new int[5];
		for(int i = 0 ; i < 5 ; i++){
			array[i] = in.nextInt();
		}
		double x = getMinPrice(array,0,1);
		System.out.println(x);
	}
	//方法1：动态规划
	public static double getMinPrice(int[] array,double lastResult,int group){
		int[] tmpArray = Arrays.copyOf(array, array.length);
		Arrays.sort(tmpArray);
		for(int i = 0 ; i < array.length ; i++){
			array[i] = tmpArray[array.length -1 - i];
		}
		System.out.println(Arrays.toString(array));
		int flag = -1;
		for(int i = 4 ; i >= 0 ; i--){
			if(array[i] > 0){
				flag = i;
				break;
			}
		}
		double[] result = new double[array.length];
		double min = Double.MAX_VALUE;
		if(flag == -1){
			return 0;
		}
		for(int i = 0 ; i <= flag ; i++){
			int[] newArray = getNewArray(array, i);
			System.out.println(Arrays.toString(array) + " 		" + Arrays.toString(newArray) + "  " + i + "   lastResult: " + lastResult);
			double add = 0;
			switch(i){
				case 0: 	
				case 1: 
				case 2: add = (i+1) * 8 * (1 - i * 0.05);
								break;
				case 3:
				case 4: add = (i+1) * 8 * (1 - (i+1) * 0.05);
								break;
			}
			result[i] = add + getMinPrice(newArray,add,group+1);
			System.out.println(Arrays.toString(array) + " 		" + Arrays.toString(newArray) + "   "  + i +"   " +  (group+1) + "  result:  " + result[i]);
			if(result[i] < min){
				min = result[i];
			}
		}
		return min;
	}
	public static int[]	getNewArray(int[] array,int flag){
		int [] newArray = new int[array.length];
		for(int i = 0 ; i < array.length ; i++){
			if(i <= flag) {
				newArray[i] = array[i]-1;
			}else{
				newArray[i] = array[i];
			}
		}
		return newArray;
	}
	/*
	public static class DescentComparator  implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 > o2){
				return -1;
			}else if( o1 < o2){
				return 1;
			}else{
				return 0;
			}
		}
	}
	*/
	//方法二：
//	public static double getMinPrice2(int[] array,int sum){
//		if(sum > 10){
//			
//		}
//	}
}
