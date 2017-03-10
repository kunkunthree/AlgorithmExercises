package algorithm;
/*
 * Fibonacci数列:
 * 		Fibonacci数列是这样定义的：
	F[0] = 0
	F[1] = 1
	for each i ≥ 2: F[i] = F[i-1] + F[i-2]
	因此，Fibonacci数列就形如：0, 1, 1, 2, 3, 5, 8, 13, ...，在Fibonacci数列中的数我们称为Fibonacci数。
	给你一个N，你想让其变为一个Fibonacci数，每一步你可以把当前数字X变为X-1或者X+1，
		现在给你一个数N求最少需要多少步可以变为Fibonacci数。
输入描述:
		输入为一个正整数N(1 ≤ N ≤ 1,000,000)
输出描述:
		输出一个最小的步数变为Fibonacci数"
输入例子:
		15
输出例子:
		2
 */
import java.util.*;
public class FibonacciSequence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		System.out.println(getSteps(x)+"");
	}
	public static int getSteps(int x){
		if(x == 0 || x == 1){
			return 0;
		}
		int sum1 = 0;
		int sum2 = 1;
		int tmp = 0;
		while(sum1 < x && sum2 < x){
			if(sum1 < sum2){
				sum1 += sum2;
			}else{
				sum2 += sum1;
			}
		}
		if(sum1 == x || sum2 == x){
			return 0;
		}
		if(sum1 < x){
			return x - sum1 < sum2 -x ? x -sum1 : sum2 - x;
		}else{
			return x - sum2 < sum1 -x ? x -sum2 : sum1 - x;
		}
	} 
}
