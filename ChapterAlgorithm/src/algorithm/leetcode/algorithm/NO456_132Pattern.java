package algorithm.leetcode.algorithm;
/*
 * medium
 * 456. 132 Pattern 
 *  Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak 
 *  such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input 
 *  and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]
Output: False

Explanation: There is no 132 pattern in the sequence.

Example 2:
Input: [3, 1, 4, 2]
Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

Example 3:
Input: [-1, 3, 2, 0]
Output: True
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

 */
import java.util.*;
public class NO456_132Pattern {
	//方法1：
	//利用stack，stack顶部一直保存当前最小的不重叠的区间
	//如果当前stack为空或者stack顶部区间最小值比当前值num大，则把区间[num,num]压进stack
	//如果num比stack顶端区间最小值要大，则判断是否比最大值小，如果比最大值小，则满足132模式，返回true
	//如果比最大值大，则更新顶端区间最大值
	//			由于当前区间最大值被更新，所以有可能会与之前的区间重叠，那么stack顶端区间出栈存为last区间
	//			如果last区间最大值大于等于当前stack顶端区间最大值，则覆盖该区间，stack顶端区间出栈，
	//					直到与stack顶端区间不重叠，或则stack为空为止
	//			如果stack非空且last区间最大值比当前stack顶端区间最小值大，则last最大值在此区间内，满足132
	public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        Stack<int[]> stack = new Stack<int[]>();
        int length = nums.length,num;
        int[] last;
        for(int i = 0 ; i < length ; i++){
            num = nums[i];
            if(stack.isEmpty() || num < stack.peek()[0]){
                stack.push(new int[]{num,num});
            }else if(num > stack.peek()[0]){
                last = stack.pop();
                if(last[1] > num){
                    return true;
                }else{
                    last[1] = num;
                    while(!stack.isEmpty() && num >= stack.peek()[1]){
                        stack.pop();
                    }
                    if(!stack.isEmpty() && num > stack.peek()[0]){
                        return true;
                    }
                    stack.push(last);
                }
            }
        }
        return false;
    }
	//方法2：
	//迭代，不用stack，
	//找出每一个递增区间，判断后面有没有元素在这个区间之间
	public boolean find132pattern2(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        Stack<int[]> stack = new Stack<int[]>();
        int length = nums.length,i = 0;
        while(i < length-1){
            while(i < length-1 && nums[i] >= nums[i+1]){
                i++;
            }
            int j = i+1;
            while(j<length-1 && nums[j] <= nums[j+1]){
                j++;
            }
            for(int k = j+1 ; k < length ; k++){
                if(nums[k] > nums[i] && nums[k] < nums[j]){
                    return true;
                }
            }
            i = j+1;
        }
        return false;
    }
}
