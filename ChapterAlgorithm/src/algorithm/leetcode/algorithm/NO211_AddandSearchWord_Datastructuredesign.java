package algorithm.leetcode.algorithm;
/*
 * medium
 * 211. Add and Search Word - Data structure design
 *  Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or .
A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Note:
You may assume that all words are consist of lowercase letters a-z. 
 */
public class NO211_AddandSearchWord_Datastructuredesign {
	//方法1：
	//用一个节点表示字符，一个数组指向下一个字符
	public class WordDictionary {
	    class Node{
	        char val;
	        boolean isWord;
	        Node[] next = new Node[26];
	        Node(char val,boolean isWord){
	            this.val = val;
	            this.isWord = isWord;
	        }
	    }
	    private Node head;
	    /** Initialize your data structure here. */
	    public WordDictionary() {
	        head = new Node('\0',false);
	    }
	    
	    /** Adds a word into the data structure. */
	    public void addWord(String word) {
	        if(word == null){
	            return;
	        }
	        int length = word.length();
	        Node node = head;
	        int c;
	        for(int i = 0 ; i < length ; i++){
	            c = word.charAt(i)-'a';
	            if(node.next[c] == null){
	                node.next[c] = new Node(word.charAt(i),false);
	            }
	            node = node.next[c];
	        }
	        node.isWord = true;
	    }
	    
	    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	    public boolean search(String word) {
	        return dfs(word,head,0);
	    }
	    private boolean dfs(String word,Node node,int count) {
	        if(node == null || word == null){
	            return false;
	        }
	        if(count == word.length()){
	            return node.isWord;
	        }
	        for(int i = 0 ; i < 26 ; i++){
	            if((i + 'a') == word.charAt(count)){
	                if(dfs(word,node.next[word.charAt(count)-'a'],count+1) == true){
	                    return true;
	                }
	            }else if('.' == word.charAt(count)){
	                if(dfs(word,node.next[i],count+1) == true){
	                    return true;
	                }
	            } 
	        }
	        return false;
	    }
	}
}
