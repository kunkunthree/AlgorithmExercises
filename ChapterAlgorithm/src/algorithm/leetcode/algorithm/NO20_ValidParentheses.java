package algorithm.leetcode.algorithm;
/*
 * easy
 * 20. Valid Parentheses 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 
 * similar problems:
 * 22. Generate Parentheses 
 * 32. Longest Valid Parentheses 
 * 301. Remove Invalid Parentheses 
 */
import java.util.*;
public class NO20_ValidParentheses {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			System.out.println(isValid(in.next()));
		}
	}
    public static boolean isValid(String s) {
        if(s == null || s.length() == 0 || (s.length()&1)==1 ){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        int length = s.length();
        stack.push(s.charAt(0));
        int i = 1;
        while(i<length){
            char tmp = s.charAt(i);
            if(tmp == '(' || tmp == '{' || tmp == '['){
                stack.push(tmp);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                if(tmp == ')' && stack.peek() == '(' 
                || tmp == '}' && stack.peek() == '{' 
                || tmp == ']' && stack.peek() == '['){
                    stack.pop();
                }
            }
            i++;
        }
        return stack.isEmpty();
    }
    //更简单的写法
    public static boolean isValid2(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	for (char c : s.toCharArray()) {
    		if (c == '(')
    			stack.push(')');
    		else if (c == '{')
    			stack.push('}');
    		else if (c == '[')
    			stack.push(']');
    		else if (stack.isEmpty() || stack.pop() != c)
    			return false;
    	}
    	return stack.isEmpty();
    }
}
