package algorithm.leetcode.algorithm;

/*
 * medium
 * 36. Valid Sudoku
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

 A partially filled sudoku which is valid.

 Note:
 A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
 */
import java.util.*;

public class NO36_ValidSudoku {
	// 判断现有数独表格已存在的数字是否合理
	// 方法1：迭代判断每行每列每个区域是否有重复出现
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			Set<Character> rowSet = new HashSet<Character>();
			Set<Character> colSet = new HashSet<Character>();
			Set<Character> cubeSet = new HashSet<Character>();
			for (int j = 0; j < 9; j++) {
				// 第i行是否重复
				if (board[i][j] != '.' && !rowSet.add(board[i][j])) {
					return false;
				}
				// 第i列是否重复
				if (board[j][i] != '.' && !colSet.add(board[j][i])) {
					return false;
				}
				// 第i个区域是否重复
				int cubeX = 3 * (i / 3) + j / 3;
				int cubeY = 3 * (i % 3) + j % 3;
				if (board[cubeX][cubeY] != '.'
						&& !cubeSet.add(board[cubeX][cubeY])) {
					return false;
				}
			}
		}
		return true;
	}
	// 方法2：
	// 通过对不同行，不同列，不同区域进行规定不同格式的字符串，用一个Set保存起来，看是否有重复出现
	// Collect the set of things we see, encoded as strings. For example:
	// '4' in row 7 is encoded as "(4)7".
	// '4' in column 7 is encoded as "7(4)".
	// '4' in the top-right block is encoded as "0(4)2".
	// Scream false if we ever fail to add something because it was already
	// added (i.e., seen before).
	public boolean isValidSudoku2(char[][] board) {
	    Set seen = new HashSet();
	    for (int i=0; i<9; ++i) {
	        for (int j=0; j<9; ++j) {
	            if (board[i][j] != '.') {
	                String b = "(" + board[i][j] + ")";
	                if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
	                    return false;
	            }
	        }
	    }
	    return true;
	}
}
