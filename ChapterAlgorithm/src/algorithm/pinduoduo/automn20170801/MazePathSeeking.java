package algorithm.pinduoduo.automn20170801;
/*
 * [编程题] 迷宫寻路
时间限制：1秒
空间限制：131072K

假设一个探险家被困在了地底的迷宫之中，要从当前位置开始找到一条通往迷宫出口的路径。
迷宫可以用一个二维矩阵组成，有的部分是墙，有的部分是路。
迷宫之中有的路上还有门，每扇门都在迷宫的某个地方有与之匹配的钥匙，只有先拿到钥匙才能打开门。
请设计一个算法，帮助探险家找到脱困的最短路径。如前所述，迷宫是通过一个二维矩阵表示的，
每个元素的值的含义如下 0-墙，1-路，2-探险家的起始位置，3-迷宫的出口，大写字母-门，
小写字母-对应大写字母所代表的门的钥匙
输入描述:
迷宫的地图，用二维矩阵表示。第一行是表示矩阵的行数和列数M和N
后面的M行是矩阵的数据，每一行对应与矩阵的一行（中间没有空格）。M和N都不超过100, 门不超过10扇。

输出描述:
路径的长度，是一个整数

输入例子1:
5 5
02111
01a0A
01003
01001
01111

输出例子1:
7
 */
import java.util.*;
public class MazePathSeeking {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = Integer.valueOf(in.next());
		int n = Integer.valueOf(in.next());
		int[][] maze = new int[m][n];
		for(int i = 0 ; i < m ; i++){
			for(int j = 0 ; j < n ; j++){
				maze[i][j] = in.next().charAt(0);
			}
		}
		int x= -1,y = -1;
		for(int i = 0 ; i < m ; i++){
			for(int j = 0 ; j < n ; j++){
				
			}
		}
	}
}
