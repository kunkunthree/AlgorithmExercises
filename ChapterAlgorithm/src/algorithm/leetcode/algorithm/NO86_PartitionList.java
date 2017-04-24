package algorithm.leetcode.algorithm;
/*
 * medium
 * Given a linked list and a value x, partition it such that all nodes less than x come 
 * before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5. 
 */
public class NO86_PartitionList {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(2,1);
		int x = 2;
		System.out.println(partition(head, x));
	}
	//方法1：
	//用4个指针来操作：
	//lessEnd：表示当前排在最后小于x的节点
	//notLessStart：表示当前第一个大于等于x的节点
	//notLessEnd：表示当前最后一个大于等于x的节点
	//cur：表示当前遍历指向的节点
	//先初始化这4个指针位置，
	//再通过移动cur判断是否小于x，如果小于x则插在lessEnd后面，notLessStart前面
	//如果大于等于x则更新lessEnd为cur，cur继续往后移动，直到cur为空
	public static ListNode partition(ListNode head, int x) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode lessEnd = newHead,notLessStart,notLessEnd,cur;
        while(lessEnd.next != null && lessEnd.next.val < x){
            lessEnd = lessEnd.next;
        }
        notLessStart = lessEnd.next;
        notLessEnd = notLessStart;
        if(notLessEnd != null){
            cur = notLessEnd.next;
        }else{
            cur = null;
        }
        while(cur != null){
            if(cur.val < x){
                notLessEnd.next = cur.next;
                lessEnd.next = cur;
                cur.next = notLessStart;
                lessEnd = cur;
                cur = notLessEnd.next;
            }else{
                notLessEnd = cur;
                cur = cur.next;
            }
        }
        return newHead.next;
    }
	
	//方法2：
	//用两个链表分别用来存储小于x的节点和大于等于x的节点，less和notLess
	//遍历原来的链表，用less把小于x的节点按顺序串起来，用notLess把大于等于x的节点按顺序串起来
	//最后一定要把notLess的尾节点的next置为null，因为不能保证notLess尾节点为原链表的尾节点
	public ListNode partition2(ListNode head, int x) {
        ListNode less = new ListNode(0),notLess = new ListNode(0);
        ListNode p1 = less,p2 = notLess;
        while(head != null){
            if(head.val < x){
                p1.next = head;
                p1 = p1.next;
            }else{
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }
        p2.next = null;	//不能缺少，因为不能保证最后一个节点是大于等于x的节点
        p1.next = notLess.next;
        return less.next;
    }
}
