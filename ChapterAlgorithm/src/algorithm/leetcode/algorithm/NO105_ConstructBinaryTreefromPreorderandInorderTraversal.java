package algorithm.leetcode.algorithm;
/*
 * medium
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree. 
 */
import java.util.*;
public class NO105_ConstructBinaryTreefromPreorderandInorderTraversal {
	public static void main(String[] args) {
		int[] preorder = new int[]{1,2,4,5,3,6,7};
		int[] inorder = new int[]{4,2,5,1,6,3,7};
		System.out.println(buildTree3(preorder, inorder));
	}
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,inorder,0,preorder.length);
    }
    public static TreeNode buildTree(int[] preorder,int preIndex, int[] inorder,int inIndex,int length) {
        //检查边界条件
        if(preIndex < 0 || inIndex < 0 
            || preIndex >= preorder.length || inIndex >= inorder.length 
            || length == 0){
            return null;
        }
        //先序遍历的第一个节点为根
        TreeNode node = new TreeNode(preorder[preIndex]);
        node.left = null;
        node.right = null;
        //叶子节点则返回
        if(length == 1){
            return node;
        }
        int tmpLength = 0;
        int leftEndIndex = inIndex;
        //计算左子树长度
        while(inorder[leftEndIndex] != preorder[preIndex]){
            tmpLength++;
            if(tmpLength > length){
                break;
            }
            leftEndIndex++;
        }
        //左子树长度
        int nLeftLength = tmpLength;
        //右子树长度
        int nRightLength = length - tmpLength - 1;
        //重建左子树
        node.left = buildTree(preorder,preIndex+1,inorder,inIndex,nLeftLength);
        //重建右子树
        node.right = buildTree(preorder,preIndex+nLeftLength+1,inorder,leftEndIndex+1,nRightLength);
        return node;
    }
    
    //递归简洁写法
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
    
    public static TreeNode buildTree3(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }
        Stack<TreeNode> StackNode =new Stack<TreeNode>();
        Stack<Integer> stackInt = new Stack<Integer>();
        TreeNode t,r,root;
        
        int i = 0,j = 0,f = 0;
//        stackInt.push(preorder[i]);
        root = new TreeNode(preorder[i]);
        StackNode.push(root);
        t = root;
        i++;
        while(i<preorder.length)
        {
            if(!StackNode.isEmpty() && StackNode.peek().val==inorder[j])
            {
                t = StackNode.pop();
//                stackInt.pop();
                f = 1;
                j++;
            }
            else
            {
                if(f==0)
                {
//                    stackInt.push(preorder[i]);
                    t.left = new TreeNode(preorder[i]);
                    t = t.left;
                    StackNode.push(t);
                    i++;
                }
                else 
                {
                    f = 0;
//                    stackInt.push(preorder[i]);
                    t.right = new TreeNode(preorder[i]);
                    t = t.right;
                    StackNode.push(t);
                    i++;
                }
            }
        }
        
        return root;
    }
}
