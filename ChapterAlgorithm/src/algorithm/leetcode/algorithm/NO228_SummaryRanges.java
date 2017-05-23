package algorithm.leetcode.algorithm;
/*
 * medium
 * 228. Summary Ranges
 *  Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]. 
 */
import java.util.*;
public class NO228_SummaryRanges {
	//方法1：
	//
	public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return list;
        }
        int start = nums[0],end = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] != nums[i-1]+1){
                if(start == end){
                    list.add(start+"");
                }else{
                    list.add(start+"->"+end);
                }
                start = nums[i];
                end = nums[i];
            }else{
                end = nums[i];
            }
        }
        if(start == end){
            list.add(start+"");
        }else{
            list.add(start+"->"+end);
        }
        return list;
    }
	//方法2：
	//简洁写法
	public List<String> summaryRanges2(int[] nums) {
	    int length = nums.length;
	    List<String> result = new ArrayList<String>(length);
	    for (int i = 0; i < length; i++) {
	        int num = nums[i];
	        while (i < length - 1 && nums[i] + 1 == nums[i + 1]) {
	            i++;
	        }
	        if (num != nums[i]) {
	            result.add(num + "->" + nums[i]);
	        } else {
	            result.add(num + "");
	        }
	    }
	    return result;
	}
}
