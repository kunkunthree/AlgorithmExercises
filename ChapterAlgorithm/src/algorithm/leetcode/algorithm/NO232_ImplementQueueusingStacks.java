package algorithm.leetcode.algorithm;
/*
 * easy
 * 232. Implement Queue using Stacks
 *  Implement the following operations of a queue using stacks.
    push(x) -- Push element x to the back of queue.
    pop() -- Removes the element from in front of queue.
    peek() -- Get the front element.
    empty() -- Return whether the queue is empty.

Notes:

    You must use only standard operations of a stack -- which means
     only push to top, peek/pop from top, size, and is empty operations are valid.
    Depending on your language, stack may not be supported natively. 
    You may simulate a stack by using a list or deque (double-ended queue),
     as long as you use only standard operations of a stack.
    You may assume that all operations are valid (for example, 
    no pop or peek operations will be called on an empty queue).

 */
import java.util.*;
public class NO232_ImplementQueueusingStacks {
	class MyQueue {
	    Stack<Integer> stack1,stack2;
	    /** Initialize your data structure here. */
	    public MyQueue() {
	        stack1 = new Stack<Integer>();
	        stack2 = new Stack<Integer>();
	    }
	    
	    /** Push element x to the back of queue. */
	    public void push(int x) {
	        while(!stack1.isEmpty()){
	            stack2.push(stack1.pop());
	        }
	        stack1.push(x);
	        while(!stack2.isEmpty()){
	            stack1.push(stack2.pop());
	        }
	    }
	    
	    /** Removes the element from in front of queue and returns that element. */
	    public int pop() {
	        return stack1.pop();
	    }
	    
	    /** Get the front element. */
	    public int peek() {
	        return stack1.peek();
	    }
	    
	    /** Returns whether the queue is empty. */
	    public boolean empty() {
	        return stack1.isEmpty();
	    }
	}
	//效率更高的写法
	class MyQueue2 {

	    Stack<Integer> input = new Stack();
	    Stack<Integer> output = new Stack();
	    
	    public void push(int x) {
	        input.push(x);
	    }

	    public void pop() {
	        peek();
	        output.pop();
	    }

	    public int peek() {
	        if (output.empty())
	            while (!input.empty())
	                output.push(input.pop());
	        return output.peek();
	    }

	    public boolean empty() {
	        return input.empty() && output.empty();
	    }
	}
}
