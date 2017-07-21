package algorithm.leetcode.algorithm;
/*
 * easy
 * 122. Best Time to Buy and Sell Stock II 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 *  (ie, buy one and sell one share of the stock multiple times). 
 *  However, you may not engage in multiple transactions at the same time 
 *  (ie, you must sell the stock before you buy again).
 *  
 *  similar problems:
 *  121. Best Time to Buy and Sell Stock 
 *  123. Best Time to Buy and Sell Stock III 
 *  188. Best Time to Buy and Sell Stock IV 
 *  309. Best Time to Buy and Sell Stock with Cooldown 
 */
public class NO122_BestTimetoBuyandSellStockII {
	//方法1：
	//求每一个最低点到下一个最高点的差值的总和（假设-1位置是无限大，n位置是无限小）
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1){
            return 0;
        }
        int minIndex = -1,maxP = 0,tmp,tmpMaxP = 0;
        boolean isMin = false;
        if(prices[0] < prices[1]){
            isMin = true;
            minIndex = 0;
        }
        for(int i = 1 ; i < prices.length-1 ; i++){
            if(isMin == false && prices[i] <= prices[i-1] && prices[i] < prices[i+1]){
                minIndex = i;
                isMin = true;
            }else if(isMin == true && prices[i] >= prices[i-1] && prices[i] > prices[i+1]){
                isMin = false;
                maxP+=(prices[i]-prices[minIndex]);
            }
        }
        if(isMin == true){
            maxP+=(prices[prices.length-1]-prices[minIndex]);
        }
        return maxP;
    }
    //方法2：
    //最简单的写法，直接求递增的数目，只求上升的部分
    public int maxProfit2(int[] prices) {
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
            if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }
        
        return total;
    }
}
