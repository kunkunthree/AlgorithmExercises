package algorithm;
/*
 * 分苹果:
 * 		n 只奶牛坐在一排，每个奶牛拥有 ai 个苹果，现在你要在它们之间转移苹果，
 * 使得最后所有奶牛拥有的苹果数都相同，每一次，你只能从一只奶牛身上拿走恰好两个苹果到另一个奶牛上，
 * 问最少需要移动多少次可以平分苹果，如果方案不存在输出 -1。
输入描述:
		每个输入包含一个测试用例。每个测试用例的第一行包含一个整数 n（1 <= n <= 100），
		接下来的一行包含 n 个整数 ai（1 <= ai <= 100）。
输出描述:
		输出一行表示最少需要移动多少次可以平分苹果，如果方案不存在则输出 -1。
输入例子:
		4
		7 15 9 5
输出例子:
		3
 */
import java.util.*;
public class DivideApples {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[]	array = new int[n];
		int sum = 0,average = 0;
		int addTime = 0,subTime = 0,time = 0;
		for(int i = 0 ; i < n ; i++){
			array[i] = in.nextInt();
			sum+=array[i];
		}
		average = sum/n;
		for(int i = 0 ; i < n ; i++){
			if((array[i] -average) % 2 != 0){
				time = -1;
				break;
			}else if(array[i] > average){
				while(array[i] > average){
					addTime++;
					array[i]-=2;
				}
			}else if(array[i] < average){
				while(array[i] < average){
					subTime++;
					array[i]+=2;
				}
			}
		}
		if(time != -1){
			if(subTime == addTime){
				time = subTime;
			}else{
				time = -1;
			}
		}
		System.out.println(time);
	}
}
