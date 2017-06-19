package algorithm.leetcode.algorithm;
/*
 * hard
 * 145. Binary Tree Postorder Traversal 
 * Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3

return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
 */
import java.util.*;
public class NO145_BinaryTreePostorderTraversal {
	//方法1：
	//非迭代实现，利用stack，O(n)time,O(n)space
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode pre = null,cur = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            cur = stack.peek();
            if(cur.left == null && cur.right == null 
            || (pre != null && (pre == cur.left || pre == cur.right))){
                list.add(cur.val);
                stack.pop();
                pre = cur;
            }else{
                if(cur.right != null){
                    stack.push(cur.right);
                }
                if(cur.left != null){
                    stack.push(cur.left);
                }
            }
        }
        return list;
    }
	//方法2：
	//同方法1，但是代码更简单，利用从右往左的先序遍历的逆序
	public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode cur = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            cur = stack.pop();
            list.add(0,cur.val);
            if(cur.left != null){
                stack.push(cur.left);
            }
            if(cur.right != null){
                stack.push(cur.right);
            }
        }
        return list;
    }
}
