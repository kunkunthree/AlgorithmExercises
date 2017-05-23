package algorithm.leetcode.algorithm;
/*
 * medium
 * 538. Convert BST to Greater Tree 
 * Given a Binary Search Tree (BST), convert it to a Greater Tree 
 * such that every key of the original BST is changed to the original key plus sum of all keys greater than 
 * the original key in BST.

Example:
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13
Output: The root of a Greater Tree like this:
             18
            /   \
          20     13

 */
import java.util.*;
public class NO538_ConvertBSTtoGreaterTree {
	//方法1：
	//DFS，从右到左的中序遍历
	private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.right);
        sum+=root.val;
        root.val = sum;
        dfs(root.left);
    }
    //方法2：
    //从右到左的中序遍历，迭代形式
    public TreeNode convertBST2(TreeNode root) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.right;
            }
            if(!stack.isEmpty()){
                node = stack.pop();
                sum+=node.val;
                node.val = sum;
                node = node.left;
            }
        }
        return root;
    }
}
