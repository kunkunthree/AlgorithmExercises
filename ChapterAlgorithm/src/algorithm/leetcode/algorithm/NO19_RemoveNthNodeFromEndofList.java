package algorithm.leetcode.algorithm;
/*
 * medium
 * 19. Remove Nth Node From End of List
 * Given a linked list, remove the nth node from the end of list and return its head.

For example,
   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass. 
 */
public class NO19_RemoveNthNodeFromEndofList {
	//方法1：
	//先用一个新的头节点newHead，指向原来的头节点head
	//用两个指针，一个快指针fast，一个慢指针slow，fast指向的节点在慢指针指向的节点之后的第n-1个节点
	//slow一开始指向newHead，fast指向slow之后的第n-1个节点
	//不断同时把fast和慢指针后移，直到fast.next为null为止，此时，删除满指针所指向的节点
	//特殊情况，n=1，把fast指向slow之后的第1个节点，且最后的删除操作，不进行值的复制
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode slow = newHead,fast = newHead;
        int tmp = n-1;
        if(n == 1){
            tmp++;
        }
        while(tmp > 0 && fast != null){
            fast = fast.next;
            tmp--;
        }
        while(fast!= null && fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        if(slow != null && slow.next != null){
            if(n > 1){
                slow.val = slow.next.val;
            }
            slow.next = slow.next.next;
        }
        return newHead.next;
    }
    
    //更简单写法：不需要特殊处理，只需要把fast指向slow之后的第n个节点即可
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;
        
        //Move fast in front so that the gap between slow and fast becomes n
        for(int i=1; i<=n+1; i++)   {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }
}
