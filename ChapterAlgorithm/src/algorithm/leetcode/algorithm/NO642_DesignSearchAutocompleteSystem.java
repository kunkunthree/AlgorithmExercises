package algorithm.leetcode.algorithm;
/*
 * hard
 * 642. Design Search Autocomplete System
 * Design a search autocomplete system for a search engine. Users may input a sentence 
 * (at least one word and end with a special character '#'). 
 * For each character they type except '#', you need to return the top 3 historical hot sentences 
 * that have prefix the same as the part of sentence already typed. Here are the specific rules:
    1.		The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
    2.		The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). 
    3.		If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
    4.		If less than 3 hot sentences exist, then just return as many as you can.
    5.		When the input is a special character, it means the sentence ends, and in this case, 
    			you need to return an empty list.

Your job is to implement the following functions:
The constructor function:

AutocompleteSystem(String[] sentences, int[] times): 
		This is the constructor. The input is historical data. 
		Sentences is a string array consists of previously typed sentences. 
		Times is the corresponding times a sentence has been typed. 
		Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): 
		The input c is the next character typed by the user. 
		The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). 
		Also, the previously typed sentence should be recorded in your system. 
		The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. 
Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". 
Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. 
And the following input will be counted as a new search.

Note:
    1.		The input sentence will always start with a letter and end with '#', 
    			and only one blank space will exist between two words.
    2.		The number of complete sentences that to be searched won't exceed 100. 
    			The length of each sentence including those in the historical data won't exceed 100.
    3.		Please use double-quote instead of single-quote when you write test cases even for a character input.
    4.		Please remember to RESET your class variables declared in class AutocompleteSystem, 
    			as static/class variables are persisted across multiple test cases. Please see here for more details.

 */
import java.util.*;
public class NO642_DesignSearchAutocompleteSystem {
	public static void main(String[] args) {
		String[] sentences = new String[]{"i love you","island","iroman","i love leetcode"};
		int[] times = new int[]{5,3,2,2}; 
		NO642_DesignSearchAutocompleteSystem test = new NO642_DesignSearchAutocompleteSystem();
		AutocompleteSystem as = test.new AutocompleteSystem(sentences, times);
		System.out.println(as.input('i'));
		System.out.println(as.input(' '));
		System.out.println(as.input('a'));
		System.out.println(as.input('#'));
		
		System.out.println(as.input('i'));
		System.out.println(as.input(' '));
		System.out.println(as.input('a'));
		System.out.println(as.input('#'));
		
		System.out.println(as.input('i'));
		System.out.println(as.input(' '));
		System.out.println(as.input('a'));
		System.out.println(as.input('#'));
		
		System.out.println(as.input('i'));
		System.out.println(as.input(' '));
		System.out.println(as.input('a'));
		System.out.println(as.input('#'));
//		System.out.println(" ".compareTo("a"));
//		final Map<String,Integer> map = new HashMap<String, Integer>();
//		map.put("a", 1);
//		map.put("b", 2);
//		map.put("c", 3);
//		PriorityQueue<String> queue = new PriorityQueue<String>(1,new Comparator<String>(){
//            public int compare(String s1,String s2){
//                int count1 = map.containsKey(s1) ? map.get(s1) : 0;
//                int count2 = map.containsKey(s2) ? map.get(s2) : 0;
//                if(count1 == count2){
//                    return s1.compareTo(s2);
//                }
//                return count2 - count1;
//            }
//        });
//		queue.offer("a");
//		queue.offer("b");
//		queue.offer("c");
//		Iterator<String> it = queue.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
	}
	
	//方法1：
	//利用字典树，每一个节点都保存一个PriorityQueue，用来保存该路径下的字符串,根据map中的计数进行排序
	public class AutocompleteSystem {
	    class Trie{
	        Trie[] children;
	        PriorityQueue<String> queue;
	        public Trie(){
	            children = new Trie[27];
	            queue = new PriorityQueue<String>(1,new Comparator<String>(){
	                public int compare(String s1,String s2){
	                    int count1 = map.containsKey(s1) ? map.get(s1) : 0;
	                    int count2 = map.containsKey(s2) ? map.get(s2) : 0;
	                    if(count1 == count2){
	                        return s1.compareTo(s2);
	                    }
	                    return count2 - count1;
	                }
	            });
	        }
	    }
	    Map<String,Integer> map = new HashMap<>();
	    private PriorityQueue<String> queue;
	    private Trie root;
	    private Trie inputNode = null;
	    String input = "";
	    public AutocompleteSystem(String[] sentences, int[] times) {
	        int n = sentences.length;
	        for(int i = 0 ; i < n ; i++){
	            if(!map.containsKey(sentences[i])){
	                map.put(sentences[i],0);
	            }
	            map.put(sentences[i],map.get(sentences[i])+times[i]);
	        }
	        root = new Trie();
	        for(String s : map.keySet()){
	            if(map.get(s) > 0){
	                addIntoTrie(root,s);
	            }
	        }
	        inputNode = root;
	    }

	    private void addIntoTrie(Trie node,String s){
	        for(int i = 0 ; i < s.length() ; i++){
	            char c = s.charAt(i);
	            if(c >= 'a' && c <= 'z'){
	                if(node.children[c-'a'] == null){
	                    node.children[c-'a'] = new Trie();
	                }
	                node = node.children[c-'a'];
	            }else if(c == ' '){
	                if(node.children[26] == null){
	                    node.children[26] = new Trie();
	                }
	                node = node.children[26];
	            }
	            //这里删除后再添加是为了能按照更新后的计数重新排序
	            node.queue.remove(s);
	            node.queue.offer(s);
	        }
	    }

	    public List<String> input(char c) {
	        List<String> list = new ArrayList<>();
	        if(c >= 'a' && c <= 'z'){
	            input+=c;
	            if(inputNode != null)inputNode = inputNode.children[c-'a'];
	        }else if(c == ' '){
	            input+=c;
	            if(inputNode != null)inputNode = inputNode.children[26];
	        }else{
	            if(!map.containsKey(input)){
	                map.put(input,0);
	            }
	            map.put(input,map.get(input)+1);
	            addIntoTrie(root,input);
	            input = "";
	            inputNode = root;
	            return list;
	        }
	        if(inputNode == null){
	            return list;
	        }
	        for(int i = 0 ; i < 3 && !inputNode.queue.isEmpty() ; i++){
	            list.add(inputNode.queue.poll());
	        }
	        for(String s : list){
	            inputNode.queue.offer(s);
	        }
	        return list;
	    }
	}
}
