package algorithm.leetcode.algorithm;
/*
 * medium
 * 341. Flatten Nested List Iterator
 * Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6]. 
 */
import java.util.*;
public class NO341_FlattenNestedListIterator {
	public static void main(String[] args) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
	}
	//方法1：
	//用一个stack把每一个列表逆序进栈，判断栈顶是否能得到一个Integer
	public class NestedIterator implements Iterator<Integer> {
	    Stack<NestedInteger> stack;
	    public NestedIterator(List<NestedInteger> nestedList) {
	        stack = new Stack<NestedInteger>();
	        for(int i = nestedList.size()-1 ; i >= 0 ; i--){
	            stack.push(nestedList.get(i));
	        }
	    }
	    @Override
	    public void remove() {
	    	// TODO Auto-generated method stub
	    	
	    }
	    @Override
	    public Integer next() {
	        return stack.pop().getInteger();
	    }

	    @Override
	    public boolean hasNext() {
	        while(!stack.isEmpty()){
	            if(stack.peek().isInteger()){
	                return true;
	            }
	            else{
	                List<NestedInteger> nestedList = stack.pop().getList();
	                for(int i = nestedList.size()-1 ; i >= 0 ; i--){
	                    stack.push(nestedList.get(i));
	                }
	            }
	        }
	        return false;
	    }
	}
}
