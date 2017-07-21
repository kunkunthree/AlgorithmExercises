package algorithm.leetcode.algorithm;
/*
 * medium
 * 337. House Robber III
 *  The thief has found himself a new place for his thievery again. There is only one entrance to this area, 
 *  called the "root." Besides the root, each house has one and only one parent house. 
 *  After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
 *  It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

     3
    / \
   2   3
    \    \ 
     3    1

Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

     3
    / \
   4   5
  / \   \ 
 1   3   1

Maximum amount of money the thief can rob = 4 + 5 = 9. 
 */
import java.util.*;
public class NO337_HouseRobberIII {
	//方法1：
	//递归
	public int rob(TreeNode root) {
        int sum1 = 0,sum2 = 0;
        if(root == null){
            return 0;
        }
        sum1+=root.val;
        if(root.left != null){
            sum2+=rob(root.left);
            sum1+=rob(root.left.left)+rob(root.left.right);
        }
        if(root.right != null){
            sum2+=rob(root.right);
            sum1+=rob(root.right.left)+rob(root.right.right);
        }
        return Math.max(sum1,sum2);
    }
	//方法2：
	//节省计算，利用map保存中间结果
	public int rob2(TreeNode root) {
	    return robSub(root, new HashMap<TreeNode, Integer>());
	}

	private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
	    if (root == null) return 0;
	    if (map.containsKey(root)) return map.get(root);
	    
	    int val = 0;
	    
	    if (root.left != null) {
	        val += robSub(root.left.left, map) + robSub(root.left.right, map);
	    }
	    
	    if (root.right != null) {
	        val += robSub(root.right.left, map) + robSub(root.right.right, map);
	    }
	    
	    val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
	    map.put(root, val);
	    
	    return val;
	}
	//方法3：
	//不用map，直接得到取和不取该值的结果
	public int rob3(TreeNode root) {
	    int[] res = robSub(root);
	    return Math.max(res[0], res[1]);
	}

	private int[] robSub(TreeNode root) {
	    if (root == null) return new int[2];
	    
	    int[] left = robSub(root.left);
	    int[] right = robSub(root.right);
	    int[] res = new int[2];

	    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
	    res[1] = root.val + left[0] + right[0];
	    
	    return res;
	}
}
