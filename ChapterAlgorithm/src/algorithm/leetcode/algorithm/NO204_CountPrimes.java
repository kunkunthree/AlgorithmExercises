package algorithm.leetcode.algorithm;
/*
 * easy
 * 204. Count Primes 
 * Description:
Count the number of prime numbers less than a non-negative number, n.

similar problems：
263. Ugly Number 
264. Ugly Number II 
279. Perfect Squares 
 */
import java.util.*;
public class NO204_CountPrimes {
	public static void main(String[] args) {
		System.out.println(countPrimes(499979));
	}
	//方法1：
	//用一个布尔数组notPrimes，true表示合数，false表示质数
	//i从2到n-1进行遍历，初始化质数个数count = 0
	//当notPrimes[i]为false时，说明是质数，count++
	//		遍历小于n的i的合数，设置为true
	//当notPrimes[i]为true时，i++
    public static  int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n];
        int count = 0;
        for(int i = 2 ; i < n ; i++){
            if(notPrimes[i] == false){
                count++;
                for(int j = 2; i*j < n ; j++){
                    notPrimes[i*j] = true;
                }
            }
        }
        return count;
    }
}
