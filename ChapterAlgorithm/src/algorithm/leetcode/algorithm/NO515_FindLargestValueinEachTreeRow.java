package algorithm.leetcode.algorithm;
/*
 * medium
 * 515. Find Largest Value in Each Tree Row 
 * You need to find the largest value in each row of a binary tree.

Example:

Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]

 */
import java.util.*;
public class NO515_FindLargestValueinEachTreeRow {
	//方法1：
	//分层遍历，BFS
	public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int length,i,max;
        queue.offer(root);
        while(!queue.isEmpty()){
            length = queue.size();
            max = Integer.MIN_VALUE;
            for(i = 0 ; i < length ; i++){
                root = queue.poll();
                max = Math.max(max,root.val);
                if(root.left != null){
                    queue.offer(root.left);
                }
                if(root.right != null){
                    queue.offer(root.right);
                }
            }
            list.add(max);
        }
        return list;
    }
	//方法2：
	//DFS
	public List<Integer> largestValues2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list,root,0);
        return list;
    }
    private void dfs(List<Integer> list,TreeNode root,int depth){
        if(root == null){
            return;
        }
        if(depth == list.size()){
            list.add(root.val);
        }
        list.set(depth,Math.max(list.get(depth),root.val));
        dfs(list,root.left,depth+1);
        dfs(list,root.right,depth+1);
    }
}
