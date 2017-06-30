package algorithm.leetcode.algorithm;
/*
 * easy
 * 628. Maximum Product of Three Numbers 
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6

Example 2:
Input: [1,2,3,4]
Output: 24

Note:
    1.		The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
    2.		Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

 */
import java.util.*;
public class NO628_MaximumProductOfThreeNumbers {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4};
		System.out.println(maximumProduct(nums));
	}
	//方法1：
	//求最大的三个数和最小的两个数
	//最终结果取Math.max(max[0]*max[1]*max[2],max[0]*min[0]*min[1]);
	//因为有可能有负数
	public  static int maximumProduct(int[] nums) {
        int N = 3;
        if(nums == null || nums.length < N){
            return 0;
        }
        if(nums.length == N){
            int result = 1;
            for(int num : nums){
                result*=num;
            }
            return result;
        }
        int[] max = new int[N];
        Arrays.fill(max,Integer.MIN_VALUE);
        int[] min = new int[N-1];
        Arrays.fill(min,Integer.MAX_VALUE);
        for(int num : nums){
            for(int i = 0 ; i < N ; i++){
                if(num > max[i]){
                    for(int j = N-1 ; j >= i+1 ; j--){
                        max[j] = max[j-1];
                    }
                    max[i] = num;
                    break;
                }
            }
            for(int i = 0 ; i < N-1 ; i++){
                if(num < min[i]){
                    for(int j = N-2 ; j >= i+1 ; j--){
                        min[j] = min[j-1];
                    }
                    min[i] = num;
                    break;
                }
            }
        }
        return Math.max(max[0]*max[1]*max[2],max[0]*min[0]*min[1]);
    }
	//方法2：
	//同方法1
	public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }
	
	//方法3：
	//先排序
	public int maximumProduct3(int[] nums) {
        
        Arrays.sort(nums);
        //One of the Three Numbers is the maximum value in the array.

        int a = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        int b = nums[0] * nums[1] * nums[nums.length - 1];
        return a > b ? a : b;
   }
}
