package algorithm.leetcode.algorithm;
/*
 * medium
 * 279. Perfect Squares
 *  Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) 
 *  which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9. 
 */
import java.util.*;
public class NO279_PerfectSquares {
	//方法1：
	//dp,动态规划
	//从1到n遍历，遍历到i的时候，令dp[i]是所有dp[i-j*j]+1中最小的一个，其中j=1,2,3,4....,且j*j<=i
	public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1 ; i <=n ; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j*j <= i ; j++){
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
	//方法2：
	//数学原理：
	//拉格朗日四平方和定理，费马平方和原理，勒让德三平方和原理
	public int numSquares2(int n) {
        // Based on Lagrange's Four Square theorem, there 
        // are only 4 possible results: 1, 2, 3, 4.
        
        // If n is a perfect square, return 1.
        if(isSquare(n)){
            return 1;
        }
        // The result is 4 if and only if n can be written in the 
        // form of 4^k*(8*m + 7). Please refer to 
        // Legendre's three-square theorem.
        while((n & 3) == 0) // n%4 == 0  
        {
            n >>= 2;  
        }
        if((n & 7) == 7) // n%8 == 7
        {
            return 4;
        }
        // Check whether 2 is the result.
        int sqrt_n = (int)(Math.sqrt(n)); 
        for(int i = 1; i <= sqrt_n; i++)
        {  
            if (isSquare(n - i*i)) 
            {
                return 2;  
            }
        }  
        return 3;
    }
    private boolean isSquare(int n){
        int sq = (int)Math.sqrt(n);
        return sq * sq == n;
    }
    //方法3：
    //迭代，利用queue保存每一个第一次得到平方和的节点，
    //遍历小于n的平方列表，把下一个第一次出现的平方和放入队列
    public int numSquares3(int n) {
        if(n <= 0){
            return 0;
        }
        int[] nums = new int[n+1];
        List<Integer> sqList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1 ; i*i <= n ; i++){
            sqList.add(i*i);
            queue.offer(i*i);
            nums[i*i] = 1;
        }
        if(sqList.get(sqList.size()-1) == n){
            return 1;
        }
        int count = 1;
        while(!queue.isEmpty()){
            count++;
            int length = queue.size();
            for(int i = 0 ; i < length ; i++){
                int tmp = queue.poll();
                for(int sq : sqList){
                    if(tmp+sq == n){
                        return count;
                    }else if(tmp+sq < n && nums[tmp+sq] == 0){
                        nums[tmp+sq] = count;
                        queue.add(sq+tmp);
                    }else if(tmp+sq > n){
                        break;
                    }
                }
            }
        }
        return 0;
    }
}
