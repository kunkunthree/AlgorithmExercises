package algorithm.leetcode.algorithm;
/*
 * medium
 * 337. House Robber III
 *  The thief has found himself a new place for his thievery again. There is only one entrance to this area, 
 *  called the "root." Besides the root, each house has one and only one parent house. 
 *  After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
 *  It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

     3
    / \
   2   3
    \    \ 
     3    1

Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

     3
    / \
   4   5
  / \   \ 
 1   3   1

Maximum amount of money the thief can rob = 4 + 5 = 9. 
 */
public class NO337_HouseRobberIII {
	//方法1：
	//递归
	public int rob(TreeNode root) {
        int sum1 = 0,sum2 = 0;
        if(root == null){
            return 0;
        }
        sum1+=root.val;
        if(root.left != null){
            sum2+=rob(root.left);
            sum1+=rob(root.left.left)+rob(root.left.right);
        }
        if(root.right != null){
            sum2+=rob(root.right);
            sum1+=rob(root.right.left)+rob(root.right.right);
        }
        return Math.max(sum1,sum2);
    }
}
