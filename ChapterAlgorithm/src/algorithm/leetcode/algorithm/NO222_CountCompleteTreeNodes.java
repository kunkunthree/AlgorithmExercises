package algorithm.leetcode.algorithm;
/*
 * medium
 * 222. Count Complete Tree Nodes
 * Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible.
 It can have between 1 and 2h nodes inclusive at the last level h.
 */
import java.util.*;
public class NO222_CountCompleteTreeNodes {
	//方法1：
	//O(n)time，n为节点总数，太慢
	public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int num = 0,length;
        TreeNode node;
        while(!queue.isEmpty()){
            length = queue.size();
            num+=length;
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return num;
    }
	//方法2:
	//DFS,O(n)time，n为节点总数，太慢
	private int count = 0;
    public int countNodes2(TreeNode root) {
        dfs(root);
        return count;
    }
    public void dfs(TreeNode root){
        if(root == null){
            return;
        }
        count++;
        dfs(root.left);
        dfs(root.right);
    }
    //方法3：
    //先求左子树最左子节点的深度，再求右子树最右子节点深度，如果两个深度相同，则直接计算总结点数
    //如果不相等，则遍历左右子节点，总节点数为两个节点递归遍历的结果+1
    //O((log(n))^2)time
    public int countNodes3(TreeNode root) {
        if(root == null){
            return 0;
        }
        int lh = 0,rh = 0;
        TreeNode node = root;
        while(node != null){
            lh++;
            node = node.left;
        }
        node = root;
        while(node != null){
            rh++;
            node = node.right;
        }
        if(lh == rh){
            return (1<<lh)-1;
        }
        return 1+countNodes3(root.left)+countNodes3(root.right);
    }
    //方法4：
    //O((log(n))^2)time
    public int countNodes4(TreeNode root) {
        if(root == null){
            return 0;
        }
        int lh = 0,rh = 0;
        TreeNode node = root.left;
        while(node != null){
            lh++;
            node = node.left;
        }
        node = root.right;
        while(node != null){
            rh++;
            node = node.left;
        }
        if(lh == rh){
            return (1<<lh)+countNodes4(root.right);
        }else{
            return (1<<rh)+countNodes4(root.left);
        }
    }
}
