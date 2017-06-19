package algorithm.leetcode.algorithm;
/*
 * hard
 * Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
see NO52_N_QueensII.png

 */
public class NO52_N_QueensII {
	//方法1：
	//dfs，回溯
	public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                board[i][j] = '.';
            }
        }
        int[] count = new int[1];
        count[0] = 0;
        helper(board,0,count);
        return count[0];
    }
    private  void helper(char[][] board,int n,int[] count){
        if(n == board.length){
            count[0]++;
            return ;
        }
        for(int i = 0 ; i < board.length ; i++){
            board[n][i] = 'Q';
            if(noAttacksExist(board,n,i)){
                helper(board,n+1,count);
            }
            board[n][i] = '.';
        }
    }
    private boolean noAttacksExist(char[][] board,int x,int y){
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || y == j))
                    return false;
            }
        }
        
        return true;
    }
}
