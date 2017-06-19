package algorithm.leetcode.algorithm;
/*
 * hard
 * 123. Best Time to Buy and Sell Stock III 
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
import java.util.*;
public class NO123_BestTimeToBuyAndSellStockIII {
	//方法1：
	//dp，动态规划，节省空间写法，O(n*k)time，O(k)space
	public int maxProfit(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[] buy = new int[k+1];
        Arrays.fill(buy,Integer.MIN_VALUE);
        int[] sell = new int[k+1];
        for(int i = 0 ; i < n ; i++){
            for(int j = 1 ; j <= k ; j++){
                if(buy[j] < sell[j-1] - prices[i]){
                    buy[j] = sell[j-1] - prices[i];
                }
                if(sell[j] < buy[j] + prices[i]){	//如果当天买当天卖，相当于增加0次
                    sell[j] = buy[j] + prices[i];
                }
            }
        }
        return sell[k];
    }
	//方法2：
	//dp，动态规划，非节省空间写法,O(n*k)time，O(n*k)space
	public int maxProfit2(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][] buy = new int[k+1][n+1];
        for(int i = 0 ; i < k+1 ; i++){
            Arrays.fill(buy[i],Integer.MIN_VALUE);
        }
        int[][] sell = new int[k+1][n+1];
        for(int i = 0 ; i < n ; i++){
            for(int j = 1 ; j <= k ; j++){
                buy[j][i+1] = Math.max(buy[j][i],sell[j-1][i] - prices[i]);
                sell[j][i+1] = Math.max(sell[j][i],buy[j][i+1] + prices[i]);
            }
        }
        return sell[k][n];
    }
}
