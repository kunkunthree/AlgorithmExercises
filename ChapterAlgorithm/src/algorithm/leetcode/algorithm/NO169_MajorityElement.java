package algorithm.leetcode.algorithm;
/*
 * easy
 * 169. Majority Element 
 * Given an array of size n, find the majority element. 
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class NO169_MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int time = 1;
        for(int i = 1 ; i < nums.length ; i++){
            if(time == 0){
                candidate = nums[i];
                time++;
            }else{
                if(candidate == nums[i]){
                    time++;
                }else{
                    time--;
                }
            }
        }
        return candidate;
    }
}
