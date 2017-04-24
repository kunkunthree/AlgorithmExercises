package algorithm.leetcode.algorithm;
/*
 * easy
 * 203. Remove Linked List Elements
 * Remove all elements from a linked list of integers that have value val.
Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5 
 */
public class NO203_RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode node = newHead;
        while(node != null){
            if(node.next != null){
                if(node.next.val == val){
                    node.next = node.next.next;
                }else{
                    node = node.next;
                }
            }else{
                node = node.next;
            }
        }
        return newHead.next;
    }
    //3行代码，利用递归
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
}
}
