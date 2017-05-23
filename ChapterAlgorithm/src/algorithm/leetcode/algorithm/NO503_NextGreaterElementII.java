package algorithm.leetcode.algorithm;
/*
 * medium
 * 503. Next Greater Element II 
 *  Given a circular array (the next element of the last element is the first element of the array), 
 *  print the Next Greater Number for every element. 
 *  The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
 *  which means you could search circularly to find its next greater number. 
 *  If it doesn't exist, output -1 for this number.

Example 1:

Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.

Note: The length of given array won't exceed 10000. 
 */
import java.util.*;
public class NO503_NextGreaterElementII {
	//方法1：
	//利用stack，保存当前还未遇到比其大的值的下标，用map把下标和其对应的下一个比它指向的数大的数保存起来
	//当遇到比stack顶端大的值num时，stack出栈，map.put(stack.pop(),num);
	//一直执行map.put(stack.pop(),num)，直到stack为空或者stack顶端的值所指向的数比num大为止，
	//			num进栈，stack.push(num)
	//当遇到num小于等于stack顶端时,num进栈，stack.push(num)
	public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        int length = nums.length,num,index;
        for(int i = 0 ; i < 2*length-1 ; i++){
            index = i%length;
            num = nums[index];
            if(stack.isEmpty() || nums[stack.peek()] >= num){
                stack.push(index);
            }else{
                while(!stack.isEmpty() && nums[stack.peek()] < num){
                    map.put(stack.pop(),num);
                }
                stack.push(index);
            }
        }
        int[] result = new int[length];
        for(int i = 0 ; i < length ; i++){
            if(map.containsKey(i)){
                result[i] = map.get(i);
            }else{
                result[i] = -1;
            }
        }
        return result;
    }
	//方法2：
	//方法1的优化
	//利用stack，保存当前还未遇到比其大的值的下标
	//当遇到比stack顶端大的值num时，stack出栈，result[stack.pop()] = num,
	//一直执行result[stack.pop()] = num，直到stack为空或者stack顶端的值所指向的数比num大为止，
	//			num对应的下标index进栈，stack.push(index)
	//当遇到num小于等于stack顶端时,num对应的下标index进栈，stack.push(index)
	public int[] nextGreaterElements2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();
        int length = nums.length,num,index;
        int[] result = new int[length];
        Arrays.fill(result,-1);
        for(int i = 0 ; i < 2*length-1 ; i++){
            index = i%length;
            num = nums[index];
            if(stack.isEmpty() || nums[stack.peek()] >= num){
                stack.push(index);
            }else{
                while(!stack.isEmpty() && nums[stack.peek()] < num){
                    result[stack.pop()] = num;
                }
                stack.push(index);
            }
        }
        return result;
    }
}
