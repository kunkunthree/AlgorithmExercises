package algorithm.leetcode.algorithm;
/*
 * medium
 * 48. Rotate Image
 * You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
 */
public class NO48_RotateImage {
	public static void main(String[] args) {
		
		int[][] result = new int[1][];
	}
	
	//方法1：
	//用额外的空间把旋转后的二维数组存储起来，再把临时存储的二维数组赋给原来的数组
	//旋转方式，把原数组中第i行的元素赋给临时空间的倒数第i列
    public void rotate(int[][] matrix) {
        int rowLength = matrix[0].length;
        int colLength = matrix.length;
        int[][] result = new int[rowLength][colLength];
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[i].length ; j++){
                result[j][colLength-1-i] = matrix[i][j];
            }
        }
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[i].length ; j++){
                matrix[i][j] = result[i][j];
            }
        }
    }
    
    //方法2：
    //先沿着对角线旋转180°，然后再沿着竖直中线旋转180°
    public void rotate2(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
}
