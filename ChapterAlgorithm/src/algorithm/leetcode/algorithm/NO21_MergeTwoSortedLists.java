package algorithm.leetcode.algorithm;
/*
 * easy
 * 21. Merge Two Sorted Lists 
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 * 
 * similar problems：
 * 23. Merge k Sorted Lists 
 * 88. Merge Sorted Array
 * 148. Sort List 
 */
public class NO21_MergeTwoSortedLists {
	//方法1：
	//用两个指针分别指向两个链表，
	//值小的指针指向的节点的next指向值大的指针指向的节点，值小的指针指向原来节点的next
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
