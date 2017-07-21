package algorithm.leetcode.algorithm;
/*
 * medium
 * 59. Spiral Matrix II
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,
You should return the following matrix:

[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

similar problems:
54. Spiral Matrix
 */
public class NO59_SpiralMatrixII {
	
	//方法1：
	//直接法，直接对四种方向的移动做处理，规定四种方向的边界值
    public int[][] generateMatrix(int n) {
        if(n < 0){
            return null;
        }
        int[][] matrix = new int[n][n];
        int length = n * n,count = 1;
        int i = 0,j = 0,top = 0,bottom = n,right = n,left = -1;
        while(j < right){
            while(j < right){
                matrix[i][j++] = count++;
            }
            right--;
            j--;
            i++;
            while(i < bottom){
                matrix[i++][j] = count++;
            }
            bottom--;
            i--;
            j--;
            while(j > left){
                matrix[i][j--] = count++;
            }
            left++;
            j++;
            i--;
            while(i > top){
                matrix[i--][j] = count++;
            }
            top++;
            i++;
            j++;
        }
        return matrix;
    }
	//方法2：
    //用一个二维数组把移动的方向存起来，移动到边界后，回退到上一步的位置，并进行下一个方向的移动
    public int[][] generateMatrix2(int n) {
        if(n < 0){
            return null;
        }
        int[][] matrix = new int[n][n];
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};	//save the directions
        int count = 1;
        int i = 0,j = 0,k = 0,top = -1,bottom = n,right = n,left = -1;
        while(i > top && i < bottom && j < right && j > left){
        	//move in one direction
            while(i > top && i < bottom && j < right && j > left ){
                matrix[i][j] = count++;
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
        return matrix;
    }
}
