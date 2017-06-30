package algorithm.leetcode.algorithm;
/*
 * hard
 * 460. LFU Cache 
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2); // capacity 

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

similar problems：
432. All O`one Data Structure 
 */
import java.util.*;
public class NO460_LFUCache {
	public static void main(String[] args) {
		NO460_LFUCache test1 = new NO460_LFUCache();
		LFUCache test2 = test1.new LFUCache(1);
		test2.put(2, 1);
		System.out.println(test2.get(2));
		test2.put(3, 2);
		System.out.println(test2.get(2));
		System.out.println(test2.get(3));
	    System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.1f)));  
	    System.out.println(Long.toBinaryString(Double.doubleToLongBits(0.1f)));  
	    System.out.println(Long.toBinaryString(Double.doubleToLongBits(0.1d)));  
	    Stack<Integer> stack = new Stack<>();
	    stack.push(1);
	    stack.push(2);
	    stack.push(3);
	    Iterator<Integer> it = stack.iterator();
	    while(it.hasNext()){
	    	System.out.println(it.next());
	    }
	}
	//方法1：
	//用一个双向链表和两个Map
	//双向链表用于记录排序访问次数,链表节点Node存储当前访问次数，
	//以及相同访问次数的key的数组，根据访问时间的近远排序
	//nodeMap用于快速查找链表中key对应的节点
	//valueMap用于快速查找key对应存储的value值
	public class LFUCache {
		class Node{
	        int count;
	        List<Integer> keys;
	        Node pre,next;
	        Node(int count,int key){
	            this.count = count;
	            keys = new ArrayList<>();
	            keys.add(key);
	        }
	    }
	    private HashMap<Integer,Node> nodeMap;
	    private HashMap<Integer,Integer> valueMap;
	    private Node head,tail;
	    private int capacity;
	    public LFUCache(int capacity) {
	        head = null;
	        tail = null;
	        this.capacity = capacity;
	        nodeMap = new HashMap<>();
	        valueMap = new HashMap<>();
	    }
	    
	    public int get(int key) {
	        if(!valueMap.containsKey(key)){
	            return -1;
	        }else{
	            put(key,valueMap.get(key));
	            return valueMap.get(key);
	        }
	    }
	    
	    public void put(int key, int value) {
	        if(capacity <= 0){
	            return;
	        }
	        valueMap.put(key,value);
	        if(head == null && tail == null){
	            Node node = new Node(1,key);
	            head = node;
	            tail = node;
	            nodeMap.put(key,node);
	        }else if(nodeMap.containsKey(key)){
	            Node node = nodeMap.get(key);
	            node.keys.remove(new Integer(key));
	            if(node.pre == null){
	                Node newNode = new Node(node.count+1,key);
	                head = newNode;
	                head.next = node;
	                node.pre = head;
	                nodeMap.put(key,newNode);
	            }else if(node.pre.count == node.count+1){
	                node.pre.keys.add(0,key);
	                nodeMap.put(key,node.pre);
	            }else{
	                Node newNode = new Node(node.count+1,key);
	                newNode.next = node;
	                newNode.pre = node.pre;
	                node.pre.next = newNode;
	                node.pre = newNode;
	                nodeMap.put(key,newNode);
	            }
	            checkEmpty(node);
	        }else{
	            if(nodeMap.size() == capacity){
	                valueMap.remove(tail.keys.get(tail.keys.size()-1));
	                nodeMap.remove(tail.keys.get(tail.keys.size()-1));
	                tail.keys.remove(tail.keys.get(tail.keys.size()-1));
	                checkEmpty(tail);
	            }
	            if(tail == null){
	                put(key,value);
	            }else if(tail.count == 1){
	                tail.keys.add(0,key);
	                nodeMap.put(key,tail);
	            }else{
	                Node newNode = new Node(1,key);
	                tail.next = newNode;
	                newNode.pre = tail;
	                tail = newNode;
	                nodeMap.put(key,newNode);
	            }
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
	            node.pre.next = node.next;
	            node.next.pre = node.pre;
	        }
	    }
	}
	
	//方法2：
	//其他人的实现
	public class LFUCache2 {
	    private Node head = null;
	    private int cap = 0;
	    private HashMap<Integer, Integer> valueHash = null;
	    private HashMap<Integer, Node> nodeHash = null;
	    
	    public LFUCache2(int capacity) {
	        this.cap = capacity;
	        valueHash = new HashMap<Integer, Integer>();
	        nodeHash = new HashMap<Integer, Node>();
	    }
	    
	    public int get(int key) {
	        if (valueHash.containsKey(key)) {
	            increaseCount(key);
	            return valueHash.get(key);
	        }
	        return -1;
	    }
	    
	    public void set(int key, int value) {
	        if ( cap == 0 ) return;
	        if (valueHash.containsKey(key)) {
	            valueHash.put(key, value);
	        } else {
	            if (valueHash.size() < cap) {
	                valueHash.put(key, value);
	            } else {
	                removeOld();
	                valueHash.put(key, value);
	            }
	            addToHead(key);
	        }
	        increaseCount(key);
	    }
	    
	    private void addToHead(int key) {
	        if (head == null) {
	            head = new Node(0);
	            head.keys.add(key);
	        } else if (head.count > 0) {
	            Node node = new Node(0);
	            node.keys.add(key);
	            node.next = head;
	            head.prev = node;
	            head = node;
	        } else {
	            head.keys.add(key);
	        }
	        nodeHash.put(key, head);      
	    }
	    
	    private void increaseCount(int key) {
	        Node node = nodeHash.get(key);
	        node.keys.remove(key);
	        
	        if (node.next == null) {
	            node.next = new Node(node.count+1);
	            node.next.prev = node;
	            node.next.keys.add(key);
	        } else if (node.next.count == node.count+1) {
	            node.next.keys.add(key);
	        } else {
	            Node tmp = new Node(node.count+1);
	            tmp.keys.add(key);
	            tmp.prev = node;
	            tmp.next = node.next;
	            node.next.prev = tmp;
	            node.next = tmp;
	        }

	        nodeHash.put(key, node.next);
	        if (node.keys.size() == 0) remove(node);
	    }
	    
	    private void removeOld() {
	        if (head == null) return;
	        int old = 0;
	        for (int n: head.keys) {
	            old = n;
	            break;
	        }
	        head.keys.remove(old);
	        if (head.keys.size() == 0) remove(head);
	        nodeHash.remove(old);
	        valueHash.remove(old);
	    }
	    
	    private void remove(Node node) {
	        if (node.prev == null) {
	            head = node.next;
	        } else {
	            node.prev.next = node.next;
	        } 
	        if (node.next != null) {
	            node.next.prev = node.prev;
	        }
	    }
	    
	    class Node {
	        public int count = 0;
	        public LinkedHashSet<Integer> keys = null;
	        public Node prev = null, next = null;
	        
	        public Node(int count) {
	            this.count = count;
	            keys = new LinkedHashSet<Integer>();
	            prev = next = null;
	        }
	    }
	}
	//方法3：
	class LFUCache3 {
	    HashMap<Integer, node> map1;
	    HashMap<Integer, node[]> map2;
	    int size, lowestFreq;
	    public class node {
	        int key;
	        int value;
	        int frequency;
	        node prev;
	        node next;
	        public node(int k, int v, int f) {
	            key = k;
	            value = v;
	            frequency = f;
	        }
	    }
	    
	    public void delete(node x) {
	        node prev = x.prev;
	        node next = x.next;
	        prev.next = next;
	        next.prev = prev;
	    }
	    
	    public void addToHead(int freq, node x) {
	        if (!map2.containsKey(freq)) {
	            node h = new node(0, 0, freq);
	            node t = new node(0, 0, freq);
	            h.next = t;
	            t.prev = h;
	            map2.put(freq, new node[]{h, t});
	        }
	        node head = map2.get(freq)[0];
	        x.next = head.next;
	        head.next.prev = x;
	        head.next = x;
	        x.prev = head;
	    }
	    
	    public LFUCache3(int capacity) {
	        map1 = new HashMap<Integer, node>();
	        map2 = new HashMap<Integer, node[]>();
	        size = capacity;
	        lowestFreq = 0;
	    }
	    
	    public int get(int key) {
	        if (!map1.containsKey(key))
	            return -1;
	        node current = map1.get(key);
	        delete(current);
	        if (lowestFreq == current.frequency && map2.get(lowestFreq)[0].next.value == 0)
	            lowestFreq += 1;
	        current.frequency += 1;
	        addToHead(current.frequency, current);
	        return current.value;
	    }
	    
	    public void put(int key, int value) {
	        if (size == 0)
	            return;
	        if (map1.containsKey(key)) {
	            node current = map1.get(key);
	            delete(current);
	            if (lowestFreq == current.frequency && map2.get(lowestFreq)[0].next.value == 0)
	                lowestFreq += 1;
	            current.value = value;
	            current.frequency += 1;
	            addToHead(current.frequency, current);
	        }
	        else {
	            if (map1.size() == size) {
	                node tail = map2.get(lowestFreq)[1];
	                map1.remove(tail.prev.key);
	                delete(tail.prev);
	            }
	            node nd = new node(key, value, 1);
	            map1.put(key, nd);
	            addToHead(1, nd);
	            lowestFreq = 1;
	        }
	    }
	}
}
