package algorithm.leetcode.algorithm;
/*
 * easy
 * 141. Linked List Cycle 
 *  Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space? 
 */
public class NO141_LinkedListCycle {
	//方法1：
	//用两个指针，一快一慢进行遍历，快指针移动速度是慢指针的两倍
	//判断快指针是否为null或者快慢指针是否会重合。
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode walker = head,runner = head;
        while(runner.next!=null && runner.next.next!=null){
            walker = walker.next;
            runner = runner.next.next;
            if(runner == walker){
                return true;
            }
        }
        return false;
    }
}
