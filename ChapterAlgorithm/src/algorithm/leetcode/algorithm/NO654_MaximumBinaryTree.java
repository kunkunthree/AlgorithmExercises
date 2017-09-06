package algorithm.leetcode.algorithm;
/*
 * medium
 * 654. Maximum Binary Tree 
 *  Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
    1.	The root is the maximum number in the array.
    2.	The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    3.	The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.

Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:
      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1

Note:

    The size of the given array will be in the range [1,1000].

 */
public class NO654_MaximumBinaryTree {
	//方法1:
	//O(n^2)
	public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        return helper(nums,0,nums.length-1);
    }
    private TreeNode helper(int[]nums,int start,int end){
        if(start > end){
            return null;
        }
        if(start == end){
            return new TreeNode(nums[start]);
        }
        int maxIndex = start;
        for(int i = start+1 ; i <= end ; i++){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = helper(nums,start,maxIndex-1);
        root.right = helper(nums,maxIndex+1,end);
        return root;
    }
}
