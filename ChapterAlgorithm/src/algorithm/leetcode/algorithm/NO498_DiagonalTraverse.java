package algorithm.leetcode.algorithm;
/*
 * medium
 * 498. Diagonal Traverse
 *  Given a matrix of M x N elements (M rows, N columns), 
 *  return all elements of the matrix in diagonal order as shown in the below image.

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:
see NO498_DiagonalTraverse.png

Note:

    The total number of elements of the given matrix will not exceed 10,000.

 */
public class NO498_DiagonalTraverse {
	//方法1：
	//直接法，O(m*n)time,O(1)space
	//先判断是否x == m-1且朝右上，是则，y++，改变方向
	//再判断是否y == n-1且朝左下，是则，x++，改变方向
	//再判断是否x == 0且朝右上，是则，y++，改变方向
	//再判断是否y == 0且朝左下，是则，x++，改变方向
	//如果以上都不符合，则说明不是在边界上，则按照原来方向走就可以，即x+=move[dir][0];   y+=move[dir][1];
	//注意，必须先判断是否为某边末尾边界，才能判断另一条边的头边界，
	//如必须先判断是否x == m-1 && dir == 0，才能判断y == 0 && dir == 0，否则当y==0，x==m-1时，执行x++会越界
	public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return new int[0];
        }
        int m = matrix.length,n = matrix[0].length,count = 0,length = m*n;
        int[] result = new int[length];
        int[][] move = new int[][]{{1,-1},{-1,1}};
        int dir = 1,x = 0,y = 0;	//dir：0表示向左下，1表示向右上
        while(count < length){
            result[count++] = matrix[x][y];
            if(count < length){
                if(x == m-1 && dir == 0){
                    y++;
                    dir^=1;
                }else if(y == n-1 && dir == 1){
                    x++;
                    dir^=1;
                }else if(x == 0 && dir == 1){
                    y++;
                    dir^=1;
                }else if(y == 0 && dir == 0){
                    x++;
                    dir^=1;
                }else{
                    x+=move[dir][0];
                    y+=move[dir][1];
                }
            }
        }
        return result;
    }
	//方法2：
	//跟方法1差不多，更加简洁
	public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 0;
        int[][] dirs = {{-1, 1}, {1, -1}};
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];
            
            //修正是否越界
            if (row >= m) { row = m - 1; col += 2; d = 1 - d;}
            if (col >= n) { col = n - 1; row += 2; d = 1 - d;}
            if (row < 0)  { row = 0; d = 1 - d;}
            if (col < 0)  { col = 0; d = 1 - d;}
        }
        
        return result;
    }
}
