package algorithm.leetcode.algorithm;
/*
 * medium
 * 39. Combination Sum
 *  Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 *   find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:

[
  [7],
  [2, 2, 3]
]

 */
import java.util.*;
public class NO39_CombinationSum {
	public static void main(String[] args) {
		int[] candidates = new int[]{2,3,6,7};
		int target = 7;
		System.out.println(combinationSum(candidates, target));
	}
	//方法1：先对candidate数组排序，然后再使用贪心算法
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.sort(candidates);
        return combinationSumHelper(candidates,0,new ArrayList<Integer>(),target);
    }
    public static List<List<Integer>> combinationSumHelper(int[] candidates,int index,List<Integer> list,int target){
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        if(target == 0){
            ll.add(new ArrayList<Integer>(list));
            return ll;
        }
        if(candidates[index] > target){
            return ll;
        }
        for(int i = index ; i < candidates.length ; i++){
            if(candidates[i] <= target){
                list.add(candidates[i]);
                for(List<Integer> l : combinationSumHelper(candidates,i,list,target-candidates[i])){
                    ll.add(l);
                }
                list.remove(list.size()-1);
            }
        }
        return ll;
    }
    
    //方法2：迭代，dp
    public List<List<Integer>> combinationSum2(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { // run through all targets from 1 to t
            List<List<Integer>> newList = new ArrayList(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }
}
