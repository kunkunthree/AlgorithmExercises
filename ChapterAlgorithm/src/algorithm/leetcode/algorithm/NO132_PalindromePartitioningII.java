package algorithm.leetcode.algorithm;
/*
 * hard
 * 132. Palindrome Partitioning II 
 *  Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
 */
public class NO132_PalindromePartitioningII {
	public static void main(String[] args) {
		String s = "aab";
		System.out.println(minCut(s));
	}
	//方法1：
	//dp，动态规划,O(n^2) space,O(n^2)time
	public static int minCut(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            dp[0][i] = Integer.MAX_VALUE;
            int min = Integer.MAX_VALUE;
            for(int left = i ; left >= 0; left--){
                if(s.charAt(left) == s.charAt(i) && (i - left <= 2 || dp[left+1][i-1] == 0)){
                    dp[left][i] = 0;
                    if(left > 0){
                        min = Math.min(min,1+dp[0][left-1]);
                    }
                }else{
                	dp[left][i] = 1+dp[left][i-1];
                }
            }
            dp[0][i] = Math.min(dp[0][i],min);
        }
        return dp[0][n-1];
    }
	//方法2：
	//效率比方法1高,O(n^2) space,O(n^2)time
	public int minCut2(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;  
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
	//方法3：
	//O(n) space,O(n^2)time
	public int minCut3(String s) {
        if(s == null || s.isEmpty()) return 0;
        int[] cut = new int[s.length()];
        //设置[0,i]位置分隔得到回文字符的最大次数
        for(int i = 0; i < cut.length; i++) cut[i] = i;
        //从某一个位置向两边扩散，寻找最长的回文字符串
        for(int i = 0; i < s.length(); i++){
            cutting(s, i, i, cut);	//奇数个字符
            cutting(s, i, i+1, cut);		//偶数个字符
        }
        return cut[s.length() - 1];
    }
    
    public void cutting(String s, int i, int j, int[] cut) {
        while(i >= 0 && j < s.length()) {
            if(s.charAt(i) != s.charAt(j)) {
                break;
            } else {
                if(i == 0) cut[j] = 0; 
                else if(i <= j) {  // i > 0
                    cut[j] = Math.min(cut[j], cut[i-1] + 1);
                }
                i--;
                j++;
            }
        }
    }
}
