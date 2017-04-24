package algorithm.leetcode.algorithm;
/*
 * medium
 * 94. Binary Tree Inorder Traversal
 * Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],

   1
    \
     2
    /
   3

return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
 */
import java.util.*;
public class NO94_BinaryTreeInorderTraversal {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(1);
		stack.add(2);
		stack.add(3);
		stack.add(4);
		System.out.println(stack);
		stack.clear();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack);
	}
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        for(Integer i : inorderTraversal(root.left)){
            list.add(i);
        }
        list.add(root.val);
        for(Integer i : inorderTraversal(root.right)){
            list.add(i);
        }
        return list;
    }
	//方法2：
	//中序遍历，中根遍历，迭代形式
	public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
            	root = stack.pop();
            	list.add(root.val);
            	root = root.right;
            }
        }
        return list;
    }
}
