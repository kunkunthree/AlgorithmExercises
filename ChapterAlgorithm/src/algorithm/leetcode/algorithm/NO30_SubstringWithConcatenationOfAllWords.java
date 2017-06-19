package algorithm.leetcode.algorithm;
/*
 * hard
 * 30. Substring with Concatenation of All Words 
 *  You are given a string, s, and a list of words, words, that are all of the same length. 
 *  Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 *   and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter). 
 */
import java.util.*;
public class NO30_SubstringWithConcatenationOfAllWords {
	public static void main(String[] args) {
		String s = "aaaaaa";
		String[] words = new String[]{"aaa","aaa"};
		System.out.println(findSubstring(s, words));
	}
	public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if(s == null || words == null || words.length == 0 
            || s.length() < words.length * words[0].length()){
            return list;
        }
        int wordLength = words[0].length(),wordsNum = words.length,index,start;
        int[] count = new int[wordLength];
        String endWord,startWord;
        HashMap<String,Integer>[] maps = new HashMap[wordLength];
        for(int i = 0 ; i < wordLength ; i++){
            maps[i] = new HashMap<String,Integer>();
        }
        for(String word : words){
            for(HashMap<String,Integer> map : maps){
                if(!map.containsKey(word)){
                    map.put(word,0);
                }
                map.put(word,map.get(word)+1);
            }
        }
        for(int i = 0 ; i < s.length() ; i++){
            if(i+1-wordLength >= 0){
                endWord = s.substring(i+1-wordLength,i+1);
                index = (i+1)%wordLength;
                if(!maps[index].containsKey(endWord)){
                    maps[index].put(endWord,0);
                }
                if(maps[index].get(endWord) > 0){
                    count[index]++;
                }
                maps[index].put(endWord,maps[index].get(endWord)-1);
                start = i+1 - wordLength * wordsNum;
                if(count[index] == wordsNum){
                    list.add(start);
                }
                if(start >= 0){
                    startWord = s.substring(start,start+wordLength);
                    if(maps[index].get(startWord) >= 0){
                        count[index]--;
                    }
                    maps[index].put(startWord,maps[index].get(startWord)+1);
                }
            }
        }
        return list;
    }
	//方法2：
	public List<Integer> findSubstring2(String s, String[] words) {
		int N = s.length();
		List<Integer> indexes = new ArrayList<Integer>(s.length());
		if (words.length == 0) {
			return indexes;
		}
		int M = words[0].length();
		if (N < M * words.length) {
			return indexes;
		}
		int last = N - M + 1;
		
		//map each string in words array to some index and compute target counters
		Map<String, Integer> mapping = new HashMap<String, Integer>(words.length);
		int [][] table = new int[2][words.length];
		int failures = 0, index = 0;
		for (int i = 0; i < words.length; ++i) {
			Integer mapped = mapping.get(words[i]);
			if (mapped == null) {
				++failures;
				mapping.put(words[i], index);
				mapped = index++;
			}
			++table[0][mapped];
		}
		
		//find all occurrences at string S and map them to their current integer, -1 means no such string is in words array
		int [] smapping = new int[last];
		for (int i = 0; i < last; ++i) {
			String section = s.substring(i, i + M);
			Integer mapped = mapping.get(section);
			if (mapped == null) {
				smapping[i] = -1;
			} else {
				smapping[i] = mapped;
			}
		}
		
		//fix the number of linear scans
		for (int i = 0; i < M; ++i) {
			//reset scan variables
			int currentFailures = failures; //number of current mismatches
			int left = i, right = i;
			Arrays.fill(table[1], 0);
			//here, simple solve the minimum-window-substring problem
			while (right < last) {
				while (currentFailures > 0 && right < last) {
					int target = smapping[right];
					if (target != -1 && ++table[1][target] == table[0][target]) {
						--currentFailures;
					}
					right += M;
				}
				while (currentFailures == 0 && left < right) {
					int target = smapping[left];
					if (target != -1 && --table[1][target] == table[0][target] - 1) {
						int length = right - left;
						//instead of checking every window, we know exactly the length we want
						if ((length / M) ==  words.length) {
							indexes.add(left);
						}
						++currentFailures;
					}
					left += M;
				}
			}
			
		}
		return indexes;
	}
}
