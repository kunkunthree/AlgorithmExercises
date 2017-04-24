package algorithm.leetcode.algorithm;
/*
 * easy
 * 204. Count Primes 
 * Description:
Count the number of prime numbers less than a non-negative number, n.
 */
import java.util.*;
public class NO204_CountPrimes {
	public static void main(String[] args) {
		System.out.println(countPrimes(499979));
	}
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
