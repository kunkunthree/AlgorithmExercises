package algorithm.microsoft.beauty.of.programming.chapter3;
/*
 * 3.10	分层遍历二叉树：
 * 问题1：
 * 		给定一棵二叉树，要求分层遍历该二叉树，即从上到下按层次访问二叉树（每一层将单独输出一行），
 * 每一层要求访问的顺序为从左到右，并将节点依次编号。
 * 问题2：
 * 		写另外一个函数，打印二叉树中的某层次的节点（从左往右），其中根节点为第0层，函数原型为
 * int PrintNodeAtLevel(Node node,int level)，成功返回1，失败返回2。
 * 
 */
import xie.util.*;
import java.util.*;
public class HierarchicallyTraversingBinaryTree {
	public static int PrintNodeAtLevel(BNode root,int level){
		if(root == null || level < 0){
			return 0;
		}
		if(level == 0){
			System.out.println(root.value);
			return 1;
		}
		return PrintNodeAtLevel(root.leftChild, level-1) + PrintNodeAtLevel(root.rightChild, level-1);
	}
	public static void PrintNodeByLevel(BNode root){
		if(root == null){
			return;
		}
		ArrayList<BNode> list = new ArrayList<BNode>();
		int cur = 0;
		list.add(root);
		int last = 1;
		while(cur < list.size()){
			last = list.size();		//新的一行访问开始，重新定位last于当前行最后一个节点的下一个位置
			while(cur < last){
				System.out.print(list.get(cur).value + " ");		//访问节点
				if(list.get(cur).leftChild != null){
					list.add(list.get(cur).leftChild);
				}
				if(list.get(cur).rightChild != null){
					list.add(list.get(cur).rightChild);
				}
				cur++;
			}
			System.out.println();			//当前行访问结束
		}
	}
}
