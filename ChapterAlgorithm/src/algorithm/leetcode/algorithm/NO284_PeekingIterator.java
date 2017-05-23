package algorithm.leetcode.algorithm;
/*
 * medium
 * 284. Peeking Iterator
 * Given an Iterator class interface with methods: next() and hasNext(), 
 * design and implement a PeekingIterator that support the peek() operation -- 
 * it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
import java.util.*;
public class NO284_PeekingIterator {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Iterator<Integer> iterator =  list.iterator();
		if(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		System.out.println(list);
	}
	//方法1：
	//用List存储起来
	class PeekingIterator implements Iterator<Integer> {
	    List<Integer> list;
		public PeekingIterator(Iterator<Integer> iterator) {
		    // initialize any member here.
		    list = new ArrayList<>();
		    while(iterator.hasNext()){
		        list.add(iterator.next());
		    }
		}
		@Override
		public void remove() {
			list.remove(0);
		}
	    // Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
	        return list.get(0);
		}

		// hasNext() and next() should behave the same as in the Iterator interface.
		// Override them if needed.
		@Override
		public Integer next() {
		    return list.remove(0);
		}

		@Override
		public boolean hasNext() {
		    return list.size() > 0;
		}
	}
	//方法2：
	class PeekingIterator2 implements Iterator<Integer> {
	    Integer next = null;
	    Iterator<Integer> it;
		public PeekingIterator2(Iterator<Integer> iterator) {
		    // initialize any member here.
		    it = iterator;
		    if(iterator.hasNext()){
		        next = it.next();
		    }
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			next = it.next();
			it.remove();
		}
	    // Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
	        return next;
		}

		// hasNext() and next() should behave the same as in the Iterator interface.
		// Override them if needed.
		@Override
		public Integer next() {
		    Integer res = next;
		    if(it.hasNext()){
		        next = it.next();
		    }else{
		        next = null;
		    }
		    return res;
		}

		@Override
		public boolean hasNext() {
		    return next != null;
		}
	}
}
