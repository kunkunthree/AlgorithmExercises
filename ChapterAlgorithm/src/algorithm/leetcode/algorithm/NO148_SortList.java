package algorithm.leetcode.algorithm;
/*
 * medium
 * 148. Sort List
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class NO148_SortList {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(4,3,2,1);
		System.out.println(sortList(head));
	}
	//方法1：
	//归并排序，不改变节点的值，改变节点的顺序
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
    //方法2：
    //快速排序：不改变节点的结构，改变节点的值
    public static ListNode sortList2(ListNode head) {
    	quickSortSLL(head,null);
    	return head;
    }
    public static void quickSortSLL(ListNode head,ListNode tail){
		if(head != tail){
			ListNode separator = getSeparator(head, tail);
			quickSortSLL(head, separator);
			quickSortSLL(separator.next, tail);
		}
	}
	private static ListNode getSeparator(ListNode head,ListNode tail){
		if(head == null){
			return head;
		}
		ListNode left = head;
		ListNode right = head.next;
		int key = left.val;
		while(right != null){
			if(right.val < key){
				left = left.next;
				swap(left,right);
			}
			right = right.next;
		}
		swap(left,head);
		return left;
	}
	private static void swap(ListNode node1 , ListNode node2){
		if(node1 != null && node2 != null){
			int tmp = node1.val;
			node1.val = node2.val;
			node2.val = tmp;
		}
	}
}
