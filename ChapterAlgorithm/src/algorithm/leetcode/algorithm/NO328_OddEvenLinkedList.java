package algorithm.leetcode.algorithm;
/*
 * medium
 * 328. Odd Even Linked List
 * Given a singly linked list, group all odd nodes together followed by the even nodes. 
 * Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ... 
 */
public class NO328_OddEvenLinkedList {
	//方法1：
	//用两个头指针和两个尾指针分别指向奇数节点和偶数节点的头和尾，皆为非空
	public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode oddHead = head,evenHead = head.next;
        ListNode oddTail = head,evenTail = head.next;
        while(evenTail.next != null){
            oddTail.next = evenTail.next;
            oddTail = oddTail.next;
            if(oddTail.next == null){
                break;
            }
            evenTail.next = oddTail.next;
            evenTail = evenTail.next;
        }
        oddTail.next = evenHead;
        evenTail.next = null;
        return oddHead;
    }
	//方法2：
	//简化写法，只判断偶数尾指针或其next是否为空作为终止条件
	public ListNode oddEvenList2(ListNode head) {
	    if (head != null) {
	    
	        ListNode odd = head, even = head.next, evenHead = even; 
	    
	        while (even != null && even.next != null) {
	            odd.next = odd.next.next; 
	            even.next = even.next.next; 
	            odd = odd.next;
	            even = even.next;
	        }
	        odd.next = evenHead; 
	    }
	    return head;
	}
}
