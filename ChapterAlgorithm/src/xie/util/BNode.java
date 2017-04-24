package xie.util;
/*
 * 二叉树节点
 */
public class BNode{
	public BNode leftChild;
	public BNode rightChild;
	public int value;
	public BNode(BNode leftChild,BNode rightChild,int value) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.value = value;
	}
	public BNode(int value) {
		this.leftChild = null;
		this.rightChild = null;
		this.value = value;
	}
}