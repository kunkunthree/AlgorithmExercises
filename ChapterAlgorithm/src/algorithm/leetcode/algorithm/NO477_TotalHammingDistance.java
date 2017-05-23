package algorithm.leetcode.algorithm;
/*
 * medium
 * 477. Total Hamming Distance 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits 
 * are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2
Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Note:

    1.	Elements of the given array are in the range of 0 to 10^9
    2.	Length of the array will not exceed 10^4.

 */
public class NO477_TotalHammingDistance {
	//方法1：
	//O(n)time,O(1)space
	//计算出每一位的0和1的个数，0和1的组合数就是这一位的各个数之间的Hamming Distance 
	//把每一位的各个数之间的Hamming Distance 加起来就是所有数之间的Hamming Distance 
	public int totalHammingDistance(int[] nums) {
        int length = nums.length,tmpZeroCount,count = 0;
        for(int i = 0 ; i < 32 ; i++){
            tmpZeroCount = 0;
            for(int j = 0 ; j < length ; j++){
                if(((nums[j]>>i)&1) == 0){
                    tmpZeroCount++;
                }
            }
            count+=tmpZeroCount*(length-tmpZeroCount);
        }
        return count;
    }
}
