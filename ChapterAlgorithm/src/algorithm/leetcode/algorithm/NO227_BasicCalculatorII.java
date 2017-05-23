package algorithm.leetcode.algorithm;
/*
 * medium
 * 227. Basic Calculator II
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:

"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5

Note: Do not use the eval built-in library function. 
 */
import java.util.*;
public class NO227_BasicCalculatorII {
	public static void main(String[] args) {
		String s = " 1 + 1";
		System.out.println(calculate(s));
	}
	//方法1：
	//把每一个加减号分割的表达式的值计算好，放进stack中，最后把所有值相加
	public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0,length = s.length();
        char sign = '+',c;
        for(int i = 0 ; i < length ; i++){
            c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                num = num * 10 + c - '0';
            }
            if((c < '0' || c > '9') && c != ' ' || i == length-1){
                if(sign == '-'){
                    stack.push(-num);
                }
                if(sign == '+'){
                    stack.push(num);
                }
                if(sign == '*'){
                    stack.push(stack.pop()*num);
                }
                if(sign == '/'){
                    stack.push(stack.pop()/num);
                }
                sign = c;
                num = 0;
            }
        }
        for(int i : stack){
            num+=i;
        }
        return num;
    }
	//方法2：
	//不用stack存储中间结果，O(n)time,O(1)space
	public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0,length = s.length(),rest = 0,pre = 0;
        char sign = '+',c;
        for(int i = 0 ; i < length ; i++){
            c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                num = num * 10 + c - '0';
            }
            if((c < '0' || c > '9') && c != ' ' || i == length-1){
                if(sign == '-'){
                    rest+=pre;
                    pre = -num;
                }
                if(sign == '+'){
                    rest+=pre;
                    pre = num;
                }
                if(sign == '*'){
                    pre = pre * num;
                }
                if(sign == '/'){
                    pre = pre / num;
                }
                sign = c;
                num = 0;
            }
        }
        rest+=pre;
        return rest;
    }
}
