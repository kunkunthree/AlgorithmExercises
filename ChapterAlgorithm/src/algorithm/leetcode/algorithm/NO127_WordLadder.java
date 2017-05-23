package algorithm.leetcode.algorithm;
/*
 * medium
 * 127. Word Ladder
 *  Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]

As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings).
 Please reload the code definition to get the latest changes. 
 */
import java.util.*;
public class NO127_WordLadder {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
	}
	//方法1：
	//Dijkstra算法，寻找最短路径，因为每一步路径权值都为1，那么最先达到的肯定是最短路径
	//所以到达某个字符串后，可以从wordList中删除
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> reached = new HashSet<String>(),toAdd;
        reached.add(beginWord);
        Set<String> wordSet = new HashSet<String>(wordList);
        int distance = 1;
        while(!reached.contains(endWord)){
            toAdd = new HashSet<String>();
            for(String each : reached){
                char[] c = each.toCharArray();
                //遍历改变字符串中任意一个字符，寻找wordList中存在的字符串
                for(int i = 0 ; i < c.length ; i++){
                    char tmp = c[i];
                    for(char ch = 'a' ; ch <= 'z' ; ch++){
                        c[i] = ch;
                        String s = new String(c);
                        //wordList中存在该字符串，那么将其添加到下一步需要执行的set中，并从wordSet中移除
                        if(wordSet.contains(s)){
                            toAdd.add(s);
                            wordSet.remove(s);
                        }
                    }
                    //恢复该位置的字符，以免遍历下一位置时改变的字符不止1个
                    c[i] = tmp;
                }
            }
            if(toAdd.size() == 0){
                return 0;
            }
            distance++;
            reached = toAdd;
        }
        return distance;
    }
}
