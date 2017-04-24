package algorithm.leetcode.algorithm;

/*
 * medium
 * 8. String to Integer (atoi) 
 * Implement atoi to convert a string to an integer.

 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below 
 and ask yourself what are the possible input cases.

 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
 You are responsible to gather all the input requirements up front.

 Update (2015-02-10):
 The signature of the C++ function had been updated. If you still see your function signature
 accepts a const char * argument, please click the reload button to reset your code definition.

 spoilers alert... click to show requirements for atoi.
 
 
 
 
1.discards all leading whitespaces
2.sign of the number
3.overflow
4.invalid input

 */
public class NO8_StringtoInteger_atoi {
	public static void main(String[] args) {
		String str = "2147483648";
		System.out.println(myAtoi(str));
	}

	public static int myAtoi(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		int positive = 1, length = str.length(), prev;
		int result = 0;
		char[] s = str.toCharArray();
		int i = 0;
		while (i < length && s[i] == ' ') {
			i++;
		}
		if (i < length) {
			if (s[i] == '-') {
				positive = -1;
				i++;
			} 
		}
		while (i < length && s[i] >= '0' && s[i] <= '9') {
			prev = result;
			result = s[i] - '0' + result * 10;
			if (prev > result || (result - s[i] + '0') / 10 != prev) {
				if (positive == 1) {
					return Integer.MAX_VALUE;
				} else {
					return Integer.MIN_VALUE;
				}
			}
			i++;
		}
		return positive * result;
	}
}
