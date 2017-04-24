package algorithm.leetcode.algorithm;
/*
 * medium
 * 73. Set Matrix Zeroes 
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
 */
import java.util.*;
public class NO73_SetMatrixZeroes {
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
		setZeroes2(matrix);
	}
	//方法1：
	//用两个set分别保存出现0的行和列
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowSet = new HashSet<Integer>();
        Set<Integer> colSet = new HashSet<Integer>();
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[i].length ; j++){
                if(matrix[i][j] == 0){
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }
        for(int row : rowSet){
            for(int j = 0 ; j < matrix[row].length ; j++){
                matrix[row][j] = 0;
            }
        }
        for(int col : colSet){
            for(int i = 0 ; i < matrix.length ; i++){
                matrix[i][col] = 0;
            }
        }
    }
    //方法2
    //两次遍历：
    //第一次遍历把每行除了第一个元素的所有的a[i][j] = 0的情况置a[i][0] = 0,a[0][j] = 0,
    //而第一个元素出现0的情况则说明第一列为0，置标志位col0为true
    //第二次遍历从后往前遍历，先对行号大于等于1的情况处理，再处理第一个元素，
    //行号大于等于1时，如果a[i][0] = 0 或a[0][j] = 0，则置a[i][j] = 0
    //如果col0 = true，则置第一个元素为0
    //以免前面的变化导致后面结果产生变化
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        boolean col0 = false;
        for(int i = 0 ; i < m ; i++){
            if(matrix[i][0] == 0){
                col0 = true;
            }
            for(int j = 1 ; j < n ; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = m-1 ; i >= 0 ; i--){
            for(int j = n-1 ; j >= 1 ; j--){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
            if(col0 == true){
                matrix[i][0] = 0;
            }
        }
    }
}
