package algorithm.leetcode.algorithm;

/*
 * hard
 * 99. Recover Binary Search Tree 
 *  Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.
 Note:
 A solution using O(n) space is pretty straight forward. Could you devise a constant space solution? 
 */
public class NO99_RecoverBinarySearchTree {
	// 方法1：
	// Morris Inorder Traversal, no stack
	// O(n)time,O(1)space
	// see NO99_RecoverBinarySearchTree_1.png
	// 中序遍历
	// 步骤：
	// 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
	// 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
	// a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
	// b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。
	//		 当前节点更新为当前节点的右孩子。
	// 3. 重复以上1、2直到当前节点为空。
	public void recoverTree(TreeNode root) {
		TreeNode firstNode = null, secondNode = null, preNode = null;
		while (root != null) {
			if (root.left == null) {		// 1.如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
				if (preNode != null && preNode.val > root.val) {
					if (firstNode == null) {
						firstNode = preNode;
					}
					secondNode = root;
				}
				preNode = root;
				root = root.right;
			} else {				
				//2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
				//寻找中序遍历的上一个节点，判断该节点是否指向当前节点
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
					//			输出当前节点。当前节点更新为当前节点的右孩子。
					if (preNode != null && preNode.val > root.val) {
						if (firstNode == null) {
							firstNode = preNode;
						}
						secondNode = root;
					}
					preNode = root;
					//恢复树的形状
					tmpNode.right = null;
					root = root.right;
				}
			}
		}
		if (firstNode != null && secondNode != null) {
			int tmp = firstNode.val;
			firstNode.val = secondNode.val;
			secondNode.val = tmp;
		}
	}
	//方法2：
	//递归实现，中序遍历
	private TreeNode firstNode = null,secondNode = null,preNode = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree2(TreeNode root) {
        inorder(root);
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }
    private void inorder(TreeNode root){
        if(root == null){
            return;
        }
        inorder(root.left);
        if(firstNode == null && preNode.val >= root.val){
            firstNode = preNode;
        }
        if(firstNode != null && preNode.val >= root.val){
            secondNode = root;
        }
        preNode = root;
        inorder(root.right);
    }
}
