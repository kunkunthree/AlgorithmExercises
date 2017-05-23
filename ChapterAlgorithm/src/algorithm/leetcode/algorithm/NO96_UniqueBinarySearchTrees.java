package algorithm.leetcode.algorithm;
/*
 * medium
 * 96. Unique Binary Search Trees
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */
import java.util.*;
public class NO96_UniqueBinarySearchTrees {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(numTrees(n));
	}
	//方法1：
	//递归，并用HashMap保存中间结果减少重复计算
	public static int numTrees(int n) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(0,1);
        map.put(1,1);
        return numTreesHelper(n,map);
    }
    public static  int numTreesHelper(int n,Map<Integer,Integer> map){
        if(map.containsKey(n)){
            return map.get(n);
        }
        int sum = 0;
        for(int i = 1 ; i <= n ; i++){
            sum+=numTreesHelper(i-1,map)*numTreesHelper(n-i,map);
        }
        map.put(n,sum);
        return sum;
    }
    //方法2：
    //动态规划，dp，以当前每一个节点作为头节点，继续为左右子树建立BST，左右子树是独立的，因此组合数为其乘积
    //G(n) = F(1, n) + F(2, n) + ... + F(n, n).
    //F(i, n) = G(i-1) * G(n-i)
    public int numTrees2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2 ; i <= n ; i++){
            for(int j = 1; j <= i ; j++){
                dp[i]+=dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}
