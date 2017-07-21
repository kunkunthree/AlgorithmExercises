package algorithm.leetcode.algorithm;
/*
 * medium
 * 634. Find the Derangement of An Array 
 *  In combinatorial mathematics, a derangement is a permutation of the elements of a set, 
 *  such that no element appears in its original position.

There's originally an array consisting of n integers from 1 to n in ascending order, 
you need to find the number of derangement it can generate.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:

Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].

Note:
n is in the range of [1, 106]. 
 */
public class NO634_FindTheDerangementOfAnArray {
	//countDer(n) = (n-1)*[countDer(n-1) + countDer(n-2)]
	//There are n – 1 ways for element 0 (this explains multiplication with n-1).
	// Let 0 be placed at index i. There are now two possibilities, depending on
	// whether or not element i is placed at 0 in return.
	//
	// i is placed at 0: This case is equivalent to solving the problem for n-2
	// elements as two elements have just swapped their positions.
	// i is not placed at 0: This case is equivalent to solving the problem for
	// n-1 elements as now there are n-1 elements, n-1 positions and every
	// element has n-2 choices
	
	//方法1：
	//dp,动态规划，O(n)time,O(n)space
	public int findDerangement(int n) {
        if (n<2) return 0;
        long f[]=new long[n+1];
        f[1]=0;f[2]=1;
        for (int i=3;i<=n;i++) f[i]=(f[i-1]+f[i-2])*(i-1)%1000000007;
        return (int)f[n];
    }
	//方法2"：
	//dp,动态规划，O(n)time,O(1)space
	public int findDerangement2(int n) {
        long dn2 = 0, dn1 = 1;
        long res = n==1 ? 0 : 1; 
        for (int i = 3; i <= n; i++){
            res = ((i-1) * (dn1+dn2))%1000000007;
            dn2 = dn1;
            dn1 = res;           
        }
        return (int) res;
    }
}
