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
public class BinaryTreeTraverse {
	public static void main(String[] args) {
		BNode BTree = new BNode(1);
		BTree.leftChild = new BNode(2);
		BTree.rightChild = new BNode(3);
		PostOrderTraverse2(BTree);
	}
	//先序遍历（先根遍历）
	public static void PreOrderTraverse(BNode BTree){
		if(BTree == null){
			return;
		}
		System.out.println(BTree.data);
		PreOrderTraverse(BTree.leftChild);
		PostOrderTraverse(BTree.rightChild);
	}
	//先序遍历（先根遍历）,非递归实现
		public static void PreOrderTraverse2(BNode BTree){
			Stack<BNode> stack = new Stack<BNode>();
			while(BTree != null || !stack.isEmpty()){
				while(BTree != null){
					System.out.println(BTree.data);
					stack.push(BTree);
					BTree = BTree.leftChild;
				}
				if(!stack.isEmpty()){
					BTree = stack.pop();
					BTree = BTree.rightChild;
				}
			}
		}
	//中序遍历（中跟遍历），递归实现
	public static void InOrderTraverse(BNode BTree){
		if(BTree == null){
			return;
		}
		PreOrderTraverse(BTree.leftChild);
		System.out.println(BTree.data);
		PostOrderTraverse(BTree.rightChild);
	}
	//中序遍历（中跟遍历），非递归实现
	public static void InOrderTraverse2(BNode BTree){
		if(BTree == null){
			return;
		}
		Stack<BNode> stack = new Stack<BNode>();
		while(BTree != null || !stack.isEmpty()){
			while(BTree != null){
				stack.push(BTree);
				BTree = BTree.leftChild;
			}
			if(!stack.isEmpty()){
				BTree = stack.pop();
				System.out.println(BTree.data);
				BTree = BTree.rightChild;
			}
		}
		
	}
	//后序遍历（后根遍历）
	public static void PostOrderTraverse(BNode BTree){
		if(BTree == null){
			return;
		}
		PreOrderTraverse(BTree.leftChild);
		PostOrderTraverse(BTree.rightChild);
		System.out.println(BTree.data);
	}
	//后序遍历（后根遍历）,非递归实现
		public static void PostOrderTraverse2(BNode BTree){
			Stack<BNode> stack = new Stack<BNode>();
			if(BTree == null){
				return;
			}
			stack.push(BTree);
			BNode cur,pre = null;
			while(!stack.isEmpty()){
				cur = stack.peek();
				if(cur.leftChild == null && cur.rightChild == null || 
						(pre != null && (pre == cur.leftChild || pre == cur.rightChild))){
					System.out.println(cur.data);		//如果当前节点没有左右子树或者左右子节点都已经被访问
					stack.pop();
					pre = cur;
				}else{
					if(cur.rightChild != null){
						stack.push(cur.rightChild);
					}
					if(cur.leftChild != null){
						stack.push(cur.leftChild);
					}
				}
			}
		}
}
