package algorithm.leetcode.algorithm;
/*
 * medium
 * 138. Copy List with Random Pointer
 *  A linked list is given such that each node contains an additional random pointer 
 *  which could point to any node in the list or null.

Return a deep copy of the list. 

similar problems:
133. Clone Graph 
 */
import java.util.*;
public class NO138_CopyListwithRandomPointer {
	//方法1：
	//利用map把所有节点存起来，避免重复创建，O(n)space,O(n)time
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null){
            return null;
        }
        Map<Integer,RandomListNode> map = new HashMap<>();
        RandomListNode result = new RandomListNode(head.label);
        RandomListNode node;
        map.put(result.label,result);
        while(head != null){
            if(!map.containsKey(head.label)){
                map.put(head.label,new RandomListNode(head.label));
            }
            node = map.get(head.label);
            if(head.random != null){
                if(!map.containsKey(head.random.label)){
                    map.put(head.random.label,new RandomListNode(head.random.label));
                }
                node.random = map.get(head.random.label);
            }
            if(head.next != null){
                if(!map.containsKey(head.next.label)){
                    map.put(head.next.label,new RandomListNode(head.next.label));
                }
                node.next = map.get(head.next.label);
            }
            head = head.next;
        }
        return result;
    }
	//方法2：
	//迭代，三个循环，
	//第一次循环为每一个节点后插入一个相同标签的新节点
	//第二次循环为每一个新节点设置random
	//第三次循环为每一个新节点设置next
	public RandomListNode copyRandomList2(RandomListNode head) {
        if(head == null){
            return null;
        }
        RandomListNode newHead,p1,p2;
        for(p1 = head ; p1 != null ; p1 = p1.next.next){
            p2 = new RandomListNode(p1.label);
            p2.next = p1.next;
            p1.next = p2;
        }
        newHead = head.next;
        for(p1 = head ; p1 != null ; p1 = p1.next.next){
            if(p1.random != null){
                p1.next.random = p1.random.next;
            }
        }
        for(p1 = head ; p1 != null ; p1 = p1.next){
            p2 = p1.next;
            p1.next = p2.next;
            if(p1.next != null){
                p2.next = p1.next.next;
            }
        }
        return newHead;
    }
	//方法2：
		//迭代，三个循环，
		//第一次循环为每一个节点random后插入一个相同标签的新节点
		//第二次循环为每一个新节点设置random
		//第三次循环为每一个新节点设置next,恢复原链表的random
	public RandomListNode copyRandomList3(RandomListNode head) {
        if(head == null){
            return null;
        }
        RandomListNode newHead,p1,p2;
        for(p1 = head ; p1 != null ; p1 = p1.next){
            p2 = new RandomListNode(p1.label);
            p2.next = p1.random;
            p1.random = p2;
        }
        newHead = head.random;
        for(p1 = head ; p1 != null ; p1 = p1.next){
            p2 = p1.random;
            if(p2.next != null){
                p2.random = p2.next.random;
            }else{
                p2.random = null;
            }
        }
        for(p1 = head ; p1 != null ; p1 = p1.next){
            p2 = p1.random;
            p1.random = p2.next;
            if(p1.next != null){
                p2.next = p1.next.random;
            }else{
                p2.next = null;
            }
        }
        return newHead;
    }
}
