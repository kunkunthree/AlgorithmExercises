package algorithm.leetcode.algorithm;

/*
 * easy
 *  Given a binary tree, return all root-to-leaf paths.
 For example, given the following binary tree:
 1
 /   \
 2     3
 \
 5

 All root-to-leaf paths are:

 ["1->2->5", "1->3"]
 */
import java.util.*;

public class NO257_BinaryTreePaths {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("0123456789");
		System.out.println(sb.lastIndexOf("789"));
		sb.replace(5, sb.length(), "");
		System.out.println(sb.toString());
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<String> stringQueue = new LinkedList<String>();
		if (root != null) {
			nodeQueue.offer(root);
			stringQueue.offer(root.val + "");
		}
		while (!nodeQueue.isEmpty()) {
			int length = nodeQueue.size();
			for (int i = 0; i < length; i++) {
				TreeNode node = nodeQueue.poll();
				String s = stringQueue.poll();
				if (node.left != null) {
					nodeQueue.offer(node.left);
					stringQueue.offer(s + "->" + node.left.val);
				}
				if (node.right != null) {
					nodeQueue.offer(node.right);
					stringQueue.offer(s + "->" + node.right.val);
				}
				if (node.left == null && node.right == null) {
					list.add(s);
				}
			}
		}
		return list;
	}

	// 更简单写法，利用递归的DFS
	public List<String> binaryTreePaths2(TreeNode root) {
		List<String> answer = new ArrayList<String>();
		if (root != null)
			searchBT(root, "", answer);
		return answer;
	}

	private void searchBT(TreeNode root, String path, List<String> answer) {
		if (root.left == null && root.right == null)
			answer.add(path + root.val);
		if (root.left != null)
			searchBT(root.left, path + root.val + "->", answer);
		if (root.right != null)
			searchBT(root.right, path + root.val + "->", answer);
	}

	// 同样利用递归，但是不用写辅助方法
	public List<String> binaryTreePaths3(TreeNode root) {
		List<String> paths = new LinkedList<>();
		if (root == null)
			return paths;
		if (root.left == null && root.right == null) {
			paths.add(root.val + "");
			return paths;
		}
		for (String path : binaryTreePaths3(root.left)) {
			paths.add(root.val + "->" + path);
		}
		for (String path : binaryTreePaths3(root.right)) {
			paths.add(root.val + "->" + path);
		}
		return paths;
	}
}
