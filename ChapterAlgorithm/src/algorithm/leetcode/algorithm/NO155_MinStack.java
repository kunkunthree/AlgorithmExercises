package algorithm.leetcode.algorithm;
/*
 * easy
 * 155. Min Stack
 *  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

 */
import java.util.*;
public class NO155_MinStack {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.pop();
		for(Integer i : stack){
			
		}
	}
}
class MinStack{
    public int min;
    public Stack<Integer> stack;
    /** initialize your data structure here. */
    public MinStack() {
        min = Integer.MAX_VALUE;
        stack = new Stack<Integer>();
    }
    public void push(int x) {
        if(x < min){
            min = x;
        }
        stack.push(x);
    }
    public void pop() {
        if(stack.pop() == min){
            min = Integer.MAX_VALUE;
            for(Integer i : stack){
                if(i < min){
                    min = i;
                }
            }
        }
    }
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}