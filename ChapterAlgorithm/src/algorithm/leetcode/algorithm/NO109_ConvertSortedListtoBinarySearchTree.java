package algorithm.leetcode.algorithm;
/*
 * medium
 * 109. Convert Sorted List to Binary Search Tree
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * 
 * similar problems:
 * 108. Convert Sorted Array to Binary Search Tree 
 */
import java.util.*;
public class NO109_ConvertSortedListtoBinarySearchTree {
	//方法1：
	//先将其转换为数组，再用递归求得平衡二叉查找树
	public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<Integer>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        return sortedListToBSTHelper(list,0,list.size()-1);
    }
    public TreeNode sortedListToBSTHelper(List<Integer> list,int start,int end ){
        if(start > end){
            return null;
        }
        if(start == end){
            return new TreeNode(list.get(start));
        }
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = sortedListToBSTHelper(list,start,mid-1);
        node.right = sortedListToBSTHelper(list,mid+1,end);
        return node;
    }
    
    //方法2：
    //递归，不用数组
    public TreeNode sortedListToBST2(ListNode head) {
        return sortedListToBSTHelper(head,null);
    }
    public TreeNode sortedListToBSTHelper(ListNode head,ListNode tail){
        ListNode fast = head,slow = head;
        if(head == tail){
            return null;
        }
        while(fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBSTHelper(head,slow);
        node.right = sortedListToBSTHelper(slow.next,tail);
        return node;
    }
}
