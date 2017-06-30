package algorithm.data.structure;

public class RedBlackTree {
	public RedBlackTreeNode root,NULL;
	public void leftRotate(RedBlackTreeNode x){
		RedBlackTreeNode y = x.right;
		x.right = y.left;
		y.left.parent = x;
		if(x.parent == NULL){
			root = y;
		}else if(x == x.parent.left){
			x.parent.left = y;
		}else{
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	public void rightRotate(RedBlackTreeNode y){
		RedBlackTreeNode x = y.left;
		y.left = x.right;
		x.right.parent = y;
		if(y.parent == NULL){
			root = x;
		}else if(y == y.parent.left){
			y.parent.left = x;
		}else{
			y.parent.right = x;
		}
		x.right = y;
		y.parent = x;
	}
	public void insert(RedBlackTreeNode z){
		RedBlackTreeNode y = NULL,x = root;
		while(x != NULL){
			y = x;
			if(z.key < x.key){
				x = z.left;
			}else{
				x = z.right;
			}
		}
		z.parent = y;
		if(y == NULL){
			root = z;
		}else if(z.key < y.key){
			y.left = z;
		}else{
			y.right = z;
		}
		z.left = NULL;
		z.right = NULL;
		z.color = RedBlackTreeNode.RED;
		insertFixup(z);
	}
	private void insertFixup(RedBlackTreeNode z){
		while(z.parent.color == RedBlackTreeNode.RED){
			if(z.parent == z.parent.parent.left){
				RedBlackTreeNode y = z.parent.parent.right;
				if(y.color == RedBlackTreeNode.RED){
					z.parent.color = RedBlackTreeNode.BLACK;
					y.color = RedBlackTreeNode.BLACK;
					z.parent.parent.color = RedBlackTreeNode.RED;
					z = z.parent.parent;
				}else if(z == z.parent.right){
					z = z.parent;
					leftRotate(z);
				}
				z.parent.color = RedBlackTreeNode.BLACK;
				z.parent.parent.color = RedBlackTreeNode.RED;
				rightRotate(z.parent.parent);
			}else{
				RedBlackTreeNode y = z.parent.parent.left;
				if(y.color == RedBlackTreeNode.RED){
					z.parent.color = RedBlackTreeNode.BLACK;
					y.color = RedBlackTreeNode.BLACK;
					z.parent.parent.color = RedBlackTreeNode.RED;
					z = z.parent.parent;
				}else if(z == z.parent.left){
					z = z.parent;
					rightRotate(z);
				}
					z.parent.color = RedBlackTreeNode.BLACK;
					z.parent.parent.color = RedBlackTreeNode.RED;
					leftRotate(z.parent.parent);
			}
		}
		root.color = RedBlackTreeNode.BLACK;
	}
}
