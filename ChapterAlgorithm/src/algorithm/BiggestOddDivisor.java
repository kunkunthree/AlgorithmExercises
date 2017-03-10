package algorithm;
/*
 *  最大的奇约数:
 *  		小易是一个数论爱好者，并且对于一个数的奇数约数十分感兴趣。
 *  一天小易遇到这样一个问题： 定义函数f(x)为x最大的奇数约数，x为正整数。 例如:f(44) = 11.
	现在给出一个N，需要求出 f(1) + f(2) + f(3).......f(N)
	例如： N = 7
	f(1) + f(2) + f(3) + f(4) + f(5) + f(6) + f(7) = 1 + 1 + 3 + 1 + 5 + 3 + 7 = 21
		小易计算这个问题遇到了困难，需要你来设计一个算法帮助他。
输入描述:
		输入一个整数N (1 ≤ N ≤ 1000000000)
输出描述:
		输出一个整数，即为f(1) + f(2) + f(3).......f(N)
输入例子:
		7
输出例子:
		21
 */
/*
 * 奇数的最大约数就是本身。问题就是求所有f(i), i为偶数的和 因为要求的是最大奇约数，所以f(2k) = f(k)，所以f(2) + f(4)
 * + ... + f(2k) = f(1) + f(2) + ... + f(k);
 * 
 * sum(n) = sum (n / 2) + 1 + 3 + ... + n - 1 = sum (n/2) + n*n/4（n 为偶数） 
 *        
 *        = sum (n - 1) + n （n为奇数）
 */
import java.io.*;
public class BiggestOddDivisor {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		System.out.println(getBiggestOddDivisorSum(num));
//		for(long i = 1 ; i < 100 ; i++){
//			System.out.println(i + " " + getBiggestOddDivisorSum(i));
//		}
	}
	public static long getBiggestOddDivisorSum(long num){
		long sum = 0;
		for(long i = 1 ; i <= num ; i++){
			long tmp = f(i);
			System.out.print(tmp + " + ");
			sum+=tmp;
		}
		System.out.println();
		return sum;
	}
	public static long f(long num){
		while(num %2 == 0){
			num = num>>1;
		}
		return num;
	}
    private static long solve(long n) {
        long result = 0;
        while (n > 0) {
            if ((n & 1) == 0) {
    			System.out.print(((n * n) >> 2) + " + ");
                result += (n * n) >> 2;		//sum(n) = sum(n/2)+n*n/4
                n = n >> 1;							//sum(n/2)
            }else {
    			System.out.print(n + " + ");
                result += n;
                --n;
            }
        }
        System.out.println();
        return result;
    }
}
