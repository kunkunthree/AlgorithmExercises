package algorithm.leetcode.algorithm;
/*
 * hard
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution. 

A sudoku puzzle...
see NO37_SudokuSolver_1.png

..and its solution numbers marked in red. 
see NO37_SudokuSolver_2.png
 */
import java.util.*;
public class NO37_SudokuSolver {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][] board = new char[9][9];
		for(int i = 0 ; i < 9 ; i++){
			board[i] = in.nextLine().toCharArray();
		}
		NO37_SudokuSolver sudoku = new NO37_SudokuSolver();
		sudoku.solveSudoku(board);
		for(int i = 0 ; i < 9 ; i ++){
			for(int j = 0 ; j < 9 ; j++){
				System.out.print(board[i][j]+ " ");
			}
			System.out.println();
		}
	}
	//方法1：
	//利用回溯
	class Point{
        int x,y;
        int val;
        List<Character> candidates;
        Point(int x,int y){
            this.x = x;
            this.y = y;
            candidates = new ArrayList<>();
        }
    }
    public void solveSudoku(char[][] board) {
        Stack<Point> stackFilled = new Stack<>();
        Stack<Point> stackUnfilled = new Stack<>();
        Point p;
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(board[i][j] == '.'){
                    p = new Point(i,j);
                    stackUnfilled.push(p);
                }
            }
        }
        if(stackUnfilled.isEmpty()){
            return;
        }
        int total = stackUnfilled.size();
        p = stackUnfilled.pop();
        p.candidates = getCandidates(board,p.x,p.y);
        while(stackFilled.size() < total){
            if(p.candidates.size() == 0){
                stackUnfilled.push(p);
                if(stackFilled.isEmpty()){
                    return;
                }
                board[p.x][p.y] = '.';
                p = stackFilled.pop();
            }else{
                board[p.x][p.y] = p.candidates.remove(0);
                stackFilled.push(p);
                if(!stackUnfilled.isEmpty()){
                    p = stackUnfilled.pop();
                    p.candidates = getCandidates(board,p.x,p.y);
                }
            }
        }
    }
    private List<Character> getCandidates(char[][] board ,int x,int y){
        Set<Character> set = new HashSet<>();
        for(int i = 1 ; i <= 9 ; i++){
            set.add((char)('0'+i));
        }
        int cubeX,cubeY;
        for(int i = 0 ; i < 9 ; i++){
            set.remove(board[i][y]);
            set.remove(board[x][i]);
            cubeX = x/3*3 + i/3;
            cubeY = y/3*3 + i%3;
            set.remove(board[cubeX][cubeY]);
        }
        return new ArrayList<Character>(set);
    }
    
    //方法2：
    //递归回溯
    public void solveSudoku2(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            
                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' && 
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
}
