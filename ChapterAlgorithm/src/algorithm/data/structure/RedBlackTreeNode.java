package algorithm.data.structure;

public class RedBlackTreeNode {
	public static final int RED = 0,BLACK = 1;
	public int key;
	public RedBlackTreeNode parent,left,right;
	public int color; //1表示黑色，0表示红色
	public RedBlackTreeNode() {
		parent = null;
		left = null;
		right = null;
		color = 0;
	}
	public RedBlackTreeNode(int key,int color) {
		this();
		this.color = color;
		this.key = key;
	}
}
