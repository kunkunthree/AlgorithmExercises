package algorithm.netease.spring2017;
/*
 * 9.
 * [编程题] 涂棋盘:
 * 		小易有一块n*n的棋盘，棋盘的每一个格子都为黑色或者白色，小易现在要用他喜欢的红色去涂画棋盘。
 * 小易会找出棋盘中某一列中拥有相同颜色的最大的区域去涂画，帮助小易算算他会涂画多少个棋格。
输入描述:
		输入数据包括n+1行：
		第一行为一个整数n(1 ≤ n ≤ 50),即棋盘的大小
		接下来的n行每行一个字符串表示第i行棋盘的颜色，'W'表示白色，'B'表示黑色
输出描述:
		输出小易会涂画的区域大小
输入例子:
3
BWW
BBB
BWB
输出例子:
3
 */
import java.util.*;
public class SmearingChessBoard {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean[][] matrix = new boolean[n][n];
		char[] array;
		for(int i = 0 ; i < n ; i++){
			array = in.next().toCharArray();
			for(int j = 0 ; j < n ; j++){
				if(array[j] == 'B'){
					matrix[i][j] = true;
				}else{
					matrix[i][j] = false;
				}
			}
		}
//		System.out.println(Arrays.deepToString(matrix));
		System.out.println(getNumber(matrix, n));
	}
	public static int getNumber(boolean[][] matrix,int n){
		Point p;
		int x,y;
		int max = 0;
		int tmpSum = 0;
		boolean isBlack;
		for(int i = 0 ; i < n ; i++){
			if(tmpSum > max){
				max = tmpSum;
			}
			tmpSum = 1;
			isBlack = matrix[i][0];
			for(int j = 1 ; j < n ; j++){
				if(matrix[i][j] == isBlack){
					tmpSum++;
				}else{
					if(tmpSum > max){
						max = tmpSum;
					}
					tmpSum = 1;
					isBlack = matrix[i][j];
				}
			}
		}
		for(int i = 0 ; i < n ; i++){
			if(tmpSum > max){
				max = tmpSum;
			}
			tmpSum = 1;
			isBlack = matrix[0][i];
			for(int j = 1 ; j < n ; j++){
				if(matrix[j][i] == isBlack){
					tmpSum++;
				}else{
					if(tmpSum > max){
						max = tmpSum;
					}
					tmpSum = 1;
					isBlack = matrix[j][i];
				}
			}
		}
		return max;
	}
}
