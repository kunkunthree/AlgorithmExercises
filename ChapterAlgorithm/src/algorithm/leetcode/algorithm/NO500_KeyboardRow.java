package algorithm.leetcode.algorithm;
/*
 * easy
 * 500. Keyboard Row
 * Given a List of words, return the words that can be typed using letters of alphabet 
 * on only one row's of American keyboard like the image below. 
 * 
 * Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

Note:

    You may use one character in the keyboard more than once.
    You may assume the input string will only contain letters of alphabet.

 */
import java.util.*;
import java.io.*;
public class NO500_KeyboardRow {
	public static void main(String[] args) {
	}
	//把每行字符映射到同一个数字，扫描字符串，如果所有相邻两个字符映射的数字都相等，则符合，否则不符合
    public String[] findWords(String[] words) {
        char[][] rows = new char[][]{{'q','w','e','r','t','y','u','i','o','p'},{'a','s','d','f','g','h','j','k','l'},{'z','x','c','v','b','n','m'}};
        int[] map = new int[26];
        for(int i = 0 ; i < rows.length ; i++){
            for(int j = 0 ; j < rows[i].length ; j++){
                map[rows[i][j]-'a'] = i;
            }
        }
        List<String> list = new ArrayList<String>();
        for(String s : words){
            int j = 0;
            while(j < s.length()-1){
                if(map[Character.toLowerCase(s.charAt(j))-'a']!=map[Character.toLowerCase(s.charAt(j+1))-'a']){
                    break;
                }
                j++;
            }
            if(j == s.length()-1){
                list.add(s);
            }
        }
        String[] result = new String[list.size()];
        for(int i = 0 ; i < result.length ; i++){
            result[i] = list.get(i);
        }
        return result;
    }
    //利用正则表达式,java8 Stream
//    public String[] findWords2(String[] words) {
//        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
//    }
}
