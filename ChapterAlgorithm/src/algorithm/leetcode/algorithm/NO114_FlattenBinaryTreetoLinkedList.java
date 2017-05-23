package algorithm.leetcode.algorithm;
/*
 * medium
 * 114. Flatten Binary Tree to Linked List
 *  Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

 */
import java.util.*;
public class NO114_FlattenBinaryTreetoLinkedList {
	public static void main(String[] args) {
		TreeNode root = TreeNode.getTree(new Object[]{0});
		flatten(root);
		System.out.println(root);
	}
	//方法1：
	//先根遍历，迭代形式，用queue把遍历到的节点都存起来，然后再串起来
	public static void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                queue.offer(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                root = root.right;
            }
        }
        if(!queue.isEmpty()){
            root = queue.poll();
            root.left = null;
        }
        while(!queue.isEmpty()){
            root.right = queue.poll();
            root = root.right;
            root.left = null;
        }
    }
	
	//方法2：
	//先跟遍历，递归形式，用一个指针记录上一个节点
	private TreeNode pre = null;
    public void flatten2(TreeNode root) {
        if(root == null){
            return;
        }
        if(pre != null){
            pre.right = root;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        pre = root;
        flatten2(left);
        flatten2(right);
    }
    //方法3：
    //递归，后根遍历，先右后左
    public void flatten3(TreeNode root) {
        if(root == null){
            return;
        }
        flatten3(root.right);
        flatten3(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
    //方法4：
    //迭代形式，
    //步骤0：如果根节点不为空，
    //步骤1：如果左右子节点都存在，那么把右子树左移到左子树的最右端节点的右边
    //步骤2：如果左子节点不为null，那么左子树右移到根节点的右边
    //步骤3：对根节点的右子节点执行步骤0，1，2
    public void flatten4(TreeNode root) {
        while(root != null){
            if(root.left != null && root.right != null){
                TreeNode leftNode = root.left;
                // 得到左子树最右边的节点
                while(leftNode.right != null){
                    leftNode = leftNode.right;
                }
                //把左子树最右边的节点的右子节点和根节点的右子节点串起来
                leftNode.right = root.right;
            }
            if(root.left != null){
                //把左子树置为右子树
                root.right = root.left;
            }
            //左子树置空
            root.left = null;
            //继续对右子节点进行操作
            root = root.right;
        }
    }
}
