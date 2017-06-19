package algorithm.leetcode.algorithm;
/*
 * hard
 * 85. Maximal Rectangle 
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 6. 

//dp解法
The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row i 
and column j be computed by [right(i,j) - left(i,j)]*height(i,j).

All the 3 variables left, right, and height can be determined by the information from previous row, 
and also information from the current row. So it can be regarded as a DP solution. The transition equations are:

    left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row

    right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row

    height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';

    height(i,j) = 0, if matrix[i][j]=='0'


 */
import java.util.*;
public class NO85_MaximalRectangle {
	//方法1：
	//dp，动态规划
	//计算当前位置向上最高的边为高，向两边扩展的矩形的面积
	//通过计算上一行和当前行的信息，得到当前为止矩阵的宽的左顶点坐标、宽的右顶点坐标、高
	public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length,n = matrix[0].length;
        int[] left = new int[n],right = new int[n],height = new int[n];
        Arrays.fill(right,n);
        int maxArea = 0;
        for(int i = 0 ; i < m ; i++){
            int cur_left = 0,cur_right = n;
            for(int j = 0 ; j < n ; j++){   // compute height (can do this from either side)
                if(matrix[i][j] == '1'){
                    height[j]++;
                }else{
                    height[j] = 0;
                }
            }
            for(int j = 0 ; j < n ; j++){   // compute left (from left to right)
                if(matrix[i][j] == '1'){
                    left[j] = Math.max(left[j],cur_left);
                }else{
                    left[j] = 0;
                    cur_left = j+1;
                }
            }
            for(int j = n-1 ; j >= 0 ; j--){   // compute right (from right to left)
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(right[j],cur_right);
                }else{
                    right[j] = n;
                    cur_right = j;
                }
            }
            for(int j = 0 ; j < n ; j++){   // compute the area of rectangle (can do this from either side)
                maxArea = Math.max(maxArea,(right[j] - left[j]) * height[j]);
            }
        }
        return maxArea;
    }
	//方法2：
	//利用栈，得到每行当前的高度，求当前柱状图的最大矩形面积
	//类似问题如，NO84_LargestRectangleInHistogram
	public int maximalRectangle2(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length,n = matrix[0].length;
        int[] height = new int[n+1];
        height[n] = 0;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j <= n ; j++){
                if(j < n){
                    if(matrix[i][j] == '1'){
                        height[j]++;
                    }else{
                        height[j] = 0;
                    }
                }
                if(stack.isEmpty() || height[j] >= height[stack.peek()]){
                    stack.push(j);
                }else{
                    while(!stack.isEmpty() && height[j] < height[stack.peek()]){
                        int top = stack.pop();
                        maxArea = Math.max(maxArea,height[top] * (stack.isEmpty() ? j : j - 1 - stack.peek()));
                    }
                    stack.push(j);
                }
            }
            stack.clear();
        }
        return maxArea;
    }
}
