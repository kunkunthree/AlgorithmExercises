package algorithm.data.structure;
/*
 * 二叉树（节点）数据结构：
 */
public class BNode {
	public BNode leftChild;
	public BNode rightChild;
	public int data;
	public BNode() {
		this.leftChild = null;
		this.rightChild = null;
		data = -1;
	}
	public BNode(BNode leftChild,BNode rightChild,int data){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data = data;
	}
	public BNode(int data){
		this.leftChild = null;
		this.rightChild = null;
		this.data = data;
	}
}
