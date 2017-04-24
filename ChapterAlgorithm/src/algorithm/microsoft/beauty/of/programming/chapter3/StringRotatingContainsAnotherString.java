package algorithm.microsoft.beauty.of.programming.chapter3;
import java.io.InputStreamReader;
/*
 * 3.1	字符串移位包含问题：
 * 		给定两个字符串s1和s2，要求判定s2是否能够通过s1作循环移位得到的字符串包含。
 */
import java.util.*;
public class StringRotatingContainsAnotherString {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] s1 = in.next().toCharArray();
		char[] s2 = in.next().toCharArray();
		System.out.println(isRotatinglyContains(s1, s2)+"");
	}
	//判断s2是否能通过s1循环移位得到的字符串包含
	//利用了“提高空间复杂度来换取时间复杂度的降低”的思路，适用于对时间复杂度要求高的场合
	public static boolean isRotatinglyContains(char[] s1,char[] s2){
		int s1Length = s1.length;
		int s2Length = s2.length;
		if(s1Length < s2Length){
			return false;
		}
		boolean result = false;
		char[] tmp = new char[s1Length<<1];
		for(int i = 0 ; i < tmp.length ; i++){
			tmp[i] = s1[i%s1Length];
		}
		int index = 0;
		for(int i = 0 ; i < s1Length ; i++){
			if(tmp[i] == s2[0]){
				index = 0;
				while(index < s2Length && tmp[index+i] == s2[index]){
					index++;
				}
				if(index == s2Length){
					return true;
				}
			}
		}
		return false;
	}
}
