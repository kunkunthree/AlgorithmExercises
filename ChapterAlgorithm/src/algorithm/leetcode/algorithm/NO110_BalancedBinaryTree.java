package algorithm.leetcode.algorithm;
/*
 * easy
 * 110. Balanced Binary Tree 
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree
 *  in which the depth of the two subtrees of every node never differ by more than 1. 
 */
public class NO110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        if(Math.abs(getDepth(root.left)-getDepth(root.right))>1){
            return false;
        }
        return isBalanced(root.left)&&isBalanced(root.right);
    }
    public int getDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(getDepth(root.left),getDepth(root.right))+1;
    }
    //O(n)
    public boolean isBalanced2(TreeNode root) {
        if(root==null){
            return true;
        }
        return height(root)!=-1;
        
    }
    public int height(TreeNode node){
        if(node==null){
            return 0;
        }
        int lH=height(node.left);
        if(lH==-1){
            return -1;
        }
        int rH=height(node.right);
        if(rH==-1){
            return -1;
        }
        if(lH-rH<-1 || lH-rH>1){
            return -1;
        }
        return Math.max(lH,rH)+1;
    }
}
