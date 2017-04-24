package algorithm.leetcode.algorithm;
/*
 * medium
 * 61. Rotate List 
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.


 */
public class NO61_RotateList {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(new int[]{1,2,3});
		int k = 1;
//		System.out.println(head);
		System.out.println(rotateRight(head, k));
	}
	//方法1：
	//先计算链表长度length，得到尾部非空节点tail
	//在计算所需要旋转的最小长度k= k%length;
	//如果k = 0，则不需要做任何操作
	//如果k != 0，我们只需要将第length-k个节点的next置为null，tail.next置为head，返回第length-k+1个节点
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0){
            return head;
        }
        ListNode node = head,newHead = head,tail = head;
        int length = 0;
        while(tail.next != null){
            tail = tail.next;
            length++;
        }
        length++;
        k = k%length;
        if(k == 0){
        	return head;
        }
        k = length-k-1;
        while(k > 0){
            node = node.next;
            k--;
        }
        if(node.next != null){
            newHead = node.next;
            tail.next = head;
            node.next = null;
        }
        return newHead;
    }
    //方法2：
    //方法1的更简洁写法
    public ListNode rotateRight2(ListNode head, int n) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;

        int i;
        for (i=0;fast.next!=null;i++)//Get the total length 
        	fast=fast.next;
        
        for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
        	slow=slow.next;
        
        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;
        
        return dummy.next;
    }
}
