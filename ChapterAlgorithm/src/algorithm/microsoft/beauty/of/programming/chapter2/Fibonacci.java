package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.9	斐波那契数列：
 * 
 */
import java.util.*;
public class Fibonacci {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(f2(n));
	}
	public static int f1(int n){
		if(n <= 0){
			return 0;
		}
		else if(n == 1){
			return 1;
		}
		else{
			return f1(n-1)+f1(n-2);
		}
	}
	public static int f2(int n){
		if(n <= 0 )return 0;
		if(n == 1)return 1;
		int x = 0;
		int y = 1;
		int result= 0;
		for(int i = 1; i < n ; i++){
			result=(x+y);
			x= y;
			y = result;
		}
		return result;
	}
}
