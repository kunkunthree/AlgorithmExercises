package algorithm;
/*
 * 藏宝图:
 * 		牛牛拿到了一个藏宝图，顺着藏宝图的指示，牛牛发现了一个藏宝盒，藏宝盒上有一个机关，
 * 机关每次会显示两个字符串 s 和 t，根据古老的传说，牛牛需要每次都回答 t 是否是 s 的子序列。
 * 注意，子序列不要求在原字符串中是连续的，例如串 abc，它的子序列就有 {空串, a, b, c, ab, ac, bc, abc} 8 种。
输入描述:
		每个输入包含一个测试用例。每个测试用例包含两行长度不超过 10 的不包含空格的可见 ASCII 字符串。
输出描述:
		输出一行 “Yes” 或者 “No” 表示结果。
输入例子:
x.nowcoder.com
ooo
输出例子:
		Yes
 */
import java.util.*;
public class TreasureMap {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		String t = in.nextLine();
		if(s.length() < t.length()){
			System.out.println("No");
		}else{
			int t_pos = 0;
			int s_pos = 0;
			while(s_pos < s.length() && t_pos < t.length()){
				if(s.charAt(s_pos) == t.charAt(t_pos)){
					t_pos++;
				}
				s_pos++;
			}
//			System.out.println("s_pos： " + s_pos + " , t_pos : " + t_pos);
//			System.out.println("s.length()： " + s.length() + " ,  t.length() : " +  t.length());
			if(s_pos <= s.length() && t_pos == t.length()){
				System.out.println("Yes");
			}else{
				System.out.println("No");
			}
		}
	}
}
