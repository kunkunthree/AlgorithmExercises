package algorithm.leetcode.algorithm;
/*
 * medium
 * 651. 4 Keys Keyboard 
 * Imagine you have a special keyboard with the following keys:

Key 1: (A): Prints one 'A' on screen.
Key 2: (Ctrl-A): Select the whole screen.
Key 3: (Ctrl-C): Copy selection to buffer.
Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A

Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V

Note:
    1.		1 <= N <= 50
    2.		Answers will be in the range of 32-bit signed integer.

 */
public class NO651_4KeysKeyboard {
	//方法1：
	//O(n^2)time,O(n)space
	//dp，动态规划
	//dp[i] = Math.max(i,dp[i-1]+1,dp[i-j]*(j-1))，j = 3~i-3
	public int maxA(int N) {
        int[] dp = new int[N+1];
        dp[0] = 0;
        for(int i = 1 ; i <= N ; i++){
            dp[i] = Math.max(i,dp[i-1]+1);
            for(int j = 3 ; i-j >= 0 ; j++){
                dp[i] = Math.max(dp[i],dp[i-j]*(j-1));
            }
        }
        return dp[N];
    }
	//方法2：
	//O(n)time,O(n)space
	//dp，动态规划
	//dp[i - 4] * 3 and dp[i - 5] * 4 always the largest number in the series.
	public int maxA2(int N) {
        int[] dp = new int[N+1];
        dp[0] = 0;
        for(int i = 1 ; i <= N ; i++){
            dp[i] = Math.max(i,dp[i-1]+1);
            if(i >= 7)dp[i] = Math.max(dp[i - 4] * 3, dp[i - 5] * 4);
            // dp[i] = Math.max(dp[i - 4] * 3, Math.max(dp[i - 5] * 4, dp[i - 6] * 5));
        }
        return dp[N];
    }
	//方法3：
	//O(1)time,O(1)space
	//Pure math. This problem is to partition number N into 3's and 4's and get their product. 
	//n = N / 5 + 1 is to compute the number of factors(the total number of 3's and 4's).
	//With n, it's easy to know how many out of them are 3's by computing n3 = n * 5 - 1 - N. 
	//We minus 1 here because adding a single factor requires one step more than the factor itself, e.g. x4 takes 5 steps 
	//(select all, copy, paste, paste, paste). 
	//10 is special here because it's the only > 6 number where there is no enough factors to share cuts from 
	//decrement of the number of 3's which means a 5 has to be introduced.
	public int maxA3(int N) {
        if (N <= 6) return N;
        if (N == 10) return 20;
        int n = N / 5 + 1, n3 = n * 5 - 1 - N;
        return (int)Math.pow(3, n3) * (int)Math.pow(4, n - n3);
    }
}
