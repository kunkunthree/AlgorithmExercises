package algorithm.sort;
/*
 * 单项链表的快速排序：
 * 		根据普通快排的思路,选择1个点为中心点,保证中心点左边比中心点小,中心点右边比中心点大即可.

单链表的实现为：

1.使第一个节点为中心点.

2.创建2个指针(p,q),p指向头结点,q指向p的下一个节点.

3.q开始遍历,如果发现q的值比中心点的值小,则此时p=p->next,并且执行当前p的值和q的值交换,q遍历到链表尾即可.

4.把头结点的值和p的值执行交换.此时p节点为中心点,并且完成1轮快排

5.使用递归的方法即可完成排序
 */
import algorithm.leetcode.algorithm.ListNode;
public class QuickSortSingleLinkedList {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(3,2,6,2,8,4,7,0,53,42,42,1,6,3,2,6,21);
		System.out.println(head);
		quickSortSLL(head, null);
		System.out.println(head);
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
