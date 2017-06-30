package algorithm.leetcode.algorithm;
/*
 * hard
 * 432. All O`one Data Structure 
 * mplement a data structure supporting the following operations:

    Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
    Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
    GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
    GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".

Challenge: Perform all these in O(1) time complexity.

 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
import java.util.*;
public class NO432_AllO_oneDataStructure {
	//方法1：
	//创建一个双向链表，用于增加或删除节点，节点内保存了value的值，以及value对应的key集
	//利用HashMap保存key和节点的映射
	class AllOne {
	    class Node{
	        int value;
	        List<String> keys;
	        Node pre,next;
	        Node(int value,String key){
	            this.value = value;
	            keys = new ArrayList<>();
	            keys.add(key);
	        }
	    }
	    
	    private Node head,tail;
	    private HashMap<String,Node> map;
	    /** Initialize your data structure here. */
	    public AllOne() {
	        head = null;
	        tail = null;
	        map = new HashMap<>();
	    }
	    
	    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	    public void inc(String key) {
	        if(tail == null){
	            Node node = new Node(1,key);
	            head = node;
	            tail = node;
	            map.put(key,head);
	        }else if(!map.containsKey(key)){
	            if(tail.value == 1){
	                tail.keys.add(key);
	                map.put(key,tail);
	            }else{
	                Node node = new Node(1,key);
	                tail.next = node;
	                node.pre = tail;
	                tail = node;
	                map.put(key,node);
	            }
	        }else{
	            Node node = map.get(key);
	            node.keys.remove(key);
	            if(node.pre == null){
	                Node newNode = new Node(node.value+1,key);
	                newNode.next = node;
	                node.pre = newNode;
	                head = newNode;
	                map.put(key,newNode);
	            }else if(node.pre.value != node.value+1){
	                Node newNode = new Node(node.value+1,key);
	                newNode.next = node;
	                newNode.pre = node.pre;
	                node.pre.next = newNode;
	                node.pre = newNode;
	                map.put(key,newNode);
	            }else{
	                node.pre.keys.add(key);
	                map.put(key,node.pre);
	            }
	            checkEmpty(node);
	        }
	    }
	    
	    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	    public void dec(String key) {
	        if(map.containsKey(key)){
	            Node node = map.get(key);
	            node.keys.remove(key);
	            if(node.value == 1){
	                map.remove(key);
	            }else{
	                if(node.next == null){
	                    Node newNode = new Node(node.value-1,key);
	                    node.next = newNode;
	                    tail = newNode;
	                    map.put(key,newNode);
	                }else if(node.next.value != node.value-1){
	                    Node newNode = new Node(node.value-1,key);
	                    newNode.next = node.next;
	                    newNode.pre = node;
	                    node.next.pre = newNode;
	                    node.next = newNode;
	                    map.put(key,newNode);
	                }else{
	                    node.next.keys.add(key);
	                    map.put(key,node.next);
	                }
	            }
	            checkEmpty(node);
	        }
	    }
	    
	    private void checkEmpty(Node node){
	        if(head == null || tail == null || node == null || node.keys.size() > 0){
	            return;
	        }
	        if(node.pre == null && node.next == null){
	            head = null;
	            tail = null;
	        }else if(node.pre == null && node.next != null){
	            head = node.next;
	            head.pre = null;
	        }else if(node.pre != null && node.next == null){
	            tail = node.pre;
	            tail.next = null;
	        }else{
	            node.next.pre = node.pre;
	            node.pre.next = node.next;
	        }
	    }
	    /** Returns one of the keys with maximal value. */
	    public String getMaxKey() {
	        return head == null ? "" : head.keys.get(0);
	    }
	    
	    /** Returns one of the keys with Minimal value. */
	    public String getMinKey() {
	        return tail == null ? "" : tail.keys.get(0);
	    }
	}
}
