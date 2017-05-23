package algorithm.leetcode.algorithm;
/*
 * medium
 * 209. Minimum Size Subarray Sum
 *  Given an array of n positive integers and a positive integer s, 
 *  find the minimal length of a contiguous subarray of which the sum ≥ s. 
 *  If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint. 
 */
public class NO209_MinimumSizeSubarraySum {
	//方法1：
	//变长滑动窗口法，用两个指针一前left一后right，left和right 初始位置都为-1，这个初始值考虑到数组为空的情况
	//进入循环，条件为right<数组总长度length
	//指针如果两者中间的值大于等于s，则长度right-left与最小值min比较，更新min，则left指针右移，
	//如果left右移后与right重合，则退出循环
	//如果小于s，则right指针右移
	public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE,sum = 0;
        int left = -1,right = -1,length = nums.length;
        while(right < length){
            if(sum < s){
                right++;
                //考虑到数组为空的情况，nums[right]加入总和时要判断right是否合法
                if(right < length)sum+=nums[right];
            }else {
                min = Math.min(min,right - left);
                left++;
                sum-=nums[left];
                if(left == right){
                    break;
                }
            }
        }
        if(right - left > length){
            min = 0;
        }
        return min;
    }
	//方法2：
	//更简单写法
	public int minSubArrayLen2(int s, int[] a) {
		  if (a == null || a.length == 0)
		    return 0;
		  
		  int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
		  
		  while (j < a.length) {
		    sum += a[j++];
		    
		    while (sum >= s) {
		      min = Math.min(min, j - i);
		      sum -= a[i++];
		    }
		  }
		  
		  return min == Integer.MAX_VALUE ? 0 : min;
		}
}
