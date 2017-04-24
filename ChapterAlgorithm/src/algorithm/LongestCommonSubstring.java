package algorithm;
/*
 * 最长公共子串：
 * 		DP求解最长公共子串。
 * 		子串是一种特殊的子序列，因此同样可以用DP来解决。
 * 定义数组的存储含义对于后面推导转移方程显得尤为重要，糟糕的数组定义会导致异常繁杂的转移方程。
 * 考虑到子串的连续性，将二维数组c[i,j]用来记录具有这样特点的子串——
 * 结尾为母串x1x2⋯xi与y1y2⋯yj的结尾——的长度。
 * 得到转移方程：
 * c[i][j] = {		0,								i=0 or j = 0;
 * 					c[i-1][j-1]+1			x[i] = y[j];
 * 					0,								x[i] != y[j];
 * 				}
 * 最长公共子串的长度为 max(c[i,j]), i∈{1,⋯,m},j∈{1,⋯,n}。
 */
public class LongestCommonSubstring {
	public static void main(String[] args) {
		System.out.println(getLongestCommonSubstringLength("abcdeeeee", "gggcdeeeggg"));
		System.out.println(getLongestCommonSubstringString("abcdeeeee", "gggcdeeeggg"));
	}
	//最大公共子串长度
	public static int getLongestCommonSubstringLength(String s1,String s2){
		return getLongestCommonSubstringLength(s1.toCharArray(), s2.toCharArray());
	}
	//最大公共子串长度
	public static int getLongestCommonSubstringLength(char[] s1,char[] s2){
		int len1 = s1.length;
		int len2 = s2.length;
		int[][] c = new int[len1+1][len2+1];
		char[][] b = new char[len1+1][len2+1];
		int maxLength = 0;
		for(int i = 0 ; i <= len1 ; i++){
			for(int j = 0 ; j <= len2 ; j++){
				if(i == 0 || j == 0){
					c[i][j] = 0;
				}else if(s1[i-1] == s2[j-1]){
					c[i][j] = c[i-1][j-1] +1;
					if(c[i][j] > maxLength){
						maxLength = c[i][j];
					}
					b[i][j] = '↖';
				}else{
					c[i][j] = 0;
				}
			}
		}
		return maxLength;
	}
	//最大公共子串字符串
	public static String getLongestCommonSubstringString(String s1,String s2){
		return getLongestCommonSubstringString(s1.toCharArray(), s2.toCharArray());
	}
	public static String getLongestCommonSubstringString(char[] s1,char[] s2){
		int len1 = s1.length;
		int len2 = s2.length;
		int[][] c = new int[len1+1][len2+1];
		char[][] b = new char[len1+1][len2+1];
		int maxLength = 0;
		int maxX = 0,maxY = 0;
		for(int i = 0 ; i <= len1 ; i++){
			for(int j = 0 ; j <= len2 ; j++){
				if(i == 0 || j == 0){
					c[i][j] = 0;
				}else if(s1[i-1] == s2[j-1]){
					c[i][j] = c[i-1][j-1] +1;
					if(c[i][j] > maxLength){
						maxLength = c[i][j];
						maxX = i;
						maxY = j;
					}
					b[i][j] = '↖';
				}else{
					c[i][j] = 0;
				}
			}
		}
		int length = maxLength;
		String s = "";
		int i = maxX,j = maxY;
		while(length > 0){
			if(b[i][j] == '↖'){
				s = s1[i-1] + s;
				i--;j--;
				length--;
			}else if (b[i][j] == '←'){
				j--;
			}else{
				i--;
			}
		}
		return s;
	}
}
