package algorithm.leetcode.algorithm;
/*
 * medium
 * 147. Insertion Sort List
 * Sort a linked list using insertion sort.
 */
public class NO147_InsertionSortList {
	//方法1：
	//迭代，对链表进行插入排序
	public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode dummy = new ListNode(0),tmpNode;
        dummy.next = head;
        ListNode insertNode = head.next,nextNode;
        head.next = null;
        while(insertNode != null){
            tmpNode = dummy;
            nextNode = insertNode.next;
            while(tmpNode.next != null && tmpNode.next.val <= insertNode.val){
                tmpNode = tmpNode.next;
            }
            insertNode.next = tmpNode.next;
            tmpNode.next = insertNode;
            insertNode = nextNode;
        }
        return dummy.next;
    }
}
