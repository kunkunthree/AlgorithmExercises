package algorithm.leetcode.algorithm;
/*
 * hard
 * 668. Kth Smallest Number in Multiplication Table 
 *  Nearly every one have used the Multiplication Table. 
 *  But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, 
you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).

Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).

Note:
    1.	The m and n will be in the range [1, 30000].
    2.	The k will be in the range [1, m * n]

 */
public class NO668_KthSmallestNumberInMultiplicationTable {
	//方法1：
	//二分搜索
	//从1～m*n范围二分搜索，left=1，right = m*n,mid = left+(right-left)/2
	//计算mid是最多可能是第几小的数count，如果有重复的数，则按最后一个数算
	//如果count<k，那么mid肯定太小，left = mid+1;
	//如果count>=k，则有可能mid太大，right=mid
	public int findKthNumber(int m, int n, int k) {
        int left = 1,right = m*n,mid;
        while(left < right){
            mid = left + (right - left)/2;
            int j = n;
            int count = 0;
            for(int i = 1 ; i <= m ; i++){
            	int temp = Math.min(mid / i , n);
                count+=temp;
            }
            if(count < k){	//使left往第k大的数靠拢
                left = mid+1;
            }else{	//使right移动到第p大的数,p>=k，不断使p往k靠拢
                right = mid;
            }
        }
        return left;
    }
}
