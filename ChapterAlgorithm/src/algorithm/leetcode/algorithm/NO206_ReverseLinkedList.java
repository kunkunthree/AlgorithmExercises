package algorithm.leetcode.algorithm;
/*
 * easy
 * 206. Reverse Linked List 
 * Reverse a singly linked list.
 * 
 * similar problems:
 * 92. Reverse Linked List II 
 * 234. Palindrome Linked List 
 */
public class NO206_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode newHead  = new ListNode(-1),node;
        while(head != null){
            node = head;
            head = head.next;
            node.next = newHead.next;
            newHead.next = node;
        }
        return newHead.next;
    }
    //递归实现
    public ListNode reverseList2(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode nextNode=head.next;
        ListNode newHead=reverseList(nextNode);
        nextNode.next=head;
        head.next=null;
        return newHead;
    }
}
