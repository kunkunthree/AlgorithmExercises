package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * medium
 * 289. Game of Life
 *  According to the Wikipedia's article: "The Game of Life, also known simply as Life,
 *   is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules 
(taken from the above Wikipedia article):

    1.Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    2.Any live cell with two or three live neighbors lives on to the next generation.
    3.Any live cell with more than three live neighbors dies, as if by over-population..
    4.Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.

Follow up:

    1.Could you solve it in-place? Remember that the board needs to be updated at the same time: 
    		You cannot update some cells first and then use their updated values to update other cells.
    2.In this question, we represent the board using a 2D array. In principle, the board is infinite, 
    		which would cause problems when the active area encroaches the border of the array. 
    		How would you address these problems?

 */
public class NO289_GameofLife {
	public static void main(String[] args) {
		int[][] board = new int[][]{{1}};
		gameOfLife(board);
		System.out.println(Arrays.deepToString(board));
	}
	//方法1：
	//统计周围存活的个数，根据规则得到下一个状态
	//用两位二进制数表示当前和下一个状态，低位表示当前状态，高位表示下一个状态，
	//第一遍遍历整个二位数组，置高位，根据周边细胞的低位的当前状态得到当前细胞的高位下一个状态
	//第二次遍历时，二进制数右移，即使高位变为低位状态
	public static void gameOfLife(int[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return;
        }
        int count,m = board.length,n = board[0].length;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                count = 0;
                for(int x = i-1 ; x <= i+1 ; x++){
                    for(int y = j-1 ; y <= j+1 ; y++){
                        if(x >= 0 && x < m && y >= 0 && y < n){
                            if(x == i && y == j){
                                continue;
                            }
                            if((board[x][y] & 1) == 1){
                                count++;
                            }
                        }
                    }
                }
                if(board[i][j] == 1 && (count == 2 || count == 3) || board[i][j] == 0 && count == 3){
                    board[i][j]|=1<<1;
                }
            }
        }
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                board[i][j]>>=1;
            }
        }
    }
}
