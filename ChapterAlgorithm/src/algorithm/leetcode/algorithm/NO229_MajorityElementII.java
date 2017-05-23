package algorithm.leetcode.algorithm;
/*
 * medium
 * 229. Majority Element II
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
 * The algorithm should run in linear time and in O(1) space.
 */
import java.util.*;
public class NO229_MajorityElementII {
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,3,4};
		System.out.println(majorityElement(nums));
	}
	//方法1：
	//先找出出现次数最多的n个元素，再分别对其进行计数，看是否出现频率大于总数的三分之一
	//O(n)time,O(1)space
	public static List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = 2;
        int[] maj = new int[n];
        int[] count = new int[n];
        boolean existed = false;
        for(int i = 0 ; i < nums.length ; i++){
            existed = false;
            //先判断是否已经在候选当中
            for(int j = 0 ; j < n ; j++){
                if(maj[j] == nums[i]){
                    count[j]++;
                    existed = true;
                    break;
                }
            }
            //若不在候选当中，那么看候选当中是否有位置填入
            if(existed == false){
                for(int j = 0 ; j < n ; j++){
                    if(count[j] == 0){
                        count[j] = 1;
                        maj[j] = nums[i];
                        existed = true;
                        break;
                    }
                }
            }
            //如果既不在候选当中，又没有位置填入，那么所有候选元素计数减一
            if(existed == false){
                for(int j = 0 ; j < n ; j++){
                    count[j]--;
                }
            }
        }
        //对所有计数不为0的候选元素进行计数，如果计数大于n/3，那么加入list中，
        //如果想找出大于nums.length/4的所有元素，则修改变量n为4即可
        for(int j = 0 ; j < n ; j++){
            if(count[j] == 0)continue;
            count[j] = 0;
            for(int i = 0 ; i < nums.length ; i++){
                if(nums[i] == maj[j]){
                    count[j]++;
                }
            }
            if(count[j] > nums.length / 3){
                list.add(maj[j]);
            }
        }
        return list;
    }
}
