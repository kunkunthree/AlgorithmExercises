package algorithm.leetcode.algorithm;
/*
 * medium
 * 220. Contains Duplicate III
 * Given an array of integers, find out whether there are two distinct indices i and j in the array 
 * such that the absolute difference between nums[i] and nums[j] is at most t 
 * and the absolute difference between i and j is at most k. 
 */
import java.util.*;
public class NO220_ContainsDuplicateIII {
	//方法1：
	//用bucket只装一个数，bucket大小为t+1，滑动窗口法。
	//如果当前值将要放进的bucket已经有数放入了，说明前后k范围内存在差的绝对值小于等于t，则返回true
	//如果当前bucket不存在，则与上一个bucket和下一个bucket比较
	//如果上一个bucket存在数，且差的绝对值小于等于t，则返回true
	//同理，如果下一个bucket存在数，且差的绝对值小于等于t，则返回true
	//因为每个bucket只可能放1个数，且当i大于等于k时，把nums[i-k]从map中去除，
	//所以当前map存在的值都是在[i-k,i-1]范围内的数
	//如果遍历完都没有符合的，就返回false。
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(k < 1 || t < 0){
            return false;
        }
        Map<Long,Long> map = new HashMap<>();
        long bucket = (long)t + 1;
        for(int i = 0 ; i < nums.length ; i++){
            long value = (long)nums[i] - Integer.MIN_VALUE;
            long key = value / bucket;
            if(map.containsKey(key) 
                || map.containsKey(key-1) && value - map.get(key-1) <= t
                || map.containsKey(key+1) && map.get(key+1) - value <= t){
                    return true;
            }
            map.put(key,value);
            if(i >= k){
                map.remove(((long)nums[i-k] - Integer.MIN_VALUE)/bucket);
            }
        }
        return false;
    }
}
