package algorithm.leetcode.algorithm;
/*
 * medium
 * 79. Word Search 
 *  Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */
import java.util.*;
public class NO79_WordSearch {
	public static void main(String[] args) {
//		String[] ss = new String[]{"ABCE","SFCS","ADEE"};
//		char[][] board = new char[ss.length][];
//		for(int i = 0 ; i < ss.length ; i++){
//			board[i] = ss[i].toCharArray();
//		}
//		String word = "ABCCED";
//		System.out.println(exist(board, word));
		for(int i = 0 ; i < 26 ; i ++){
			System.out.println((char)(('A'+i)^256));
		}
	}
	public static boolean exist(char[][] board, String word) {
        if(board == null || board[0].length == 0 || word == null || word.length() == 0){
            return false;
        }
        char head = word.charAt(0);
        int m = board.length,n = board[0].length;
        boolean[][] run = new boolean[m][n];
        int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == head){
                    run[i][j] = true;
                    //此线路是否存在正确线路
                    if(existHelper(board,run,dir,word,1,i,j) == true){
                        return true;
                    }
                    //回溯
                    run[i][j] = false;
                }
            }
        }
        return false;
	}
    public static boolean existHelper(char[][] board,boolean[][] run,int[][] dir,String word,int count,int x,int y){
        if(count == word.length()){
            return true;
        }
        int nextX,nextY;
        for(int i = 0 ; i < dir.length ; i++){
            nextX = x+dir[i][0];
            nextY = y+dir[i][1];
            //是否在棋盘内
            if(nextX >= 0 && nextX < board.length && nextY >= 0 && nextY < board[0].length){
                //下一步是否满足没有踏足过，且为字符串下一字符
                if(run[nextX][nextY] == false && word.charAt(count) == board[nextX][nextY]){
                    run[nextX][nextY] = true;
                    //此线路是否存在正确线路
                    if(existHelper(board,run,dir,word,count+1,nextX,nextY) == true){
                        return true;
                    }
                    //回溯
                    run[nextX][nextY] = false;
                }
            }
        }
        return false;
    }
    //方法2：
    //方法1的优化，节省用额外的空间来存储是否走过的状态
    //通过字母与256进行位异或运算得到后的字符不是字母的原理，将走过的字母转换位非字母，
    //回溯的时候再用位异或运算转换回来
    public boolean exist2(char[][] board, String word) {
        if(board == null || board[0].length == 0 || word == null || word.length() == 0){
            return false;
        }
        char head = word.charAt(0);
        int m = board.length,n = board[0].length;
        boolean[][] run = new boolean[m][n];
        // Stack<List<Integer>> stackPt = new Stack<List<Integer>>();
        int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == head){
                    board[i][j] ^= 256;
                    //此线路是否存在正确线路
                    if(existHelper(board,dir,word,1,i,j) == true){
                        return true;
                    }
                    //回溯
                    board[i][j] ^= 256;
                }
            }
        }
        return false;
    }
    public boolean existHelper(char[][] board,int[][] dir,String word,int count,int x,int y){
        if(count == word.length()){
            return true;
        }
        int nextX,nextY;
        for(int i = 0 ; i < dir.length ; i++){
            nextX = x+dir[i][0];
            nextY = y+dir[i][1];
            //是否在棋盘内
            if(nextX >= 0 && nextX < board.length && nextY >= 0 && nextY < board[0].length){
                //下一步是否满足没有踏足过，且为字符串下一字符
                if(word.charAt(count) == board[nextX][nextY]){
                    board[nextX][nextY] ^= 256;
                    //此线路是否存在正确线路
                    if(existHelper(board,dir,word,count+1,nextX,nextY) == true){
                        return true;
                    }
                    //回溯
                    board[nextX][nextY] ^= 256;
                }
            }
        }
        return false;
    }
    
    //方法3：
    //方法2的简化写法
    public boolean exist3(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
        	for (int x=0; x<board[y].length; x++) {
        		if (exist(board, y, x, w, 0)) return true;
        	}
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
    	if (i == word.length) return true;
    	if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
    	if (board[y][x] != word[i]) return false;
    	board[y][x] ^= 256;
    	if(exist(board, y, x+1, word, i+1)
    			|| exist(board, y, x-1, word, i+1)
    			|| exist(board, y+1, x, word, i+1)
    			|| exist(board, y-1, x, word, i+1)){
    		return true;
    	}
    	board[y][x] ^= 256;
    	return false;
    }
}
