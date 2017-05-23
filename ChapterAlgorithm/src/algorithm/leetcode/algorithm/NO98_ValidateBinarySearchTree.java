package algorithm.leetcode.algorithm;
/*
 * medium
 * 98. Validate Binary Search Tree
 *  Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

Example 1:

    2
   / \
  1   3

Binary tree [2,1,3], return true.

Example 2:

    1
   / \
  2   3

Binary tree [1,2,3], return false. 
 */
import java.util.*;
public class NO98_ValidateBinarySearchTree {
	//方法1：
	//中根遍历二叉查找树，递归形式，得到遍历的序列，查看是否递增序列。
	public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inOrder(list,root);
        for(int i = 0 ; i < list.size()-1 ; i++){
            if(list.get(i)>=list.get(i+1)){
                return false;
            }
        }
        return true;
    }
    public void inOrder(List<Integer> list,TreeNode root) {
        if(root == null){
            return;
        }
        if(root.left != null){
            inOrder(list,root.left);
        }
        list.add(root.val);
        if(root.right != null){
            inOrder(list,root.right);
        }
    }
    //方法2：
    //中根遍历，非递归形式
    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //TreeNode pre = null;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                list.add(root.val);
                //可以用pre指针指向上一个遍历的节点，节省存储中间遍历结果的空间
                //if(pre != null && pre.val >= root.val){
                //	return false;
                //}
                //pre = root;
                root = root.right;
            }
        }
        for(int i = 0 ; i < list.size()-1 ; i++){
            if(list.get(i)>=list.get(i+1)){
                return false;
            }
        }
        return true;
    }
    //方法3：
    //递归，设置遍历时的最大值和最小值，当前节点大于等于最大值时返回false，当前节点小于等于最小值时返回false
    //当前节点为null时返回true
    //如果当前节点不为null，当前节点大于等于最大值时返回false，当前节点小于等于最小值时返回false
    //遍历左子树和右子树，遍历左子树时，更新最大值为当前节点的值，因为当前节点已经和最大值比较过了，
    //所以当前节点必然小于最大值，此时更新最大值为当前节点的值
    //同理，遍历右子树时，更新最小值为当前节点的值。
    //当遍历完左右子树都为true时，返回true。
    public boolean isValidBST3(TreeNode root) {
        return isValidBSTHelper(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public boolean isValidBSTHelper(TreeNode root,long min,long max){
        if(root == null){
            return true;
        }
        if(root.val >= max || root.val <= min){
            return false;
        }
        return isValidBSTHelper(root.left,min,root.val) && isValidBSTHelper(root.right,root.val,max);
    }
}
