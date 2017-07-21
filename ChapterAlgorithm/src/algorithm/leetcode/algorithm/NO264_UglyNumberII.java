package algorithm.leetcode.algorithm;
/*
 * medium
 * 264. Ugly Number II
 *  Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690. 

similar problems:
23. Merge k Sorted Lists 
204. Count Primes 
263. Ugly Number 
279. Perfect Squares 
313. Super Ugly Number 
 */
public class NO264_UglyNumberII {
	//方法1：
	//由于ugly number都是由2,3,5构成的，那么ugly number其实就是因子含有2,3,5的数的交集构成的
	//通过比较当前最小的含有最多2，含有最多3，含有最多5的数，这三个最小的数就是当前位置的ugly number
	//那么只需要通过三个指针不断移动，指向分别最小的含有最多2,,3,5的数，一个指针指向当前最大的ugly number
	//k[1] = min( k[0]x2, k[0]x3, k[0]x5)
	//k[2] = min( k[1]x2, k[0]x3, k[0]x5)
	//k[3] = min( k[0]x2, k[1]x3, k[0]x5)
	//k[4] = min( k[0]x2, k[0]x3, k[1]x5)
	public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0,index3 = 0,index5 = 0;
        int factor2 = 2,factor3 = 3,factor5 = 5,min;
        for(int i = 1 ; i < n ; i++){
            min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min){
                factor2 = 2*ugly[++index2];
            }
            if(factor3 == min){
                factor3 = 3*ugly[++index3];
            }
            if(factor5 == min){
                factor5 = 5*ugly[++index5];
            }
        }
        return ugly[n-1];
    }
}
