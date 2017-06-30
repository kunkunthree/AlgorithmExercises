package algorithm.leetcode.algorithm;
/*
 * hard
 * 446. Arithmetic Slices II - Subsequence 
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.

1, 1, 2, 5, 7


A zero-indexed array A consisting of N numbers is given. 
A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic 
if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. 
The output is guaranteed to be less than 231-1.

Example:
Input: [2, 4, 6, 8, 10]
Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]

 */
import java.util.*;
public class NO446_ArithmeticSlicesII_Subsequence {
	public static void main(String[] args) {
		int[] A = new int[]{2,4,6,8,10};
		System.out.println(numberOfArithmeticSlices(A));
	}
	//方法1：
	//dp，动态规划，O(n^3)time
	//dp[i][j]表示以A[i]和A[j]结尾，A[j]-A[i]为差的等差子数列的个数
	// if((long)A[k] + (long)A[i] == 2 * (long)A[j]) ， dp[j][k] += 1+dp[i][j];
	public static int numberOfArithmeticSlices(int[] A) {
		int n = A.length,result = 0;
        int[][] dp = new  int[n][n];
        for(int k = 2 ; k < n ; k++){
            for(int j = k-1 ; j >= 1 ; j--){
                for(int i = j-1 ; i >= 0 ; i--){
                    if((long)A[k] + (long)A[i] == 2 * (long)A[j]){
                        dp[j][k] += 1+dp[i][j];
                    }
                }
                result+=dp[j][k];
            }
        }
        return result;
    }
	//方法2：
	/**
    * 利用和frog jump相同的思想,
    * [0,1, ..., pre, ..., cur], [..., next]
    * dp[next][cur] += dp[cur][pre] + 1;  
    *        result += dp[cur][pre] + 1;
    * */
   public int numberOfArithmeticSlices2(int[] A) {
       int n = A.length;
       Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
       for(int i=0; i<n; i++){
           List<Integer> list = map.get(A[i]);
           if(list==null) list = new ArrayList<Integer>();
           list.add(i);
           map.put(A[i], list);
       }
       int[][] dp = new int[n][n];
       int res = 0;
       for(int cur=1; cur<n; cur++){
           for(int pre=cur-1; pre>=0; pre--){
               long nextValue = (long)A[cur]+((long)A[cur]-A[pre]);
               if(nextValue<Integer.MIN_VALUE || nextValue>Integer.MAX_VALUE) continue;
               int nextVal = (int)nextValue;
               if(map.containsKey(nextVal)){   //验证下一个节点是否存在
                   List<Integer> list = map.get(nextVal);
                   int pos = Collections.binarySearch(list, cur+1);
                   pos = pos<0?(-pos-1):pos;
                   while(pos<list.size()){     //主要是为了handle重复元素
                       int next = list.get(pos++);
                       dp[next][cur] += dp[cur][pre]+1;
                       res += dp[cur][pre]+1;
                   }
                   
               }
           }
       }
       return res;
   }
   //方法3：
   //用hashMap保存前面的数与当前值的差及其个数
   public int numberOfArithmeticSlices3(int[] A) {
	    int res = 0;
	    Map<Integer, Integer>[] map = new Map[A.length];
			
	    for (int i = 0; i < A.length; i++) {
	        map[i] = new HashMap<>(i);
	        	
	        for (int j = 0; j < i; j++) {
	            long diff = (long)A[i] - A[j];
	            if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
	        		
	            int d = (int)diff;
//	            int c1 = map[i].getOrDefault(d, 0);
//	            int c2 = map[j].getOrDefault(d, 0);
	            int c1 = map[i].get(d) == null ? 0 : map[i].get(d);
	            int c2 = map[j].get(d) == null ? 0 : map[j].get(d);
	            res += c2;
	            map[i].put(d, c1 + c2 + 1);
	        }
	    }
			
	    return res;
	}
}
