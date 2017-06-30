package algorithm.leetcode.algorithm;
/*
 * medium
 * 625. Minimum Factorization 
 * Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48 

Output:

68

Example 2
Input:

15

Output:

35
 */
import java.util.*;
public class NO625_MinimumFactorization {
	//方法1：
	//求出最少的10以内的因子，如果不能由10以内的因子构成，则返回0
	//然后把这些因子从小到大排列，得到结果，如果结果超出int范围，则返回0，否则返回该结果
	public int smallestFactorization(int a) {
        if(a < 10){
            return a;
        }
        int radix = 9;
        List<Integer> list = new ArrayList<>();
        int num = a;
        while(num != 1 && radix > 1){
            while(num%radix == 0){
                list.add(0,radix);
                num/=radix;
            }
            radix--;
        }
        if(num != 1){
            return 0;
        }
        long result = 0;
        for(int i = 0 ; i < list.size() ; i++){
            result = result*10 + list.get(i);
            if(result > Integer.MAX_VALUE){
                return 0;
            }
        }
        return (int)result;
    }
}
