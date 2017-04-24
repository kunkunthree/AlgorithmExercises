package algorithm.leetcode.algorithm;
/*
 * medium
 * 91. Decode Ways
 *  A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2. 
 */
public class NO91_DecodeWays {
	public static void main(String[] args) {
		String s= "11111";
		System.out.println(numDecodingsHelper(s, 0, 0));
	}
	//方法1：
	//递归，容易超时，重复计算
	public static int numDecodingsHelper(String s,int pre,int index){
        if(s == null || s.length() == 0){
            return 0;
        }
        int digit = s.charAt(index)-'0';
        int sum = digit + pre;
        if(index == s.length()-1){
            if(sum >= 1 && sum <= 26){
                return 1;
            }else{
                return 0;
            }
        }
        if(sum >= 1 && sum <= 26){  //是否符合字母
        	if(sum < 10){
        		return numDecodingsHelper(s,0,index+1)+numDecodingsHelper(s,digit*10,index+1);
        	}else{
        		return numDecodingsHelper(s,0,index+1);
        	}
        }
        return 0;
    }
	//方法2：
	//利用动态规划：
	//dp[i] += dp[i-1]（当前数字不为0），
	//dp[i] += dp[i-2]（当前2位数字组成的2位数大于等于10，小于等于26）
	public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int length = s.length();
        int[] dp = new int[length+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0'? 0 : 1;
        for(int i = 2 ; i <= length ; i++){
            int x1 = s.charAt(i-1) - '0';
            int x2 = s.charAt(i-2) - '0';
            if(x1 >=1 && x1 <= 9){
                dp[i]+=dp[i-1];
            }
            if(x1 + x2*10 >= 10 && x1 + x2*10 <= 26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[length];
    }
}
