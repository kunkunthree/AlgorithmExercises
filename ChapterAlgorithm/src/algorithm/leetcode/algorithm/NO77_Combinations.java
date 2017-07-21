package algorithm.leetcode.algorithm;
/*
 * medium
 * 77. Combinations
 *  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

similar problems:
39. Combination Sum 
46. Permutations 
 */
import java.util.*;
public class NO77_Combinations {
	//方法1：
	//经过修正的全排列算法，由于得到的结果都是没有重复的升序的数组，
	//所以在递归的时候需要判断是否begin为0或当begin>0是和begin位置交换的数是否比begin前面的一个数大
	//只有符合情况的时候才进行交换排列
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        int[] nums = new int[n];
        for(int i = 0 ; i < n ; i++){
            nums[i] = i+1;
        }
        perm(nums,0,n,0,k,ll);
        return ll;
    }
    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void perm(int[] nums,int begin,int end,int count,int k,List<List<Integer>> ll){
        if(count == k){
            List<Integer> l = new ArrayList<Integer>();
            for(int i = 0 ; i < k ; i++){
                l.add(nums[i]);
            }
            ll.add(l);
            return;
        }
        for(int i = begin ; i < end ; i++){
            if(begin == 0 || begin > 0 && nums[i] > nums[begin-1]){
                swap(nums,begin,i);
                perm(nums,begin+1,end,count+1,k,ll);
                swap(nums,begin,i);
            }
        }
    }
    //方法2：
    //方法1的优化，不需要判断是否过长，不需要进行交换，只要直接对每个下标递归，
    //剩余长度小于k的不进行任何操作而被过滤掉
    public static List<List<Integer>> combine2(int n, int k) {
		List<List<Integer>> combs = new ArrayList<List<Integer>>();
		combineHelper(combs, new ArrayList<Integer>(), 1, n, k);
		return combs;
	}
	public static void combineHelper(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
		if(k==0) {
			combs.add(new ArrayList<Integer>(comb));
			return;
		}
		for(int i=start;i<=n;i++) {
			comb.add(i);
			combineHelper(combs, comb, i+1, n, k-1);
			comb.remove(comb.size()-1);
		}
	}
	//方法3：
	//公式法，题目为从1~n中抽k个数字,不计顺序，也就是C(n,k)，
	//分两种情况，第一种选n，也就是剩下n-1中抽k-1,即C(n-1,k-1)（add(n)）
	//第二种是不选n,也就是从剩下n-1中抽k-1个，即C(n-1,k)
    public List<List<Integer>> combine3(int n, int k) {
        if(k == n || k == 0){
            List<Integer> list = new LinkedList<Integer>();
            for(int i = 1 ; i <= k ; i++){
                list.add(i);
            }
            return new ArrayList<List<Integer>>(Arrays.asList(list));
        }
        List<List<Integer>> result = combine(n-1,k-1);
		for(List<Integer> l : result){
		    l.add(n);
		}
		result.addAll(combine(n-1,k));
		return result;
	}
}
