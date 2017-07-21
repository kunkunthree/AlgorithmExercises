package algorithm.leetcode.algorithm;
/*
 * easy
 *  637. Average of Levels in Binary Tree
 *  Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:

Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].

Note:
    The range of node's value is in the range of 32-bit signed integer.
 */
import java.util.*;
public class NO637_AverageOfLevelsInBinaryTree {
	//方法1：
	//迭代，分层遍历
	//O(n)time,O(n)space
	public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        double sum = 0.0;
        while(!queue.isEmpty()){
            int length = queue.size();
            sum = 0.0;
            for(int i = 0 ; i < length ; i++){
                root = queue.poll();
                sum+=root.val;
                if(root.left != null){
                    queue.offer(root.left);
                }
                if(root.right != null){
                    queue.offer(root.right);
                }
            }
            list.add(sum/length);
        }
        return list;
    }
	//方法2：
	//dfs
	//O(n)time,O(n)space
	public List<Double> averageOfLevels2(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        helper(root,0,result,countList);
        for(int i = 0 ; i < result.size() ; i++){
            result.set(i,result.get(i)/countList.get(i));
        }
        return result;
    }
    private void helper(TreeNode root,int depth,List<Double> sumList,List<Integer> countList){
        if(root == null){
            return;
        }
        if(countList.size() == depth){
            countList.add(0);
            sumList.add(0.0);
        }
        countList.set(depth,countList.get(depth)+1);
        sumList.set(depth,sumList.get(depth)+root.val);
        helper(root.left,depth+1,sumList,countList);
        helper(root.right,depth+1,sumList,countList);
    }
}
