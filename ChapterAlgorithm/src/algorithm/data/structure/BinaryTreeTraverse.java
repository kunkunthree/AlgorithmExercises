package algorithm.data.structure;

/*
 * 遍历二叉树：
 * 1、先序遍历（先根遍历）：
 * 	若二叉树为空，则空操作。
 * 	1）访问根节点
 * 	2）先序遍历左子树
 * 	3）先序遍历右子树
 * 2、中序遍历（中跟遍历）
 * 	1）中序遍历左子树
 * 	2）访问根节点
 * 	3）中序遍历右子树
 * 3、后序遍历（后根遍历）
 * 	1）后序遍历左子树
 * 	2）后序遍历右子树
 * 	3）访问根节点
 */
import java.util.*;

import algorithm.leetcode.algorithm.TreeNode;

public class BinaryTreeTraverse {
	public static void main(String[] args) {
		BNode BTree = new BNode(1);
		BTree.leftChild = new BNode(2);
		BTree.rightChild = new BNode(3);
		PostOrderTraverse2(BTree);
	}

	// 先序遍历（先根遍历）
	public static void PreOrderTraverse(BNode BTree) {
		if (BTree == null) {
			return;
		}
		System.out.println(BTree.data);
		PreOrderTraverse(BTree.leftChild);
		PostOrderTraverse(BTree.rightChild);
	}

	// 先序遍历（先根遍历）,非递归实现
	public static void PreOrderTraverse2(BNode BTree) {
		Stack<BNode> stack = new Stack<BNode>();
		while (BTree != null || !stack.isEmpty()) {
			while (BTree != null) {
				System.out.println(BTree.data);
				stack.push(BTree);
				BTree = BTree.leftChild;
			}
			if (!stack.isEmpty()) {
				BTree = stack.pop();
				BTree = BTree.rightChild;
			}
		}
	}

	// 先序遍历（先根遍历）,非递归实现，利用stack，代码简单
	// 1.Create an empty stack, Push root node to the stack.
	// 2.Do following while stack is not empty.
	// 	2.1. pop an item from the stack and print it.
	// 	2.2. push the right child of popped item to stack.
	// 	2.3. push the left child of popped item to stack.
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		TreeNode cur = null;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			cur = stack.pop();
			list.add(cur.val);
			if (cur.right != null) {
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
		return list;
	}

	// 中序遍历（中跟遍历），递归实现
	public static void InOrderTraverse(BNode BTree) {
		if (BTree == null) {
			return;
		}
		PreOrderTraverse(BTree.leftChild);
		System.out.println(BTree.data);
		PostOrderTraverse(BTree.rightChild);
	}

	// 中序遍历（中跟遍历），非递归实现
	public static void InOrderTraverse2(BNode BTree) {
		if (BTree == null) {
			return;
		}
		Stack<BNode> stack = new Stack<BNode>();
		while (BTree != null || !stack.isEmpty()) {
			while (BTree != null) {
				stack.push(BTree);
				BTree = BTree.leftChild;
			}
			if (!stack.isEmpty()) {
				BTree = stack.pop();
				System.out.println(BTree.data);
				BTree = BTree.rightChild;
			}
		}
	}

	// 中序遍历（中跟遍历），非递归实现 , O(1) space
	// Morris Inorder Traversal, no stack
	// O(n)time,O(1)space
	// see NO99_RecoverBinarySearchTree_1.png
	// 中序遍历
	// 步骤：
	// 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
	// 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
	// a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
	// b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。
	// 当前节点更新为当前节点的右孩子。
	// 3. 重复以上1、2直到当前节点为空。
	public void recoverTree(TreeNode root) {
		TreeNode preNode = null;
		while (root != null) {
			if (root.left == null) { // 1.如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
				preNode = root;
				root = root.right;
			} else {
				// 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
				// 寻找中序遍历的上一个节点，判断该节点是否指向当前节点
				TreeNode tmpNode = root.left;
				while (tmpNode.right != null && tmpNode.right != root) {
					tmpNode = tmpNode.right;
				}
				// 2.a)如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
				if (tmpNode.right == null) {
					tmpNode.right = root;
					root = root.left;
				} else {
					// 2.b)如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。
					// 输出当前节点。当前节点更新为当前节点的右孩子。
					preNode = root;
					// 恢复树的形状
					tmpNode.right = null;
					root = root.right;
				}
			}
		}
	}

	// 后序遍历（后根遍历）
	public static void PostOrderTraverse(BNode BTree) {
		if (BTree == null) {
			return;
		}
		PreOrderTraverse(BTree.leftChild);
		PostOrderTraverse(BTree.rightChild);
		System.out.println(BTree.data);
	}

	// 后序遍历（后根遍历）,非递归实现
	public static void PostOrderTraverse2(BNode BTree) {
		Stack<BNode> stack = new Stack<BNode>();
		if (BTree == null) {
			return;
		}
		stack.push(BTree);
		BNode cur, pre = null;
		while (!stack.isEmpty()) {
			cur = stack.peek();
			if (cur.leftChild == null
					&& cur.rightChild == null
					|| (pre != null && (pre == cur.leftChild || pre == cur.rightChild))) {
				System.out.println(cur.data); // 如果当前节点没有左右子树或者左右子节点都已经被访问
				stack.pop();
				pre = cur;
			} else {
				// 先右后左
				if (cur.rightChild != null) {
					stack.push(cur.rightChild);
				}
				if (cur.leftChild != null) {
					stack.push(cur.leftChild);
				}
			}
		}
	}

	// 后序遍历（后根遍历）,非递归实现，代码更简单，利用从右往左的先序遍历的逆序
	public List<Integer> postorderTraversal3(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		TreeNode cur = null;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			cur = stack.pop();
			list.add(0, cur.val);
			if (cur.left != null) {
				stack.push(cur.left);
			}
			if (cur.right != null) {
				stack.push(cur.right);
			}
		}
		return list;
	}
}
