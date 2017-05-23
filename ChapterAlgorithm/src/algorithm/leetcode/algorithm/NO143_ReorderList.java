package algorithm.leetcode.algorithm;
/*
 * medium
 * 143. Reorder List
 *  Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}. 
 */
public class NO143_ReorderList {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(1,2);
		reorderList(head);
	}
	//方法1：
	//迭代，分三步骤
	//第一步，获得后半部分链表
	//第二步，将后半部分链表逆转
	//第三步，将后半部分链表插入前半部分链表
	public static void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        //第一步
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //第二步
        ListNode reverse = new ListNode(0);
        ListNode nextNode = slow.next,tmpNode;
        slow.next = null;
        while(nextNode != null){
            tmpNode = nextNode.next;
            nextNode.next = reverse.next;
            reverse.next = nextNode;
            nextNode = tmpNode;
        }
        //第三步
        slow = head;
        fast = reverse.next;
        ListNode nextSlow,nextFast;
        while(slow != null && fast != null){
            nextSlow = slow.next;
            slow.next = fast;
            slow = nextSlow;
            nextFast = fast.next;
            fast.next = slow;
            fast = nextFast;
        }
        System.out.println(head);
    }
}
