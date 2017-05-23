package algorithm.leetcode.algorithm;
/*
 * medium
 * 372. Super Pow
 *  Your task is to calculate ab mod 1337 where a is a positive integer 
 *  and b is an extremely large positive integer given in the form of an array.

Example1:
a = 2
b = [3]
Result: 8

Example2:
a = 2
b = [1,0]
Result: 1024

 */
import java.util.*;
public class NO372_SuperPow {
	public static void main(String[] args) {
		int a = 2;
		int[] b = new int[]{3};
		System.out.println(superPow2(a, b));
	}
	//方法1：
	//递归：
	//a^1234567 % k = (a^1234560 % k) * (a^7 % k) % k = (a^123456 % k)^10 % k * (a^7 % k) % k
	//f(a,1234567) = f(a, 1234560) * f(a, 7) % k = f(f(a, 123456),10) * f(a,7)%k;
	private int base = 1337;
    public int superPow(int a, int[] b) {
        return superPow(a,b,b.length-1);
    }
    public int superPow(int a, int[] b,int i){
        if(i<0){
            return 1;
        }
        int last_digit = b[i];
        return pow(superPow(a, b,i-1), 10) * pow(a, last_digit) % base;
    }
    public int pow(int e ,long n){
        e%=base;
        int result = 1;
        while(n > 0){
            if((n&1) == 1){
                result = (result * e)%base;
            }
            e = (e*e)%base;
            n>>=1;
        }
        return result;
    }
    //方法2：
    //循环映射
    private static int DIV = 1337;
    public static int superPow2(int a, int[] b) {
        if (a==0 || a==DIV || b==null || b.length == 0) return 0;
        if (a==1) return 1;
        List<Integer> list = getLoop(a);
        int length =  list.size();
        int index = modBy(length,b);
        index = index == 0 ? length : index;
        return list.get(index-1);
    }
    private static List<Integer> getLoop(int a){
        List<Integer> list = new ArrayList<>();
        a = a%DIV;
        int rem = a % DIV;
        list.add(rem);
        rem = (rem * a) % DIV;
        while(list.get(0) != rem){
            list.add(rem);
            rem = (rem * a) % DIV;
        }
        return list;
    }
    public static int modBy(int a ,int[] b){
        int result = 0;
        for(int i = 0 ; i < b.length ; i++){
            result = (result * 10 + b[i])%a;
        }
        return result;
    }
}
