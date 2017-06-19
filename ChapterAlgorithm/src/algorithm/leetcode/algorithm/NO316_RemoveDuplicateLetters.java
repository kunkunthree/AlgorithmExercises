package algorithm.leetcode.algorithm;
/*
 * hard
 * 316. Remove Duplicate Letters 
 *  Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once 
 *  and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:

Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb" 
 */
import java.util.*;
public class NO316_RemoveDuplicateLetters {
	//方法1：
	//递归
	//找到尽可能的字母序排在前的首字母，然后把该字母之后重复的该字母除去后递归寻找剩余字符串的首字母
	public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            count[s.charAt(i)-'a']++;
        }
        int pos = 0;
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            if(c < s.charAt(pos)){
                pos = i;
            }
            if(--count[c-'a'] == 0){	//count[c-'a'] == 0表明后面该字母没有再重复了，即后面的字符串缺少了该字母
                break;
            }
        }
        return s.length() == 0 ?  ""  : 
        	s.charAt(pos) + removeDuplicateLetters(s.substring(pos+1).replaceAll(s.charAt(pos)+"",""));
    }
	//方法2：
	//迭代，利用stack
	//先对每个字符进行计数
	//然后用stack进行遍历整个字符串，用一个布尔数组表示字符是否在stack中，当前遍历到的字符c
	//遇到的字符c，如果不在栈中，则与栈顶元素比较，
	//								栈顶元素不断出栈直到stack为空或者栈顶元素比c小或者字符串后续已经没有栈顶元素的字符
	//								将c压进栈，所以栈中元素一定比起下方元素要大或者下方元素在后续已经不会再出现
	//							如果字符c已经在栈中，则直接跳到下一个循环，指向下一个字符
	public String removeDuplicateLetters2(String s) {
        int[] count = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            count[s.charAt(i)-'a']++;
        }
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < s.length() ; i++){
            char c = s.charAt(i);
            count[c-'a']--;
            if(visited[c-'a'] == true){
                continue;
            }
            while(!stack.isEmpty() && c < stack.peek() && count[stack.peek()-'a'] > 0){
                visited[stack.pop()-'a'] = false;
            }
            stack.push(c);
            visited[c-'a'] = true;
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.insert(0,stack.pop());
        }
        return result.toString();
    }
}
