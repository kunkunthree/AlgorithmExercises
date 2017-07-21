package algorithm.leetcode.algorithm;
/*
 * medium
 * 46. Permutations
 *  Given a collection of distinct numbers, return all possible permutations.
 For example,
 [1,2,3] have the following permutations:

 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

similar problems:
31. Next Permutation 
47. Permutations II 
60. Permutation Sequence 
77. Combinations 
 */
import java.util.*;
public class NO46_Permutations {
	public static void main(String[] args) {
		Integer[] a = new Integer[]{1,2,3};
		System.out.println(new ArrayList<Integer>(Arrays.asList(a)));
		List<Integer> rest = new ArrayList<Integer>();
	}
	
	//方法1：DFS，递归
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> save = new ArrayList<Integer>();
        List<Integer> rest = new ArrayList<Integer>();
        for(int i = 0 ; i < nums.length ; i++){
            rest.add(nums[i]);
        }
        return permuteHelper(nums.length,save,rest);
    }
    public List<List<Integer>> permuteHelper(int length,List<Integer> save,List<Integer> rest){
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        if(save.size() == length){
            ll.add(new ArrayList<Integer>(save));
            return ll;
        }
        for(int i = 0 ; i < rest.size() ; i++){
            int tmp = rest.get(i);
            save.add(rest.get(i));
            rest.remove(i);
            for(List<Integer> l : permuteHelper(length,save,rest)){
                ll.add(l);
            }
            rest.add(i,tmp);
            save.remove(save.size()-1);
        }
        return ll;
    }
	//方法2：全排列算法
    //算法思路：
    //(1)n个元素的全排列=（n-1个元素的全排列）+（另一个元素作为前缀）；
    //(2)出口：如果只有一个元素的全排列，则说明已经排完，则输出数组；
    //(3)不断将每个元素放作第一个元素，然后将这个元素作为前缀，并将其余元素继续全排列，
    //		等到出口，出口出去后还需要还原数组；
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        perm(nums,ll,0,nums.length);
        return ll;
    }
    public void swap(int[] nums,int i,int j){
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
    public void perm(int[] nums,List<List<Integer>> ll,int start,int end){
        //出口
    	if(start == end){
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 0 ; i < end ; i++){
                list.add(nums[i]);
            }
            ll.add(list);
            return;
        }
        for(int i = start ; i < end ; i++){
            swap(nums,start,i);					//for循环将start~end中的每个数放到start位置中去  
            perm(nums,ll,start+1,end);	//假设start位置确定，那么对start+1~end中的数继续递归
            swap(nums,start,i);					//换过去后再还原
        }
    }
    
    //方法3：
    //迭代，遍历一边数组，把每一个遍历到的数从当前得到的结果集中进行不同位置的插入
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        for(int i = 0 ; i < nums.length ; i++){
            List<List<Integer>> tmpResult = new ArrayList<List<Integer>>();
            for(int j = 0 ; j <= i ; j++){
                for(List<Integer> list : result){
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(j,nums[i]);
                    tmpResult.add(newList);
                }
            }
            result = tmpResult;
        }
        return result;
    }
}
