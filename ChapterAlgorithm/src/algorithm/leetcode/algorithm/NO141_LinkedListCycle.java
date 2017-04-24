package algorithm.leetcode.algorithm;
/*
 * easy
 * 141. Linked List Cycle 
 *  Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space? 
 */
public class NO141_LinkedListCycle {
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
