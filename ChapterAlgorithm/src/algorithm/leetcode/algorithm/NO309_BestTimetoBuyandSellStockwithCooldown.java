package algorithm.leetcode.algorithm;
/*
 * medium
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]

similar problems:
121. Best Time to Buy and Sell Stock 
122. Best Time to Buy and Sell Stock II 
123. Best Time to Buy and Sell Stock III 
188. Best Time to Buy and Sell Stock IV 
 */
public class NO309_BestTimetoBuyandSellStockwithCooldown {
	//方法1：
	//动态规划，O(n^2)time
	public int maxProfit(int[] prices) {
        int n = prices.length,tmp;
        int[] dp = new int[n+1];
        for(int i = 0 ; i <= n ; i++){
            for(int j = i-1 ; j >= 0; j--){
                tmp = 0;
                if(i < n){
                    tmp = prices[i]-prices[j];
                }
                if(j-2 >= 0){
                    tmp += dp[j-2];
                }
                dp[i] = Math.max(dp[i],tmp);
                dp[i] = Math.max(dp[i],dp[j]);
            }
        }
        return dp[n];
    }
	//方法2：
	//动态规划，O(n)time,O(n)space
	//buy[i] 表示第i天为止的最后一次操作是buy的最大利润，i=0,1,2....,n-1
	//sell[i] 表示第i天为止的最后一次操作时sell的最大利润，i=0,1,2....,n-1
	//buy：
	//第i天如果最后一次操作为buy，
	//那么可能是第i天进行buy，由于buy操作的前一天不能进行sell操作，
	//则buy[i] = sell[i-2]-prices[i]，
	//也有可能第i天不进行buy操作，则buy[i] = buy[i-1]
	//所以buy[i] = Math.max(buy[i-1],sell[i-2]-prices[i]);
	//sell：
	//第i天如果最后一次进行的操作为sell，
	//那么可能是第i天进行sell，
	//则buy[i] = sell[i-1]+prices[i]，
	//也有可能第i天不进行sell操作，则buy[i] = sell[i-1]
	//所以sell[i] = Math.max(buy[i-1]+prices[i],sell[i-1]);
	public int maxProfit2(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[n];
        buy[0] = - prices[0];
        int[] sell = new int[n];
        for(int i = 1 ; i < n ; i++){
            if(i > 1){
                buy[i] = Math.max(buy[i-1],sell[i-2]-prices[i]);
            }else{
                buy[i] = Math.max(buy[i-1],-prices[i]);
            }
            sell[i] = Math.max(buy[i-1]+prices[i],sell[i-1]);
        }
        return sell[n-1];
    }
	//方法3：
	//动态规划，dp，O(n)time,O(1)space
	public int maxProfit3(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int n = prices.length;
        int buy = -prices[0],pre_buy = 0,sell = 0,pre_sell = 0;
        for(int i = 1 ; i < n ; i++){
            pre_buy = buy;
            buy = Math.max(pre_buy,pre_sell-prices[i]);
            pre_sell = sell;
            sell = Math.max(pre_buy+prices[i],pre_sell);
        }
        return sell;
    }
}
