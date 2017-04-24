package algorithm.leetcode.algorithm;
/*
 * medium
 * 24. Swap Nodes in Pairs
 *  Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. 
You may not modify the values in the list, only nodes itself can be changed. 
 */
public class NO24_SwapNodesinPairs {
	//方法1：迭代
    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode slow = newHead,fast = null;
        if(slow.next != null){
            fast = slow.next.next;
        }
        while(fast != null){
            slow.next.next = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = slow.next.next;
            if(slow != null && slow.next != null){
                fast = slow.next.next;
            }else{
                fast = null;
            }
        }
        return newHead.next;
    }
    //方法2：递归
    public ListNode swapPairs2(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs2(head.next.next);
        n.next = head;
        return n;
    }
}
