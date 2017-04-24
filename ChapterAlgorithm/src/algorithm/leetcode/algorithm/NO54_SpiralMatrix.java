package algorithm.leetcode.algorithm;
/*
 * medium
 * 54. Spiral Matrix
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

You should return [1,2,3,6,9,8,7,4,5]. 
 */
import java.util.*;
public class NO54_SpiralMatrix {
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		System.out.println(spiralOrder3(matrix));
	}
	//方法1：
	//直接法，直接对四种方向的移动做处理，规定四种方向的边界值
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if(matrix == null|| matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        int length = matrix.length * matrix[0].length;
        int i = 0,j = 0,top = 0,bottom = matrix.length,right = matrix[0].length,left = -1;
        while(list.size()<length){
            while(list.size()<length && j < right){
                list.add(matrix[i][j++]);
            }
            right--;
            j--;
            i++;
            while(list.size()<length && i < bottom){
                list.add(matrix[i++][j]);
            }
            bottom--;
            i--;
            j--;
            while(list.size()<length && j > left){
                list.add(matrix[i][j--]);
            }
            left++;
            j++;
            i--;
            while(list.size()<length && i > top){
                list.add(matrix[i--][j]);
            }
            top++;
            i++;
            j++;
        }
        return list;
    }
    //方法2：方法1的写法修改
    public List<Integer> spiralOrder2(int[][] matrix) {
        
        List<Integer> res = new ArrayList<Integer>();
        
        if (matrix.length == 0) {
            return res;
        }
        
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;
            
            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }
        return res;
    }
    //方法3：
    //用一个二维数组把移动的方向存起来，移动到边界后，回退到上一步的位置，并进行下一个方向的移动
    public static List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};	//save the directions
        if(matrix == null|| matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        int i = 0,j = 0,k = 0,top = -1,bottom = matrix.length,right = matrix[0].length,left = -1;
        while(i > top && i < bottom && j < right && j > left){
        	//move in one direction
            while(i > top &&  i < bottom && j < right && j > left){
                list.add(matrix[i][j]);
                i+=dir[k][0];
                j+=dir[k][1];
            }
            //缩减边界，narrow the bound
            switch(k){
	            case 0:top++;break;
	            case 1:right--;break;
	            case 2:bottom--;break;
	            case 3:left++;break;
	            default:break;
            }
            //回退，backtrace
            i-=dir[k][0];
            j-=dir[k][1];
            //进行下一个方向的移动，next direction
            k = (k+1)%4;
            //避免重复，avoid duplicate elements
            i+=dir[k][0];
            j+=dir[k][1];
        }
        return list;
    }
}
