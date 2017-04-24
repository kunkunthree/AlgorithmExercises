package algorithm.leetcode.algorithm;
/*
 * medium
 * 40. Combination Sum II 
 *  Given a collection of candidate numbers (C) and a target number (T),
 *   find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:

[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

 */
import java.util.*;
public class NO40_CombinationSumII {
	//方法1：DFS
	//特殊情况：重复数字，当前i = index时，不需要跳过重复数字，直接使用该数字并扫描下一个数字
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum2Helper(candidates,0,new ArrayList<Integer>(),target);
    }
    public List<List<Integer>> combinationSum2Helper(int[] candidates,int index,List<Integer> list,int target){
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        if(target  ==  0){
            ll.add(new ArrayList<Integer>(list));
            return ll;
        }
        if(index < candidates.length &&  candidates[index] > target){
            return ll;
        }
        for(int i = index; i < candidates.length ; i++){
            if(i > index && i < candidates.length && candidates[i] == candidates[i-1])continue;
            if(candidates[i] <= target){
                list.add(candidates[i]);
                for(List<Integer> l : combinationSum2Helper(candidates,i+1,list,target-candidates[i])){
                    ll.add(l);
                }
                list.remove(list.size()-1);
            }
        }
        return ll;
    }
}
