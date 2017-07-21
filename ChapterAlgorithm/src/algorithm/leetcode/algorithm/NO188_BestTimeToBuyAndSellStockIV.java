package algorithm.leetcode.algorithm;
/*
 * hard
 * 188. Best Time to Buy and Sell Stock IV 
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

similar problems:
121. Best Time to Buy and Sell Stock 
122. Best Time to Buy and Sell Stock II
123. Best Time to Buy and Sell Stock III 
309. Best Time to Buy and Sell Stock with Cooldown  
 */
import java.util.*;
public class NO188_BestTimeToBuyAndSellStockIV {
	//方法1：
	//dp，动态规划，节省空间写法，O(n*k)time，O(k)space
	//DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
	//buy[i]表示第i次进行买操作后所能得到的最大值
	//sell[i]表示第i次进行卖操作后所得到的最大值
	//注意，当k >= n/2 时，相当于可以进行任意次买卖操作
	//所以当k >= n/2 时，直接求能获得最大的利益即可
	public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) return quickSolve(prices);
//        int[][] buy = new int[k+1][n+1];
//        for(int i = 0 ; i < k+1 ; i++){
//            Arrays.fill(buy[i],Integer.MIN_VALUE);
//        }
//        int[][] sell = new int[k+1][n+1];
        int[] buy = new int[k+1];
        Arrays.fill(buy,Integer.MIN_VALUE);
        int[] sell = new int[k+1];
        for(int i = 0 ; i < n ; i++){
            for(int j = 1 ; j <= k ; j++){
//                buy[j][i+1] = Math.max(buy[j][i],sell[j-1][i] - prices[i]);
//                sell[j][i+1] = Math.max(sell[j][i],buy[j][i+1] + prices[i]);
            	buy[j] = Math.max(buy[j],sell[j-1] - prices[i]);
                sell[j] = Math.max(sell[j],buy[j] + prices[i]);
            }
        }
//        return sell[k][n];
        return sell[k];
    }
    private int quickSolve(int[] prices){
        int result = 0;
        for(int i = 1 ; i < prices.length ; i++){
            if(prices[i] >= prices[i-1]){
                result+=prices[i]-prices[i-1];
            }
        }
        return result;
    }
}
