package algorithm.leetcode.algorithm;
/*
 * medium
 * 240. Search a 2D Matrix II
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false.

similar problems：
74. Search a 2D Matrix 
 */
public class NO240_Searcha2DMatrixII {
	//方法1：O(m+n)time
	//先从最后一行的第一个数开始，如果target比这个数大，则列数加1，向右移，
	//如果target比这个数小，则行数加1，向上移
	//如果target等于这个数，返回true，出界则返回false。
	//原理：
	//如果target比matrix[i][j]大，则matrix上面整列都比target小，则无需比较，右移即可
	//如果target比matrix[i][j]小，则target可能在matrix[i][j]上面，则上移即可
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length,n = matrix[0].length;
        int row = m-1,col = 0;
        while(row >= 0  && col < n){
            if(target == matrix[row][col]){
                return true;
            }else if(target > matrix[row][col]){
                col++;
            }else if(target < matrix[row][col]){
                row--;
            }
        }
        return false;
    }
	//方法2：
	//递归
	public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int m = matrix.length,n = matrix[0].length;
        return searchMatrixHelper(matrix,0,0,m-1,n-1,target);
    }
    public boolean searchMatrixHelper(int[][] matrix,int x1,int y1,int x2,int y2, int target){
        if(x1 < 0 || x1 >= matrix.length || x2 < 0 || x2 >= matrix.length
            || y1 < 0 || y1 >= matrix[0].length || y2 < 0 || y2 >= matrix[0].length){
                return false;
            }
        if(x1 == x2 & y1 == y2){
            return matrix[x1][y1] == target;
        }
        int centerX = (x1+x2)/2;
        int centerY = (y1+y2)/2;
        boolean result = false;
        if(target > matrix[centerX][centerY]){
            result = searchMatrixHelper(matrix,x1,centerY+1,centerX,y2,target)          //右上方区域
                    || searchMatrixHelper(matrix,centerX+1,y1,x2,centerY,target)        //左下方区域
                    || searchMatrixHelper(matrix,centerX+1,centerY+1,x2,y2,target);     //右下方区域  
        }else if(target < matrix[centerX][centerY]){
            result = searchMatrixHelper(matrix,x1,centerY+1,centerX,y2,target)          //右上方区域
                    || searchMatrixHelper(matrix,centerX+1,y1,x2,centerY,target)        //左下方区域
                    || searchMatrixHelper(matrix,x1,y1,centerX,centerY,target);         //左上方区域  
        }else{
            result = true;
        }
        return result;
    }
}
