package algorithm.leetcode.algorithm;
/*
 * medium
 * 208. Implement Trie (Prefix Tree) 
 * implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z. 
 */
public class NO208_ImplementTrie_PrefixTree {
	public static void main(String[] args) {
		String s;
	}
	//方法1：
	//用二叉查找树存储
	public class Trie {
	    class Node{
	        String val;
	        Node left,right;
	        Node(String s){
	            val = s;
	        }
	    }
	    Node head = null;
	    /** Initialize your data structure here. */
	    public Trie() {
	        
	    }
	    
	    /** Inserts a word into the trie. */
	    public void insert(String word) {
	        if(head == null){
	            head = new Node(word);
	        }else{
	            Node node = head;
	            while(!node.val.equals(word)){
	                int result = word.compareTo(node.val);
	                if(result < 0){
	                    if(node.left == null){
	                        node.left = new Node(word);
	                    }
	                    node = node.left;
	                }else{
	                    if(node.right == null){
	                        node.right = new Node(word);
	                    }
	                    node = node.right;
	                }
	            }
	        }
	    }
	    
	    /** Returns if the word is in the trie. */
	    public boolean search(String word) {
	        Node node = head;
	        int result;
	        while(node != null){
	            result = word.compareTo(node.val);
	            if(result > 0){
	                node = node.right;
	            }else if(result < 0){
	                node = node.left;
	            }else{
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    /** Returns if there is any word in the trie that starts with the given prefix. */
	    public boolean startsWith(String prefix) {
	        Node node = head;
	        int result;
	        while(node != null){
	            if(node.val.startsWith(prefix)){
	                return true;
	            }
	            result = prefix.compareTo(node.val);
	            if(result > 0){
	                node = node.right;
	            }else if(result < 0){
	                node = node.left;
	            }
	        }
	        return false;
	    }
	}
	//方法2：
	//用节点中数组引用，指向下一个字母
	public class Trie2 {
	    class Node{
	        char val;
	        boolean isWord;
	        Node[] next = new Node[26];
	        Node(char s,boolean isWord){
	            val = s;
	            this.isWord = isWord;
	        }
	    }
	    Node head = null;
	    /** Initialize your data structure here. */
	    public Trie2() {
	        head = new Node('\0',false);
	    }
	    
	    /** Inserts a word into the trie. */
	    public void insert(String word) {
	        if(word == null){
	            return;
	        }
	        int index = 0,length = word.length();
	        Node node = head;
	        for(int i = 0 ; i < length ; i++){
	            if(node.next[word.charAt(i)-'a'] == null){
	                node.next[word.charAt(i)-'a'] = new Node(word.charAt(i),false);
	            }
	            node = node.next[word.charAt(i)-'a'];
	        }
	        node.isWord = true;
	    }
	    
	    /** Returns if the word is in the trie. */
	    public boolean search(String word) {
	        if(word == null){
	            return false;
	        }
	        int index = 0,length = word.length();
	        Node node = head;
	        for(int i = 0 ; i < length ; i++){
	            if(node.next[word.charAt(i)-'a'] == null){
	                return false;
	            }
	            node = node.next[word.charAt(i)-'a'];
	        }
	        return node.isWord;
	    }
	    
	    /** Returns if there is any word in the trie that starts with the given prefix. */
	    public boolean startsWith(String prefix) {
	        if(prefix == null){
	            return true;
	        }
	        int index = 0,length = prefix.length();
	        Node node = head;
	        for(int i = 0 ; i < length ; i++){
	            if(node.next[prefix.charAt(i)-'a'] == null){
	                return false;
	            }
	            node = node.next[prefix.charAt(i)-'a'];
	        }
	        return true;
	    }
	}
}
