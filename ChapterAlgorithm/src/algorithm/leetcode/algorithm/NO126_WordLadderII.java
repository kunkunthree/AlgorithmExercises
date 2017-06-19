package algorithm.leetcode.algorithm;
/*
 * hard
 * 126. Word Ladder II 
 *  Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation 
 *  sequence(s) from beginWord to endWord, such that:

    Only one letter can be changed at a time
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]

Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]

Note:

    Return an empty list if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings). 
Please reload the code definition to get the latest changes. 
 */
import java.util.*;
public class NO126_WordLadderII {
	public static void main(String[] args) {
		String beginWord = "hit",endWord = "cog";
		List<String> wordList = new ArrayList<String>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(findLadders(beginWord, endWord, wordList));
	}
	//方法1：
	//BFS
	//1.用1个map记录最短路径长度
	//2.遍历每个位置的26个字母，寻找所有可能的路径
	//3.每一个单词只允许加入到queue中一次
	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if(wordList == null || wordList.size() == 0){
            return results;
        }
        Map<String,List<String>> map = new HashMap<>();
        Map<String,Integer> ladder = new HashMap<>();
        int min = Integer.MAX_VALUE;
        Queue<String> queue = new LinkedList<>();
        for(String s : wordList){
            ladder.put(s,Integer.MAX_VALUE);
        }
        ladder.put(beginWord,0);
        queue.offer(beginWord);
      //BFS: Dijisktra search
        while(!queue.isEmpty()){
            String word = queue.poll();
            int step = ladder.get(word)+1;		//'step' indicates how many steps are needed to travel to one word. 
            if(step > min){
                break;
            }
            for(int i = 0 ; i < word.length() ; i++){
                StringBuilder builder = new StringBuilder(word);
                for(char ch='a';  ch <= 'z'; ch++){
                    builder.setCharAt(i,ch);
					String new_word=builder.toString();	
					if(ladder.containsKey(new_word)){
					    if(step > ladder.get(new_word)){		//Check if it is the shortest path to one word.
					        continue;
					    }else if(step < ladder.get(new_word)){
					        ladder.put(new_word,step);
					        queue.offer(new_word);
					    };// It is a KEY line. If one word already appeared in one ladder,
					          // Do not insert the same word inside the queue twice. Otherwise it gets TLE.
					    if (map.containsKey(new_word)) //Build adjacent Graph
					    	map.get(new_word).add(word);
					    else{
					    	List<String> list= new LinkedList<String>();
					    	list.add(word);
					    	map.put(new_word,list);
					    }
					    if (new_word.equals(endWord))
					    	min=step;
					}//End if dict contains new_word
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
        }//End While
        LinkedList<String> result = new LinkedList<String>();
		backTrace(endWord,beginWord,result,results,map);
        return results;
    }
    private static void backTrace(String word,String start,List<String> list,List<List<String>> results,Map<String,List<String>> map){
    	if (word.equals(start)){
    		list.add(0,start);
    		results.add(new ArrayList<String>(list));
    		list.remove(0);
    		return;
    	}
    	list.add(0,word);
    	if (map.get(word)!=null)
    		for (String s:map.get(word))
    			backTrace(s,start,list,results,map);
    	list.remove(0);
    }
    
    //方法2：
    //用两个指针
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();
        Set<String> dict=new HashSet<>();
        dict.addAll(wordList);
        
        List<List<String>> ListArray=new ArrayList<>();
        if(!dict.contains(endWord)) return ListArray;
        
        set1.add(beginWord);
        set2.add(endWord);
        
        Map<String, Set<String>> map=new HashMap<>();
        bfs(dict,set1,set2,map,true);
        
        List<String> reg=new ArrayList<>();
        reg.add(beginWord);
        
        dfs(map,ListArray,reg,beginWord,endWord);    
        return ListArray;
        
    }
    public void dfs(Map<String,Set<String>> map,List<List<String>> ListArray,List<String> list,String start,String end){
        if(start.equals(end)){
            ListArray.add(new ArrayList<>(list));
        }else{
            if(!map.containsKey(start)) return;
            for(String ele:map.get(start)){
                list.add(ele);
                dfs(map,ListArray,list,ele,end);
                list.remove(list.size()-1);
            }
        }
    }
    public void bfs(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, Set<String>> map,boolean forward){
        //if(set1.isEmpty()) return;
        if(set1.size()>set2.size()){
            bfs(dict,set2,set1,map,!forward);
            return;
        }
        for(String ele:set1){
            dict.remove(ele);
        }
        
        Set<String> next=new HashSet<>();
        boolean connected=false;
        for(String ele:set1){
            char[] array=ele.toCharArray();
            
            for(int i=0;i<array.length;i++){
                char a = array[i];
                for(char c='a';c<='z';c++){
                    array[i]=c;
                    String temp=new String(array);
                    
                    if(dict.contains(temp)||set2.contains(temp)){
                        if(set2.contains(temp)){
                            connected=true;
                        }else{
                            next.add(temp);
                        }
                        
                       String key=(forward==true)?ele:temp;
                       String value=(forward==true)?temp:ele;
                    
                       if(!map.containsKey(key)){
                           map.put(key,new HashSet<String>());
                       } 
                       map.get(key).add(value);
                       next.add(temp);
                    }
                    
                }
                array[i]=a;
            }
        }
        if(connected==true|| next.isEmpty()) return;
        else{
            bfs(dict,next,set2,map,forward);
        }
    }
}
