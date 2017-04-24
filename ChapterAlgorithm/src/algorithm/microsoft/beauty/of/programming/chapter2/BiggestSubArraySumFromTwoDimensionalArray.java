package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.15	求数组的子数组之和的最大值(二维数组)：
 * 
 */
import java.util.*;
public class BiggestSubArraySumFromTwoDimensionalArray {
	public static void main(String[] args) {
		Random rand = new Random();
		int m = rand.nextInt(10)+5;
		int n = rand.nextInt(10) + 5;
//		Scanner in = new Scanner(System.in);
//		int m = in.nextInt();
//		int n = in.nextInt();
		System.out.println(m + " " + n);
		int[][] array = new int[m][n];
		for(int i = 1 ; i < m ; i++){
			for(int j = 1 ; j < n ; j++){
//				array[i][j] = in.nextInt();
				array[i][j] = rand.nextInt(100)-50;
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
//		System.out.println(Arrays.deepToString(array));
		System.out.println(getMaxSum1(array));
		System.out.println(getMaxSum2(array));
		System.out.println(getMaxSum3(array));
	}
	public static int max(int x,int y){
		return x > y ? x : y;
	}
	//暴力破解法1：枚举每一个矩形区域的和
	public static int getMaxSum1(int[][] array){
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		int x1=0,x2=0,y1=0,y2=0;
		for(int i_min = 1; i_min < array.length ; i_min++ ){
			for(int i_max = i_min ; i_max < array.length ; i_max++){
				sum = 0;
				for(int j_min = 1;j_min < array[i_min].length ; j_min++ ){
					for(int j_max = j_min ; j_max < array[i_min].length ; j_max++){
						int tmp = getSum(array, i_min, j_min, i_max, j_max);
						if(tmp > maxSum){
							x1 = i_min;
							x2 = i_max;
							y1 = j_min;
							y2 = j_max;
							maxSum = tmp;
						}
//						maxSum = max(maxSum,getSum(array, i_min, j_min, i_max, j_max));
					}
				}
			}
		}
		System.out.println("("+x1+","+y1+") , (" +  x2 + "," + y2 + ")");
		return maxSum;
	}
	//暴力破解法2：枚举每一个矩形区域的和
	//对中间结果进行预处理，省略重复计算
	public static int getMaxSum2(int[][] array){
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		int[][] PS = new int[array.length][array[0].length];
		int x1=0,x2=0,y1=0,y2=0;
		//设置边界值
		for(int i = 0 ; i < array.length ; i++){
			PS[i][0] = 0;
		}
		//设置边界值
		for(int j = 0 ; j < array[0].length ; j++){
			PS[0][j] = 0;
		}
		//预处理，计算部分和
		for(int i = 1 ; i < array.length ; i++){
			for(int j = 1 ; j < array[i].length ; j++){
				PS[i][j] = PS[i-1][j] + PS[i][j-1] - PS[i-1][j-1] + array[i][j];
			}
		}
		for(int i_min = 1; i_min < array.length ; i_min++ ){
			for(int i_max = i_min ; i_max < array.length ; i_max++){
				sum = 0;
				for(int j_min = 1;j_min < array[i_min].length ; j_min++ ){
					for(int j_max = j_min ; j_max < array[i_min].length ; j_max++){
//						maxSum = max(maxSum,PS[i_max][j_max]+PS[i_min][j_min] - PS[i_min][j_max] - PS[i_max][j_min]);
						int tmp = PS[i_max][j_max]+PS[i_min-1][j_min-1] - PS[i_min-1][j_max] - PS[i_max][j_min-1];
						if(tmp > maxSum){
							x1 = i_min;
							x2 = i_max;
							y1 = j_min;
							y2 = j_max;
							maxSum = tmp;
						}
					}
				}
			}
		}
		System.out.println("("+x1+","+y1+") , (" +  x2 + "," + y2 + ")");
		return maxSum;
	}
	//把二维问题简化为一维问题
	public static int getMaxSum3(int[][] array){
		int[][] PS = new int[array.length][array[0].length];
		int x1=0,x2=0,y1=0,y2=0;
		int maxSum = Integer.MIN_VALUE;
		//设置边界值
		for(int i = 0 ; i < array.length ; i++){
			PS[i][0] = 0;
		}
		//设置边界值
		for(int j = 0 ; j < array[0].length ; j++){
			PS[0][j] = 0;
		}
		//预处理，计算部分和
		for(int i = 1 ; i < array.length ; i++){
			for(int j = 1 ; j < array[i].length ; j++){
				PS[i][j] = PS[i-1][j] + PS[i][j-1] - PS[i-1][j-1] + array[i][j];
			}
		}
		for(int i_min = 1; i_min < array.length ; i_min++ ){
			for(int i_max = i_min ; i_max < array.length ; i_max++){
				int start = Integer.MIN_VALUE;
//				System.out.println(i_min + " " + i_max + " " + start);
				int tmp_x1=0,tmp_x2=0,tmp_y1=0,tmp_y2=0;
				int all = start;
				int j_min = 0;
				for(int j = 1;j < array[i_min].length ; j++ ){
					if(start < 0){
						j_min = j;
						start = 0;
					}
					int tmp = PS[i_max][j]+PS[i_min-1][j-1] - PS[i_min-1][j] - PS[i_max][j-1];
					start +=  tmp;
//					System.out.println(i_min + " " + i_max + " " + j_min + " " +j + " " + tmp + " " + start + "  " + all);
					if(start > all){
						all = start;
						tmp_x1 = i_min;
						tmp_x2 = i_max;
						tmp_y1 = j_min;
						tmp_y2 = j;
					}
				}
				if(all > maxSum){
					x1 = tmp_x1;
					x2 = tmp_x2;
					y1 = tmp_y1;
					y2 = tmp_y2;
					maxSum = all;
				}
			}
		}
		System.out.println("("+x1+","+y1+") , (" +  x2 + "," + y2 + ")");
		return maxSum;
	}
	public static int getSum(int[][] array,int x1,int y1,int x2,int y2){
		int sum = 0;
		for(int i = x1; i <= x2 ; i++){
			for(int j = y1 ; j <= y2 ; j++){
				sum+=array[i][j];
			}
		}
		return sum;
	}
}
