package algorithm.microsoft.beauty.of.programming.chapter1;
/*
 * 电梯调度：
 * 问题描述：
	     所有的员工均在1楼进电梯的时候，选择所要到达的楼层。
	     然后计算出停靠的楼层i，当到达楼层i的时候，电梯停止。
	     所有人走出电梯，步行到所在的楼层中。
	     求所有人爬的楼层数目和的最小值。 
	     
*
*测试用例：
30 6
2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 4 4 4 4 4 4 5 5 5 5  5 6 6 6 6
31 6
2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 4 4 4 4 4 4 5 5 5 5  5 6 6 6 6 6
32 6
2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 4 4 4 4 4 4 5 5 5 5  5 6 6 6 6 6 6 
33 6
2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 4 4 4 4 4 4 5 5 5 5  5 6 6 6 6 6 6 6
 */
import java.util.*;
public class ElevatorDispatch {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int top = in.nextInt();
		int[] floor = new int[n+1];
		for(int i = 0 ; i < n ; i++){
			floor[in.nextInt()]++;
		}
		f1(floor);
		f2(floor);
	}
	//方法1：遍历所有层数
	public static void f1(int[] floor){
		int minSum = Integer.MAX_VALUE;
		int minFloor = 0;
		for(int i = 1 ; i < floor.length ; i++){
			int tmpSum = 0;
			for(int j = 1;j < floor.length ; j++){
				tmpSum+= floor[j]*( j - i > 0 ? j-i : i-j );
			}
			if(tmpSum < minSum){
				minSum = tmpSum;
				minFloor = i;
			}
		}
		System.out.println("所有人爬的楼层数目: " + minSum);
		System.out.println("最终到达的楼层: " + minFloor);
	}
	public static void f2(int[] floor){
		int minSum = 0;
		int tmpSum1 = 0 , tmpSum2 = 0;
		int minFloor = 0;
		int minFloor1 = 0;
		int minFloor2 = 0;
		int floorSum=0;
		int peopleSum = 0;
		for(int i = 1 ; i< floor.length ; i++){
			peopleSum+=floor[i];
			floorSum+=floor[i]*i;
		}
		minFloor1 = (int)Math.ceil((float)floorSum/(float)peopleSum);
		minFloor2 =  (int)Math.floor((float)floorSum/(float)peopleSum);
		for(int i = 0 ; i < floor.length ;i++){
			tmpSum1+=floor[i]*( minFloor1 - i > 0 ? minFloor1-i : i-minFloor1 );
			tmpSum2+=floor[i]*( minFloor2 - i > 0 ? minFloor2-i : i-minFloor2 );
		}
		if(tmpSum1 < tmpSum2){
			minSum = tmpSum1;
			minFloor = minFloor1;
		}else{
			minSum = tmpSum2;
			minFloor = minFloor2;
		}
		System.out.println("所有人爬的楼层数目: " + minSum);
		System.out.println("最终到达的楼层: " + minFloor);
	}
}
