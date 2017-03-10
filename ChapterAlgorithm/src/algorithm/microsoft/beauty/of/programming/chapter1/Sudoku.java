package algorithm.microsoft.beauty.of.programming.chapter1;
/*
 * 数独生成：
 * 解数独：
 */
import java.util.*;
class Point{
	public int x,y;
	Queue<Integer> validValueQueue = null;
	public Point(int x,int y) {
		this.x  = x;
		this.y = y;
	}
}
public class Sudoku {
	public static final int SIZE = 9;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int[][] matrix = new int[size+1][size+1];
		Stack<Point> stackUnFilled = new Stack<Point>();
		Queue<Point> queue = new LinkedList<Point>();
		for(int i = 1 ; i <= size ; i++){
			for(int j = 1 ; j <= size ; j++){
				matrix[i][j] = in.nextInt();
				if(matrix[i][j] == 0){
					Point p = new Point(i, j);
					queue.offer(p);
				}
			}
		}
		System.out.println(queue.size()+ " ");
		while(!queue.isEmpty()){
			Point p = queue.poll();
			p.validValueQueue = getValidValueQueue(p.x, p.y, matrix);
			System.out.println(p.x + "  " + p.y + "  "+ p.validValueQueue.toString());
			stackUnFilled.push(p);
		}
		boolean result = solve(matrix, stackUnFilled);
		if(result == false){
			System.out.println("无法得到正确结果");
		}else{
			System.out.println("结果:");
			for(int i = 1 ; i <= size ; i++){
				for(int j = 1 ; j <= size ;j++){
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	public static boolean solve(int[][] matrix,Stack<Point> stackUnFilled){
		int count = 0;
		int total = stackUnFilled.size();
		Stack<Point> stackFilled = new Stack<Point>();
		Point p = stackUnFilled.pop();
//		p.validValueList = getValidValueQueue(p.x, p.y, matrix);
		while(stackFilled.size() < total){
			if(p.validValueQueue.size() == 0){
				stackUnFilled.push(p);
				if(stackFilled.size() == 0){
					return false;
				}
				matrix[p.x][p.y] = 0;
				p = stackFilled.pop();
				System.out.println("回溯到: [" + p.x +" , " + p.y + "]");
			}else{
//				System.out.println(p.x + "  " + p.y + "  "+ p.validValueQueue.toString());
				matrix[p.x][p.y] = p.validValueQueue.poll();
				System.out.println("填入：[" + p.x +" , " + p.y + "]: " + matrix[p.x][p.y]);
				stackFilled.push(p);
				if(!stackUnFilled.isEmpty()){
					p = stackUnFilled.pop();
					p.validValueQueue = getValidValueQueue(p.x, p.y, matrix);
				}
//				printMatrix(matrix);
			}
		}
		return true;
	}
	public static void printMatrix(int[][] matrix){
		System.out.println();
		for(int i = 1 ; i <= SIZE ; i++){
			for(int j = 1 ; j <= SIZE ;j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static Queue<Integer> getValidValueQueue(int x,int y ,int[][]array){
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int i = 1 ; i <= SIZE ; i++){
			queue.add((Integer)i);
		}
		for(int i = 1 ; i <= SIZE ; i++){
			if(array[x][i] != 0){
//				System.out.println("array["+x+"]["+i+"]="+array[x][i]);
				queue.remove((Integer)array[x][i]);
			}
			if(array[i][y] != 0){
//				System.out.println("array["+i+"]["+y+"]="+array[i][y]);
				queue.remove((Integer)array[i][y]);
			}
//			if(x == y && array[i][i] != 0){
////				System.out.println("array["+i+"]["+i+"]="+array[i][i]);
//				queue.remove((Integer)array[i][i]);
//			}
		}
		return queue;
	}
//	boolean GenerateValidMatrix(){
//		//prepare for the search
//		while(true){
//			int x = 0;
//		}
//	}
}
