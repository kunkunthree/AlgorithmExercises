package algorithm.leetcode.algorithm;
/*
 * easy
 *  476. Number Complement 
 *  Given a positive integer, output its complement number.
 *   The complement strategy is to flip the bits of its binary representation.

Note:
    The given integer is guaranteed to fit within the range of a 32-bit signed integer.
    You could assume no leading zero bit in the integer’s binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. 
So you need to output 2.

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. 
So you need to output 0.

 */
public class NO476_NumberComplement {
    public int findComplement(int num) {
        int result = 0,bit = 1;
        while(num != 0){
            if((num&1) == 0){
                result|=bit;
            }
            num>>>=1;
            bit<<=1;
        }
        return result;
    }
    //得到最右边包含最左边1为止的掩码
   public static int findComplement2(int num) {
        int mask = num;
        //假设其中1的位置为i，i从右边最低位第一位开始往右数
        mask |= mask >> 1;	//置i-1位为1
        mask |= mask >> 2;	//置i-2,i-3位为1
        mask |= mask >> 4;	//置i-4,i-5,i-6,i-7,位为1
        mask |= mask >> 8;	//置i-8,i-9,i-10,i-11,i-12,i-13,i-14,i-15位为1
        mask |= mask >> 16;	//置i-16,i-17,i-18,i-19,i-20,i-21,i-22,i-23,i-24,i-25,i-26,i-27,i-28,i-29,i-30,i-31位为1
        //每一步的前一步已经置为1的位会与0做或位运算
        return num ^ mask;
    }
}
