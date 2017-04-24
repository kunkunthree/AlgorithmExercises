package algorithm.leetcode.algorithm;
/*
 * easy
 * 100. Same Tree 
 *  Given two binary trees, write a function to check if they are equal or not.
 *  Two binary trees are considered equal if they are structurally identical and the nodes have the same value. 
 */
public class NO100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null && q != null || p != null && q == null || p.val != q.val){
            return false;
        }
        boolean result = true;
        result = result && isSameTree(p.left,q.left);
        result = result && isSameTree(p.right,q.right);
        return result;
    }
}
