package algorithm.leetcode.algorithm;
/*
 * medium
 * 464. Can I Win
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. 
 * The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 
without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, 
determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 
and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.

 */
import java.util.*;
public class NO464_CanIWin {
	public static void main(String[] args) {
		int maxChoosableInteger = 10;
		int desiredTotal = 0;
	}
	//方法1：
	//动态规划，根据路径（已选项）得到特定的结果
	//用Intger的每一位表示已经选了的项，当每一条路径确定了就设置该路径的值，
	//当访问该路径时，如果已经访问过，则返回该值
	//因为如果当前路径的点是胜利的路径，那么到达该路径的上一项的路径必然是失败的路径
	//可以用数组记录，也可以用map记录已经访问过的路径
	private Boolean[] win;
    private int chosen = 0;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= 0){
            return true;
        }
        int sum = maxChoosableInteger * (1+maxChoosableInteger) / 2;
        if(sum < desiredTotal){
            return false;
        }
        win = new Boolean[1<<maxChoosableInteger];
        return helper(maxChoosableInteger,desiredTotal,0);
    }
    private boolean helper(int n,int desiredTotal,int curTotal){
        if(win[chosen] != null){
            return win[chosen];
        }
        if(curTotal >= desiredTotal){
            win[chosen] = false;
    		return false;
    	}
        for(int i = 1 ; i <= n ; i++){
            int bit = 1<<(i-1);
            if((bit&chosen) == 0){
                chosen^=bit;
            	boolean ulose = !helper(n, desiredTotal, curTotal+i);
            	chosen^=bit;
            	if(ulose){
            	    win[chosen] = true;
            	    return true;
            	}
            }
        }
        win[chosen] = false;
        return false;
    }
}
