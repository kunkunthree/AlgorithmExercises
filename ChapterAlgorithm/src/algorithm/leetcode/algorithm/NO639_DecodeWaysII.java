package algorithm.leetcode.algorithm;
/*
 * hard
 * 639. Decode Ways II 
 *  A message containing letters from A-Z is being encoded to numbers using the following mapping way:
'A' -> 1
'B' -> 2
...
'Z' -> 26

Beyond that, now the encoded string can also contain the character '*', 
which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', 
return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

Example 2:
Input: "1*"
Output: 9 + 9 = 18

Note:
    1.		The length of the input string will fit in range [1, 10^5].
    2.		The input string will only contain the character '*' and digits '0' - '9'.

similar problems:
91. Decode Ways 
 */
public class NO639_DecodeWaysII {
	//方法1：
	//O(n)time,O(n)space
	public int numDecodings(String s) {
        int len = s.length();
        int mod = 1000000007;
        long [] dp = new long[len + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0') return 0;
        if (s.charAt(0) == '*'){
            dp[1] = 9;
        }
        else{
            dp[1] = 1;
        }
        for (int i = 2; i<=len; i++) { // i-1 the index of current character in s. 
            if (s.charAt(i-1) == '0') {
                if (s.charAt(i-2) == '1' || s.charAt(i -2) == '2') {
                    dp[i] = dp[i-2];
                }
                else if(s.charAt(i-2) == '*'){
                    dp[i] = 2*dp[i-2];
                }
                else {
                    return 0;
                }
            }
            else if(s.charAt(i-1) >= '1' && s.charAt(i-1) <= '9') {
                dp[i] = dp[i-1];
                if (s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6') ){
                    dp[i] = (dp[i] + dp[i-2]) % mod;
                }
                else if (s.charAt(i-2) == '*') {
                    if (s.charAt (i-1) <= '6') {
                        dp[i] = (dp[i] + dp[i-2] * 2) % mod;
                    }
                    else {
                        dp[i] = (dp[i] + dp[i-2]) % mod;
                    }
                }
            }
            else { //s.charAt(i-1) == '*'
                dp[i] = (9*dp[i-1]) % mod;
                if ( s.charAt(i-2) == '1' ){ // 11 - 19
                    dp[i] = ( dp[i] + 9 * dp[i-2] ) % mod;
                }
                else if (s.charAt(i-2) == '2') { // 21 - 26
                    dp[i] = ( dp[i] + 6 * dp[i-2] ) % mod;
                }
                else if (s.charAt(i - 2) == '*') {
                    dp[i] = ( dp[i] + 15 * dp[i-2] ) % mod;
                }
            }
        }
        return (int)dp[len];
    }
	//方法2：
	//O(n)time,O(1)space
	private static final int M = 1000000007;
    public int numDecodings2(String s) {
        long first=1,second=s.charAt(0)=='*'?9:1;;
        
        for(int i=1;i<s.length();i++){
            long temp=second;
            if (s.charAt(i) == '*') {
	            second = 9 * second;
	            if (s.charAt(i - 1) == '1')
	                second = (second + 9 * first) % M;
	            else if (s.charAt(i - 1) == '2')
	                second = (second + 6 * first) % M;
	            else if (s.charAt(i - 1) == '*')
	                second = (second + 15 * first ) % M;
	        }else{
	            second = s.charAt(i)!='0'?second:0;
	            if (s.charAt(i - 1) == '1')
	                second = (second + first) % M;
	            else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
	                second = (second + first )% M;
	            else if (s.charAt(i - 1) == '*')
	                   second= (second + (s.charAt(i)<='6'?2:1) * first )% M;
            }
            first=temp;
    	} 
        return (int)second;
    }
}
