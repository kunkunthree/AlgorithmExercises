package algorithm.leetcode.algorithm;
/*
 * medium
 * 513. Find Bottom Left Tree Value 
 *  Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:
    2
   / \
  1   3
Output:
1

Example 2:
Input:
        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7
Output:
7

Note: You may assume the tree (i.e., the given root node) is not NULL. 
 */
import java.util.*;
public class NO513_FindBottomLeftTreeValue {
	//方法1：
	//分层遍历，BFS，得到第一个的值
	public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0,length,i;
        TreeNode node;
        while(!queue.isEmpty()){
            length = queue.size();
            for(i = 0 ; i < length ; i++){
                node = queue.poll();
                if(i == 0){
                    result = node.val;
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }
	//方法2：
	//DFS，先左后右，先序遍历
	public int findBottomLeftValue2(TreeNode root) {
        int[] result = new int[]{0,0};
        dfs(root,1,result);
        return result[1];
    }
    private void dfs(TreeNode root,int depth,int[] result){
        if(depth > result[0]){
            result[0] = depth;
            result[1] = root.val;
        }
        if(root.left != null){
            dfs(root.left,depth+1,result);
        }
        if(root.right != null){
            dfs(root.right,depth+1,result);
        }
    }
}
