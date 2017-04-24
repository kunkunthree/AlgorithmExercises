package algorithm.leetcode.algorithm;
/*
 * medium
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 *  Add the two numbers and return it as a linked list.
 *  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 */
public class NO2_AddTwoNumbers {
	public static void main(String[] args) {
		ListNode node;
		ListNode l1 = new ListNode(2);
		node = l1;
		node.next = new ListNode(4);
		node = node.next;
		node.next = new ListNode(3);
		ListNode l2 = new ListNode(5);
		node = l2;
		node.next = new ListNode(6);
		node = node.next;
		node.next = new ListNode(4);
		ListNode result = addTwoNumbers(l1, l2);
		while(result != null){
			System.out.println(result.val + " ");
			result = result.next;
		}
	}
    public static  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null,node = null;
        int x = 0;
        int val;
        int sum;
        while(l1!=null || l2!=null){
            sum = 0;
            if(l1!=null){
                sum+=l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            sum+=x;
            val = sum%10;
            x = sum/10;
            if(head == null){
                node = new ListNode(val);
                head = node;
            }else{
                node.next = new ListNode(val);
                node = node.next;
            }
        }
        if(x != 0){
            node.next = new ListNode(x);
        }
        return head;
    }
    //别人的写法，更简洁一点
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }
}
