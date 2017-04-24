package algorithm.leetcode.algorithm;
/*
 * easy
 * 21. Merge Two Sorted Lists 
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class NO21_MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode min = head;
        ListNode node1 = l1,node2 = l2;
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                min.next = node1;
                min = node1;
                node1 = node1.next;
            }else{
                min.next = node2;
                min = node2;
                node2 = node2.next;
            }
        }
        if(node1 != null){
            min.next = node1;
        }else{
            min.next = node2;
        }
        return head.next;
    }
}
