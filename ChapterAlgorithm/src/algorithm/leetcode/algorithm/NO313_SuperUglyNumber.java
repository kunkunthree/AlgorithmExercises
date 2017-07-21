package algorithm.leetcode.algorithm;
/*
 * medium
 * 313. Super Ugly Number
 *  Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence 
of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
(4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer. 

similar problems:
264. Ugly Number II 
 */
import java.util.*;
public class NO313_SuperUglyNumber {
	//方法1：
	//例子：加入primes = {2,3,5}
	//由于ugly number都是由2,3,5构成的，那么ugly number其实就是因子含有2,3,5的数的交集构成的
	//通过比较当前最小的含有最多2，含有最多3，含有最多5的数，这三个最小的数就是当前位置的ugly number
	//那么只需要通过三个指针不断移动，指向分别最小的含有最多2,,3,5的数，一个指针指向当前最大的ugly number
	//k[1] = min( k[0]x2, k[0]x3, k[0]x5)
	//k[2] = min( k[1]x2, k[0]x3, k[0]x5)
	//k[3] = min( k[0]x2, k[1]x3, k[0]x5)
	//k[4] = min( k[0]x2, k[0]x3, k[1]x5)
	public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        int[] index = new int[k];
        int[] factor = new int[k];
        for(int i = 0 ; i < k ; i++){
            factor[i] = primes[i];
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int min;
        for(int i = 1 ; i < n ; i++){
            min = Integer.MAX_VALUE;
            for(int j = 0 ; j < k ; j++){
                min = Math.min(min,factor[j]);
            }
            dp[i] = min;
            for(int j = 0 ; j < k ; j++){
                if(min == factor[j]){
                    factor[j] = primes[j] * dp[++index[j]];
                }
            }
        }
        return dp[n-1];
    }
	//方法2：
	//利用PriorityQueue得到当前最小的ugly number
	public int nthSuperUglyNumberHeap(int n, int[] primes) {
	    int[] ugly = new int[n];

	    PriorityQueue<Num> pq = new PriorityQueue<>();
	    for (int i = 0; i < primes.length; i++) pq.add(new Num(primes[i], 1, primes[i]));
	    ugly[0] = 1;

	    for (int i = 1; i < n; i++) {
	        ugly[i] = pq.peek().val;
	        while (pq.peek().val == ugly[i]) {
	            Num nxt = pq.poll();
	            pq.add(new Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p));
	        }
	    }

	    return ugly[n - 1];
	}

	private class Num implements Comparable<Num> {
	    int val;
	    int idx;
	    int p;

	    public Num(int val, int idx, int p) {
	        this.val = val;
	        this.idx = idx;
	        this.p = p;
	    }

	    @Override
	    public int compareTo(Num that) {
	        return this.val - that.val;
	    }
	}
}
