package algorithm.leetcode.algorithm;
/*
 * hard
 * 23. Merge k Sorted Lists 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 
 */
import java.util.*;
public class NO23_MergekSortedLists {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n  = Integer.valueOf(in.nextLine());
		ListNode[] lists = new ListNode[n];
		for(int i = 0 ; i < n ; i++){
			lists[i] = new ListNode(0);
			ListNode tail = lists[i];
			for(String s : in.nextLine().split(" ")){
				if(s != null && s.length() > 0){
					tail.next = new ListNode(Integer.valueOf(s));
					tail = tail.next;
				}
			}
			lists[i] = lists[i].next;
		}
		System.out.println(mergeKLists2(lists));
	}
	//方法1：
	//利用PriorityQueue
	//O(nlogn)
	public static ListNode mergeKLists(ListNode[] lists) {
        int countNull = 0,minIndex = 0,min;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(1,new Comparator<ListNode>(){
            public int compare(ListNode node1,ListNode node2){
                return node1.val - node2.val;
            }
        });
        for(ListNode node : lists){
            while(node != null){
                queue.offer(node);
                node = node.next;
            }
        }
        ListNode dummy = new ListNode(0),tail = dummy;
        while(!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;
        }
        tail.next = null;
        return dummy.next;
    }
	//方法2：
	//递归，二分法
	//O(n)time,n为所有节点总数
	public static ListNode mergeKLists2(ListNode[] lists) {
        return mergeList(lists,0,lists.length-1);
    }
    private static ListNode mergeList(ListNode[] lists,int start,int end){
        if(start > end){
            return null;
        }
        if(start == end){
            return lists[start];
        }else if(start+1 == end){
            return merge(lists[start],lists[end]);
        }
        int mid = end - (end - start)/2;
        return merge(mergeList(lists,start,mid),mergeList(lists,mid+1,end));
    }
    private static ListNode merge(ListNode l1,ListNode l2){
    	ListNode fakeHead = new ListNode(0),tail = fakeHead;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if(l1 == null){
            tail.next = l2;
        }else{
            tail.next = l1;
        }
        return fakeHead.next;
    }
}
