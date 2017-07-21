package algorithm.leetcode.algorithm;
/*
 * hard
 * 25. Reverse Nodes in k-Group 
 *  Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5 

similar problems:
24. Swap Nodes in Pairs 
 */
public class NO25_ReverseNodesInK_Group {
	public static void main(String[] args) {
		ListNode head = ListNode.getList(1,2);
		int k = 2;
		System.out.println(reverseKGroup(head,k));
	}
	//方法1：
	//分组逆转链表，到达k的倍数节点时，旋转前k个节点
	public static ListNode reverseKGroup(ListNode head, int k) {
		//k=1时，不做处理
        if(k == 1){
			return head;
		}
        //tail表示前面从头节点开始k的倍数节点分组排好序后的尾节点，tmpHead表示当前分组的头节点
        //nextNode表示下一个节点，count表示当前节点的个数
        ListNode fakeHead = new ListNode(0),tail = fakeHead,tmpHead = null,nextNode = null;
        fakeHead.next = head;
        int count = 0;
        while(head != null){
            count++;
            nextNode = head.next;
            if(count%k == 0){
            	head.next = null;
            	tail.next = reverseList(tmpHead);
            	tmpHead.next = nextNode;
            	tail = tmpHead;
            }else if(count%k == 1){
            	tmpHead = head;
            }
            head = nextNode;
        }
        return fakeHead.next;
    }
	//逆转链表
    private static ListNode reverseList(ListNode head){
        ListNode fakeHead = new ListNode(0),nextNode,tmpHead;
        while(head != null){
            nextNode = head.next;
            head.next = fakeHead.next;
            fakeHead.next = head;
            head = nextNode;
        }
        return fakeHead.next;
    }
    //方法2：
    //其他实现形式
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head==null||head.next==null||k<2) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode tail = dummy, prev = dummy,temp;
        int count;
        while(true){
            count =k;
            while(count>0&&tail!=null){
                count--;
                tail=tail.next;
            } 
            if (tail==null) break;//Has reached the end
            
            head=prev.next;//for next cycle
        // prev-->temp-->...--->....--->tail-->....
        // Delete @temp and insert to the next position of @tail
        // prev-->...-->...-->tail-->head-->...
        // Assign @temp to the next node of @prev
        // prev-->temp-->...-->tail-->...-->...
        // Keep doing until @tail is the next node of @prev
            while(prev.next!=tail){
                temp=prev.next;//Assign
                prev.next=temp.next;//Delete
                
                temp.next=tail.next;
                tail.next=temp;//Insert
                
            }
            
            tail=head;
            prev=head;
            
        }
        return dummy.next;
    }
    //方法3：
    //递归形式
    public ListNode reverseKGroup3(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup3(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}
