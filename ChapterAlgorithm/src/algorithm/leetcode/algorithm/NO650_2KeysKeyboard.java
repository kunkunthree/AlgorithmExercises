package algorithm.leetcode.algorithm;
/*
 * medium
 * 650. 2 Keys Keyboard 
 *  Initially on a notepad only one character 'A' is present. 
 *  You can perform two operations on this notepad for each step:

    Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
    Paste: You can paste the characters which are copied last time.

Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps 
permitted. Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.

Note:
    The n will be in the range [1, 1000].

 */
public class NO650_2KeysKeyboard {
	//方法1：
	//O(n^2)time,O(n)space
	//dp，动态规划
	//初始化,dp[0] = dp[1] = 0, dp[i] = i ,i > 1
	//i遍历 1～n-1
	//		j遍历 2～ n/i
	//			dp[j*i] = Math.min(dp[i*j],j+dp[i]);	j×i<=n
	public int minSteps(int n) {
        int[] dp = new int[n+1];
        for(int i = 2 ; i <= n ; i++){
            dp[i] = i;
        }
        for(int i = 1 ; i < n ; i++){
            for(int j = 2 ; j*i <= n ; j++){
                dp[j*i] = Math.min(dp[i*j],j+dp[i]);
            }
        }
        return dp[n];
    }
	//方法2：
	//同理，但是是逆序，而且只需找到最大的因子，节省时间
	public int minSteps2(int n) {
        int[] dp = new int[n+1];

        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i-1; j > 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i/j);
                    break;
                }
                
            }
        }
        return dp[n];
    }
	
	//方法3：
	//O(1)space
	//单纯迭代，无dp，无递归，无额外空间
	//当n<=1时，返回0
	//当n>2时，设置一个最小因子d，初始化次数s为0，如果d能被n整除，则s+=d，n/=d，直到d不能被n整除为止
	//d++，进入下一个循环
	 public int minSteps3(int n) {
	        int s = 0;
	        for (int d = 2; d <= n; d++) {
	            while (n % d == 0) {
	                s += d;
	                n /= d;
	            }
	        }
	        return s;
	    }
}
