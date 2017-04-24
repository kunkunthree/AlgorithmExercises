package algorithm.leetcode.algorithm;
/*
 * medium
 * 78. Subsets
 *  Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */
import java.util.*;
public class NO78_Subsets {
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		int[]nums = new int[]{1,2,3};
		System.out.println(subsets2(nums));
	}
	//方法1：
	//改写前面从1～n中抽k个数的函数，然后调用0-n的情况所有加起来就可以，但是会有大量重复计算
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        for(int i = 0 ; i <= n ; i++){
            for(List<Integer> l : combine(nums,n,i)){
                ll.add(l);
            }
        }
        return ll;
    }
    public List<List<Integer>> combine(int[] nums,int n, int k) {
        if(k == n || k == 0){
            List<Integer> list = new LinkedList<Integer>();
            for(int i = 0 ; i < k ; i++){
                list.add(nums[i]);
            }
            return new ArrayList<List<Integer>>(Arrays.asList(list));
        }
        List<List<Integer>> result = combine(nums,n-1,k-1);
		for(List<Integer> l : result){
		    l.add(nums[n-1]);
		}
		result.addAll(combine(nums,n-1,k));
		return result;
	}
    
    //方法2：
    //方法1的优化，用HashMap把计算过的结果存起来，避免重复计算
    public static List<List<Integer>> subsets2(int[] nums) {
        int n = nums.length;
        Map<List<Integer>,List<List<Integer>>> map = new HashMap<List<Integer>,List<List<Integer>>>();
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        for(int i = 0 ; i <= n ; i++){
            for(List<Integer> l : combine2(nums,n,i,map)){
                ll.add(l);
            }
        }
        return ll;
    }
    public static List<List<Integer>> combine2(int[] nums,int n, int k,Map<List<Integer>,List<List<Integer>>> map) {
        List<Integer> pair = new ArrayList<Integer>();
        pair.add(n);
        pair.add(k);
        if(map.containsKey(pair)){
            return map.get(pair);
        }
        List<List<Integer>> result;
        if(k == n || k == 0){
            List<Integer> list = new LinkedList<Integer>();
            for(int i = 0 ; i < k ; i++){
                list.add(nums[i]);
            }
            result = new ArrayList<List<Integer>>(Arrays.asList(list));
            map.put(pair,result);
            return result;
        }
        result = new ArrayList<List<Integer>>();
		for(List<Integer> l : combine2(nums,n-1,k-1,map)){
		    List<Integer> tmpl = new ArrayList<Integer>(l);
		    tmpl.add(nums[n-1]);
		    result.add(tmpl);
		}
		result.addAll(combine2(nums,n-1,k,map));
		map.put(pair,result);
		return result;
	}
    
    //方法3：
    //方法2的优化,DFS
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    private static  void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<Integer>(tempList));		//不包含start的情况
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    
    //方法4：
    //方法3的迭代形式
     public List<List<Integer>> subsets4(int[] nums) {
        
        ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums.length == 0) return ans;
        
        ans.add(new ArrayList<Integer>());
        
        for(int num: nums){
            int size = ans.size();
            for(int i = 0; i < size; i ++){
                List<Integer> tmp = new ArrayList<>(ans.get(i));
                tmp.add(num);
                ans.add(tmp);
            }
        }
        
        return ans;
    }
     
    //方法5：
    //由于该场景就是数组中每个元素是否抽取的结果，总共有2^n种结果，
    //利用位的01表示是否抽取，从0～2^n-1，这个范围内的每一个数都代表一种情况
    //利用位运算，不断右移，判断每个位是否为1，表示是否添加对应的数组中的一个元素
    public List<List<Integer>> subsets5(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        int subsetSum = (int)Math.pow(2,n);
        for(int i = 0 ; i < subsetSum ; i++){
            List<Integer> l = new ArrayList<Integer>();
            int tmp = i;
            for(int j = 0 ; j < n ; j++){
                if((tmp&1) == 1){
                    l.add(nums[j]);
                }
                tmp>>>=1;
            }
            ll.add(l);
        }
        return ll;
    }
}
