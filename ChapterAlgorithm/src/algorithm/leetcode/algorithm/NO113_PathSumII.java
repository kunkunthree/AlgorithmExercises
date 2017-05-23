package algorithm.leetcode.algorithm;
/*
 * medium
 * 113. Path Sum II
 *  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
For example:
Given the below binary tree and sum = 22,

               5
              / \
            4    8
           /       / \
          11  13  4
         /  \        / \
        7    2    5   1

return

[
   [5,4,11,2],
   [5,8,4,5]
]

 */
import java.util.*;
public class NO113_PathSumII {
	//方法1：
	//递归实现
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ll = new ArrayList<>();
        if(root == null){
            return ll;
        }
        List<Integer> l = new ArrayList<>();
        pathSumHelper(ll,l,root,sum);
        return ll;
    }
    public void pathSumHelper(List<List<Integer>> ll,List<Integer> l,TreeNode node,int sum){
        if(node == null){
            return;
        }
        l.add(node.val);
        sum-=node.val;
        if(node.left == null && node.right == null && sum == 0){
            ll.add(new ArrayList<>(l));
            l.remove(l.size()-1);
            return;
        }
        pathSumHelper(ll,l,node.left,sum);
        pathSumHelper(ll,l,node.right,sum);
        l.remove(l.size()-1);
    }
}
