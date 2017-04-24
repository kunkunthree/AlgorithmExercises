package algorithm.leetcode.algorithm;
/*
 * easy
 * 404. Sum of Left Leaves 
 * Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
      /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

 */
import java.util.*;
public class NO404_SumofLeftLeaves {
	//DFS 递归法
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left != null && root.left.left == null && root.left.right == null){
            return root.left.val+sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
    }
    
    //BFS 广度优先按搜索
    public int sumOfLeftLeaves2(TreeNode root) {
        if(root == null || root.left == null && root.right == null) return 0;
        
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if(curr.left != null && curr.left.left == null && curr.left.right == null) res += curr.left.val;
            if(curr.left != null) queue.offer(curr.left);
            if(curr.right != null) queue.offer(curr.right);
        }
        return res;
    }
}
