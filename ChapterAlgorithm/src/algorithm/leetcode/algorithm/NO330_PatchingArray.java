package algorithm.leetcode.algorithm;
/*
 * hard
 * 330. Patching Array 
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array 
 * such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. 
 * Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.
 */
public class NO330_PatchingArray {
	//方法1：
	//贪婪算法：
	//假设miss是当前[0,n]中缺少的最小的数，那么[0,miss-1]是目前部分和能够组成的范围，
	//那么当前首要选择是增加miss这个数，因为这样能最大范围地扩增部分和的范围，
	//使当前能组成的范围变成[0,2*miss-1]，即新的miss变成原来的2倍
	//那么只要遍历当前的数组，如果当前元素小于等于miss，那么就直接扩展当前的范围，miss直接增加nums[i]
	//范围变成[0,miss-1+nums[i]]，
	//如果当前元素大于miss，或者已经遍历完数组中的元素，那么只要增加miss元素，使miss直接增加miss大小
	//范围变成[0,miss*2-1]
	public int minPatches(int[] nums, int n) {
        long miss = 1;
        int count = 0,i = 0;
        while(miss <= n){
            if(i < nums.length && nums[i] <= miss){
                miss+=nums[i++];
            }else{
                miss<<=1;
                count++;
            }
        }
        return count;
    }
}
