package algorithm.leetcode.algorithm;
/*
 * hard
 * 631. Design Excel Sum Formula 
 * Your task is to design the basic function of Excel and implement the function of sum formula. 
 * Specifically, you need to implement the following functions:

Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form.
 H is a positive integer, range from 1 to 26. It represents the height. W is a character range from 'A' to 'Z'. 
 It represents that the width is the number of characters from 'A' to W. 
 The Excel form content is represented by a height * width 2D integer array C, it should be initialized to zero. 
 You should assume that the first row of C starts from 1, and the first column of C starts from 'A'.

void Set(int row, char column, int val): Change the value at C(row, column) to be val.

int Get(int row, char column): Return the value at C(row, column).

int Sum(int row, char column, List of Strings : numbers): 
		This function calculate and set the value at C(row, column), where the value should be the sum of cells 
		represented by numbers. This function return the sum result at C(row, column). 
		This sum formula should exist until this cell is overlapped by another value or another sum formula.

		numbers is a list of strings that each string represent a cell or a range of cells. 
		If the string represent a single cell, then it has the following format : ColRow. For example, 
		"F7" represents the cell at (7, F).

		If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. 
		The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, 
		and ColRow2 represents the position of the bottom-right cell.

Example 1:
Excel(3,"C"); 
// construct a 3*3 2D array with all zero.
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, "A", 2);
// set C(1,"A") to be 2.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, "C", ["A1", "A1:B2"]);
// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. 
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, "B", 2);
// set C(2,"B") to be 2. Note C(3, "C") should also be changed.
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6

Note:
    1.		You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
    2.		The test cases are using double-quotes to represent a character.
    3.		Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted 
    		across multiple test cases. Please see here for more details.

 */
import java.util.*;
public class NO631_DesignExcelSumFormula {
	//方法1：
	//用一个HashMap把所有有依赖的单元格记录下来，并把其依赖的单元格保存下来
	class Excel {
	    int[][] matrix;
	    private HashMap<String,ArrayList<String>> depends = new HashMap();
	    
	    public Excel(int H, char W) {
	        matrix = new int[H+1][W-'A'+1];
	    }
	    
	    public void set(int r, char c, int v) {
	        matrix[r][c-'A'] = v;
	        if(depends.containsKey(c+""+r)){
	            depends.remove(c+""+r);
	        }
	    }
	    
	    public int get(int r, char c) {
	        return get(r,c,new HashMap<String,Integer>());
	    }
	    
	    private int get(int r,char c,HashMap<String,Integer> mapping){
	        String key = c+""+r;
	        if(!mapping.containsKey(key)){
	            if(!depends.containsKey(key)){
	                mapping.put(key,matrix[r][c-'A']);
	            }else{
	                int result = 0;
	                for(String s : depends.get(key)){
	                    int tmpR = Integer.parseInt(s.substring(1));
	                    char tmpC = s.charAt(0);
	                    result+=get(tmpR,tmpC,mapping);
	                }
	                mapping.put(key,result);
	            }
	        }
	        return mapping.get(key);
	    }
	    
	    public int sum(int r, char c, String[] strs) {
	        int result = 0;
	        String key = c+""+r;
	        depends.put(key,new ArrayList<String>());
	        HashMap<String,Integer> mapping = new HashMap<>();
	        for(String str : strs){
	            if(str.contains(":")){
	                String[] s = str.split(":");
	                int minR = Integer.parseInt(s[0].substring(1));
	                char minC = s[0].charAt(0);
	                int maxR = Integer.parseInt(s[1].substring(1));
	                char maxC = s[1].charAt(0);
	                for(int i = minR ; i <= maxR ; i++){
	                    for(char j = minC ; j <= maxC ; j++){
	                        depends.get(key).add(j+""+i);
	                        result+=get(i,j,mapping);
	                    }
	                }
	            }else{
	                char tmpC = str.charAt(0);
	                int tmpR = Integer.parseInt(str.substring(1));
	                depends.get(key).add(str);
	                result+=get(tmpR,tmpC,mapping);
	            }
	        }
	        return result;
	    }
	}
}
