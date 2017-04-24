package algorithm.netease.game.internship2017;
/*
 * 2.
 * [编程题] 最大和:
 * 		在一个N*N的数组中寻找所有横，竖，左上到右下，右上到左下，
 * 四种方向的直线连续D个数字的和里面最大的值
输入描述:
		每个测试输入包含1个测试用例，第一行包括两个整数 N 和 D :
		3 <= N <= 100
		1 <= D <= N
		接下来有N行，每行N个数字d:
		0 <= d <= 100
输出描述:
		输出一个整数，表示找到的和的最大值
输入例子:
4 2
87 98 79 61
10 27 95 70
20 64 73 29
71 65 15 0
输出例子:
193
 */
import java.util.*;
class Point{
	int sumDown;
	int sumRight;
	int SumRightDown;
	int SumLeftDown;
	public Point() {
		sumDown = Integer.MIN_VALUE;
		sumRight = Integer.MIN_VALUE;
		SumRightDown = Integer.MIN_VALUE;
		SumLeftDown = Integer.MIN_VALUE;
	}
}
public class BiggestSum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n,d;
		int[][] matrix;
		while(in.hasNext()){
			n = in.nextInt();
			d = in.nextInt();
			matrix = new int[n][n];
			for(int i = 0 ; i < n ; i++){
				for(int j = 0 ; j < n ; j++){
					matrix[i][j] = in.nextInt();
				}
			}
			System.out.println(getBiggestSum(matrix, n, d));
		}
	}
	public static int getBiggestSum(int[][] matrix,int n,int d){
		if(n < d){
			return -1;
		}
		int tmpSum,tmpX,tmpY;
		int max = Integer.MIN_VALUE;
		Point[][] p= new Point[n][n];
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
				p[i][j] = new Point();
				//计算当前点为起点，向下长度为d的和
				if(i == 0){	//初始计算点
					tmpSum = 0;
					for(int k = 0 ; k < d ; k++){
						tmpSum+=matrix[i+k][j];
					}
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , sumDown: " + max);
					}
					p[i][j].sumDown = tmpSum;
				}else if(i-1>=0 && i <= n-d){	//能够从上一点计算的点
					tmpSum = p[i-1][j].sumDown - matrix[i-1][j] + matrix[i+d-1][j];
					p[i][j].sumDown = tmpSum;
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , sumDown: " + max);
					}
				}
				//计算当前点为起点，向右长度为d的和
				if(j == 0){	//初始计算点
					tmpSum = 0;
					for(int k = 0 ; k < d ; k++){
						tmpSum+=matrix[i][j+k];
					}
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , sumRight: " + max);
					}
					p[i][j].sumRight = tmpSum;
				}else if(j-1>=0 && j <= n-d){	//能够从上一点计算的点
					tmpSum = p[i][j-1].sumRight - matrix[i][j-1] + matrix[i][j+d-1];
					p[i][j].sumRight = tmpSum;
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , sumRight: " + max);
					}
				}
				//计算当前点为起点，向右斜下长度为d的和
				if((i == 0 && j <= n-d) || (j == 0 && i <= n-d)){	//初始计算点
					tmpSum = 0;
					for(int k = 0 ; k < d ; k++){
						tmpSum+=matrix[i+k][j+k];
					}
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , SumRightDown: " + max);
					}
					p[i][j].SumRightDown = tmpSum;
				}else if(i-1>=0 && j <= n-d && j-1>=0 && i <= n-d){	//能够从上一点计算的点
					tmpSum = p[i][j-1].SumRightDown - matrix[i-1][j-1] + matrix[i+d-1][j+d-1];
					p[i][j].SumRightDown = tmpSum;
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , SumRightDown: " + max);
					}
				}
				//计算当前点为起点，向左斜下长度为d的和
				if((i == 0 && j >= d-1) || (j == n-1 && i <= n-d)){	//初始计算点
					tmpSum = 0;
					for(int k = 0 ; k < d ; k++){
						tmpSum+=matrix[i+k][j-k];
					}
					if(tmpSum > max){
						max =tmpSum;
//						System.out.println(i + " " + j + " , SumLeftDown: " + max);
					}
					p[i][j].SumLeftDown = tmpSum;
				}else if(i-1>=0 && j >= d-1 && j+1<= n-1 && i <= n-d){	//能够从上一点计算的点
					tmpSum = p[i-1][j+1].SumLeftDown - matrix[i-1][j+1] + matrix[i+d-1][j-d+1];
					p[i][j].SumLeftDown = tmpSum;
					if(tmpSum > max){
						max = tmpSum;
//						System.out.println(i + " " + j + " , SumLeftDown: " + max);
					}
				}
			}
		}
		return max;
	}
}
