package algorithm.leetcode.algorithm;
/*
 * easy
 * 437. Path Sum III 
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards 
 * (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \     \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 */
import java.util.*;
public class NO437_PathSumIII {
	public static void main(String[] args) {
		TreeNode root = TreeNode.getTree(new Object[]{1,-2,-3,1,3,-2,null,-1});
		System.out.println(pathSum(root, -1));
	}
    public static int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        int count = 0;
        if(root.val == sum){
            count++;
        }
        count+= pathSumHelper(root.left,sum-root.val) + pathSumHelper(root.right,sum-root.val);
        count+= pathSum(root.left,sum) + pathSum(root.right,sum);
        return count;
    }
    public static int pathSumHelper(TreeNode root,int sum){
        if(root == null){
            return 0;
        }
        int count = 0;
        if(root.val == sum){
            count++;
        }
        count+=pathSumHelper(root.left,sum-root.val) + pathSumHelper(root.right,sum-root.val);
        return count;
    }
    
    //利用HashMap存储以根节点开始当前节点结尾总和为sum为key，到达当前节点总和为sum的路径数为value
    //每次到达某一个节点，计算到达上一个节点为止，更新currSum=currSum+val，
    //得到到达上一个节点key为currSum的路径数，并更新从根节点到达当前节点为止的总和的路径数+1
    //遍历左右子节点得到符合要求的路径数并加到前面的路径数中，
    //最后将map中更新从根节点到达当前节点为止的总和的路径数-1，以免影响后面的map使用，因为只有一个map
    //最终返回前面的路径数
    public int pathSum2(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = 0;
        if (preSum.containsKey(currSum - target)) {
        	res += preSum.get(currSum - target);
        }
        if (!preSum.containsKey(currSum)) {
            preSum.put(currSum, 1);
        } else {
            preSum.put(currSum, preSum.get(currSum)+1);
        }
//        int res = preSum.getOrDefault(currSum - target, 0);		//java8 map新特性 getOrDefault 
//        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}
