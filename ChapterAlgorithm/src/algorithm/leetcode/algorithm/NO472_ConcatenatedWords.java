package algorithm.leetcode.algorithm;
/*
 * hard
 * 472. Concatenated Words 
 * Given a list of words (without duplicates), please write a program that returns all concatenated words
 *  in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words 
in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Note:
    1.		The number of elements of the given array will not exceed 10,000
    2.		The length sum of elements in the given array will not exceed 600,000.
    3.		All the input string will only include lower case letters.
    4.		The returned elements order does not matter.

 */
import java.util.*;
public class NO472_ConcatenatedWords {
	public static void main(String[] args) {
		String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
//		System.out.println(findAllConcatenatedWordsInADict(words));
		NO472_ConcatenatedWords test = new NO472_ConcatenatedWords();
		System.out.println(test.findAllConcatenatedWordsInADict2(words));
		
	}
	//方法1：
	//先对长度进行排序，从小到大字符串遍历，
	//每遍历一个字符串检查是否由已有比其短或等长的字符串构成（利用动态规划）
	//是则加入结果集，否则不加入；
	//将该字符串加入preWords集，进行下一个字符串的遍历
	public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length <= 2){
            return result;
        }
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words,new Comparator<String>(){
            public int compare(String s1,String s2){
                return s1.length() - s2.length();
            }
        });
        for(String word : words){
            if(word.length() > 0){
                if(canForm(word,preWords)){
                    result.add(word);
                }
                preWords.add(word);
            }
        }
        return result;
    }
    private static boolean canForm(String s,Set<String> preWords){
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j < i ; j++){
            	if(dp[j] == true && !(j == 0 && i == n) && preWords.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
    
    //方法2：
    //字典树存储所有单词，然后对DFS每一个单词的每一个字符
    class Trie{
        boolean isWord;
        Trie[] children;
        Trie(){
            isWord = false;
            children = new Trie[26];
        }
    }
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length <= 2){
            return result;
        }
        Trie root = new Trie();
        for(String word : words){
            if(word != null && word.length() > 0){
                addToTrie(word,root);
            }
        }
        for(String word : words){
            for(int i = 1 ; i < word.length() ; i++){
                if(isInTrie(word.substring(0,i),root) && dfs(word,i,root)){
                    result.add(word);
                    break;
                }
            }
        }
        return result;
    }
    private void addToTrie(String s,Trie root){
        char c;
        for(int i = 0 ; i < s.length() ; i++){
            c = s.charAt(i);
            if(root.children[c-'a'] == null){
                root.children[c-'a'] = new Trie();
            }
            root = root.children[c-'a'];
        }
        root.isWord = true;
    }
    private boolean isInTrie(String s,Trie root){
        char c;
        int i = 0;
        for( ; i < s.length() ; i++){
            c = s.charAt(i);
            if(root.children[c-'a'] == null){
                return false;
            }
            root = root.children[c-'a'];
        }
        return root.isWord;
    }
    private boolean dfs(String s,int start, Trie root){
        if(start == s.length()){
            return true;
        }
        Trie node = root;
        char c;
        for(int i = start ; i < s.length() ; i++){
            c = s.charAt(i);
            if(node.children[c-'a'] == null){
                return false;
            }
            if(node.children[c-'a'].isWord && dfs(s,i+1,root)){
                return true;
            }
            node = node.children[c-'a'];
        }
        return false;
    }
    
    //方法3：
    class TrieNode {
        TrieNode[] children;
        String word;
        boolean isEnd;
        boolean combo; //if this word is a combination of simple words
        boolean added; //if this word is already added in result
        public TrieNode() {
            this.children = new TrieNode[26];
            this.word = new String();
            this.isEnd = false;
            this.combo = false;
            this.added = false;
        }
    }
    private void addWord(String str) {
        TrieNode node = root;
        for (char ch : str.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
        node.word = str;
    }
    private TrieNode root;
    private List<String> result;
    public List<String> findAllConcatenatedWordsInADict3(String[] words) {
        root = new TrieNode();
        for (String str : words) {
            if (str.length() == 0) {
                continue;
            }
            addWord(str);
        }
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }
    private void dfs(TrieNode node, int multi) {
    	//multi counts how many single words combined in this word
        if(node.isEnd && !node.added && multi > 1) {
            node.combo = true;
            node.added = true;
            result.add(node.word);
        }
        searchWord(node, root, multi);
    }
    private void searchWord(TrieNode node1, TrieNode node2, int multi) {
        if (node2.combo) {
            return;
        }
        if (node2.isEnd) {
            //take the pointer of node2 back to root
            dfs(node1, multi + 1);
        }
        for (int  i = 0; i < 26; i++) {
            if (node1.children[i] != null && node2.children[i] != null) {
                searchWord(node1.children[i], node2.children[i], multi);
            }
        }
    }
}
