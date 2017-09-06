package algorithm.leetcode.algorithm;
/*
 * medium
 * 287. Find the Duplicate Number
 *  Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
 *  prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 *   find the duplicate one.

Note:

    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.

 */
public class NO287_FindtheDuplicateNumber {
	//方法1：
	//寻找环的入口，因为下标时从0开始的，0-n，但是下标指向的值时非负的，说明0是一个入口，而没有指向0的指针
	//把所有索引看做是节点，节点的值表示指向下一个节点的指针，
	//假设这些节点构成的有向图无环，那么从0指针开始为头节点的线路必然是无环的，那么一定会有一个出口指向新的节点
	//但是这些节点的值都是1-n，那么其尾节点必然会指向链表中的一点，形成环路，与无环条件相矛盾
//		Step 1:
//		The array's indices are in [0, n]. The array's values are in [1, n]. 
//		This means nums[0] will lead us to an index that is in [1, n] (because nums[0] is a value). 
//		But nobody will lead us back to index 0 (because everyone's value is in [1, n] not including 0).
//
//		Step 2:
//		Starting from index 0, we can definitely reach a cycle. Why? By contradiction. 
//		If we cannot reach a cycle, that is to say, we always meet a new index, and then meet a new index,
//		but there are only finite number of indices. So we will reach a cycle.
//
//		Step 3:
//		Then this cycle's entrace's index must be one duplicated number (because two guys lead us to it).
//
//		Step 4:
//		Since there is only one duplicated number, then that's it.
	public static int findDuplicate(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        int slow = nums[0],fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
	public static void main(String[] args) {
		int[] num = new int[]{};
	}
}
