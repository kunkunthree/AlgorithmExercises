package algorithm.leetcode.algorithm;
/*
 * hard
 * 164. Maximum Gap 
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */
import java.util.*;
public class NO164_MaximumGap {
	//方法1：
	//O(n)time,O(n)space
	//先得到数组最大值max和最小值min，那么可以将数组进行分组，分组范围大小为gap = ceiling[(max - min ) / (N - 1)].
	//那么最大的gap，maxGap不会比gap小
	//用两个数组进行记录每个分组里的最大值和最小值，bucketMIN，bucketMAX
	//将每个数映射到每个分组中，得到相应分组的最大值和最小值
	//初始化maxGap = gap
	//最后遍历一边分组，将每个分组的最小值和上一个分组的最大值相减得到每个分组间的gap
	//maxGap = max(maxGap,gap)
	//最后还要记得把max和最后的pre做差得到最后一个gap进行比较
	//maxGap = Math.max(maxGap, max - pre);
	public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int result = 0,n =nums.length;
        // get the max and min value of the array
        // 得到数组最大值和最小值
        int min = nums[0],max = nums[0];
        for(int i = 1 ; i < n ; i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        // the minimum possibale gap, ceiling of the integer division
        //得到最小的可能的gap，并以这个gap作为bucket进行分组
        int range = max-min;
        int gap = range%(n-1) == 0 ? range/(n-1) : range/(n-1) + 1;
        // store the min value in that bucket
        //  存储该分组的最小值
        int[] bucketMIN = new int[n-1];
        // store the max value in that bucket
        //  存储该分组的最大值
        int[] bucketMAX = new int[n-1];
        Arrays.fill(bucketMIN,Integer.MAX_VALUE);
        Arrays.fill(bucketMAX,Integer.MIN_VALUE);
        // put numbers into buckets
        //把数放到分组里
        for(int i = 0 ; i < n ; i++){
            if (nums[i] == min || nums[i] == max){
                continue;
            }
            int index = (nums[i]-min)/gap;
            bucketMIN[index] = Math.min(bucketMIN[index],nums[i]);
            bucketMAX[index] = Math.max(bucketMAX[index],nums[i]);
        }
        // scan the buckets for the max gap
        //扫描分组，得到最大的gap
        int maxGap = gap;
        int pre = min;
        for(int i = 0 ; i < n-1 ; i++){
            // empty bucket     空分组
            if(bucketMIN[i] == Integer.MAX_VALUE && bucketMAX[i] == Integer.MIN_VALUE){
                continue;
            }
            // min value minus the previous value is the current gap
            //该分组最小值减去上一个分组的最大值，即为当前的gap
            maxGap = Math.max(maxGap,bucketMIN[i] - pre);
            // update previous bucket value
            pre = bucketMAX[i];
        }
        // updata the final max value gap
        maxGap = Math.max(maxGap, max - pre);
        return maxGap;
    }
}
