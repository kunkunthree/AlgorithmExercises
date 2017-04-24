package algorithm.leetcode.algorithm;
/*
 * medium
 * 55. Jump Game
 *  Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false. 
 */
import java.util.*;
public class NO55_JumpGame {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,1,1,0,5};
		System.out.println(canJump2(nums));
	}
	//方法1：
	//迭代，判断当前位置是否可达，如果不可达则返回false；
	//如果可达，比较当前位置的下一步可达位置，更新最远可达距离
	public boolean canJump(int[] nums) {
        int max = 0;
        for(int i=0;i<nums.length;i++){
            if(i>max) {return false;}
            max = Math.max(nums[i]+i,max);
        }
        return true;
    }
	//超时，太慢
    public static boolean canJump2(int[] nums) {
        int right = nums.length-1,left = right-1;
        while(left >= 0){
            if(left + nums[left] >= right){
                right = left;
            }
            left--;
        }
        return right == 0 ? true : false;
    }
    //超时，太慢
    public static boolean canJump3(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int left = 0,right,max;
        if(nums[0] < 0 && nums.length > 0){
            return false;
        }
        stack.push(left);
        right = left + nums[left];
        max = right;
        while(!stack.isEmpty() && max < nums.length-1){
            if(stack.peek() < right){
                if(right + nums[right] > max){
                    max = right + nums[right];
                    stack.push(right);
                    right = max;
                }else{
                    right--;
                }
            }else{
                stack.pop();
            }
        }
        return max >= nums.length-1 ? true : false;
    }
}
