package algorithm.netease.game.internship2017;
/*
 * 1.
 * [编程题] 字符串编码:
 * 		给定一个字符串，请你将字符串重新编码，将连续的字符替换成“连续出现的个数+字符”。
 * 比如字符串AAAABCCDAA会被编码成4A1B2C1D2A。
输入描述:
		每个测试输入包含1个测试用例
		每个测试用例输入只有一行字符串，字符串只包括大写英文字母，长度不超过10000。
输出描述:
		输出编码后的字符串
输入例子:
AAAABCCDAA
输出例子:
4A1B2C1D2A
 */
import java.util.*;
public class StringEncoding {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String s = in.next();
			System.out.println(stringEncoding(s));
		}
	}
	public static String stringEncoding(String s){
		if(s == null || s.length() == 0){
			return null;
		}
		int  length = s.length();
		StringBuilder result = new StringBuilder();
		char tmp = s.charAt(0);
		int count = 1;
		for(int i = 1 ; i < length ; i++){
			if(tmp != s.charAt(i)){
				result.append(count);
				result.append(tmp);
				tmp = s.charAt(i);
				count = 1;
			}else{
				count++;
			}
		}
		result.append(count);
		result.append(tmp);
		return result.toString();
	}
}
