package algorithm.leetcode.algorithm;
/*
 * hard
 * 51. N-Queens 
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 *  such that no two queens attack each other.
 *  see NO51_N_Queens.png
 *  
 *  Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */
import java.util.*;
public class NO51_N_Queens {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(solveNQueens(n));
	}
	//方法1：
	//dfs，回溯
	public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        helper(board,0,result);
        return result;
    }
    private static  void helper(char[][] board,int n,List<List<String>> ll){
        if(n == board.length){
            List<String> list = new ArrayList<>();
            for(char[] row : board){
                list.add(String.valueOf(row));
            }
            ll.add(list);
            return ;
        }
        for(int i = 0 ; i < board.length ; i++){
            board[n][i] = 'Q';
            if(noAttacksExist(board,n,i)){
                helper(board,n+1,ll);
            }
            board[n][i] = '.';
        }
    }
    private static boolean noAttacksExist(char[][] board,int x,int y){
    	for(int i = 0 ; i < board.length ; i++){
            if(i != x && board[i][y] == 'Q'){
                return false;
            }
            if(i != y && board[x][i] == 'Q'){
                return false;
            }
            if(i != 0 && x+i < board.length && y+i < board.length && board[x+i][y+i] == 'Q'){
                return false;
            }
            if(i != 0 && x+i < board.length && y-i >= 0 && board[x+i][y-i] == 'Q'){
                return false;
            }
            if(i != 0 && x-i >= 0 && y+i < board.length && board[x-i][y+i] == 'Q'){
                return false;
            }
            if(i != 0 && x-i >= 0 && y-i >= 0 && board[x-i][y-i] == 'Q'){
                return false;
            }
        }
        return true;
    }
    //方法2：
    //同方法1，不过判定是否重叠方法不一样
    public List<List<String>> solveNQueens2(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(board, 0, res);
        return res;
    }
    
    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if(colIndex == board.length) {
            res.add(construct(board));
            return;
        }
        
        for(int i = 0; i < board.length; i++) {
            if(validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }
    
    private boolean validate(char[][] board, int x, int y) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {
                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
                    return false;
            }
        }
        
        return true;
    }
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}
