package algorithm.leetcode.algorithm;
/*
 * easy
 * 653. Two Sum IV - Input is a BST
 * Given a Binary Search Tree and a target number, 
 * return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7
Target = 9
Output: True

Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7
Target = 28
Output: False

 */
import java.util.*;
public class NO653_TwoSumIV_InputIsABST {
	//方法1：
	//O(n)time,O(n)space
	//递归，先序遍历所有节点，用一个Set保存遍历过的值
	//遍历过程：
	//		1.先判断当前节点是否为null，如果为null，则返回false
	//		2.如果不为null则判断k-root.val是否在set中，如果存在则返回true
	//			如果不存在，则将root.val保存到set中，然后返回遍历左右两个子节点的结果的并集
	private Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null){
            return false;
        }
        
        if(set.contains(k-root.val)){
            return true;
        }
        set.add(root.val);
        return findTarget(root.left,k) || findTarget(root.right,k);
    }
    
    //方法2：
    //方法1的迭代实现
    public boolean findTarget2(TreeNode root, int k) {
        if(root == null){
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            if(set.contains(k-root.val)){
                return true;
            }
            set.add(root.val);
            if(root.right != null){
                stack.push(root.right);
            }
            if(root.left != null){
                stack.push(root.left);
            }
        }
        return false;
    }
    
    //方法3：
    //O(nlogn)time，O(h)space,h is the height of the tree, which is logn at best case, and n at worst case.
    public boolean findTarget3(TreeNode root, int k) {
        return helper(root,k,root);
    }
    private boolean helper(TreeNode node,int k,TreeNode root){
        if(node == null){
            return false;
        }
        if(isExist(root,node,k)){
            return true;
        }
        return helper(node.left,k,root) || helper(node.right,k,root);
    }
    private boolean isExist(TreeNode root,TreeNode node,int k){
        if(root == null){
            return false;
        }
        if(root.val == k-node.val && node != root){	//注意：本题要求不能是同一个节点使用两次
            return true;
        }
        if(root.val > k-node.val){
            return isExist(root.left,node,k);
        }else{
            return isExist(root.right,node,k);
        }
    }
}
