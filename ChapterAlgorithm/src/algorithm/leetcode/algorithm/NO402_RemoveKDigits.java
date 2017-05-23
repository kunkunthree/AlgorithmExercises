package algorithm.leetcode.algorithm;
/*
 * medium
 * 402. Remove K Digits 
 * Given a non-negative integer num represented as a string, remove k digits from the number 
 * so that the new number is the smallest possible.

Note:

    The length of num is less than 10002 and will be ≥ k.
    The given num does not contain any leading zero.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

 */
public class NO402_RemoveKDigits {
	public static void main(String[] args) {
		String num = "1234567890";
		int k = 9;
		System.out.println(removeKdigits(num, k));
		StringBuilder sb = new StringBuilder();
	}
	//方法1：
	//只要当前得到的字符串末尾数字大于当前所读数字，把末尾数字去除，直到比当前所读数字小为止，
	//这样数值大小一定变小
	//最后清除头部0，如果最后k不为0，则删除尾部k个数字
	public static String removeKdigits(String num, int k) {
		if(num == null || k >= num.length()){
            return "0";
        }
        StringBuilder result = new StringBuilder();
        char cur,pre = 0;
        int n = num.length();
        for(int i = 0 ; i < n ; i++){
            cur = num.charAt(i);
            while(result.length() > 0 && pre > cur && k > 0){
                k--;
                result.deleteCharAt(result.length()-1);
                if(result.length() > 0){
                    pre = result.charAt(result.length()-1);
                }
            }
            result.append(cur);
            pre = cur;
        }
        while(result.length() > 0 && result.charAt(0) == '0'){
            result.deleteCharAt(0);
        }
        return result.length() == 0 ? "0" : result.substring(0,result.length()-k);
    }
}
