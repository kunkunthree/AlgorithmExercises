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

similar problems:
39. Combination Sum
40. Combination Sum II
216. Combination Sum III
377. Combination Sum IV
 */
import java.util.*;
public class NO216_CombinationSumIII {
	public static void main(String[] args) {
		int k = 3;
		int n = 9;
		System.out.println(combinationSum3(k, n));
	}
	//方法1：
	//DFS,backtracking
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
    //方法2：
    //dp，动态规划，迭代
    public List<List<Integer>> combinationSum3_2(int k, int n) {
        List<List<Integer>>[] lists = new List[n+1];
        for(int i = 0 ; i <= n ; i++){
            lists[i] = new ArrayList<List<Integer>>();
        }
        lists[0].add(new ArrayList<Integer>());
        for(int count = 1 ; count <= k ; count++){
            List<List<Integer>>[] newLists = new List[n+1];
            for(int i = 0 ; i <= n ; i++){
                newLists[i] = new ArrayList<List<Integer>>();
            }
            for(int sum = 0 ; sum <= n ; sum++){
                for(int add = 1 ; add <= 9 && add <= sum ; add++){
                    for(List<Integer> list : lists[sum-add]){
                        if(count == 1 || add > list.get(list.size()-1)){
                            List<Integer> newList = new ArrayList<>();
                            newList.addAll(list);
                            newList.add(add);
                            newLists[sum].add(newList);
                        }
                    }
                }
            }
            lists = newLists;
        }
        return lists[n];
    }
}
