package algorithm.leetcode.algorithm;
/*
 * medium
 * 260. Single Number III
 *  Given an array of numbers nums, in which exactly two elements appear only once 
 *  and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:

    The order of the result is not important. So in the above example, [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. 
    Could you implement it using only constant space complexity?

 */
public class NO260_SingleNumberIII {
	//方法1：
	//两次遍历，假设只出现1次的数为a和b
	//第一次遍历得到a和b的异或即a^b
	//a^b得到的是a和b不相同的位
	//那么只要找到其中一个不相同的位，把数组中这个位不相同的数分为2组，一组为1，一组为0
	//因为除了a和b的其他元素都是出现2次，所以相同元素都会分到同一组，那么a和b必然分到不同的组
	//那么这两个组的共同特点是：只有1个元素出现1次，其他元素都出现2次
	//那么只要对这两个组分别进行异或，最终结果就分别是a和b
	public int[] singleNumber(int[] nums) {
        int ab = 0;
        for(int i = 0 ; i < nums.length ; i++){
            ab^=nums[i];
        }
        int bit = ab&(-ab);
        int a = 0,b = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if((nums[i]&bit) == 0){
                a^=nums[i];
            }else{
                b^=nums[i];
            }
        }
        return new int[]{a,b};
    }
}
