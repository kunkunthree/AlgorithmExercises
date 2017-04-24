package algorithm.leetcode.algorithm;
/*
 * easy
 * 104. Maximum Depth of Binary Tree
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down 
 * to the farthest leaf node.
 */
public class NO104_MaximumDepthofBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth1 = 1+maxDepth(root.left);
        int depth2 = 1+maxDepth(root.right);
        return depth1 > depth2 ? depth1 : depth2;
    }
}
