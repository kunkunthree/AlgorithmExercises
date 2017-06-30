package algorithm.leetcode.algorithm;
/*
 * hard
 * 224. Basic Calculator 
 * Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:

"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

Note: Do not use the eval built-in library function. 
 */
import java.util.*;
public class NO224_BasicCalculator {
	//方法1：
	//迭代，用stack
	public int calculate(String s) {
        int len = s.length(),sign = 1,result = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < len ; i++){
            if(Character.isDigit(s.charAt(i))){
                int sum = s.charAt(i) - '0';
                while(i+1 < len && Character.isDigit(s.charAt(i+1))){
                    sum = sum * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                result = result + sign * sum;
            }else if(s.charAt(i) == '+'){
                sign = 1;
            }else if(s.charAt(i) == '-'){
                sign = -1;
            }else if(s.charAt(i) == '('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if(s.charAt(i) == ')'){
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }
	//方法2：
	//递归，用index做全局变量
	public int calculate2(String s) {
		return cal(s.toCharArray());
	}
	int idx = 0;
	public int cal(char[] c) {
		int num = 0, sign = 1, res = 0;
		while (idx < c.length && c[idx] != ')') {
			if (Character.isDigit(c[idx]))
				num = num * 10 + c[idx] - '0';
			else if (c[idx] == '+' || c[idx] == '-') {
				res += sign * num;
				num = 0;
				sign = c[idx] == '+' ? 1 : -1;
			} else if (c[idx] == '(') {
				idx++;
				res += sign * cal(c);
			}
			idx++;
		}
		return res + sign * num;
	}
}
