package algorithm.leetcode.algorithm;
/*
 * medium
 * 368. Largest Divisible Subset
 *  Given a set of distinct positive integers, 
 *  find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)

Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]

 */
import java.util.*;
public class NO368_LargestDivisibleSubset {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		System.out.println(largestDivisibleSubset(nums));
	}
	//方法1：
	//O(n^2)time
	//dp
	public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> lds = new ArrayList<>();
        List<Integer>[] dp = new List[n];
        for(int i = 0 ; i < n ; i++){
            for(int j = i-1 ; j >=0 ; j--){
                if(nums[i]%nums[j] == 0){
                    if(dp[i] == null || dp[j].size() >= dp[i].size()){
                        dp[i] = new ArrayList<Integer>(dp[j]);
                    }
                }
            }
            if(dp[i] == null){
                dp[i] = new ArrayList<Integer>();
            }
            dp[i].add(nums[i]);
            if(dp[i].size() > lds.size()){
                lds = dp[i];
            }
        }
        return lds;
    }
	//方法2：
	//方法1的优化，节省额外空间，利用一个数组记录序列的上一个序号，等遍历完再从新得到该序列
	public List<Integer> largestDivisibleSubset2(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }
}
