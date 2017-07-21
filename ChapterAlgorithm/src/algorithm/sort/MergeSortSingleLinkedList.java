package algorithm.sort;
/*
 * 对单项链表进行归并排序：
 *		利用两个指针，一快一慢，快指针遍历到尾部，此时慢指针得到中间节点
 */
import algorithm.leetcode.algorithm.ListNode;
public class MergeSortSingleLinkedList {
	public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode fast = head.next,slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        return merge(sortList(head),sortList(fast));
    }
    public static ListNode merge(ListNode list1,ListNode list2){
        ListNode head = new ListNode(0);
        ListNode node = head,tmpNode;
        head.next = list1;
        while(node.next != null && list2 != null){
            if(node.next.val <= list2.val){
                node = node.next;
            }else{
                tmpNode = list2.next;
                list2.next = node.next;
                node.next = list2;
                node = node.next;
                list2 = tmpNode;
            }
        }
        if(node.next == null){
            node.next = list2;
        }
        return head.next;
    }
}
