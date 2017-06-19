package algorithm.leetcode.algorithm;
/*
 * hard
 * 124. Binary Tree Maximum Path Sum 
 *  Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node
 in the tree along the parent-child connections. The path must contain at least one node and does 
 not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3

Return 6. 
 */
public class NO124_BinaryTreeMaximumPathSum {
	//方法1：
	//dfs，借助一个辅助函数返回通往跟节点的无分支的最长的路径
	private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        maxPathRootSum(root);
        return max;
    }
    private int maxPathRootSum(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxPathRootSum(root.left);
        int right = maxPathRootSum(root.right);
        //左右路径中权值最大的路径
        int path = Math.max(left,right);
        //如果左右路径种权值最大的路径大于0，则选择该路径，否则不选择，此时最大路径只有根节点
        int result = root.val + (path > 0 ? path : 0);
        //更新最大路径
        max = Math.max(max,root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0));
        return result;
    }
    //方法2：
    //方法1的更简单写法
    int maxValue;
    public int maxPathSum2(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
