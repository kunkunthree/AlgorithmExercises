package algorithm.leetcode.algorithm;
/*
 * medium
 * 474. Ones and Zeroes 
 * In the computer world, use restricted resource you have to generate maximum benefit 
 * is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. 
On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s.
Each 0 and 1 can be used at most once.

Note:

    1.		The given numbers of 0s and 1s will both not exceed 100
    2.		The size of given string array won't exceed 600.

Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”

Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

similar problems:
 600. Non-negative Integers without Consecutive Ones

 */
public class NO474_OnesandZeroes {
	//方法1：
	//O(mn)space,O(kl + kmn)time， where k is the length of input string array and l is the average length of a string within the array.
	//dp，01背包问题的变种，二维费用背包问题
	//问题：
	//二维费用的背包问题是指：
	//		对于每件物品，具有两种不同的费用；选择这件物品必须同时付出这两种代价；
	//		对于每种代价都有一个可付出的最大值（背包容量）。问怎样选择物品可以得到最大的价值。
	//		设这两种代价分别为代价1和代价2，第i件物品所需的两种代价分别为a[i]和b[i]。
	//		两种代价可付出的最大值（两种背包容量）分别为V和U。物品的价值为w[i]。
	//算法：
	//		费用加了一维，只需状态也加一维即可。
	//		设f[i][v][u]表示前i件物品付出两种代价分别为v和u时可获得的最大价值。
	//		状态转移方程就是：f [i][v][u]=max{f[i-1][v][u],f[i-1][v-a[i]][u-b[i]]+w[i]}。
	//		如前述方法，可以只使用二维的数组：
	//				当每件物品只可以取一次时变量v和u采用顺序的循环，当物品有如完全背包问题时采用逆序的循环。
	//				当物品有如多重背包问题时拆分物品。
	public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][] count = new int[length][2];
        for(int i = 0 ; i < length ; i++){
            for(int j = 0 ; j < strs[i].length() ; j++){
                if(strs[i].charAt(j) == '0'){
                    count[i][0]++;
                }else if(strs[i].charAt(j) == '1'){
                    count[i][1]++;
                }
            }
        }
        int[][] dp = new int[m+1][n+1];
        for(int i = 0 ; i < length ; i++){
            for(int j = m ; j >= count[i][0] ; j--){
                for(int k = n ; k >= count[i][1] ; k--){
                    dp[j][k] = Math.max(dp[j][k],dp[j-count[i][0]][k-count[i][1]]+1);
                }
            }
        }
        return dp[m][n];
    }
}
