package algorithm.leetcode.algorithm;

/*
 * easy
 * 107. Binary Tree Level Order Traversal II 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 * (ie, from left to right, level by level from leaf to root).
 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 */
import java.util.*;
public class NO107_BinaryTreeLevelOrderTraversalII {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> ll = levelOrderBottom(root);
		for (List<Integer> l : ll) {
			for (Integer i : l) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> ll = new ArrayList<List<Integer>>();
		List<Integer> l = new ArrayList<Integer>();
		Stack<List<Integer>> stack = new Stack<List<Integer>>();
		if (root != null) {
			queue.offer(root);
		}
		int level = 1;
		int count = 1, tmpCount = 0;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			l.add(node.val);
			count--;
			if (node.left != null) {
				queue.offer(node.left);
				tmpCount++;
			}
			if (node.right != null) {
				queue.offer(node.right);
				tmpCount++;
			}
			if (count == 0) {
				level++;
				stack.push(l);
				l = new ArrayList<Integer>();
				count = tmpCount;
				tmpCount = 0;
			}
		}
		while (!stack.isEmpty()) {
			ll.add(stack.pop());
		}
		return ll;
	}
	
	//别的方法，深度优先搜索，利用LinkedList的addFirst()方法添加到列表前面
	public List<List<Integer>> levelOrderBottom2(TreeNode root) {
		LinkedList<List<Integer>> list = new LinkedList<List<Integer>>();
		addLevel(list, 0, root);
		return list;
	}
	private void addLevel(LinkedList<List<Integer>> list, int level, TreeNode node) {
		if (node == null) return;
		if (list.size()-1 < level) list.addFirst(new LinkedList<Integer>());
		list.get(list.size()-1-level).add(node.val);
		addLevel(list, level+1, node.left);
		addLevel(list, level+1, node.right);
	}
}
