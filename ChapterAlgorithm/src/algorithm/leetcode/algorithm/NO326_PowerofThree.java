package algorithm.leetcode.algorithm;
/*
 * easy
 * 326. Power of Three
 *  Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion? 

similar problems:
231. Power of Two 
342. Power of Four 
 */
public class NO326_PowerofThree {
	public static void main(String[] args) {
		System.out.println(100.5%1);
	}
	//1162261467是int范围内最大的3的幂
    public static boolean isPowerOfThree(int n) {
        return n <= 0 ? false : 1162261467%n == 0;
    }
    //其他方法
    public static  boolean isPowerOfThree2(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}
