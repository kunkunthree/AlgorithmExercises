package algorithm.leetcode.algorithm;
/*
 * medium
 * 130. Surrounded Regions
 *  Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

 */
public class NO130_SurroundedRegions {
	public static void main(String[] args) {
		String[] ss = new String[]{"XXXX","XOOX","XXOX","XOXX"};
		char[][] board = new char[ss.length][];
		for(int i = 0 ; i < ss.length ; i++){
			board[i] = ss[i].toCharArray();
		}
		solve(board);
	}
	//方法1：
	//递归DFS
	public static void solve(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        int row = board.length,col = board[0].length;
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        for(int i = 0 ; i < row ; i++){
            if(board[i][0] == 'O'){
                solve(board,i,0);
            }
            if(board[i][col-1] == 'O'){
                solve(board,i,col-1);
            }
        }
        for(int j = 0 ; j < col ; j++){
            if(board[0][j] == 'O'){
                solve(board,0,j);
            }
            if(board[row-1][j] == 'O'){
                solve(board,row-1,j);
            }
        }
        for(int i = 0 ; i < row ; i++){
            for(int j = 0 ; j < col ; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'Q'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    public static void solve(char[][] board,int x ,int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length){
            return;
        }
        if(board[x][y] == 'O'){
            board[x][y] = 'Q';
        }
        if(x > 1 && board[x-1][y] == 'O'){
            solve(board,x-1,y);
        }
        if(x < board.length-1 && board[x+1][y] == 'O'){
            solve(board,x+1,y);
        }
        if(y > 1 && board[x][y-1] == 'O'){
            solve(board,x,y-1);
        }
        if(y < board[0].length-1 && board[x][y+1] == 'O'){
            solve(board,x,y+1);
        }
    }
    
    
//	public void solve(char[][] board) {
//        if(board == null || board.length == 0){
//            return;
//        }
//        int row = board.length,col = board[0].length;
//        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
//        for(int i = 0 ; i < row ; i++){
//            if(board[i][0] == 'O'){
//                solve(board,dir,i,0);
//            }
//        }
//        for(int i = 0 ; i < row ; i++){
//            if(board[i][col-1] == 'O'){
//                solve(board,dir,i,col-1);
//            }
//        }
//        for(int j = 0 ; j < col ; j++){
//            if(board[0][j] == 'O'){
//                solve(board,dir,0,j);
//            }
//        }
//        for(int j = 0 ; j < col ; j++){
//            if(board[row-1][j] == 'O'){
//                solve(board,dir,row-1,j);
//            }
//        }
//        for(int i = 0 ; i < row ; i++){
//            for(int j = 0 ; j < col ; j++){
//                if(board[i][j] == 'O'){
//                    board[i][j] = 'X';
//                }else if(board[i][j] == 'Q'){
//                    board[i][j] = 'O';
//                }
//            }
//        }
//    }
//    public void solve(char[][] board,int[][] dir,int x ,int y){
//        Queue<Integer> queueX = new LinkedList<Integer>();
//        Queue<Integer> queueY = new LinkedList<Integer>();
//        queueX.offer(x);
//        queueY.offer(y);
//        int tmpX,tmpY;
//        while(!queueX.isEmpty() && !queueX.isEmpty()){
//            x = queueX.poll();
//            y = queueY.poll();
//            if(board[x][y] == 'O'){
//                board[x][y] = 'Q';
//            }
//            for(int i = 0 ; i < dir.length ; i++){
//                tmpX = x+dir[i][0];
//                tmpY = y+dir[i][1];
//                if(tmpX >= 0 && tmpX < board.length && tmpY >= 0 && tmpY < board[0].length && board[tmpX][tmpY] == 'O'){
//                    queueX.offer(tmpX);
//                    queueY.offer(tmpY);
//                }
//            }
//        }
//    }

}
