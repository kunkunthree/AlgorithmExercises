package algorithm.leetcode.algorithm;
/*
 * medium
 * 50. Pow(x, n)
 * Implement pow(x, n). 
 */
import java.util.*;
public class NO50_Pow_x_n {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double x = in.nextDouble();
		int n = in.nextInt();
		System.out.println(myPow(x, n));
	}
	//方法1：
	//迭代法，快速求幂
    public static double myPow(double x, int n) {
        if(x == 0){
            return 0;
        }
        double result = 1,factor = x;
        boolean p = true;		//指数的正负性
        if(n < 0){
            n = -(n+1);		//特殊处理，以免溢出
            result *= x;
            p = false;
        }
        while(n > 0){
            if((n&1) == 1){
                result *= factor;
            }
            factor*=factor;
            n>>=1;
        }
        return p == true ? result : 1/result;		//如果指数为负，求其倒数
    }
    //方法2：
    //方法1的递归形式
    double myPow2(double x, int n) {
        if(n<0) return 1/x * myPow2(1/x, -(n+1));
        if(n==0) return 1;
        return n%2 == 0 ? myPow2(x*x, n/2) :  x*myPow2(x*x, n/2);
    }
    
}
