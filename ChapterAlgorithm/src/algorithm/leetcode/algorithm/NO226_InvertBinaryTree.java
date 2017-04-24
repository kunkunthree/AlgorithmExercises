package algorithm.leetcode.algorithm;

/*
 * easy
 * 226. Invert Binary Tree
 * Invert a binary tree.
 4
 /   \
 2     7
 / \   / \
 1   3 6   9

 to

 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 */
import java.util.*;

public class NO226_InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode tmp;
		tmp = invertTree(root.left);
		root.left = invertTree(root.right);
		root.right = tmp;
		return root;
	}

	//
	public TreeNode invertTree2(TreeNode root) {

		if (root == null) {
			return null;
		}

		final Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			final TreeNode node = stack.pop();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;

			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		return root;
	}

	// BFS
	public TreeNode invertTree3(TreeNode root) {

		if (root == null) {
			return null;
		}

		final Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			final TreeNode node = stack.pop();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;

			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		return root;
	}
}
