package algorithm.leetcode.algorithm;
/*
 * medium
 * 659. Split Array into Consecutive Subsequences 
 * You are given an integer array sorted in ascending order (may contain duplicates), 
 * you need to split them into several subsequences, where each subsequences consist of 
 * at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5

Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5

Example 3:
Input: [1,2,3,4,4,5]
Output: False

Note:
    The length of the input is in range of [1, 10000]

 */
import java.util.*;
public class NO659_SplitArrayIntoConsecutiveSubsequences {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
	}
	//方法1：
	//O(n)time,O(n)space
	//贪婪算法：尽量在当前已经遍历构成数量最少的符合的数列
	//用一个map保存当前可用数的频率freq，用一个map保存当前以某个数num-1结尾的长度大于等于3的个数appendFreq
	//1.先遍历一遍数组，统计每个数出现的频率，用一个map freq包存起来
	//2.再遍历一遍数组，当前数为num，
	//		2.1如果当前数在freq中对应为0，则表示已经在前面组成了数列中第二个或第三个数，跳过
	//		2.2如果当前数在freq中对应不为0，则说明还未处理，那么
	//			2.2.1 如果在appendFreq中的数大于0，则表示可以将该数添加到结尾的数列个数大于0，
	//						那么就直接将该数添加到其中某个数列中，对应个数减1
	//			2.2.2 否则在appendFreq中的数小于等于0，如果num+1和num+2在freq中个数都大于0，
	//						那么说明可以以num为开头建立一个新的长度大于等于3的数列，把num+1和num+2在freq中对应的个数-1
	//						在appendFreq中num+3的个数+1
	//			2.2.3	如果以上都不符合，即不能添加到数列中，也不能构成新数列，那么就返回false
	//			freq中num对应的个数-1
	public boolean isPossible(int[] nums) {
        Map<Integer,Integer> freq = new HashMap<>(), appendFreq = new HashMap<>();
        for(int num : nums){
            if(!freq.containsKey(num)){
                freq.put(num,0);
            }
            freq.put(num,freq.get(num)+1);
        }
        for(int num : nums){
            if(freq.get(num) == 0){
                continue;
            }else if(appendFreq.containsKey(num) && appendFreq.get(num) > 0){
                appendFreq.put(num,appendFreq.get(num)-1);
                if(!appendFreq.containsKey(num+1)){
                    appendFreq.put(num+1,0);
                }
                appendFreq.put(num+1,appendFreq.get(num+1)+1);
            }else if(freq.containsKey(num+1) && freq.get(num+1) > 0 
            		&& freq.containsKey(num+2) && freq.get(num+2) > 0){
                freq.put(num+1,freq.get(num+1)-1);
                freq.put(num+2,freq.get(num+2)-1);
                if(!appendFreq.containsKey(num+3)){
                    appendFreq.put(num+3,0);
                }
                appendFreq.put(num+3,appendFreq.get(num+3)+1);
            }else{
                return false;
            }
            freq.put(num,freq.get(num)-1);
        }
        return true;
    }
}
