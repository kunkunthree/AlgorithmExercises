package algorithm.leetcode.algorithm;
/*
 * easy
 * 118. Pascal's Triangle 
 * Given numRows, generate the first numRows of Pascal's triangle.
For example, given numRows = 5,
Return
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

 */
import java.util.*;
public class NO118_PascalsTriangle {
	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		int tmp = l.get(0);
		System.out.println(tmp);
	}
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        if(numRows == 0){
            return ll;
        }
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(1);
        ll.add(l);
        List<Integer> last;
        int left,right;
        for(int i = 2 ; i <= numRows ;i++){
            last = ll.get(i-2);
            l = new ArrayList<Integer>();
            for(int j = 0 ; j < i ; j++){
                if(j == 0 || j == last.size()){
                    l.add(1);
                }else{
                    l.add(last.get(j-1)+last.get(j));
                }
            }
            ll.add(l);
        }
        return ll;
    }
    //简洁写法
    public List<List<Integer>> generate2(int numRows)
    {
    	List<List<Integer>> allrows = new ArrayList<List<Integer>>();
    	ArrayList<Integer> row = new ArrayList<Integer>();
    	for(int i=0;i<numRows;i++)
    	{
    		row.add(0, 1);
    		for(int j=1;j<row.size()-1;j++)
    			row.set(j, row.get(j)+row.get(j+1));
    		allrows.add(new ArrayList<Integer>(row));
    	}
    	return allrows;
    	
    }
}
