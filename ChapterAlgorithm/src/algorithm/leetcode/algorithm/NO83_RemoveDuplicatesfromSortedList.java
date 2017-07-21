package algorithm.leetcode.algorithm;
/*
 * easy
 * 83. Remove Duplicates from Sorted List 
 *  Given a sorted linked list, delete all duplicates such that each element appear only once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3. 

similar problems:
82. Remove Duplicates from Sorted List II 
 */
public class NO83_RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = head.next,save = head;
        while(node != null){
            if(node.val == save.val){
                node = node.next;
            }else{
                save.next = node;
                save = node;
            }
        }
        save.next = node;
        return head;
    }
    //简洁写法，利用递归
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates2(head.next);
        return head.val == head.next.val ? head.next : head;
}
}
