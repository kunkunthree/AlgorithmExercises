package algorithm.leetcode.algorithm;
/*
 * medium
 * 47. Permutations II
 *  Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:

[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

 */
import java.util.*;
public class NO47_PermutationsII {
	public static void main(String[] args) {
		int[] nums = new int[]{1,1,2};
		System.out.println(permuteUnique2(nums));
	}
	//方法1：DFS，递归
	//先对nums数组排序，把重复出现的排在一起，每次递归都只添加唯一的数字，然后继续排序
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
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
            if(i > 0 && rest.get(i) == rest.get(i-1))continue;
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
    //方法2：经过修改的全排列算法
    //解析：由于有重复出现的元素，所以先排序，然后把原来每次递归交换start和i的函数改为
    //每次递归前把第i个元素移动到第start个元素的位置，第start到i-1个元素依次往后移
    //还原时，把第start个元素移动到第i个元素的位置，第start+1到第i个元素依次往前移
    public static List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        perm(nums,ll,0,nums.length);
        return ll;
    }
    public static void moveTo(int[] nums,int i ,int j){
    	//move i to j
    	if(i < j){	//如果是从前往后移,则j及其前面直到第i+1项依次往前移一项，第i项移动到第j项
    		int tmp = nums[i];
    		while(i < j){
    			nums[i] = nums[i+1];
    			i++;
    		}
    		nums[j] = tmp;
    	}else if( i > j){	//如果是从后往前移,则j及其后面面直到第i-1项依次往后移一项，第i项移动到第j项
    		int tmp = nums[i];
    		while(i > j){
    			nums[i] = nums[i-1];
    			i--;
    		}
    		nums[j] = tmp;
    	}
    }
    public static void perm(int[] nums,List<List<Integer>> ll,int start,int end){
        if(start == end){
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 0 ; i < end ; i++){
                list.add(nums[i]);
            }
            ll.add(list);
            return;
        }
        for(int i = start ; i < end ; i++){
            if(i > start && nums[i] == nums[i-1])continue;
            moveTo(nums,i,start);			//
            perm(nums,ll,start+1,end);
            moveTo(nums,start,i);
        }
    }
}
