package algorithm.coding.interviews;

import java.util.Arrays;

/*
 * 翻转单词顺序
 */
public class NO_42_ReverseSentence {
	public static void main(String[] args) {
		String s  = "I am a student.";
		System.out.println(reverseSentence(s));
	}
	private static void reverse(char[] str,int start,int end){
		char tmp;
		while(start < end){
			tmp = str[start];
			str[start] = str[end];
			str[end] = tmp;
			start++;
			end--;
		}
	}
	public static String reverseSentence(String s){
		char[] str = s.toCharArray();
		int start = 0,end = 0,length = str.length;
		reverse(str, start, length-1);
		while(start < length){
			if(str[start] == ' '){
				start++;
				end++;
			}else{
				while(end < length && str[end] != ' ' ){
					end++;
				}
				reverse(str, start, end-1);
				start = end;
			}
		}
		return String.copyValueOf(str);
	}
}
