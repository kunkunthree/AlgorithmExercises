package algorithm.leetcode.algorithm;
/*
 * easy
 * 119. Pascal's Triangle II 
 * Given an index k, return the kth row of the Pascal's triangle.
For example, given k = 3,
Return [1,3,3,1].
Note:
Could you optimize your algorithm to use only O(k) extra space? 
 */
import java.util.*;
public class NO119_PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> l = new ArrayList<Integer>();
        for(int i = 0 ; i <= rowIndex ; i++){
            l.add(0,1);
            for(int j = 1; j < l.size()-1 ; j++){
                l.set(j,l.get(j)+l.get(j+1));
            }
        }
        return l;
    }
}
