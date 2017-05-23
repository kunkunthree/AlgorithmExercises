package algorithm.leetcode.algorithm;
/*
 * medium
 * 508. Most Frequent Subtree Sum
 *  Given the root of a tree, you are asked to find the most frequent subtree sum. 
 *  The subtree sum of a node is defined as the sum of all the node values formed 
 *  by the subtree rooted at that node (including the node itself). 
 *  So what is the most frequent subtree sum value? 
 *  If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3

return [2, -3, 4], since all the values happen only once, return all of them in any order.

Examples 2
Input:

  5
 /  \
2   -5

return [2], since 2 happens twice, however -5 only occur once.

Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer. 
 */
import java.util.*;
public class NO508_MostFrequentSubtreeSum {
	//方法1：
	//DFS，用map记录子树所有节点的和出现的次数
	private int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        getTreeSum(root,map);
        List<Integer> list = new ArrayList<>();
        for(int sum : map.keySet()){
            if(map.get(sum) == max){
                list.add(sum);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i++){
            result[i] = list.get(i);
        }
        return result;
    }
    private int getTreeSum(TreeNode root,Map<Integer,Integer> map){
        if(root == null){
            return 0;
        }
        int result = root.val;
        result+=getTreeSum(root.left,map)+getTreeSum(root.right,map);
        if(!map.containsKey(result)){
            map.put(result,0);
        }
        map.put(result,map.get(result)+1);
        max = Math.max(max,map.get(result));
        return result;
    }
}
