package algorithm.leetcode.algorithm;
/*
 * medium
 * 150. Evaluate Reverse Polish Notation
 *  Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 */
import java.util.*;
public class NO150_EvaluateReversePolishNotation {
	public static void main(String[] args) {
		String[] tokens = new String[]{"10","6","9","3","+","-11","/","17","+","5","+"};
		System.out.println(evalRPN(tokens));
	}
	//方法1：
	//利用栈stack
	public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        int x,y;
        for(int i = 0 ; i < tokens.length ; i++){
            if(isOperator(tokens[i])){
                y = stack.pop();
                x = stack.pop();
                stack.push(calculate(x,y,tokens[i]));
            }else{
                stack.push(getNumber(tokens[i]));
            }
        }
        return stack.pop();
    }
    public static boolean isOperator(String s){
        if(s != null && s.length() == 1){
            switch(s.charAt(0)){
                case '+':
                case '-':
                case '*':
                case '/':
                    return true;
            }
        }
        return false;
    }
    public static int getNumber(String s){
        int result = 0,pn = 1;
        int i = 0;
        if(s.charAt(i) == '+'){
            pn = 1;
            i++;
        }else if(s.charAt(i) == '-'){
            pn = -1;
            i++;
        }
        for(; i < s.length() ; i++){
            result = result * 10 + s.charAt(i) - '0';
        }
        return pn*result;
    }
    public static int calculate(int x,int y,String s){
        int result = 0;
        switch(s.charAt(0)){
            case '+': result = x + y;break;
            case '-': result = x - y;break;
            case '*': result = x * y;break;
            case '/': result = x / y;break;
        }
        return result;
    }
}
