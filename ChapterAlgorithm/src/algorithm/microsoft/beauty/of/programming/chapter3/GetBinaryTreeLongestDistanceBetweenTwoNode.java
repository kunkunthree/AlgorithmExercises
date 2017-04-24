package algorithm.microsoft.beauty.of.programming.chapter3;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/*
 * 3.8	求二叉树中节点的最大距离：
 * 		如果我们把二叉树看成一个图，父子节点之间的连线看成是双向的，我们姑且定义”距离”为两节点之间边的个数。
 * 写一个程序求一棵二叉树中相距最远的两个节点之间的距离。
 */
public class GetBinaryTreeLongestDistanceBetweenTwoNode {
	class Node{
		public Node left;
		public Node right;
		public int nMaxLeft;
		public int nMaxRight;
		public int value;
	}
	public static int nMaxLen = 0;
	public static void getMaxLength(Node root){
		if(root == null){
			return;
		}
		if(root.left ==null){
			root.nMaxLeft = 0;
		}
		if(root.right ==null){
			root.nMaxRight = 0;
		}
		if(root.left != null){
			getMaxLength(root.left);
		}
		if(root.right != null){
			getMaxLength(root.right);
		}
		if(root.left != null){
			int nTmpMax = 0;
			if(root.left.nMaxLeft > root.left.nMaxRight){
				nTmpMax = root.left.nMaxLeft;
			}else{
				nTmpMax = root.left.nMaxRight;
			}
			root.nMaxLeft = nTmpMax+1;
		}
		if(root.right != null){
			int nTmpMax = 0;
			if(root.right.nMaxLeft > root.right.nMaxRight){
				nTmpMax = root.right.nMaxLeft;
			}else{
				nTmpMax = root.right.nMaxRight;
			}
			root.nMaxRight = nTmpMax +1;
		}
		if(root.nMaxLeft + root.nMaxRight > nMaxLen){
			nMaxLen = root.nMaxLeft + root.nMaxRight;
		}
	}
}
