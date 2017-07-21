package algorithm.leetcode.algorithm;
/*
 * hard
 * 32. Longest Valid Parentheses 
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) 
 * parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 

similar problems:
20. Valid Parentheses 
 */
import java.util.*;
public class NO32_LongestValidParentheses {
	public static void main(String[] args) {
		String s = "";
		System.out.println(longestValidParentheses2(s));
	}
	//方法1：
	//dp，O(n)time
	//If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.
	//Else if s[i] is ')'
	//		If s[i-1] is '(', longest[i] = longest[i-2] + 2
    //		Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]
	public static int longestValidParentheses(String s) {
        if(s == null || s.length() <= 1){
            return 0;
        }
        int n = s.length(),max = 0;
        int[] dp = new int[n];
        for(int i = 0 ; i < n ; i++){
            char c = s.charAt(i);
            if(c == '('){
                dp[i] = 0;
            }else if(c == ')'){
                if(i-1 >= 0 && s.charAt(i-1) == '('){
                     dp[i] = i-2 >= 0 ? dp[i-2]+2 : 2 ;
                }else if(i-1 >= 0 && s.charAt(i-1) == ')'){
                    int pre = i - dp[i-1] - 1;
                    if(pre >= 0 && s.charAt(pre) == '('){
                        dp[i] = dp[i-1] + 2 + (pre-1 >= 0 ? dp[pre-1] : 0);
                    }
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }
	//方法2：
	//使用stack，stack顶部保存最新的'('的下标，left变量记录当出现')'多于'('时，最后一个')'的位置
	public static int longestValidParentheses2(String s) {
		if(s == null || s.length() <= 1){
            return 0;
        }
        int n = s.length(),max = 0,left = -1;
        char c;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ;  i < n ; i++){
            c = s.charAt(i);
            if(c == '('){
                stack.push(i);
            }else{
                if(stack.isEmpty()){
                    left = i;
                }else{
                    stack.pop();
                    if(stack.isEmpty()){
                        max = Math.max(max,i-left);
                    }else{
                        max = Math.max(max,i-stack.peek());
                    }
                }
            }
        }
        return max;
    }
}
