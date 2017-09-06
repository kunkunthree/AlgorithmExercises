package algorithm.leetcode.algorithm;
/*
 * medium
 * 646. Maximum Length of Pair Chain 
 *  You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c.
 Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. 
You needn't use up all the given pairs. You can select pairs in any order.

Example 1:

Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]

Note:
    The number of given pairs will be in the range [1, 1000].

similar problems:
300. Longest Increasing Subsequence 
491. Increasing Subsequences 
 */
import java.util.*;
public class NO646_MaximumLengthOfPairChain {
	public static void main(String[] args) {
		int[][] pairs = new int[][]{{-10,-8},{8,9},{-5,0},{6,10},{-6,-4},{1,7},{9,10},{-4,7}};
		System.out.println(findLongestChain(pairs));
	}
	//方法1：
	//O(n^2)time,O(n)space
	//先排序，然后dp,动态规划
	public static int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs,new Comparator<int[]>(){
            public int compare(int[] pair1,int[] pair2){
                if(pair1[0] == pair2[0]){
                    return pair1[1] - pair2[1];
                }
                return pair1[0] - pair2[0];
            }
        });
        int n = pairs.length;
        int[] dp = new int[n];
        for(int i = 0 ; i < n ; i++){
            dp[i] = 1;
            for(int j = i-1 ; j >= 0 ; j--){
                if(pairs[j][1] < pairs[i][0] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 1;
        for(int i = 0 ; i < n ; i++){
            max = Math.max(max,dp[i]);
        }
        return max;
    }
	//方法2：
	//O(nlogn)time,O(1)space
	//等同于时间间隔安排问题
	//先按照片段末端大小从小到达排序
	//然后从头到尾遍历，由于前面的末尾在前，然后必然可以替换后面的任意一个片段作为链的开头
	//所以从头开始算最长的链，每遇到一个符合的链就将其末端作为新的末端，然后过滤与当前链有重叠的片段
	public int findLongestChain2(int[][] pairs) {
		Arrays.sort(pairs,new Comparator<int[]>(){
            public int compare(int[] pair1,int[] pair2){
                return pair1[1] - pair2[1];
            }
        });
	    int sum = 0, n = pairs.length, i = -1;
	    while (++i < n) {
	        sum++;
	        int curEnd = pairs[i][1];
	        while (i+1 < n && pairs[i+1][0] <= curEnd) i++;
	    }
	    return sum;
	}
}
