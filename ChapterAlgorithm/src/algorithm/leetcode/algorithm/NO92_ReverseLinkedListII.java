package algorithm.leetcode.algorithm;
/*
 * medium
 * 92. Reverse Linked List II
 *  Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list. 
 */
public class NO92_ReverseLinkedListII {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(3,5);
		int m = 1,n = 2;
		System.out.println(reverseBetween(head, m, n));
	}
	public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n){
            return head;
        }
        ListNode newHead = new ListNode(0),pre,tail,then;
        newHead.next = head;
        pre = newHead;
        int i = 0;
        while(i < m-1 && pre != null){
            pre = pre.next;
            i++;
        }
        //此时pre为逆转开始前的一个节点，pre的下一节点指向逆转子链的头节点
        tail = pre.next;   //逆转的尾节点
        then = tail.next;   //下一个开始逆转的节点
        while(i < n-1 && then != null){
            tail.next = then.next;     //逆转子链的尾节点指向当前需要逆转的节点的下一节点
            then.next = pre.next;       //当前需要逆转的节点指向当前已经逆转子链的头节点
            pre.next = then;            //更新新的逆转子链的头节点为当前逆转的节点
            then = tail.next;           //指向下一个需要逆转的节点
            i++;
        }
        return newHead.next;
    }
}
