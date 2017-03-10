package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 计算1的个数:
 * 问题描述:
 *        给定一个十进制整数N,求出从1到N的所有整数中出现”1”的个数。 
 *例如：N=2，1,2出现了1个“1”。
 * N=12，1,2,3,4,5,6,7,8,9,10,11,12。出现了5个“1”。
 * 
 */
import java.util.*;
public class CountOfOne {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
//		System.out.println("Count of One : " + getCountOfOne1(n));
		System.out.println(getCountOfOne2(n));
	}
	public static int getCountOfOne1(int n){
		int count = 0;
		for(int i = 1 ; i <= n ; i++){
			int tmp = i;
			while(tmp != 0){
				if(tmp%10 == 1){
					count++;
				}
				tmp/=10;
			}
		}
		return count;
	}
	public static int getCountOfOne2(int n){
		int count = 0;
		int factor = 1;
		int lowerNum = 0;
		int currentNum  =0 ;
		int higherNum = 0;
		while(n / factor != 0){
			lowerNum = n - (n / factor) * factor;
			currentNum = (n /factor) % 10;
			higherNum = n/factor/10;
			switch(currentNum){
				case 0:
					count+=higherNum * factor;
					break;
				case 1:
					count+=higherNum * factor + lowerNum+1;
					break;
				default:
					count+=(higherNum+1)*factor;
				break;
			}
			factor*=10;
		}
		return count;
	}
}
