package algorithm.leetcode.algorithm;
/*
 * easy
 * 530. Minimum Absolute Difference in BST
 * Given a binary search tree with non-negative values, find the minimum absolute difference 
 * between values of any two nodes.
Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

Note: There are at least two nodes in this BST. 
 */
import java.util.*;
public class NO530_MinimumAbsoluteDifferenceinBST {
	public static void main(String[] args) {
		TreeNode root = TreeNode.getTree(new Object[]{2147483647,2147483646});
		System.out.println(getMinimumDifference3(root));
//		TreeSet<Integer> set = new TreeSet<Integer>();
	}
    public static int min = Integer.MAX_VALUE;
    public static Integer pre = null;
    public static int getMinimumDifference(TreeNode root) {
        if(root == null){
            return Integer.MAX_VALUE;
        }
        min = Math.min(min,getMinimumDifference(root.left));
        if(pre != null){
            min = Math.min(min,Math.abs(pre - root.val));
        }
        pre = root.val;
        min = Math.min(min,getMinimumDifference(root.right));
        return min;
    }
    //假设不是BST：
    //利用TreeSet的floor函数和ceil函数
    //floor(e)，获得比这个元素e更小的最大元素
    //ceil(e)，获得比这个元素e更大的最小元素
    TreeSet<Integer> set = new TreeSet<>();
    int min2 = Integer.MAX_VALUE;
    
    public int getMinimumDifference2(TreeNode root) {
        if (root == null) return min2;
        
        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min2 = Math.min(min2, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min2 = Math.min(min2, set.ceiling(root.val) - root.val);
            }
        }
        
        set.add(root.val);
        
        getMinimumDifference2(root.left);
        getMinimumDifference2(root.right);
        
        return min2;
    }
    
    //先根遍历，利用二叉查找树的性质，
    //左子树所有节点的最大上限为它的根节点的值，右子树所有节点的最小下限为它的根节点的值
    static long minDiff = Long.MAX_VALUE;
    public static int getMinimumDifference3(TreeNode root) {
        helper(root,Long.MIN_VALUE,Long.MAX_VALUE);
        return (int)minDiff;
    }
    private static void helper(TreeNode curr, long lb, long rb){
        if(curr==null) return;
        if(lb!=Long.MIN_VALUE){
            minDiff = Math.min(minDiff,curr.val - lb);
        }
        if(rb!=Long.MAX_VALUE){
        	minDiff = Math.min(minDiff,rb - curr.val);
        }
        helper(curr.left,lb,curr.val);
        helper(curr.right,curr.val,rb);
    }
}
