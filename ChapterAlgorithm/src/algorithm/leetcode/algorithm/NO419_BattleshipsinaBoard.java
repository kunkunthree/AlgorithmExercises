package algorithm.leetcode.algorithm;
/*
 * medium
 * 419. Battleships in a Board
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, 
 * empty slots are represented with '.'s. You may assume the following rules:

    You receive a valid board, made of only battleships or empty slots.
    Battleships can only be placed horizontally or vertically. In other words, 
    they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), 
    where N can be of any size.
    At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

Example:

X..X
...X
...X

In the above board there are 2 battleships.

Invalid Example:

...X
XXXX
...X

This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */
import java.util.*;
public class NO419_BattleshipsinaBoard {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		char[][] board = new char[m][n];
		for(int i = 0 ; i < m ; i++){
			board[i] = in.next().toCharArray();
		}
		System.out.println(countBattleships2(board));
	}
	//方法1：
	//DFS，对于不符合规则的棋盘，也能应用
	public static int countBattleships(char[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return 0;
        }
        int count = 0,m = board.length,n = board[0].length,ver,hor;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 'X'){
                    ver = check(board,i,j,true);
                    hor = check(board,i,j,false);
                    if((ver | hor) == 1){
                        count++;
                    }
                    markAll(board,i,j,'O');
                }
            }
        }
        return count;
    }
    private static  int check(char[][] board,int x,int y,boolean ver){
        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '.'){
            return 1;
        }
        if(ver == false){
            if(y-1 >= 0 && board[x][y-1] == 'X' 
            || y+1 < board[0].length && board[x][y+1] == 'X'){
                return 0;
            }
            return check(board,x+1,y,ver);
        }else{
            if(x-1 >= 0 && board[x-1][y] == 'X' 
            || x+1 < board.length && board[x+1][y] == 'X'){
                return 0;
            }
            return check(board,x+1,y,ver);
        }
    }
    private static void markAll(char[][] board,int x,int y,char mark){
        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'X'){
            return;
        }
        if(board[x][y] == 'X'){
            board[x][y] = mark;
        }
        markAll(board,x-1,y,mark);
        markAll(board,x,y-1,mark);
        markAll(board,x+1,y,mark);
        markAll(board,x,y+1,mark);
    }
    //方法2：
    //只输出最左上角的数目，不能应用于不符合规则的棋盘
    //由于一个valid board 的所有船时没有相邻的
    public static int countBattleships2(char[][] board) {
        int m = board.length;
        if (m==0) return 0;
        int n = board[0].length;
        
        int count=0;
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i-1][j] == 'X') continue;
                if (j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }
        
        return count;
    }
}
