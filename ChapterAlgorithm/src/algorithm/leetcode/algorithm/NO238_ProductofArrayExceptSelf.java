package algorithm.leetcode.algorithm;
/*
 * medium
 * 238. Product of Array Except Self
 *  Given an array of n integers where n > 1, nums, return an array output 
 *  such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6]. 

Follow up:
Could you solve it with constant space complexity? 
(Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class NO238_ProductofArrayExceptSelf {
	//方法1：
	//如果数组中0的个数大于等于2，则所有输出都为0
	//如果数组中0的个数等于1，则除0的位置以外的所有输出都为0
	//如果数组中0的个数等于0，则所有位置输出都不为0
	//所以只需要先统计0的个数，和计算非0元素的乘积，再决定输出即可
	public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] output = new int[length];
        int zeroCount = 0;
        int product = 1;
        for(int i = 0 ; i < length ; i++){
            if(nums[i] == 0){
                zeroCount++;
            }else{
                product*=nums[i];
            }
        }
        for(int i = 0 ; i < length ; i++){
            if(zeroCount == 1 && nums[i] == 0){
                output[i] = product;
            }else if(zeroCount == 0){
                output[i] = product/nums[i];
            }
        }
        return output;
    }
	//方法2：
	//通过先后得到左右两边的乘积，最后得到最终的结果
	public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] output = new int[length];
        output[0] = 1;
        //先计算左边元素相乘的结果
        for(int i = 1 ; i < length ; i++){
            output[i] = output[i-1] * nums[i-1];
        }
        //再计算右边元素相乘的结果,最后相乘得到左右两边相乘的积
        int right = 1;
        for(int i = length-1 ; i >= 0 ; i--){
           output[i]*=right;
           right*=nums[i];
        }
        return output;
    }
}
