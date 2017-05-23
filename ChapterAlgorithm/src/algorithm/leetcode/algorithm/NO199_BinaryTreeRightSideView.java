package algorithm.leetcode.algorithm;
/*
 * medium
 * 199. Binary Tree Right Side View
 * Given a binary tree, imagine yourself standing on the right side of it,
 *  return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,

    1            <---
  /   \
2     3         <---
 \       \
  5       4       <---

You should return [1, 3, 4]. 
 */
import java.util.*;
public class NO199_BinaryTreeRightSideView {
	//方法1：
	//分层遍历扫描，只取最后一个节点
	public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int length;
        TreeNode node = null;
        while(!queue.isEmpty()){
            length = queue.size();
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(node.val);
        }
        return list;
    }
	//方法2：
	//DFS，
	//1.Each depth of the tree only select one node.
	//2.View depth is current size of result list.
	public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideViewHelper(list,root,0);
        return list;
    }
    public void rightSideViewHelper(List<Integer> list,TreeNode root,int depth){
        if(root == null){
            return;
        }
        if(depth == list.size()){
            list.add(root.val);
        }
        rightSideViewHelper(list,root.right,depth+1);
        rightSideViewHelper(list,root.left,depth+1);
    }
}
