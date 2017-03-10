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
  （2）求原字符串s1与旋转字符串s2中，最长公共子串的长度；
  （3）删除的字符数目 = 原字符串的长度 - 最长公共子串的长度。
需要解决的子问题：
   求两个字符串s1和s2中最长公共子串的长度。
   子问题的求解方式：动态规划
     设 MaxLen(i,j)表示s1左边i个字符与s2左边j个字符的最长公共子串长度，
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
		ArrayList<Integer> results = new ArrayList<Integer>();
		while(in.hasNext()){
			char[] str = in.nextLine().toCharArray();
//			System.out.println(min(getDeleteNum(str),getDeleteNum(getReverseCharArray(str))));
			results.add(min(getDeleteNum(str),getDeleteNum(getReverseCharArray(str))));
		}
		for(int i = 0 ; i < results.size() ; i++){
			System.out.println(results.get(i));
		}
	}
	public static int getDeleteNum(char[] str){
		int left = 0,right = str.length-1;
		int num = 0;
		while(left<right){
			while(left<right && str[left] != str[right]){
				right--;
				num++;
			}
			right--;
			left++;
			while(left<right && str[left] != str[right]){
				left++;
				num++;
			}
			right--;
			left++;
		}
		return num;
	}
	public static int min(int x,int y){
		return x < y ? x : y;
	}
	public static char[] getReverseCharArray(char[] str){
		char[] tmp = new char[str.length];
		for(int i = 0 ; i < str.length ; i++){
			tmp[i] = str[str.length-1-i];
		}
		return tmp;
	}
}
