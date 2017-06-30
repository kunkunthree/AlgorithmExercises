package algorithm.leetcode.algorithm;
/*
 * hard
 * 493. Reverse Pairs 
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3

Note:
    1.		The length of the given array will not exceed 50,000.
    2.		All the numbers in the input array are in the range of 32-bit integer.

similar problems:
315. Count of Smaller Numbers After Self 
327. Count of Range Sum 
 */
import java.util.*;
public class NO493_ReversePairs {
	//方法1：
	//二叉查找树，太慢，最差情况O(n^2)时间复杂度
	class Node{
        int curCount,leftCount;
        long value;
        Node left,right;
        Node(long value,int count){
            this.value = value;
            this.curCount = count;
            leftCount = 0;
            left = null;
            right = null;
        }
    }
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        Node root = null;
        int[] count = new int[]{0};
        for(int i = nums.length-1 ; i >= 0 ; i--){
            search(count,root,nums[i]/2.0);
            root = build(root,nums[i]);
        }
        return count[0];
    }
    private Node build(Node root,int value){
        if(root == null){
            return new Node(value,1);
        }
        if(root.value > value){
            root.leftCount++;
            root.left = build(root.left,value);
        }else if(root.value < value){
            root.right = build(root.right,value);
        }else{ //root.val == val
            root.curCount++;
        }
        return root;
    }
    private void search(int[] count , Node root,double value){
        if(root == null){
            return;
        }
        if(root.value < value){
            count[0]+=root.leftCount+root.curCount;
            search(count,root.right,value);
        }else if(root.value > value){
            search(count,root.left,value);
        }else{ //root.value == value
            count[0]+=root.leftCount;
            search(count,root.right,value);
        }
    }
    
    //方法2：
    //利用归并排序中间过程，统计个数
    //O(nlogn)time
    public int reversePairs2(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    private int mergeSort(int[] nums, int s, int e){
        if(s>=e) return 0; 
        int mid = s + (e-s)/2; 
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); 
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++; 
            cnt += j-(mid+1); 
        }
        Arrays.sort(nums, s, e+1); 
        return cnt; 
    }
}
