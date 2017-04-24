package algorithm.leetcode.algorithm;
/*
 * medium
 * 82. Remove Duplicates from Sorted List II
 *  Given a sorted linked list, delete all nodes that have duplicate numbers, 
 *  leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3. 
 */
public class NO82_RemoveDuplicatesfromSortedListII {
	//方法1：
	//直接法，过滤重复节点
	public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(0),node = newHead,tmpNode;
        newHead.next = head;
        while(node.next != null){
            tmpNode = node.next;
            if(tmpNode.next != null && tmpNode.val == tmpNode.next.val){	//有重复节点出现的情况
                while(tmpNode.next != null && tmpNode.val == tmpNode.next.val){
                    tmpNode = tmpNode.next;
                }
                node.next = tmpNode.next;	//删除重复出现的节点
            }else{		//非重复节点
                node = node.next;	//遍历通过非重复节点
                if(node == null){
                    break;
                }
            }
        }
        return newHead.next;
    }
	
	//方法2：
	//递归
	public ListNode deleteDuplicates2(ListNode head) {
	    if (head == null) return null;
	    
	    if (head.next != null && head.val == head.next.val) {
	        while (head.next != null && head.val == head.next.val) {
	            head = head.next;
	        }
	        return deleteDuplicates2(head.next);
	    } else {
	        head.next = deleteDuplicates2(head.next);
	    }
	    return head;
	}
}
