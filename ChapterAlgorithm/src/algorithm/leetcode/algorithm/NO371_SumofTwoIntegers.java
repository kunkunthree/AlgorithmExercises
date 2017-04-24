package algorithm.leetcode.algorithm;
/*
 * easy
 * 371. Sum of Two Integers
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3. 
 */
public class NO371_SumofTwoIntegers {
	//加法运算：
	//a&b获得需要进1的位
	//a^b得到相加后还没加进位的结果
	//把最开始的b当做要进位的位置，不断计算进位并赋给b，直到没有进位（即b为0）为止
    public int getSum(int a, int b) {
        if(a == 0){
            return b;
        }
        if(b == 0){
            return a;
        }
        while(b != 0){
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
    //上面的递归写法
    public int getSum2(int a, int b) {
        return b == 0 ? a : getSum2(a^b,(a&b)<<1);
    }
    //减法运算：
    //(~a) &b 得到相减后需要借位的位置，
    //a ^ b 得到的是相减后没有进行借位的结果
    //把b当做第一次需要进行借位的位置，不断计算进位并赋给b，直到没有借位（即b为0）为止
    public int getSubstract(int a, int b) {
        while(b != 0){
        	int borrow = (~a) & b;
        	a = a ^ b;
        	b = borrow << 1;
        }
        return a;
    }
    //递归写法
    public int getSubstract2(int a, int b) {
        return b == 0 ? a : getSubstract2(a^b, ((~a)&b)<<1);
    }
}
