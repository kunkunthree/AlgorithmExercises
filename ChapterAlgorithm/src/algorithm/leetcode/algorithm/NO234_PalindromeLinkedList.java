package algorithm.leetcode.algorithm;
/*
 * easy
 *  234. Palindrome Linked List 
 *  Given a singly linked list, determine if it is a palindrome.
Follow up:
Could you do it in O(n) time and O(1) space?
 */
public class NO234_PalindromeLinkedList {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(0);
		head.next.next = new ListNode(0);
		System.out.println(isPalindrome(head));
	}
    public static  boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode slow = head,fast = head,pre = head,rest = head.next;
        //逆序存储前半段的链表，然后和后半段做对比
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = rest;
            rest = rest.next;
            slow.next = pre;
            pre = slow;
        }
        //如果长度为奇数，则前半段多一个，去除多余的一个
        if(fast.next == null){
            pre = pre.next;
        }
        while(pre != null && rest != null){
            if(pre.val != rest.val){
                return false;
            }
            pre = pre.next;
            rest = rest.next;
        }
        return true;
    }
}
