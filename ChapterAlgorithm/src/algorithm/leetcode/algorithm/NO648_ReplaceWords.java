package algorithm.leetcode.algorithm;
/*
 * medium
 * 648. Replace Words 
 *  In English, we have a concept called root, which can be followed by some other words to 
 *  form another longer word - let's call this word successor. 
 *  For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence.
 You need to replace all the successor in the sentence with the root forming it. 
 If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Note:
    1.		The input will only have lower-case letters.
    2.		1 <= dict words number <= 1000
    3.		1 <= sentence words number <= 1000
    4.		1 <= root length <= 100
    5.		1 <= sentence words length <= 1000

similar problems:
208. Implement Trie (Prefix Tree) 
 */
import java.util.*;
public class NO648_ReplaceWords {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
	}
	//方法1：
	//利用字典树Trie记录字典中最短的根，然后遍历句子中所有单词
	//如果单词的子字符串在字典树中没有对应的根，则返回单词本身，如果有则返回这个根
	class Trie{
        public Trie[] children;
        public boolean isWord;
        public String word;
        public Trie(){
            children = new Trie[26];
            isWord = false;
            word = "";
        }
    }
    public String replaceWords(List<String> dict, String sentence) {
        if(sentence == null || sentence.length() == 0){
            return sentence;
        }
        Trie root = new Trie();
        for(String s : dict){
            updateRoot(root,s);
        }
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s : words){
            sb.append(getRoot(root,s)+" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    public void updateRoot(Trie root,String s){
        int n = s.length();
        Trie node = root;
        int i = 0;
        for( ; i < n ; i++){
            if(node.isWord == true){
                break;
            }else{
                char c = s.charAt(i);
                if(node.children[c-'a'] == null){
                    node.children[c-'a'] = new Trie();
                }
                node = node.children[c-'a'];
            }
        }
        if(i == n){
            node.isWord = true;
            node.word = s;
        }
    }
    public String getRoot(Trie root,String s){
        int n = s.length();
        for(int i = 0 ; i < n ; i++){
            if(root.isWord == true){
                return root.word;
            }else{
                char c = s.charAt(i);
                if(root.children[c-'a'] == null){
                    return s;
                }
                root = root.children[c-'a'];
            }
        }
        return s;
    }
    
    //方法2：
    //利用HashSet记录字典中所有单词，然后遍历句子中所有单词
    //从每个单词的第一个字母开始，判断以第一个字母开始的子字符串是否在Set中，如果在，则返回该子字符串，
    //否则范围整个单词
    public String replaceWords2(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0) return sentence;
        
        Set<String> set = new HashSet<>();
        for (String s : dict) set.add(s);
        
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split("\\s+");
        
        for (String word : words) {
            String prefix = "";
            for (int i = 1; i <= word.length(); i++) {
                prefix = word.substring(0, i);
                if (set.contains(prefix)) break;
            }
            sb.append(" " + prefix);
        }
        
        return sb.deleteCharAt(0).toString();
    }
}
