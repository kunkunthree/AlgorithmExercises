package algorithm.leetcode.algorithm;
/*
 * hard
 * 72. Edit Distance 
 *  Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
 *  (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 */
import java.util.*;
public class NO72_EditDistance {
	public static void main(String[] args) {
		String word1 = "abcdefgaaaaaaaa";
		String word2 = "abcdefeaaba";
		System.out.println(minDistance2(word1, word2));
	}
	//方法1：
	//dp，动态规划
	//f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2
	public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0 ; i <= m ; i++){
            dp[i][0] = i;
        }
        for(int j = 0 ; j <= n ; j++){
            dp[0][j] = j;
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j< n ; j++){
                if(word1.charAt(i) == word2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                }else{
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j],dp[i+1][j]),dp[i][j+1])+1;
                }
            }
        }
        return dp[m][n];
    }
	//方法2：
	//递归，效率太低
    public static int minDistance2(String word1, String word2) {
        Map<int[],Integer> map = new HashMap<>();
        return minDistance2(0,word1.length()-1,word1,0,word2.length()-1,word2,map);
    }
    private static int minDistance2(int start1,int end1,String word1, int start2,int end2,String word2,Map<int[],Integer> map) {
        int[] key = new int[4];
        key[0] = start1;
        key[1] = end1;
        key[2] = start2;
        key[3] = end2;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int result = 0;
        if(start1 > end1 || start2 > end2){
            if(start1 > end1 && start2 > end2){
                result = 0;
            }else if(start2 > end2){
                result =  end1 - start1 + 1;
            }else{
                result = end2 - start2 + 1;
            }
            map.put(key,result);
            return result;
        }
        if(word1.charAt(start1) == word2.charAt(start2)){
            result = minDistance2(start1+1,end1,word1,start2+1,end2,word2,map);
        }else{
            int t1 = minDistance2(start1+1,end1,word1,start2,end2,word2,map);
            int t2 = minDistance2(start1,end1,word1,start2+1,end2,word2,map);
            int t3 = minDistance2(start1+1,end1,word1,start2+1,end2,word2,map);
            result = Math.min(Math.min(t1,t2),t3)+1;
        }
        map.put(key,result);
        return result;
    }
}
