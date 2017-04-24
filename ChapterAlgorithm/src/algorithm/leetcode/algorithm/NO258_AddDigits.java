package algorithm.leetcode.algorithm;
/*
 * easy
 * 258. Add Digits
 *  Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime? 
 */
public class NO258_AddDigits {
    public int addDigits(int num) {
        int tmp;
        while(num/10 != 0){
            tmp = 0;
            while(num > 0){
                tmp+=num%10;
                num/=10;
            }
            num = tmp;
        }
        return num;
    }
//    公式法求数根：
//    a的数根b = ( a - 1) % 9 + 1
    public int addDigits2(int num) {
    	return num == 0 ? 0 : (num-1)%9 +1;
    }
}
