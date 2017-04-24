package algorithm.netease.spring2017;
/*
 * 5
 * [编程题] 魔力手环:
 * 		小易拥有一个拥有魔力的手环上面有n个数字(构成一个环),当这个魔力手环每次使用魔力的时候就会发生
 * 一种奇特的变化：每个数字会变成自己跟后面一个数字的和(最后一个数字的后面一个数字是第一个),
 * 一旦某个位置的数字大于等于100就马上对100取模(比如某个位置变为103,就会自动变为3).
 * 现在给出这个魔力手环的构成，请你计算出使用k次魔力之后魔力手环的状态。
输入描述:
		输入数据包括两行：
		第一行为两个整数n(2 ≤ n ≤ 50)和k(1 ≤ k ≤ 2000000000),以空格分隔
		第二行为魔力手环初始的n个数，以空格分隔。范围都在0至99.
输出描述:
		输出魔力手环使用k次之后的状态，以空格分隔，行末无空格。
输入例子:
3 2
1 2 3
输出例子:
8 9 7

解法：
		利用快速求幂和矩阵的乘法求矩阵快速幂
 */
import java.util.*;
public class MagicalBracelet {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] array = new int[n];
		for(int i = 0 ; i < n ; i++){
			array[i] = in.nextInt();
		}
		int[][] A = new int[n][n];
		for(int i = 0 ; i < n-1 ; i ++){
			A[i][i] = 1;
			A[i+1][i] = 1;
		}
		A[n-1][n-1] = 1;
		A[0][n-1] = 1;
		int[][] result = new int[1][n];
		result[0] = array;
		result = matrixMultAndMod(result, matrixPower(A, k, 100), 100);
		for(int i = 0 ; i < n-1 ; i++){
			System.out.print(result[0][i] + " ");
		}
		System.out.println(result[0][n-1]);
	}
	public static int[][] matrixMultAndMod(int[][] a,int[][] b,int mod){
		int rowCount = a.length;
		int columnCount = b[0].length;
		int[][] c = new int[rowCount][columnCount];
		int tmp;
		for(int i = 0 ; i < rowCount ; i++){
			for(int j = 0 ; j < columnCount ; j++){
				tmp = 0;
				for(int k = 0 ; k < a[i].length ; k++){
					tmp+=a[i][k]*b[k][j];
				}
				c[i][j] = tmp%mod;
			}
		}
		return c;
	}
	public static int[][] matrixPower(int[][] A,int n,int mod){
		int length = A.length;
		int[][] result = new int[length][length];
		for(int i = 0 ; i < length ; i++){
				result[i][i] = 1;
		}
		while(n > 1){
			if((n & 1) == 1){
				result = matrixMultAndMod(result, A, mod);
			}
			n>>=1;
			A = matrixMultAndMod(A, A,mod);
		}
		if(n == 1){
			result = matrixMultAndMod(result, A, mod);
		}
		return result;
	}
	public static void change(int[] array,int k){
		int length = array.length;
		for(int i = 0 ; i < k ; i++){
			for(int j = 0 ; j < length-1 ; j++){
				array[j] = (array[j] + array[j+1])%100;
			}
			array[length-1] = array[0];
		}
	}
}
