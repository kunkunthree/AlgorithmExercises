package algorithm.leetcode.algorithm;
/*
 * medium
 * 623. Add One Row to Tree 
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v 
 * at the given depth d. The root node is at depth 1.

The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, 
create two tree nodes with value v as N's left subtree root and right subtree root. 
And N's original left subtree should be the left subtree of the new left subtree root, 
its original right subtree should be the right subtree of the new right subtree root. 
If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root 
of the whole original tree, and the original tree is the new root's left subtree.

Example 1:
Input: 
A binary tree as following:
       4
     /   \
    2     6
   / \   / 
  3   1 5   

v = 1
d = 2

Output: 
       4
      / \
     1   1
    /     \
   2       6
  / \     / 
 3   1   5   

Example 2:
Input: 
A binary tree as following:
      4
     /   
    2    
   / \   
  3   1    

v = 1
d = 3

Output: 
      4
     /   
    2
   / \    
  1   1
 /     \  
3       1

Note:
    1.		The given d is in range [1, maximum depth of the given tree + 1].
    2.		The given binary tree has at least one tree node.

 */
import java.util.*;
public class NO623_AddOneRowToTree {
	//方法1：
	//分层遍历树
	public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d == 1){
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        queue.offer(root);
        TreeNode node;
        while(!queue.isEmpty()){
            int length = queue.size();
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                if(depth == d-1){
                    TreeNode left = new TreeNode(v);
                    TreeNode right = new TreeNode(v);
                    left.left = node.left;
                    right.right = node.right;
                    node.left = left;
                    node.right = right;
                }else{
                    if(node.left != null){
                        queue.offer(node.left);
                    }
                    if(node.right != null){
                        queue.offer(node.right);
                    }
                }
            }
            if(depth++ == d-1){
                break;
            }
        }
        return root;
    }
	//方法2：
	//dfs，有helper
	public TreeNode addOneRow2(TreeNode root, int v, int d) {
        if(d == 1){
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        dfs(root,1,v,d);
        return root;
    }
    private void dfs(TreeNode root,int depth,int v,int d){
        if(depth == d-1){
            TreeNode left = new TreeNode(v);
            left.left = root.left;
            TreeNode right = new TreeNode(v);
            right.right = root.right;
            root.left = left;
            root.right = right;
        }else{
            if(root.left != null)dfs(root.left,depth+1,v,d);
            if(root.right != null)dfs(root.right,depth+1,v,d);
        }
    }
    //方法3：
    //dfs，without helper
    public TreeNode addOneRow3(TreeNode root, int v, int d) {
        if(d < 2){
            TreeNode node = new TreeNode(v);
            if(d == 1){
                node.left = root;
            }else{
                node.right = root;
            }
            return node;
        }
        if(root == null){
            return null;
        }
        root.left = addOneRow3(root.left,v,d-1);
        root.right = addOneRow3(root.right,v,d == 2 ? 0 : d-1);
        return root;
    }
}
