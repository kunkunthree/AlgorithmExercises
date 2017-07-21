package algorithm.leetcode.algorithm;
/*
 * medium
 * 116. Populating Next Right Pointers in Each Node 
 *  Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }

Populate each next pointer to point to its next right node. If there is no next right node, 
the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

    You may only use constant extra space.
    You may assume that it is a perfect binary tree (ie, all leaves are at the same level, 
    and every parent has two children).

For example,
Given the following perfect binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

similar problems:
117. Populating Next Right Pointers in Each Node II 
199. Binary Tree Right Side View 
 */
public class NO116_PopulatingNextRightPointersinEachNode {
	public static void main(String[] args) {
		TreeLinkNode root = TreeLinkNode.getTree(new Object[]{0,1,2,3,4,5,6});
		connect2(root);
		System.out.println(root);
	}
	//方法1：
	//递归，DFS
	//如果左子节点和右子节点都不为null，则把左子节点的next指向右子节点
	//如果右子节点和next都不为null，那么把右子节点的next指向根节点的next指向的节点的left
	public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        if(root.left != null && root.right != null){
            root.left.next = root.right;
        }
        if(root.next != null && root.right != null){
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }
	//方法2：
	//迭代形式，不断遍历根节点的left，不断操作，把每一层的节点连接起来
	public static void connect2(TreeLinkNode root) {
		while(root != null){
            TreeLinkNode tmp = root;
            while(tmp != null){
                if(tmp.left != null && tmp.right != null){
                    tmp.left.next = tmp.right;
                }
                if(tmp.next != null && tmp.right != null){
                    tmp.right.next = tmp.next.left;
                }
                tmp = tmp.next;
            }
            root = root.left;
        }
    }
}
