package algorithm.leetcode.algorithm;
/*
 * easy
 * 342. Power of Four
 *  Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion? 
 */
public class NO342_PowerofFour {
	public static void main(String[] args) {
		int x = 1;
		while(x*2 > x && x*3 > x && x*4>x){
			x=x*4;
		}
		System.out.println(x);
	}
	//1向左移动2的倍数位得到4的幂
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
    }
    public boolean isPowerOfFour2(int num) {
        return (Math.log10(num) / Math.log10(4)) % 1 == 0;
    }
}
