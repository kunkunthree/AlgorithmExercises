package algorithm;

import java.util.ArrayDeque;

/*
 * hard
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

 For example:
 Given n = 13,
 Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13. 
 */
public class NO233_NumberOfDigitOne {
	// 方法1：
	// O(logn)time
	// The idea is to calculate occurrence of 1 on every digit. There are 3
	// scenarios, for example
	// if n = xyzdabc
	// and we are considering the occurrence of one on thousand, it should be:
	// (1) xyz * 1000						 	if d == 0
	// (2) xyz * 1000 + abc + 1 		if d == 1
	// (3) xyz * 1000 + 1000 			if d > 1
	// iterate through all digits and sum them all will give the final answer
	public int countDigitOne(int n) {
		int prefix = n, base = 1, result = 0, digit = 0;
		do {
			digit = prefix % 10;
			prefix /= 10;
			result += prefix * base;
			if (digit == 1) {
				result += (n % base) + 1;
			} else if (digit > 1) {
				result += base;
			}
			base *= 10;
		} while (prefix > 0);
		return result;
	}
	//方法2：
	//同方法1
	public int countDigitOne2(int n) {
        if(n<=0) return 0;
        int front = n, back = 1;
        int res = 0;
        int i = String.valueOf(n).length()-1;
        while(front > 0) {
            front/=10;
            int d = String.valueOf(n).charAt(i)-'0';
            res+=front*back;
            if(d==1) res+=n%back+1; 
            else if(d>1) res+=back;
            back = back*10;
            i--;
        }
        return res;
    }
}
