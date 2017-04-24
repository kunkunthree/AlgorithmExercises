package algorithm.leetcode.algorithm;

/*
 * easy
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *  Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v 
 and w as the lowest node in T that has both v and w as descendants 
 (where we allow a node to be a descendant of itself).”

 _______6______
 /             			 \
 ___2__          	___8__
 /      		\       	/      		\
 0     		 _4      7       	9
 /  	\
 3   5

 For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class NO235_LowestCommonAncestorofaBinarySearchTree {
	public static void main(String[] args) {
		Object[] x = new Object[] { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 };
		TreeNode node = TreeNode.getTree(x);
		TreeNode q = node.left;
		TreeNode p = node.left.right.left;
		TreeNode result = lowestCommonAncestor(node, p, q);
		System.out.println(result.val);
	}

	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,
			TreeNode q) {
		if (root == null || p == null || q == null) {
			return root;
		}
		TreeNode node;
		if (p.val > q.val) {
			node = p;
			p = q;
			q = node;
		}
		if (root.val > p.val && root.val > q.val) {
			return lowestCommonAncestor(root.left, p, q);
		}
		if (root.val < p.val && root.val < q.val) {
			return lowestCommonAncestor(root.right, p, q);
		}
		if (root.val > p.val && root.val < q.val || root == p || root == q) {
			return root;
		}
		return null;
	}
	//简单写法，只需判断是否root分别和p、q的差的乘积小于0时，则为最近公共祖先
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
	    while ((root.val - p.val) * (root.val - q.val) > 0)
	        root = p.val < root.val ? root.left : root.right;
	    return root;
	}
}
