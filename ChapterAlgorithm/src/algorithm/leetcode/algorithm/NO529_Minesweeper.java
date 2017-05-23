package algorithm.leetcode.algorithm;
/*
 * medium
 * 529. Minesweeper
 * Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 
'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent 
(above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent 
to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), 
return the board after revealing this position according to the following rules:

    If a mine ('M') is revealed, then the game is over - change it to 'X'.
    If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and 
    all of its adjacent unrevealed squares should be revealed recursively.
    If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') 
    representing the number of adjacent mines.
    Return the board when no more squares will be revealed.

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:
see NO529_Minesweeper_1.png

Example 2:

Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:
see NO529_Minesweeper_2.png

Note:

    1.		The range of the input matrix's height and width is [1,50].
    2.		The click position will only be an unrevealed square ('M' or 'E'), 
    			which also means the input board contains at least one clickable square.
    3.		The input board won't be a stage when game is over (some mines have been revealed).
    4.		For simplicity, not mentioned rules should be ignored in this problem. For example, 
    			you don't need to reveal all the unrevealed mines when the game is over, 
    			consider any cases that you will win the game or flag any squares.

 */
import java.util.*;
public class NO529_Minesweeper {
	//方法1：
	//DFS
	public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return board;
        }
        int m = board.length,n = board[0].length;
        helper(board,click[0],click[1],m,n,true);
        return board;
    }
    private void helper(char[][] board,int x,int y,int m,int n,boolean first){
        if(x < 0 || x >= m || y < 0 || y >= n){
            return;
        }
        if(board[x][y] == 'E'){
            int count = 0;
            for(int i = -1 ; i <= 1 ; i++){
                for(int j = -1 ; j <= 1 ; j++){
                    if(x+i >=0 && x+i < m && y+j >= 0 && y+j < n && board[x+i][y+j] == 'M'){
                        count++;
                    }
                }
            }
            if(count > 0){
                board[x][y] = (char)('0' + count);
            }else{
                board[x][y] = 'B';
                for(int i = -1 ; i <= 1 ; i++){
                    for(int j = -1 ; j <= 1 ; j++){
                        helper(board,x+i,y+j,m,n,false);
                    }
                }
            }
        }else if(first == true && board[x][y] == 'M'){
            board[x][y] = 'X';
        }
    }
    //方法2：
    //BFS
    public char[][] updateBoard2(char[][] board, int[] click) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return board;
        }
        int m = board.length,n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        boolean first = true;
        int x = 0,y = 0;
        while(!queue.isEmpty()){
            click = queue.poll();
            x = click[0];
            y = click[1];
            if(x < 0 || x >= m || y < 0 || y >= n){
                continue;
            }
            if(board[x][y] == 'E'){
                int count = 0;
                for(int i = -1 ; i <= 1 ; i++){
                    for(int j = -1 ; j <= 1 ; j++){
                        if(x+i >=0 && x+i < m && y+j >= 0 && y+j < n && board[x+i][y+j] == 'M'){
                            count++;
                        }
                    }
                }
                if(count > 0){
                    board[x][y] = (char)('0' + count);
                }else{
                    board[x][y] = 'B';
                    for(int i = -1 ; i <= 1 ; i++){
                        for(int j = -1 ; j <= 1 ; j++){
                            queue.offer(new int[]{x+i,y+j});
                        }
                    }
                }
            }else if(first == true && board[x][y] == 'M'){
                board[x][y] = 'X';
            }
            first = false;
        }
        return board;
    }
}
