package algorithm;
/*
 * 求最长公共子序列：
 * 		最长公共子序列不要求字符是连续的。
 * 例如：
 * 		cnblogs和belong的最长公共子序列是blog
 * 求解算法：
 * 		假设Z=<z1,z2,⋯,zk>是X与Y的LCS， 我们观察到
 * 如果xm=yn，则zk=xm=yn，有Zk−1是Xm−1与Yn−1的LCS；
 * 如果xm≠yn，则Zk是Xm与Yn−1的LCS，或者是Xm−1与Yn的LCS。
 * 因此，求解LCS的问题则变成递归求解的两个子问题。
 * 但是，上述的递归求解的办法中，重复的子问题多，效率低下。
 * 改进的办法——用空间换时间，用数组保存中间状态，方便后面的计算。
 * 这就是动态规划（DP)的核心思想了。
 * DP求解LCS:
 * 		用二维数组c[i][j]记录串x1x2⋯xi与y1y2⋯yj的LCS长度，则可得到状态转移方程.
 * 	c[i][j] = {				0,							i = 0或j = 0
 * 					c[i-1][j-1] + 1, 				i,j>0且x[i] = y[j]
 * 				max(c[i-1][j],c[i][j-1])		i,j > 0 且x[i] != y[j]
 * 				}
 * 	
 */
public class LongestCommonSubsequence {
	public static void main(String[] args) {
		System.out.println(getLongestCommonSubsequenceLength("cnblogs", "belong"));
		System.out.println(getLongestCommonSubsequenceString("cnblogs", "belong"));
	}
	//求最长公共子序列长度
	public static int getLongestCommonSubsequenceLength(String s1,String s2){
		return getLongestCommonSubsequenceLength(s1.toCharArray(), s2.toCharArray());
	}
	//求最长公共子序列长度
	public static int getLongestCommonSubsequenceLength(char[] s1,char[] s2){
		int len1 = s1.length;
		int len2 = s2.length;
		int[][] c = new int[len1+1][len2+1];
		for(int i = 0 ; i <= len1 ; i++){
			for(int j = 0 ; j <= len2 ; j++){
				if(i == 0 || j ==0){
					c[i][j] = 0;
				}else if(s1[i-1] == s2[j-1]){
					c[i][j] = c[i-1][j-1] + 1;
				}else{
					c[i][j] = max(c[i-1][j],c[i][j-1]);
				}
			}
		}
		return c[len1][len2];
	}
	//求最长公共子序列的字符串
		public static String getLongestCommonSubsequenceString(String s1,String s2){
			return getLongestCommonSubsequenceString(s1.toCharArray(), s2.toCharArray());
		}
	//求最长公共子序列的字符串
	public static String getLongestCommonSubsequenceString(char[] s1,char[] s2){
		int len1 = s1.length;
		int len2 = s2.length;
		int[][] c = new int[len1+1][len2+1];
		char[][] b = new char[len1+1][len2+1];
		for(int i = 0 ; i <= len1 ; i++){
			for(int j = 0 ; j <= len2 ; j++){
				if(i == 0 || j ==0){
					c[i][j] = 0;
				}else if(s1[i-1] == s2[j-1]){
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = '↖';
				}else{
					if(c[i-1][j] >= c[i][j-1]){
						c[i][j] = c[i-1][j];
						b[i][j] = '↑';
					}else{
						c[i][j] = c[i][j-1];
						b[i][j] = '←';
					}
				}
			}
		}
		int length = c[len1][len2];
		String s = "";
		int i = len1,j = len2;
		while(i > 0 && j > 0){
			if(b[i][j] == '↖'){
				s = s1[i-1] + s;
				i--;j--;
			}else if (b[i][j] == '←'){
				j--;
			}else{
				i--;
			}
		}
		return s;
	}
	public static  int max(int...array){
		int tmp = Integer.MIN_VALUE;
		for(int i : array){
			if(i > tmp){
				tmp = i;
			}
		}
		return tmp;
	}
}
