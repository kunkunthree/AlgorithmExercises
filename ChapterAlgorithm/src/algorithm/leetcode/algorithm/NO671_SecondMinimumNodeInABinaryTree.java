package algorithm.leetcode.algorithm;
/*
 * easy
 * 671. Second Minimum Node In a Binary Tree 
 *  Given a non-empty special binary tree consisting of nodes with the non-negative value, 
 *  where each node in this tree has exactly two or zero sub-node. 
 *  If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of 
all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

 */
import java.util.*;
public class NO671_SecondMinimumNodeInABinaryTree {
	//方法1：
	//递归，深度优先搜索，找大于根节点的值的最小节点值
	public int findSecondMinimumValue(TreeNode root) {
        if(root == null){
            return -1;
        }
        return helper(root,root.val);
    }
    public int helper(TreeNode node,int rootValue){
        if(node == null){
            return -1;
        }
        int result = -1;
        if(node.val > rootValue){
            result = node.val;
        }else{
            int left = helper(node.left,rootValue);
            if(left > -1){
                result = left;
            }
            int right = helper(node.right,rootValue);
            if(right > -1){
                result = result > -1 ? Math.min(result,right) : right;
            }
        }
        return result;
    }
    
    //方法2：
    //迭代，BFS，分层遍历
    public int findSecondMinimumValue2(TreeNode root) {
        if(root == null){
            return -1;
        }
        int result = -1;
        int rootValue = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0 ; i < len ; i++){
                root = queue.poll();
                if(root.val > rootValue){
                    result = result == -1 ? root.val : Math.min(result,root.val);
                }
                if(root.left != null){
                    queue.offer(root.left);
                }
                if(root.right != null){
                    queue.offer(root.right);
                }
            }
        }
        return result;
    }
    
    //方法3：
    //方法1的简化写法
    public int findSecondMinimumValue3(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
        
        int left = root.left.val;
        int right = root.right.val;
        
        // if value same as root value, need to find the next candidate
        if (root.left.val == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }
        
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }
}
