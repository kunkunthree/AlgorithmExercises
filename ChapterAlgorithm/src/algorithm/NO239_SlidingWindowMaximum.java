package algorithm;
/*
 * hard
 * 239. Sliding Window Maximum 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array 
 * to the very right. You can only see the k numbers in the window. Each time the sliding window moves 
 * right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */
import java.util.*;
public class NO239_SlidingWindowMaximum {
	//方法1：
	//利用双向链表，O(n)time，节点存储的是在数组中的位置
	//利用一个指针，表示窗口尾部，从左向右移动
	//当移动到新位置i时，
	//			1.如果链表不为空，且头部位置超出当前窗口范围，则去掉头部，循环直到头部在窗口内
	//			2.如果链表不为空，且尾部位置所指数值小于当前数值nums[i]，则去掉尾部，
	//				循环直到尾部数值大于等于nums[i]位置
	//			3.把i位置放到双向链表尾部
	//			4.如果i>=k-1,则记录链表头部数值为当前窗口最大值result[i-k+1] = nums[deque.peek()];
	//			5.窗口移动到下一个位置
	public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k <= 0){
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length-k+1];
        for(int i = 0 ; i < nums.length ; i++){
            while(!deque.isEmpty() && deque.peek() < i-k+1){
                deque.poll();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offer(i);
            if(i >= k-1){
                result[i-k+1] = nums[deque.peek()];
            }
        }
        return result;
    }
}
