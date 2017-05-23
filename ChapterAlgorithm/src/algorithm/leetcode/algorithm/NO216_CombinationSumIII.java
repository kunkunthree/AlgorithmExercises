package algorithm.leetcode.algorithm;
/*
 * medium
 * 216. Combination Sum III 
 * 

Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Example 1:
Input: k = 3, n = 7
Output:
[[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output:
[[1,2,6], [1,3,5], [2,3,4]]

 */
import java.util.*;
public class NO216_CombinationSumIII {
	public static void main(String[] args) {
		int k = 3;
		int n = 9;
		System.out.println(combinationSum3(k, n));
	}
	public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ll = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum3(ll,list,k,n,1,9);
        return ll;
    }
    public static void combinationSum3(List<List<Integer>> ll,List<Integer> list,int k,int rest,int start,int end){
        if(rest < 0){
            return;
        }
        if(k == 0){
            if(rest == 0){
                ll.add(new ArrayList<Integer>(list));
            }
            return;
        }
        for(int i = start ; i <= end ; i++){
            list.add(i);
            combinationSum3(ll,list,k-1,rest-i,i+1,end);
            list.remove(list.size()-1);
        }
    }
    
}
