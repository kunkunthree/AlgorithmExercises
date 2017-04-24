package algorithm.leetcode.algorithm;
/*
 * easy
 *  Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
 *   Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array
 *    and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Note:
    The pairs (i, j) and (j, i) count as the same pair.
    The length of the array won't exceed 10,000.
    All the integers in the given input belong to the range: [-1e7, 1e7].

 */
import java.util.*;
public class NO532_K_diffPairsinanArray {
    public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5};
		int k = 2;
		System.out.println(findPairs(nums, k));
	}
    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int tmp;
        boolean newFlag = false;
        for(int i = 0 ; i < nums.length-1 ; i++){
            int j = i+1;
            while(j < nums.length && nums[j] - nums[i] < k){
                j++;
            }
            if(j < nums.length && nums[j] - nums[i] == k){
                count++;
            }
            while(i+1 < nums.length && nums[i] == nums[i+1]){
                i++;
            }
        }
        return count;
    }
    //超时
//	public static int findPairs(int[] nums, int k) {
//        Arrays.sort(nums);
//        HashMap<Integer,Integer> countMap = new HashMap<Integer,Integer>();
//        HashMap<Integer,Set<Integer>> appMap = new HashMap<Integer,Set<Integer>>();
//        int x,y;    //x >= y
//        Set<Integer> set;
//        for(int i = 0 ; i < nums.length ; i++){
//            for(int j = 0 ; j < nums.length ; j++){
//                if(i != j){
//                    if(nums[i] > nums[j]){
//                        x = nums[i];
//                        y = nums[j];
//                    }else{
//                        x = nums[j];
//                        y = nums[i];
//                    }
//                    if(appMap.containsKey(x)){
//                        set = appMap.get(x);
//                    }else{
//                        set = new HashSet<Integer>();
//                        appMap.put(x,set);
//                    }
//                    if(!set.contains(y)){
//                        if(countMap.containsKey(x-y)){
//                            countMap.put(x-y,countMap.get(x-y)+1);
//                        }else{
//                            countMap.put(x-y,1);
//                        }
//                        set.add(y);
//                    }
//                }
//            }
//        }
//        if(countMap.containsKey(k)){
//            return countMap.get(k);
//        }
//        return 0;
//    }
}
