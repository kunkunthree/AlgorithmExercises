package algorithm.leetcode.algorithm;
/*
 * medium
 * 221. Maximal Square
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4. 
 */
import java.util.*;
public class NO221_MaximalSquare {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m  = in.nextInt();
		int n = in.nextInt();
		char[][] matrix = new char[m][n];
		for(int i = 0 ; i < m ; i++){
			matrix[i] = in.next().toCharArray();
		}
		System.out.println(maximalSquare(matrix));
	}
	//方法1：
	//根据左上角的正方形，判断该点的行和列是否能构成正方形
	//O(1) space,O(m*n)time
	public static int maximalSquare(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length,n = matrix[0].length;
        int max = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i > 0 && j > 0 && matrix[i][j] > '0' && matrix[i-1][j-1] > '0'){
                    boolean flag = true;
                    int k = 1;
                    for( ; k <= matrix[i-1][j-1] - '0' ; k++){
                        if(matrix[i][j-k] == '0' || matrix[i-k][j] == '0'){
                            flag = false;
                            break;
                        }
                    }
                    matrix[i][j] = (char)(k+'0');
                }
                if(matrix[i][j] - '0' > max){
                    max = matrix[i][j] - '0';
                }
            }
        }
        return max*max;
    }
	//方法2：
	//一个边长为n的正方形，可以看做是三个边长为n-1的正方形(左边，上边，左上角)加上一个边长为1的的正方形重叠构成
	public int maximalSquare2(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length,n = matrix[0].length;
        int max = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i > 0 && j > 0 && matrix[i][j] > '0' && matrix[i-1][j-1] > '0'){
                    matrix[i][j] = (char)(Math.min(Math.min(matrix[i-1][j-1],matrix[i][j-1]),matrix[i-1][j])+1);
                }
                if(matrix[i][j] - '0' > max){
                    max = matrix[i][j] - '0';
                }
            }
        }
        return max*max;
    }
	//方法3：
	//动态规划，用一个一维数组存储中间结果，O(m*n)time,O(n)space
	public int maximalSquare3(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length,n = matrix[0].length;
        int max = 0,pre = 0,tmp;
        int[] dp = new int[n+1];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                tmp = dp[j+1];
                if(matrix[i][j] == '1'){
                    dp[j+1] = (char)(Math.min(Math.min(pre,dp[j+1]),dp[j])+1);
                    max = Math.max(max,dp[j+1]);
                }else{
                    dp[j+1] = 0;
                }
                pre =tmp;
            }
        }
        return max*max;
    }
}
