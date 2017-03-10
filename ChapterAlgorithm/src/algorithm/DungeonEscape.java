package algorithm;
/*
 * 地牢逃脱:
 * 		给定一个 n 行 m 列的地牢，其中 '.' 表示可以通行的位置，'X' 表示不可通行的障碍，
 * 牛牛从 (x0 , y0 ) 位置出发，遍历这个地牢，和一般的游戏所不同的是，他每一步只能按照一些指定的步长遍历地牢，
 * 要求每一步都不可以超过地牢的边界，也不能到达障碍上。地牢的出口可能在任意某个可以通行的位置上。
 * 牛牛想知道最坏情况下，他需要多少步才可以离开这个地牢。
输入描述:
		每个输入包含 1 个测试用例。
		每个测试用例的第一行包含两个整数 n 和 m（1 <= n, m <= 50），表示地牢的长和宽。接下来的 n 行，
		每行 m 个字符，描述地牢，地牢将至少包含两个 '.'。接下来的一行，包含两个整数 x0, y0，
		表示牛牛的出发位置（0 <= x0 < n, 0 <= y0 < m，左上角的坐标为 （0, 0），出发位置一定是 '.'）。
		之后的一行包含一个整数 k（0 < k <= 50）表示牛牛合法的步长数，接下来的 k 行，
		每行两个整数 dx, dy 表示每次可选择移动的行和列步长（-50 <= dx, dy <= 50）
输出描述:
		输出一行一个数字表示最坏情况下需要多少次移动可以离开地牢，如果永远无法离开，输出 -1。
		以下测试用例中，牛牛可以上下左右移动，在所有可通行的位置.上，地牢出口如果被设置在右下角，
		牛牛想离开需要移动的次数最多，为3次。
输入例子:
3 3
...
...
...
0 1
4
1 0
0 1
-1 0
0 -1
输出例子:
		3
 */
import java.util.*;
class Point{
	int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class DungeonEscape {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.valueOf(in.next());
		int m = Integer.valueOf(in.next());
		int [][] array = new int[n][m];
		for(int i = 0 ; i < n ; i++){
			String str = in.next();
			for(int j = 0 ; j < m ; j++){
				if(str.charAt(j) == 'X'){
					array[i][j] = -1;
				}else if(str.charAt(j) == '.'){
					array[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		int x = Integer.valueOf(in.next());
		int y = Integer.valueOf(in.next());
		array[x][y] = 0;
		int steps = Integer.valueOf(in.next());
		int[][] dir = new int[steps][2];
		for(int i = 0 ; i < steps ; i++){
			dir[i][0] = Integer.valueOf(in.next());			//dx
			dir[i][1] = Integer.valueOf(in.next());			//dy
		}
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(x,y));
		//方法一：广度优先搜索
		while(!queue.isEmpty()){
			Point p = queue.poll();
			for(int i = 0 ; i < dir.length ;i++){
//				System.out.println("y+dir["+i+"][1]:"+(y+dir[i][1]) + " ,     x+dir["+i+"][1]:" + (x+dir[i][0]));
				if(p.y+dir[i][1] >= 0  && p.x+dir[i][0] >= 0 && p.y+dir[i][1] <= array[p.x].length-1  && p.x+dir[i][0] <= array.length-1){
//					System.out.println("array[x+dir["+i+"][0]][y+dir["+i+"][1]]: "+ array[x+dir[i][0]][y+dir[i][1]]);
					if(array[p.x+dir[i][0]][p.y+dir[i][1]] > -1 && array[p.x+dir[i][0]][p.y+dir[i][1]] > array[p.x][p.y]+1){
						array[p.x+dir[i][0]][p.y+dir[i][1]] = array[p.x][p.y]+1;
						queue.offer(new Point(p.x+dir[i][0],p.y+dir[i][1]));
					}
				}
			}
		}
//		go(array,x,y,dir);
//		System.out.println(dir.length + "    " + dir[0].length);
//		System.out.println(array.length + "    " + array[0].length);
//		System.out.println(Arrays.deepToString(dir));
//		System.out.println(Arrays.deepToString(array));
		System.out.println(getBiggestSteps(array));
	}
	//方法二：深度优先搜索
	public static void go(int[][] array,int x,int y,int dir[][]){
		for(int i = 0 ; i < dir.length ;i++){
//			System.out.println("y+dir["+i+"][1]:"+(y+dir[i][1]) + " ,     x+dir["+i+"][1]:" + (x+dir[i][0]));
			if(y+dir[i][1] >= 0  && x+dir[i][0] >= 0 && y+dir[i][1] <= array[x].length-1  && x+dir[i][0] <= array.length-1){
//				System.out.println("array[x+dir["+i+"][0]][y+dir["+i+"][1]]: "+ array[x+dir[i][0]][y+dir[i][1]]);
				if(array[x+dir[i][0]][y+dir[i][1]] > -1 && array[x+dir[i][0]][y+dir[i][1]] > array[x][y]+1){
					array[x+dir[i][0]][y+dir[i][1]] = array[x][y]+1;
					go(array,x+dir[i][0],y+dir[i][1],dir);
				}
			}
		}
	}
	public static int getBiggestSteps(int[][] array){
		int result = 0;
		for(int i = 0 ; i < array.length ; i++){
			for(int j = 0 ; j < array[i].length ; j++){
				if(array[i][j] == Integer.MAX_VALUE){
					return -1;
				}
				if(result < array[i][j]){
					result = array[i][j];
				}
			}
		}
		return result;
	}
}
