package algorithm.leetcode.algorithm;

public class TreeLinkNode {
	public int val;
	TreeLinkNode left,right,next;
	public TreeLinkNode(int x) {
		val = x;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		TreeLinkNode root = this,tmp;
		while(root != null){
			tmp = root;
			while(tmp != null){
				sb.append(tmp.val+",");
				tmp = tmp.next;
			}
			sb.append("#,");
			root = root.left;
		}
		sb.setCharAt(sb.length()-1, '}');
		return sb.toString();
	}
	public static TreeLinkNode getTree(Object[] x){
		if(x == null || x.length == 0 || x[0] == null){
			return null;
		}
		TreeLinkNode[] tree = new TreeLinkNode[x.length];
		tree[0] = new TreeLinkNode((int)x[0]);
		for(int i = 1; i < x.length ; i++){
			if(x[i] != null){
				tree[i] = new TreeLinkNode((int)x[i]);
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
