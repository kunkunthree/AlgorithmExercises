package algorithm;

/*
 * 买苹果:
 * 	小易去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装(包装不可拆分)。 
 * 可是小易现在只想购买恰好n个苹果，小易想购买尽量少的袋数方便携带。
 * 如果不能购买恰好n个苹果，小易将不会购买。
输入描述:
		输入一个整数n，表示小易想购买n(1 ≤ n ≤ 100)个苹果
输出描述:
		输出一个整数表示最少需要购买的袋数，如果不能买恰好n个苹果则输出-1
输入例子:
		20
输出例子:
		3
		思路：
				这是一个线性规划问题，6X+8Y=N;求X+Y的最小值
				当Y>=X且Y取最可能大的值时，X+Y最小
 */
import java.io.*;
public class BuyingApple {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		System.out.println(getMinBagNum(n));
	}
	public static int getMinBagNum(int n){
		if(n % 2 != 0){
			return -1;
		}
		int num = 0;
		while(n >= 6){
			if(n % 8 == 0){
				num+= n/8;
				n = 0;
			}else{
				num++;
				n-=6;
			}
		}
		return n == 0 ? num : -1;
	}
}
