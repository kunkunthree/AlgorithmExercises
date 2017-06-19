package algorithm.leetcode.algorithm;
/*
 * hard
 * 140. Word Break II 
 *  Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 *  add spaces in s to construct a sentence where each word is a valid dictionary word. 
 *  You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). 
Please reload the code definition to get the latest changes. 
 */
import java.util.*;
public class NO140_WordBreakII {
	public static void main(String[] args) {
		String s = "catsanddog";
		String[] ss = new String[]{"cat","cats","and","sand","dog"};
		List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
		System.out.println(wordBreak(s, wordDict));
	}
	//方法1：
	//dp，动态规划
	public static List<String> wordBreak(String s, List<String> wordDict) {
		List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0){
            return result;
        }
        int n = s.length();
        List<Integer>[] lists = new List[n+1];
        for(int i = 0 ; i <= n ; i++){
            lists[i] = new ArrayList<Integer>();
        }
        boolean[] reach = new boolean[n+1];
        reach[0] = true;
        for(int i = 0 ; i < n ; i++){
            if(reach[i] == true){
                for(String tmp : wordDict){
                    if(i+tmp.length() <= n && s.substring(i,i+tmp.length()).equals(tmp)){
                        reach[i+tmp.length()] = true;
                        lists[i+tmp.length()].add(i);
                    }
                }
            }
        }
        backtrack(result,s,new StringBuilder(),n,lists);
        return result;
    }
    private static void backtrack(List<String> result,String s,StringBuilder builder,int cur,List<Integer>[] lists){
    	if(cur == 0){
            result.add(builder.substring(1,builder.length()));
            return;
        }
        for(int pre : lists[cur]){
            builder.insert(0," " + s.substring(pre,cur));
            backtrack(result,s,builder,pre,lists);
            builder.delete(0,cur-pre+1);
        }
    }
    //方法2：
    //DFS
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }       
    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s)) 
            return map.get(s);
            
        LinkedList<String>res = new LinkedList<String>();     
        if (s.length() == 0) {
            res.add("");
            return res;
        }               
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) 
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
            }
        }       
        map.put(s, res);
        return res;
    }
}
