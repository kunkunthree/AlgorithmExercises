package algorithm.leetcode.algorithm;
/*
 * easy
 * 496. Next Greater Element I
 *  You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
 *  Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *  The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
 *   If it does not exist, output -1 for this number.

Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

Note:

    All elements in nums1 and nums2 are unique.
    The length of both nums1 and nums2 would not exceed 1000.

 */
import java.util.*;
public class NO496_NextGreaterElementI {
	public static void main(String[] args) {
		int[] findNums = new int[]{4,1,2};
		int[] nums = new int[]{1,3,4,2};
		System.out.println(Arrays.toString(nextGreaterElement2(findNums, nums)));
	}
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
    	//穷举法
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0 ; i < findNums.length ; i++){
            int j = 0;
            for(j = 0 ; j < nums.length ; j++){
                if(findNums[i] == nums[j]){
                    break;
                }
            }
            while(++j < nums.length){
                if(findNums[i] < nums[j]){
                    break;
                }
            }
            if(j < nums.length){
                list.add(nums[j]);
            }else{
                list.add(-1);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0 ; i < result.length ; i++){
            result[i] = list.get(i);
        }
        return result;
    }
    //利用栈，假设当前扫描到的数为nums[i],
    //如果栈不为空且栈顶元素小于nums[i]，则用map以栈顶元素为key，当前数为value存储起来，栈顶元素出栈，
    //循环以上操作，直到栈为空或者栈顶元素小于等于nums[i],则把nums[i]压进栈，扫描下一个元素nums[i+1]
    public static int[] nextGreaterElement2(int[] findNums, int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0 ; i < nums.length ; i++){
            while(!stack.isEmpty() && stack.peek() < nums[i]){
                map.put(stack.pop(),nums[i]);
            }
            stack.push(nums[i]);
        }
        for(int i = 0 ; i < findNums.length ; i++){
            if(map.containsKey(findNums[i])){
                findNums[i] = map.get(findNums[i]);
            }else{
                findNums[i] = -1;
            }
        }
        return findNums;
    }
}
