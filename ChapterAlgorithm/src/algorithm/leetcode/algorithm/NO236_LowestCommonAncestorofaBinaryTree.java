package algorithm.leetcode.algorithm;
/*
 * medium
 *  Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “
The lowest common ancestor is defined between two nodes v and w as the lowest node in T 
that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4

For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself 
according to the LCA definition.
 */
public class NO236_LowestCommonAncestorofaBinaryTree {
	//寻找二叉树中两节点最近公共祖先
	//先递归找根节点左子树是否含有最近公共祖先或者含有其中一个点，
	//再递归找右子树是否含有最近公共祖先或者含有其中一个点，
	//如果只有一侧含有，则返回该侧的结果，如果两侧都有，则返回根节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        return left==null ? right : (right==null ? left : root);
    }
}
