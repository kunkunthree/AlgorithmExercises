package algorithm.leetcode.algorithm;
/*
 * medium
 * 173. Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree. 
 */
import java.util.*;
//方法1：
//中根遍历，用队列一次性把所有值存起来
class BSTIterator {
    Queue<Integer> queue;
    public BSTIterator(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        queue  = new LinkedList<Integer>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                queue.offer(root.val);
                root = root.right;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll();
    }
}
//方法2：
//循环遍历左子树，并用stack存起每一步遍历到的节点
//去的最小值的节点为stack的顶端节点，每次去完后都遍历右子节点的左子树
class BSTIterator2 {
    Stack<TreeNode> stack;
    public BSTIterator2(TreeNode root) {
        stack = new Stack<>();
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int result = node.val;
        node = node.right;
        while(node != null){
            stack.push(node);
            node = node.left;
        }
        return result;
    }
}
public class NO173_BinarySearchTreeIterator {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		BSTIterator iterator = new BSTIterator(root);
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
