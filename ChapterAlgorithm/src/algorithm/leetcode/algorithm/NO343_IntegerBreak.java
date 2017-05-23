package algorithm.leetcode.algorithm;
/*
 * medium
 * 343. Integer Break
 *  Given a positive integer n, break it into the sum of at least two positive integers 
 *  and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58. 
 */
import java.math.*;
public class NO343_IntegerBreak {
	public static void main(String[] args) {
	}
	//方法1：
	//遍历所有最接近n/i因子的组合
	public int integerBreak(int n) {
        int max = 0;
        for(int i = 1 ; i < n ; i++){
            int x = n/i;
            int rest = n%i;
            if(rest == 0 || i == 1){
                max = Math.max(max,pow(i,x));
            }else{
                if(x == 1){
                    max = Math.max(max,pow(i,x)*(rest));
                }else{
                    max = Math.max(Math.max(max,pow(i,x-1)*(i+rest)),pow(i,x)*(rest));
                }
            }
        }
        return max;
    }
    public int pow(int e,int n){
        int result = 1;
        while(n > 0){
            if((n&1) == 1){
                result *= e;
            }
            e*=e;
            n>>=1;
        }
        return result;
    }
    //方法2：
    //f=x(N-x)，当x = N/2时，f有最大值，
    //但是x为整数，所以当N为偶数时，f最大值为(N/2)*(N-N/2) = (N^2)/4
    //当N为奇数时，f最大值为(N+1)/2 * (N-(N+1)/2) = (N+1)/2*(N-1)/2 = (N^2-1)/4
    //当N>= 4 时，(N^2)/4 >= N，即大于等于4的偶数可以分解为2个相等因子获得最大的积
    //当N>=5时，(N^2-1)/4 >= N，即大于等于5的奇数可以分解为2个相差1的因子获得最大的积
    //所以最大值的因子应该小于4，即1或2或3，否则可由以上两式分解得到更小的因子得到更大的积
    //因为当N = 6时，3*3 > 2*2*2，所以最好的情况时不要使用超过3个2
    //O(n)time
    public int integerBreak2(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int product = 1;
        while(n>4){
            product*=3;
            n-=3;
        }
        product*=n;
        return product;
    }
    //方法3：
    //O(logN)time
    public int integerBreak3(int n) {
        if(n == 2)
            return 1;
        else if(n == 3)
            return 2;
        else if(n%3 == 0)
            return (int)Math.pow(3, n/3);
        else if(n%3 == 1)
            return 2 * 2 * pow(3, (n - 4) / 3);
        else 
            return 2 * pow(3, n/3);
    }
}
