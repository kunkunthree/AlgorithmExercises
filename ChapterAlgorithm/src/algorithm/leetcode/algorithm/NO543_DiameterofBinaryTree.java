package algorithm.leetcode.algorithm;

/*
 * easy
 * 543. Diameter of Binary Tree 
 *  Given a binary tree, you need to compute the length of the diameter of the tree. 
 *  The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 *   This path may or may not pass through the root.

 Example:
 Given a binary tree

 1
 / \
 2   3
 / \     
 4   5    
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them. 
 */
public class NO543_DiameterofBinaryTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		System.out.println(diameterOfBinaryTree(root));
	}
	public static int max = 0;
	public static int diameterOfBinaryTree(TreeNode root) {
		traverse(root);
		return max;
	}
	public static int traverse(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = traverse(root.left);
		int right = traverse(root.right);
		max = Math.max(max, left + right);
		return Math.max(left, right) + 1;
	}
	// public int max = 0;
	// public int diameterOfBinaryTree(TreeNode root) {
	// traverse(root);
	// return max;
	// }
	// public int traverse(TreeNode root){
	// if(root == null || root.left == null && root.right == null){
	// return 0;
	// }
	// int left = root.left == null ? 0 : 1+traverse(root.left);
	// int right = root.right == null ? 0 : 1+traverse(root.right);
	// max = Math.max(max,left+right);
	// return Math.max(left,right);
	// }
}
