package algorithm.leetcode.algorithm;
/*
 * medium
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The most significant digit comes first and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

 */
import java.util.*;
public class NO445_AddTwoNumbersII {
	public static void main(String[] args) {
		ListNode l1 = ListNode.getList(7,2,4,3);
		ListNode l2 = ListNode.getList(5,6,4);
		System.out.println(addTwoNumbers2(l1, l2));
	}
	//方法1：
	//迭代，利用2个stack保存两个链表的节点
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode head = null,tmp;
        int sum;
        int add = 0;
        while(l1 != null){
            stack1.push(l1);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2);
            l2 = l2.next;
        }
        while(!stack1.isEmpty() || !stack2.isEmpty() || add != 0){
            sum = 0;
            if(!stack1.isEmpty()){
                sum+=stack1.pop().val;
            }
            if(!stack2.isEmpty()){
                sum+=stack2.pop().val;
            }
            sum+=add;
            add = sum/10;
            sum%=10;
            tmp = new ListNode(sum);
            tmp.next = head;
            head = tmp;
        }
        return head;
    }
	//方法2：
	//递归
	//先递归得到两个链表的长度，再进行末尾到头序号一致的运算
	public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode tmp;
        int sum,depth1 = 0,depth2 = 0;
        int add = 0;
        tmp = l1;
        while(tmp != null){
            depth1++;
            tmp = tmp.next;
        }
        tmp = l2;
        while(tmp != null){
            depth2++;
            tmp = tmp.next;
        }
        ListNode dummyHead = new ListNode(0);
        add = helper(l1,depth1,l2,depth2,dummyHead);
        if(add > 0){
            ListNode node = new ListNode(add);
            node.next = dummyHead.next;
            dummyHead.next = node;
        }
        return dummyHead.next;
    }
    private static int helper(ListNode l1,int depth1,ListNode l2,int depth2,ListNode dummyHead){
    	int add = 0,sum = 0;
        if(depth1 == depth2){
            if(depth1 == 0){
                return 0;
            }else{
                sum += helper(l1.next,depth1-1,l2.next,depth2-1,dummyHead);
                sum+=l1.val+l2.val;
                add = sum/10;
                sum%=10;
                ListNode node = new ListNode(sum);
                node.next = dummyHead.next;
                dummyHead.next = node;
            }
        }else{
            if(depth1 > depth2){
                sum+= helper(l1.next,depth1-1,l2,depth2,dummyHead);
                sum+=l1.val;
                add = sum/10;
                sum%=10;
            }else{
                sum+= helper(l1,depth1,l2.next,depth2-1,dummyHead);
                sum+=l2.val;
                add = sum/10;
                sum%=10;
            }
            ListNode node = new ListNode(sum);
            node.next = dummyHead.next;
            dummyHead.next = node;
        }
        return add;
    }
}
