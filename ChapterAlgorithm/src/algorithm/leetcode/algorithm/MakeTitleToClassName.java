package algorithm.leetcode.algorithm;
/*
 * 将复制的标题转换成类名：
 * 
 */
import java.util.*;
public class MakeTitleToClassName {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		while(in.hasNext()){
			s = in.nextLine();
			if(s == null || s.length() == 0)continue;
			System.out.println(getClassName(s));
		}
	}
	public static String getClassName(String s){
		String[] str = s.trim().split(" ");
		StringBuilder result = new StringBuilder("NO");
//		System.out.println(Arrays.toString(str[0].split("\\.")));
		str[0] = str[0].split("\\.")[0];
		result.append(str[0]+"_");
		for(int i = 1 ; i < str.length ; i++){
			char head = Character.toUpperCase(str[i].charAt(0));
			str[i] = head+str[i].replace('-', '_').substring(1);
			result.append(str[i]);
		}
		return result.toString();
	}
}
