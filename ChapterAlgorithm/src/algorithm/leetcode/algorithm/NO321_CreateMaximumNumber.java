package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * hard
 * 321. Create Maximum Number 
 *  Given two arrays of length m and n with digits 0-9 representing two numbers. 
 *  Create the maximum number of length k <= m + n from digits of the two. 
 *  The relative order of the digits from the same array must be preserved. 
 *  Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:

nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:

nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:

nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9] 
 */
public class NO321_CreateMaximumNumber {
	public static void main(String[] args) {
		int[] nums1 = new int[]{3,4,6,5};
		int[] nums2 = new int[]{9,1,2,5,8,3};
		int k = 5;
		System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
	}
	//方法1：
	//贪婪算法，动态规划
	//分别对两个数组用贪婪算法求len长度和k-len长度的组成最大数的数组
	//然后将两个数组合并为长度为k的数组
	//比较所有合并后的结果，取其中的最大值
	public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int get_from_nums1 = Math.min(nums1.length,k);
        int [] result = new int[k];
        for(int i = Math.max(k-nums2.length, 0) ; i <= get_from_nums1 ; i++){
            int[] res1 = getMaxNumber(nums1,i);
            int[] res2 = getMaxNumber(nums2,k-i);
            int[] res = merge(res1,res2,k);
            if(compare(res,0,result,0)){
                result = res;
            }
        }
        return result;
    }
	//合并两个数组，组成最大数值的数组
	private static  int[] merge(int[] nums1,int[] nums2,int k){
        int[] result = new int[k];
        for(int pos1 = 0,pos2 = 0,pos = 0 ; pos < k ; pos++){
            result[pos] = compare(nums1,pos1,nums2,pos2) ? nums1[pos1++] : nums2[pos2++];
        }
        return result;
    }
	//比较两个数组，判断应取哪一个数组的数，应取前缀大或前缀部分相等但更长的
    private static boolean compare(int[] nums1,int pos1,int[] nums2,int pos2){
        while(pos1 < nums1.length && pos2 < nums2.length){
            if(nums1[pos1] > nums2[pos2]){
                return true;
            }
            if(nums1[pos1] < nums2[pos2]){
                return false;
            }
            pos1++;
            pos2++;
        }
        return pos1 != nums1.length;
    }
    //贪婪算法求k位数字
    private static int[] getMaxNumber(int[] nums,int k){
        int[] result = new int[k];
        int len = 0;
        for(int i = 0 ; i < nums.length ; i++){
            while(len > 0 && nums.length-i > k-len && nums[i] > result[len-1]){
                len--;
            }
            if(len < k){
                result[len++] = nums[i];
            }
        }
        return result;
    }
}
