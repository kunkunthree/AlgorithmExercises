package algorithm.leetcode.algorithm;
/*
 * medium
 * 413. Arithmetic Slices
 * A sequence of number is called arithmetic if it consists of at least three elements 
 * and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.

1, 1, 2, 5, 7


A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) 
such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.

Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

 */
public class NO413_ArithmeticSlices {
	//方法1：
	//迭代，直接统计每一段连续等差数列的长度，计算子序列个数，累加起来
	public int numberOfArithmeticSlices(int[] A) {
        int result = 0,count = 0,n = A.length;
        for(int i = 1 ; i < n-1 ; i++){
            if(A[i]-A[i-1] == A[i+1]-A[i]){
                count++;
            }else{
                result+=(count*(count+1))/2;
                count = 0;
            }
        }
        result+=(count*(count+1))/2;
        return result;
    }
	//方法2：
	//方法1的另一种实现形式
	public int numberOfArithmeticSlices2(int[] A) {
	    int curr = 0, sum = 0;
	    for (int i=2; i<A.length; i++)
	        if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
	            curr += 1;
	            sum += curr;
	        } else {
	            curr = 0;
	        }
	    return sum;
	}
}
