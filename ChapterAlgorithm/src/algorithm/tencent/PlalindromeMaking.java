package algorithm.tencent;
import java.io.InputStreamReader;
/*
 * [编程题] 构造回文
给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
输出需要删除的字符个数。
输入描述:
		输入数据有多组，每组包含一个字符串s，且保证:1<=s.length<=1000.
输出描述:
		对于每组数据，输出一个整数，代表最少需要删除的字符个数。
输入例子:
		abcda
		google
输出例子:
		2
		2

解题思路：
  （1）把字符串旋转形成另外一个字符串，称为旋转字符串；
  （2）求原字符串s1与旋转字符串s2中，最大公共子序列的长度；
  （3）删除的字符数目 = 原字符串的长度 - 最大公共子序列的长度。
需要解决的子问题：
   求两个字符串s1和s2中最大公共子序列的长度。
   子问题的求解方式：动态规划
     设 MaxLen(i,j)表示s1左边i个字符与s2左边j个字符的最大公共子序列长度，
     则子问题的解为MaxLen(strlen(s1),strlen(s2));
     MaxLen(i,j)的求解方式为：
       若s1第i个字符与s2第j个字符相匹配，则 return 1+MaxLen(i-1,j-1);
       否则：return max(MaxLen(i-1,j),MaxLen(i,j-1))
    边界条件：
    MaxLen(i,n)=0; for n in 0 to strlen(s2)
    MaxLen(n,j)=0; for n in 0 to strlen(s1)
 */
import java.util.*;
public class PlalindromeMaking {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		System.out.println(getDeleteNum(s));
//		ArrayList<Integer> results = new ArrayList<Integer>();
//		while(in.hasNext()){
//			String str = in.nextLine();
//			results.add(getDeleteNum(str));
//		}
//		for(int i = 0 ; i < results.size() ; i++){
//			System.out.println(results.get(i));
//		}
	}
	public static int getDeleteNum(String s){
//		System.out.println(reverse(s));
		return s.length() - getLongestCommonSubsequenceLength(s, reverse(s));
	}
	public static String reverse(String s){
		int len = s.length();
		StringBuilder result = new StringBuilder();
		for(int i = 0 ; i < len ; i++ ){
			result.append( s.charAt(len-i-1));
		}
		return result.toString();
	}
	public static int getLongestCommonSubsequenceLength(String  s1,String s2){
		return getLongestCommonSubsequenceLength(s1.toCharArray(), s2.toCharArray());
	}
	public static int getLongestCommonSubsequenceLength(char[]  s1,char[] s2){
		int len1 = s1.length;
		int len2 = s2.length;
		int[][] c = new int[len1+1][len2+1];
		for(int i = 0 ; i <= len1 ; i++){
			for(int j = 0 ; j <= len2 ; j++){
				if(i == 0 || j == 0){
					c[i][j] = 0;
				}else if (s1[i-1] == s2[j-1]){
					c[i][j] = c[i-1][j-1] + 1;
				}else{
					c[i][j] = c[i][j-1] > c[i-1][j] ? c[i][j-1] : c[i-1][j];
				}
			}
		}
		return c[len1][len2];
	}

}
