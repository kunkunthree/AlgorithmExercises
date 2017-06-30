package algorithm.leetcode.algorithm;
/*
 * medium
 * 629. K Inverse Pairs Array 
 *  Given two integers n and k, find how many different arrays consist of numbers from 1 to n 
 *  such that there are exactly k inverse pairs.

We define an inverse pair as following: For ith and jth element in the array, 
if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.

Since the answer may very large, the answer should be modulo 109 + 7.

Example 1:

Input: n = 3, k = 0
Output: 1
Explanation: 
Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.

Example 2:

Input: n = 3, k = 1
Output: 2
Explanation: 
The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.

Note:
    The integer n is in the range [1, 1000] and k is in the range [0, 1000].

 */
public class NO629_KInversePairsArray {
	//方法1：
	//dp，动态规划
	//dp[n][k] 表示由1到n组成的数列能够构成k对逆序对的数列数
	//假设已知dp[n-1][j] , 0 <= j <= k-(n-1)
	//那么插入n，
	//把n插入到第1个位置，则此时能构成k对逆序对的数列数为dp[n-1][0]
	//把n插入到第2个位置，则此时能构成k对逆序对的数列数为dp[n-1][1]
	//...
	//把n插入到第n个位置，则此时能构成k对逆序对的数列数为dp[n-1][k-(n-1)]
	//即得到：dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k-(n-2)]+dp[n-1][k-(n-1)]
	//则dp[n][k+1] = dp[n-1][k+1]+dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k-(n-2)] 
	//通过错位相减：dp[n][k+1] - dp[n][k] = dp[n-1][k+1]-dp[n-1][k-(n-1)]
	//最终得到：dp[n][k+1] = dp[n][k]+dp[n-1][k+1]-dp[n-1][k+1-n]，k >= n-1
	
	//O(nk)space,O(nk)time
	public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        if(k > n*(n-1)*2 || k < 0){
            return 0;
        }
        if( k == 0 || k == n*(n-1)/2){
            return 1;
        }
        long[][] dp = new long[n+1][k+1];
        dp[2][0] = 1;
        dp[2][1] = 1;
        for(int i = 3 ; i <= n ; i++){
            dp[i][0] = 1;
            for(int j = 1 ; j <= Math.min(k,i*(i-1)/2) ; j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
                if(j >= i)dp[i][j] -= dp[i-1][j-i];
                dp[i][j] = (dp[i][j] + mod) % mod;
            }
        }
        return (int)dp[n][k];
    }
	//方法2：
	//O(nk)time,O(k)space
	public static int kInversePairs2(int n, int k) {   
		  int[] dp = new int[k+1];
		  dp[0] = 1; int mod = 1000000007;
		  for (int i=1;i<=n;i++) {
		      int[] temp = new int[k+1];
		      for (int j=0;j<=k;j++) {
		          if (j==0) temp[j] = 1;
		          else {
		            temp[j] = temp[j-1] + dp[j]; 
		            temp[j] %= mod;
		            if (j >= 1 + Math.min(i-1, j))  temp[j] += mod - dp[j-1-Math.min(i-1, j)];
		            temp[j] %= mod;
		          }
		      }
		      dp = temp;
		  }
		  return dp[k];
		}
}
