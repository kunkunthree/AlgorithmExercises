package algorithm.microsoft.beauty.of.programming.chapter3;


/*
 * 3.9	重建二叉树：
 * 		假设已经有先序遍历和中序遍历的结果，希望通过一个算法重建这颗树。
 */
import xie.util.*;
public class RebuildBinaryTree {
	public static RebuildBinaryTree rbt = new RebuildBinaryTree();
	public static void main(String[] args) {
	}
	public static BNode rebuildBinaryTree(BNode[] preOrder,int preIndex,BNode[] inOrder,int InIndex,int nTreeLength){
		//检查边界条件
		if(preOrder[preIndex] == null || inOrder[InIndex] == null || nTreeLength < 1){
			return null;
		}
		//获得先序遍历的第一个节点
		BNode node = new BNode(preOrder[preIndex].value);
		node.leftChild = null;
		node.rightChild = null;
		//如果当前树长度为1，那么已经是最后一个节点
		if(nTreeLength == 1){
			return node;
		}
		//寻找子树的长度
		int leftEndIndex = InIndex;
		int tmpLength = 0;
		while(preOrder[preIndex] != inOrder[leftEndIndex]){
			tmpLength++;
			//记录临时长度，以免溢出
			if(tmpLength > nTreeLength){
				break;
			}
			leftEndIndex++;
		}
		//寻找左子树长度
		int nLeftLength = leftEndIndex - InIndex;
		//寻找右子树长度
		int nRightLength = nTreeLength - nLeftLength - 1;
		//重建左子树
		node.leftChild = rebuildBinaryTree(preOrder, preIndex+1, inOrder, InIndex, nLeftLength);
		//重建右子树
		node.rightChild = rebuildBinaryTree(preOrder, preIndex+nLeftLength+1, inOrder, leftEndIndex+1, nRightLength);
		
		//返回重建后的二叉树根节点
		return node;
	}
}
