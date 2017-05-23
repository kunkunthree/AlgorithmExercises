package algorithm.leetcode.algorithm;
/*
 * medium
 * 378. Kth Smallest Element in a Sorted Matrix
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
 * find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */
import java.util.*;
public class NO378_KthSmallestElementinaSortedMatrix {
	//方法1：
	//利用PriorityQueue进行排序，从第一排往下搜索，每次从queue中获得一个节点，
	//则把它下面比它大的节点加入到queue中
	//O(klogn)
	//Solution 1 : Heap
	//		1.Build a minHeap of elements from the first row.
	//		2.Do the following operations k-1 times :
	//			Every time when you poll out the root(Top Element in Heap), 
	//			you need to know the row number and column number of that element
	//			(so we can create a tuple class here), replace that root with the next element from the same column.
	class Point{
        int x;
        int y;
        int val;
        Point(int x,int y,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Point> queue = new PriorityQueue<Point>(1,new Comparator<Point>(){
            public int compare(Point p1,Point p2){
                return p1.val - p2.val;
            }
        });
        int m = matrix.length,n = matrix[0].length;
        for(int j = 0 ; j < n ; j++){
            queue.offer(new Point(0,j,matrix[0][j]));
        }
        Point p = null;
        while(k > 0 && !queue.isEmpty()){
            p = queue.poll();
            if(p.x+1 < m){
                queue.offer(new Point(p.x+1,p.y,matrix[p.x+1][p.y]));
            }
            k--;
        }
        return p.val;
    }
    //方法2：
    //二分搜索，搜索范围
    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length,n = matrix[0].length;
        int left = matrix[0][0],right = matrix[m-1][n-1],mid;
        while(left < right){
            mid = left + (right - left)/2;
            int j = n-1;
            int count = 0;
            for(int i = 0 ; i < m ; i++){
                while(j >= 0 && matrix[i][j] > mid){
                    j--;
                }
                count+=(j+1);
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
