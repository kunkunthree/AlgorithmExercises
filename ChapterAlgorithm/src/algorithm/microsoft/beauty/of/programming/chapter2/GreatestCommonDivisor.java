package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 *2.7 最大公约数：
 *1）辗转相除法
 *2）
 */
import java.util.*;
public class GreatestCommonDivisor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		System.out.println(x + " 和 " + y + "  的最大公约数是： " +getGCD3(x, y));
	}
	//辗转相除法	f(x,y) = f(y,x%y)
	public static int getGCD1(int x,int y){
		return y == 0 ? x : getGCD1(y, x%y);
	}
	//求差判定法	f(x,y) = f(x-y,y)
	public static int getGCD2(int x,int y){
		return y == 0 ? x : (x >= y ? getGCD2(x-y, y) : getGCD2(y, x));
	}
	//结合辗转相除法+求差判定法		
	//对x,y，如果y = k*y1 , x = k * x1,	则 f(x,y) = k *(x1,y1)
	//如果x = p * x1, p 为素数, y % p != 0, 则 f(x,y) =f(p * x1,y) = f(x1,y);	
	//2为最小的素数，p取2
	//x > y , f(x,y) = f(x - y , y)
	public static int getGCD3(int x,int y){
		if(y == 0){
			return x;
		}else if (x < y){
			return getGCD3(y, x);
		}else if((x&1) == 0 && (y&1) == 0){
			return getGCD3(x>>1, y>>1)<<1;
		}else if((x&1) == 0 && (y&1) == 1){
			return getGCD3(x>>1, y);
		}else if((x&1) == 1 && (y&1) == 0){
			return getGCD3(x, y>>1);
		}else{
			return getGCD3(x-y, y);
		}
	}
}
