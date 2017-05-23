package algorithm.leetcode.algorithm;
/*
 * medium
 * Given a singly linked list, return a random node's value from the linked list. 
 * Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? 
Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

 */
import java.util.*;
public class NO382_LinkedListRandomNode {
	//方法1：
	//水塘抽样，Reservoir Sampling
	//我们可以得出结论，在取第n个数据的时候，我们生成一个0到1的随机数p，如果p小于1/n，保留第n个数。
	//大于1/n，继续保留前面的数。直到数据流结束，返回此数。
	//证明：NO382_LinkedListRandomNode.jpg
	public class Solution {
	    private Random rand;
	    private ListNode head;
	    /** @param head The linked list's head.
	        Note that the head is guaranteed to be not null, so it contains at least one node. */
	    public Solution(ListNode head) {
	        rand = new Random();
	        this.head = head;
	    }
	    /** Returns a random node's value. */
	    public int getRandom() {
	        int count = 1,result = head.val;
	        ListNode tmp = head;
	        while(tmp != null){
	            int a = rand.nextInt(count);
	            if(a == 0){
	                result = tmp.val;
	            }
	            count++;
	            tmp = tmp.next;
	        }
	        return result;
	    }
	}
}
