package algorithm.leetcode.algorithm;
/*
 * medium
 * 80. Remove Duplicates from Sorted Array II
 *  Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
It doesn't matter what you leave beyond the new length. 
 */
import java.util.*;
public class NO80_RemoveDuplicatesfromSortedArrayII {
	//方法1：
	//统计每个数字出现的次数，当出现次数达到2时，则跳到下一个不同的数字重新统计次数
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int count = 0,tmpCount = 0;
        for(int i = 0 ; i < n ; i++){
            count++;
            if(i > 0 && nums[i] == nums[i-1]){
                tmpCount++;
            }else{
                tmpCount = 1;
            }
            nums[count-1] = nums[i];
            if(tmpCount == 2){
              while(i+1 < n && nums[i] == nums[i+1]){
                  i++;
              }
            }
        }
        return count;
    }
    //方法2
    //用两个指针遍历，一个指针fast用于遍历整个数组，一个指针slow用于指向去除多出元素后的下标
    //只有当nums[fast]比nums[slow-2]大时，才能把nums[slow]置为nums[fast]，slow++,fast++;
    //特殊情况，slow<2时，即slow=0或1时，这是数字出现次数必然小于等于2，所以不做处理
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }
    
    //方法2的扩展:
    //去除出现次数超过n的多余元素，保留元素出现次数最大为n
    public int removeDuplicates2(int[] nums,int n) {
    	//Arrays.sort(nums);		//注意，该数组必须时有序的
        int i = 0;
        for (int num : nums)
            if (i < n || num > nums[i-n])
                nums[i++] = num;
        return i;
    }
}
