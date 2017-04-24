package algorithm.leetcode.algorithm;
/*
 * medium
 * 74. Search a 2D Matrix
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]

Given target = 3, return true.
 */
public class NO74_Searcha2DMatrix {
	public static void main(String[] args) {
//		int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		int[][] matrix = new int[][]{{1},{3}};
		int target = 3;
		System.out.println(searchMatrix(matrix, target));
	}
	//方法1：
	//先二分搜索最小的行首值大于等于target的行，确定行后，再在该行二分搜索target
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length,n = matrix[0].length;
        int left = 0,right = m-1,mid;
        while(left < right){
            mid = right - (right - left) / 2;
            if(matrix[mid][0] > target){
                right = mid-1;
            }else{
                left = mid;
            }
        }
        int row = left;
        left = 0;
        right =n-1;
        while(left < right){
            mid = left + (right - left) / 2;
            if(matrix[row][mid] > target){
                right = mid-1;
            }else if(matrix[row][mid] < target){
                left = mid+1;
            }else{
                left = mid;
                break;
            }
        }
        int col = left;
        return matrix[row][col] == target;
    }
    //方法2：
    //把二维矩阵看做是一个一维的数组进行二分搜索
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length,n = matrix[0].length;
        int left = 0,right = m*n-1,mid,row = 0,col = 0;
        while(left<=right){
            mid = (right + left) / 2;
            row = mid / n;
            col = mid % n;
            if(matrix[row][col] > target){
                right = mid -1;
            }else if(matrix[row][col] < target){
                left = mid + 1;
            }else{
                break;
            }
        }
        return matrix[row][col] == target;
    }
}
