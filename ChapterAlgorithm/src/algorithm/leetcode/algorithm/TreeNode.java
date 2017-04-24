package algorithm.leetcode.algorithm;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
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
}
