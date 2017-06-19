package algorithm.leetcode.algorithm;
/*
 * hard
 * 336. Palindrome Pairs 
 *  Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
 *  so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
import java.util.*;
/**
 * @author xiepuxin
 *
 */
public class NO336_PalindromePairs {
	public static void main(String[] args) {
		String s = "";
		System.out.println(s.substring(1,1));
	}
	//方法1：
	//O(n*k^2)time,(n: total number of words; k: average length of each word)
	//利用Map把所有字符串和其下标的映射保存起来
	//然后遍历所有字符串的所有位置假设当前位置为i，字符串为words[i]，将字符串分割成两部分str1和str2
	//判断str1是否是回文，是的话就判断str2的逆序字符串str2vrs是否在map中有对应，
	//有的话说明map中的字符串words[map.get(str2vrs)]构成回文字符串words[map.get(str2vrs)]+words[i]，
	//如果map.get(str2vrs) != i，那么就将{map.get(str2vrs),i}加入到结果数组中
	//同理，如果str2时回文，且str1的逆序字符串str1vrs在map中且map.get(str1vrs) != i，
	//那么就将{i,map.get(str2vrs)}加入到结果数组中
	public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0 ; i < words.length ; i++){
            map.put(words[i],i);
        }
        for(int i = 0 ; i < words.length ; i++){
            // notice it should be "j <= words[i].length()", Consider the test case of ["a", ""];
            for(int j = 0 ; j <= words[i].length() ; j++){
                String str1 = words[i].substring(0,j);
                String str2 = words[i].substring(j);
                if(isPalindrome(str1)){
                    String str2vrs = new StringBuilder(str2).reverse().toString();
                    if(map.containsKey(str2vrs) && map.get(str2vrs) != i){
                        result.add(Arrays.asList(map.get(str2vrs),i));
                    }
                }
                // check "str.length() != 0" to avoid duplicates ,(consider test case ["abcd", "dcba"])
                if(str2.length()!=0 && isPalindrome(str2)){
                    String str1vrs = new StringBuilder(str1).reverse().toString();
                    if(map.containsKey(str1vrs) && map.get(str1vrs) != i){
                        result.add(Arrays.asList(i,map.get(str1vrs)));
                    }
                }
            }
        }
        return result;
    }
    private boolean isPalindrome(String s){
        int left = 0,right = s.length()-1;
        while(left <= right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
    //方法2：
    //利用字典树Trie，主要思想和方法1一样，但是存储和查找words[i]和i的结构不用map，而是用字典树
    class Trie{
        int index;
        Trie[] children;
        Trie(){
            index = -1;
            children = new Trie[26];
        }
    }
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Trie root = new Trie();
        for(int i = 0 ; i < words.length ; i++){
            addIntoTrie(root,words[i],i);
        }
        for(int i = 0 ; i < words.length ; i++){
            // notice it should be "j <= words[i].length()", Consider the test case of ["a", ""];
            for(int j = 0 ; j <= words[i].length() ; j++){
                String str1 = words[i].substring(0,j);
                String str2 = words[i].substring(j);
                if(isPalindrome2(str1)){
                    String str2vrs = new StringBuilder(str2).reverse().toString();
                    int index = getIndexInTrie(root,str2vrs);
                    if(index >= 0 && index != i){
                        result.add(Arrays.asList(index,i));
                    }
                }
                // check "str.length() != 0" to avoid duplicates ,(consider test case ["abcd", "dcba"])
                if(str2.length()!=0 && isPalindrome2(str2)){
                    String str1vrs = new StringBuilder(str1).reverse().toString();
                    int index = getIndexInTrie(root,str1vrs);
                    if(index >= 0 && index != i){
                        result.add(Arrays.asList(i,index));
                    }
                }
            }
        }
        return result;
    }
    private void addIntoTrie(Trie root,String s,int index){
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(root.children[c-'a'] == null){
                root.children[c-'a'] = new Trie();
            }
            root = root.children[c-'a'];
        }
        root.index = index;
    }
    private int getIndexInTrie(Trie root,String s){
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(root.children[c-'a'] == null){
                return -1;
            }
            root = root.children[c-'a'];
        }
        return root.index;
    }
    private boolean isPalindrome2(String s){
        int left = 0,right = s.length()-1;
        while(left <= right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
    
    //方法3：
    //方法2的最简单易懂的实现代码
    class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs3(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();
        // 每个单词添加到Trie中
        for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
        // 每个单词都遍历一遍
        for (int i = 0; i < words.length; i++) search(words, i, root, res);

        return res;
    }

    /**
     *  向root 中添加单词
     * @param root Trie 树
     * @param word 单词
     * @param index 单词在原数组中的下标
     */
    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.next[j] == null) root.next[j] = new TrieNode();
            if (isPalindrome(word, 0, i)) root.list.add(index);
            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    /**
     *  判断某个单词是否可以与Trie 中的单词构成 palindrome
     * @param words 单词数组
     * @param i 单词数组中某个单词的下标
     * @param root Trie 的 root
     * @param res 最终的List
     */
    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        // 遍历单词i 中的每个字符
        for (int j = 0; j < words[i].length(); j++) {
            // 判断条件满足三点：
            // 1） Trie已经遍历到了叶子结点
            // 2） 该节点不是words[i]单词
            // 3） words[i]单词从j位置到最后，是回文
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        // 当Trie节点较长时，加入List中
        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    /**
     *  判断当前的单词是否为回文
     * @param word 指定的单词
     * @param i 左下标
     * @param j 右下标
     * @return
     */
    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }
}
