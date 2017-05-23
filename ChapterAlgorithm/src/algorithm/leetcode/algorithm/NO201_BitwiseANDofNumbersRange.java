package algorithm.leetcode.algorithm;
/*
 * medium
 * 201. Bitwise AND of Numbers Range
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, 
 * return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4. 
 */
public class NO201_BitwiseANDofNumbersRange {
	public static void main(String[] args) {
		int m = 1;
		int n = 3;
		System.out.println(rangeBitwiseAnd(m, n));
	}
	//方法1：
	//[m,n]范围内所有数字的交集的结果是m和n从最高位向最低位方向唯一一段相等的位
	//如m = 12,n = 13时，m 二进制 1100,n 二进制 1111, 从最高位向最低位方向唯一一段相等的位为1100
	public static int rangeBitwiseAnd(int m, int n) {
		if(m == n){
            return m;
        }
        int bit = n;
        int tmp = bit-1;
        while((bit&tmp) != 0){
            bit = bit&tmp;
            tmp = bit-1;
        }
        int result = 0;
        while((n&bit) == (m&bit)){
            result = result | (m&bit);
            bit>>=1;
        }
        return result;
    }
	//方法2:
	//将m和n右移，记录移动次数，直到m=n，把m再往左移原来移动的次数
	public int rangeBitwiseAnd2(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }
	//方法3：
	//记录低位不相等的最高位数x，最终和的结果为n&(1<<x)
	public int rangeBitwiseAnd3(int m, int n) {
	    int r=Integer.MAX_VALUE;
	    while((m&r)!=(n&r))  r=r<<1;
	    return n&r;
	}
}
