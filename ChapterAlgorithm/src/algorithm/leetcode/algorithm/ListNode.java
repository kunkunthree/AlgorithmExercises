package algorithm.leetcode.algorithm;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public static ListNode getList(int...x){
    	if(x == null || x.length == 0){
    		return null;
    	}
    	ListNode head = new ListNode(x[0]);
    	ListNode node = head;
    	for(int i = 1; i < x.length ; i++){
    		node.next = new ListNode(x[i]);
    		node = node.next;
    	}
    	return head;
    }
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	ListNode node = this;
    	while(node != null){
    		if(node.next == null){
    			sb.append(node.val);
    		}else{
    			sb.append(node.val+",");
    		}
    		node = node.next;
    	}
    	return sb.toString();
    }
}
