package algorithm.leetcode.algorithm;
/*
 * hard
 * 660. Remove 9 
 * Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...
So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...
Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:
Input: 9
Output: 10
Hint: n will not exceed 9 x 10^8. 
 */
public class NO660_Remove9 {
	public static void main(String[] args) {
		int n = 17;
		System.out.println(newInteger3(n));
	}
	//方法1：
	//用9进制表示n，再将9进制表示当做10进制表示
	public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n,9));
    }
	//方法2：
	//同1，但是不用Integer自带的函数
	public int newInteger2(int n) {
		int ans = 0;
		int base = 1;
			
		while (n > 0){
			ans += n % 9 * base;
			n /= 9;
			base *= 10;
		}
		return ans;
	}
	//假设是move 7
	public static  int newInteger3(int n) {
		StringBuilder sb = new StringBuilder(Integer.toString(n, 9));
		for(int i = 0 ; i < sb.length() ; i++){
			if(sb.charAt(i) >= '7'){
				sb.setCharAt(i, (char)(sb.charAt(i)+1));
			}
		}
        return Integer.parseInt(sb.toString());
    }
	//假设是move 7
	public static int newInteger4(int n) {
		int ans = 0;
		int base = 1;
		int remainder; 
			
		while (n > 0){
			remainder = n % 9;
			if(remainder >= 7){
				remainder++;
			}
			ans += remainder * base;
			n /= 9;
			base *= 10;
		}
		return ans;
	}
}
