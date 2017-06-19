package algorithm.leetcode.algorithm;
/*
 * hard
 * 146. LRU Cache 
 *  Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  );	// capacity

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

 */
import java.util.*;
public class NO146_LRUCache {
	//LRU是最近最少使用页面置换算法(Least Recently Used),也就是首先淘汰最长时间未被使用的页面!
	//LRU关键是看页面最后一次被使用到发生调度的时间长短,
	//方法1：
	//利用map记录key和value的值，利用双向链表记录使用情况，
	//首节点head的下一个节点为最近访问的节点，尾节点的前一个节点表示最近最长时间没访问的节点
	//当访问了链表中某个节点后，将其从链表中移除，再添加到头部
	//如果添加新的节点超过容量，则把尾节点的前一个节点删除，再将新节点添加到头部
	class Node {
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key, int value) {
        	this.key = key;
        	this.value = value;
        }
    }
    private void deleteNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    private void addToHead(Node node){
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }
    HashMap<Integer, Node> map;
    int capacity, count;
    Node head, tail;
    public NO146_LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        count = 0;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
    }
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            deleteNode(node);
            addToHead(node);
            return node.value;
        }else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }else{
            Node node = new Node(key,value);
            map.put(key,node);
            if(count < capacity){
                addToHead(node);
                count++;
            }else{
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }
}
