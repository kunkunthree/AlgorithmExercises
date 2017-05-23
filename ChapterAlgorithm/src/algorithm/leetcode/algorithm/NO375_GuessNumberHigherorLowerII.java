package algorithm.leetcode.algorithm;
/*
 * medium
 * 375. Guess Number Higher or Lower II
 * We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x.
 You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.

Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 */
public class NO375_GuessNumberHigherorLowerII {
	public static void main(String[] args) {
		System.out.println(getMoneyAmount(10));
	}
	//方法1：
	//dp，动态规划，自底向上
	//	Definition of dp[i][j]: minimum number of money to guarantee win for subproblem [i, j].
	//	Target: dp[1][n]
	//	Corner case: dp[i][i] = 0 (because the only element must be correct)
	//	Equation: we can choose k (i<=k<=j) as our guess, and pay price k.
	//	After our guess, the problem is divided into two subproblems. 
	//	Notice we do not need to pay the money for both subproblems. 
	//	We only need to pay the worst case (because the system will tell us which side we should go) to guarantee win. 
	//	So dp[i][j] = min (i<=k<=j) { k + max(dp[i][k-1], dp[k+1][j]) }
	public static int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        for(int j_i = 1 ; j_i < n ; j_i++){
            for(int i = 1 ; i+j_i <= n ; i++){
                int j = i + j_i;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i ; k <= j ; k++){
                    dp[i][j] = Math.min(dp[i][j],k+Math.max(k-1 >= i ? dp[i][k-1] : 0,k+1 <= j ? dp[k+1][j] : 0));
                }
            }
        }
        return dp[1][n];
    }
	//方法2：
	//递归
	public int getMoneyAmount2(int n) {
        int[][] table = new int[n+1][n+1];
        return DP(table, 1, n);
    }
    int DP(int[][] t, int s, int e){
        if(s >= e) return 0;
        if(t[s][e] != 0) return t[s][e];
        int res = Integer.MAX_VALUE;
        for(int x=s; x<=e; x++){
            int tmp = x + Math.max(DP(t, s, x-1), DP(t, x+1, e));
            res = Math.min(res, tmp);
        }
        t[s][e] = res;
        return res;
    }
}
