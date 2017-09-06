package algorithm.leetcode.algorithm;
/*
 * hard
 * 514. Freedom Trail 
 *  In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial 
 *  called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

Given a string ring, which represents the code engraved on the outer ring and another string key, 
which represents the keyword needs to be spelled. 
You need to find the minimum number of steps in order to spell all the characters in the keyword.
Initially, the first character of the ring is aligned at 12:00 direction. 
You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise 
to make each character of the string key aligned at 12:00 direction and then by pressing the center button.
At the stage of rotating the ring to spell the key character key[i]:

    1.		You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. 
    		The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, 
    		where this character must equal to the character key[i].
    2.		If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, 
    which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), 
    otherwise, you've finished all the spelling.

Example:
see NO514_FreedomTrail.jpg

Input: ring = "godding", key = "gd"
Output: 4
Explanation:
 For the first key character 'g', since it is already in place, we just need 1 step to spell this character. 
 For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps 
 to make it become "ddinggo".
 Also, we need 1 more step for spelling.
 So the final output is 4.

Note:
    1.		Length of both ring and key will be in range 1 to 100.
    2.		There are only lowercase letters in both strings and might be some duplcate characters in both strings.
    3.		It's guaranteed that string key could always be spelled by rotating the string ring.

 */
import java.util.*;
public class NO514_FreedomTrail {
	//方法1：
	//dp，动态规划，递归
	//O(m*n)space
	//以当前下标为原点向两边遍历，用一个二维数组记录当前最小路径
	public int findRotateSteps(String ring, String key) {
        if(ring == null || ring.length() <= 0 || key == null || key.length() <= 0){
            return 0;
        }
        int n = ring.length(),m = key.length();
        int[][] memo = new int[m][n];
        for(int i = 0 ; i < m ; i ++){
            Arrays.fill(memo[i],Integer.MAX_VALUE);
        }
        return helper(ring,key,memo,0,0,0);
    }
    public int helper(String ring,String key,int[][] memo,int start,int index,int curSteps){
        if(start == key.length()){
            return curSteps;
        }
        int result = Integer.MAX_VALUE;
        int n = ring.length();
        int halfLen = n/2;
        for(int i = 0 ; i <= halfLen ; i++){
            int left = (index+i)%n;
            if(key.charAt(start) == ring.charAt(left) && curSteps+i+1 < memo[start][left]){
                memo[start][left] = curSteps+i+1;
                result = Math.min(result,helper(ring,key,memo,start+1,left,memo[start][left]));
            }
            int right = (index+n-i)%n;
            if(key.charAt(start) == ring.charAt(right) && curSteps+i+1 < memo[start][right]){
                memo[start][right] = curSteps+i+1;
                result = Math.min(result,helper(ring,key,memo,start+1,right,memo[start][right]));
            }
        }
        return result;
    }
    //方法2：
    //dp，动态规划，迭代
    public int findRotateSteps2(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }
        
        return dp[0][0] + m;
    }
}
