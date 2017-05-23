package algorithm.leetcode.algorithm;
/*
 * medium
 * 102. Binary Tree Level Order Traversal
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
     /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

 */
import java.util.*;
public class NO102_BinaryTreeLevelOrderTraversal {
	//方法1：
	//利用queue分层遍历二叉树
	public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        if(root == null){
            return ll;
        }
        List<Integer> l;
        queue.offer(root);
        TreeNode node;
        while(!queue.isEmpty()){
            l = new ArrayList<Integer>();
            int length = queue.size();
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                l.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ll.add(l);
        }
        return ll;
    }
	
	//方法2：
	//BFS，通过深度优先搜索，得到每层的节点，并将其添加到对应层的数组中
	public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        levelOrderHelper(ll,root,0);
        return ll;
    }
    public void levelOrderHelper(List<List<Integer>> ll,TreeNode node,int height){
        if(node == null){
            return;
        }
        List<Integer> l;
        if(ll.size() <= height){
            l = new ArrayList<Integer>();
            ll.add(l);
        }else{
            l = ll.get(height);
        }
        l.add(node.val);
        levelOrderHelper(ll,node.left,height+1);
        levelOrderHelper(ll,node.right,height+1);
    }
}
