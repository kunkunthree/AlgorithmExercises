package algorithm.leetcode.algorithm;
/*
 * medium
 * 142. Linked List Cycle II
 *  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space? 

similar problems：
141. Linked List Cycle 
287. Find the Duplicate Number 
 */
public class NO142_LinkedListCycleII {
	public static void main(String[] args) {
		ListNode[] nodes = new ListNode[8];
		for(int i = 0 ; i < 8 ; i++){
			nodes[i] = new ListNode(i+1);
			if(i > 0){
				nodes[i-1].next = nodes[i];
			}
		}
		nodes[7].next = nodes[4];
		ListNode result = detectCycle(nodes[0]);
		if(result == null){
			System.out.println(result);
		}else{
			System.out.println(result.val);
		}
	}
	//方法1：
	//用两个指针，一个快fast，一个慢slow，fast移动速度时slow的两倍，
	//初始fast = head，slow = head，然后开始移动，直到fast == null 或者fast == null 或者 slow == fast。
	//如果最后slow = fast，说明有环，否则无环
	//当slow==fast的时候，此时，fast走过的距离是slow走过的2倍，即fast在环上走过的距离和slow走过的距离相等
	//这就相当于一开始fast在此时的位置以相同的速度开始移动，移动相同的距离后，fast和slow都在这个位置上。
	//那么只要此时slow从head开始，fast从此时位置开始，两个指针以相同速度移动，
	//那么就一定会在环开始的地方相遇。
	public static ListNode detectCycle(ListNode head) {
        ListNode fast = head,slow = head;
        while(fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null;
        }
        slow = head;
        System.out.println(fast.val);
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
