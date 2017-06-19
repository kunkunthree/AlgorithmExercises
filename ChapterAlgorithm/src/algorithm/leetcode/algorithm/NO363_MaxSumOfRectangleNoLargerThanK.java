package algorithm.leetcode.algorithm;
/*
 * hard
 * 363. Max Sum of Rectangle No Larger Than K 
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix 
 * such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2

The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
    1.		The rectangle inside the matrix must have an area > 0.
    2.		What if the number of rows is much larger than the number of columns?

类似的问题：
NO327_CountOfRangeSum
 */
import java.util.*;
public class NO363_MaxSumOfRectangleNoLargerThanK {
	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		LinkedList<Integer> list1 = new LinkedList<>();
	}
	//方法1：
	//通过归并对部分和进行归并排序，在排序过程中求最大的小于等于k部分和的值
	//即将二维的问题转化为一维的问题
	//O(n^2 * mlogm)time
	public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length , n = matrix[0].length;
        long[] sum = new long[m+1]; //sum[0] 一直都为0
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; i++){
            long[] sumInRow = new long[m];
            for(int j = i ; j < n ; j++){
                for(int p = 0 ; p < m ; p++){
                    sumInRow[p]+=matrix[p][j];
                    sum[p+1] = sum[p] + sumInRow[p];
                }
                //sum[p]为sum[p][i,j],即累计到第p行的第i到j列的和，
                //即sum[p+1] = sumInRow[0] + sumInRow[1] +...+ sumInRow[p],其中sumInRow[p]为第p行第i到j列的和
                max = Math.max(max,mergeSort(sum,0,m+1,k));
                if(max == k){
                    return k;
                }
            }
        }
        return max;
    }
    private int mergeSort(long[] sum,int start,int end,int k){
        if(start+1 == end){
            return Integer.MIN_VALUE;
        }
        int mid = start + (end - start)/2;
        //先对前半部分排序，获得其小于等于k的最大部分和
        int result = mergeSort(sum,start,mid,k);
        if(result == k){
            return k;
        }
        //再对前半部分排序，获得其小于等于k的最大部分和
        result = Math.max(result,mergeSort(sum,mid,end,k));
        if(result == k){
            return k;
        }
        long[] cache = new long[end - start];
        int index = 0;
        for(int i = start,j = mid,p = mid; i < mid ; i++){
            //寻找当前小于等于k的最大部分和
            while(j < end && sum[j] - sum[i] <= k){
                j++;
            }
            if(j - 1 >= mid){
                result = Math.max(result,(int)(sum[j-1]-sum[i]));
                if(result == k){
                    return k;
                }
            }
            //排序
            while(p < end && sum[p] < sum[i]){
                cache[index++] = sum[p++];
            }
            cache[index++] = sum[i];
        }
        System.arraycopy(cache,0,sum,start,index);
        return result;
    }
    
    //方法2：
    //O(min(m,n)^2*max(m,n)*log(max(m,n))) time
    //利用TreeSet对累加部分和进行排序，每一次添加前进行二分搜索set中大于等于当前值val-k的数subResult，
    //如果存在，比较max和val-subResult，更新max
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        int row = matrix.length , col = matrix[0].length;
        int m = Math.min(row,col);
        int n = Math.max(row,col);
        boolean colIsBig = col > row;
        int max = Integer.MIN_VALUE;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i = 0 ; i < m ; i++){
            int[] array = new int[n];
            for(int j = i ; j >= 0 ; j--){
                int val = 0;
                treeSet.clear();
                treeSet.add(0);
                for(int p = 0 ; p < n ; p++){
                    array[p]+=colIsBig ? matrix[j][p] : matrix[p][j];
                    val+=array[p];
                    Integer subResult = treeSet.ceiling(val - k);
                    if(subResult != null){
                        max = Math.max(max,val-subResult);
                        if(max == k){
                            return k;
                        }
                    }
                    treeSet.add(val);
                }
            }
        }
        return max;
    }
}
