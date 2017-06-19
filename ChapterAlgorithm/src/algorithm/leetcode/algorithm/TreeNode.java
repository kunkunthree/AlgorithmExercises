package algorithm.leetcode.algorithm;
import java.util.*;
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	TreeNode(int x) { val = x; }
	public static TreeNode getTree(Object[] x){
		if(x == null || x.length == 0 || x[0] == null){
			return null;
		}
		TreeNode[] tree = new TreeNode[x.length];
		tree[0] = new TreeNode((int)x[0]);
		for(int i = 1; i < x.length ; i++){
			if(x[i] != null){
				tree[i] = new TreeNode((int)x[i]);
			}else{
				tree[i] = null;
			}
			if(tree[(i-1)/2] != null){
				if((i&1) == 1){
					tree[(i-1)/2].left = tree[i];
				}else{
					tree[(i-1)/2].right = tree[i];
				}
			}
		}
		return tree[0];
	}
	@Override
	public String toString() {
		int level = getLevel(this);
		TreeNode[] nodes = new TreeNode[(int)(Math.pow(2, level))];
		nodes[1] = this;
		StringBuilder sb = new StringBuilder("[");
		for(int i = 1 ; i <= nodes.length-1 ; i++){
			if(nodes[i] != null){
				sb.append(nodes[i].val);
				if(i < nodes.length-1)sb.append(",");
				if(nodes[i].left != null){
					nodes[2*i] = nodes[i].left;
				}
				if(nodes[i].right != null){
					nodes[2*i+1] = nodes[i].right;
				}
			}else{
				sb.append("null");
				if(i < nodes.length-1)sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	public static int getLevel(TreeNode root){
		if(root == null){
			return 0;
		}
		return 1 + Math.max(getLevel(root.left), getLevel(root.right));
	}
}
