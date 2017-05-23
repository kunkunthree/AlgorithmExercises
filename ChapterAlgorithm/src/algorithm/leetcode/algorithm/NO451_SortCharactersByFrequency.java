package algorithm.leetcode.algorithm;
/*
 * medium
 *  451. Sort Characters By Frequency 
 *  Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input:
"tree"
Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input:
"cccaaa"
Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input:
"Aabb"
Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

 */
import java.util.*;
public class NO451_SortCharactersByFrequency {
	//方法1：
	//迭代，用map记录字符和出现频率，用list数组存储字符，下标为频率
	public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int length = s.length(),max = 0;
        char c;
        for(int i = 0 ; i < length ; i++){
            c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,0);
            }
            map.put(c,map.get(c)+1);
            max = Math.max(max,map.get(c)+1);
        }
        List<Character>[] list = new List[max+1];
        for(Character ch : map.keySet()){
            int index = map.get(ch);
            if(list[index] == null){
                list[index] = new ArrayList<>();
            }
            list[index].add(ch);
        }
        StringBuilder result = new StringBuilder();
        for(int i = max ; i > 0 ; i--){
            if(list[i] != null){
                for(char ch : list[i]){
                    for(int j = 0 ; j < i ; j++){
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
}
