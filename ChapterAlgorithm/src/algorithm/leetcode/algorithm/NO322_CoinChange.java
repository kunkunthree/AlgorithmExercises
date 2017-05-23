package algorithm.leetcode.algorithm;
/*
 * medium
 * 322. Coin Change
 *  You are given coins of different denominations and a total amount of money amount. 
 *  Write a function to compute the fewest number of coins that you need to make up that amount. 
 *  If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin. 
 */
import java.util.*;
public class NO322_CoinChange {
	public static void main(String[] args) {
		int amount = 100;
		int[] coins = new int[]{1,2,5};
		System.out.println(coinChange2(coins, amount));
	}
	//方法1：
	//完全背包问题，dp，动态规划
	//f[i][v]=max{f[i-1][v-k*c[i]]+k*w[i]|0<=k*c[i]<=v}
	//迭代实现，O(n*m)time,O(amount)space
	public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for(int i = 1 ; i <= amount ; i++){
            dp[i] = -1;
        }
        for(int i = 1 ; i <= amount ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i-coins[j] >= 0 && dp[i-coins[j]] >= 0){
                    if(dp[i] == -1){
                        dp[i] = dp[i-coins[j]]+1;
                    }else{
                        dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                    }
                }
            }
        }
        return dp[amount];
    }
	//方法2：
	//递归，使用HashMap存储中间结果，减少不必要的计算
	static Map<Integer,Integer> map = new HashMap<>();
    public static  int coinChange2(int[] coins, int amount) {
        if(amount == 0){
            return 0;
        }
        if(amount < 0){
            return -1;
        }
        if(map.containsKey(amount)){
            return map.get(amount);
        }
        int rest = amount+1;
        int n = coins.length;
        for(int j = 0 ; j < n ; j++){
            int next = coinChange2(coins,amount - coins[j])+1;
            if(next > 0){
                rest = Math.min(rest,next);
            }
        }
        int result = rest == amount+1 ? -1 : rest;
        map.put(amount,result);
        return result;
    }
}
