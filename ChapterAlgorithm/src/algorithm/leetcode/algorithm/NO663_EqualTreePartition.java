package algorithm.leetcode.algorithm;
/*
 * medium
 * 663. Equal Tree Partition 
 *  Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees
 *   which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3
Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15

Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20
Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.

Note:
    1.	The range of tree node value is in the range of [-100000, 100000].
    2.	1 <= n <= 10000

 */
import java.util.*;
public class NO663_EqualTreePartition {
	//方法1：
	//递归，O(n)time,O(n)space
	//用两个map分别保存左子树、右子树的总和
	//cur表示当前节点切除左右子树后整棵树的总和
	//遍历每一个节点，判断是否存在root.right != null && cur+left == right或root.left != null && cur+right == left，
	//如果存在，则返回true，否则遍历左右子树
	public boolean checkEqualTree(TreeNode root) {
        if(root == null){
            return false;
        }
        Map<TreeNode,Integer> parentMap = new HashMap<>();
        Map<TreeNode,Integer> leftMap = new HashMap<>();
        Map<TreeNode,Integer> rightMap = new HashMap<>();
        getSum(root,leftMap,rightMap);
        parentMap.put(root,0);
        leftMap.put(null,0);
        rightMap.put(null,0);
        return checkEqualTree(root,0,leftMap,rightMap);
    }
    public boolean checkEqualTree(TreeNode root,int parent,
                                  Map<TreeNode,Integer> leftMap,
                                  Map<TreeNode,Integer> rightMap){
        if(root == null){
            return false;
        }
        int cur = parent+root.val;
        int left = leftMap.get(root.left);
        int right = rightMap.get(root.right);
        if(root.right != null && cur+left == right){
            return true;
        }
        if(root.left != null && cur+right == left){
            return true;
        }
        return checkEqualTree(root.left,cur+right,leftMap,rightMap) || checkEqualTree(root.right,cur+left,leftMap,rightMap);
    }
    public int getSum(TreeNode root,Map<TreeNode,Integer> leftMap,Map<TreeNode,Integer> rightMap){
        if(root == null){
            return 0;
        }
        int left = getSum(root.left,leftMap,rightMap);
        leftMap.put(root.left,left);
        int right = getSum(root.right,leftMap,rightMap);
        rightMap.put(root.right,right);
        return root.val+left+right;
    }
    
    //方法2：
    //方法1的优化
    //只需要一个map，保存以某个节点为树的根节点对应整棵树的总和及相应的个数
    //得到根节点对应的整棵树的总和sum，判断map中是否存在sum/2（sum为偶数）的节点，
    //如果存在，则返回true，如果不存在，则返回false
    //特殊情况：
    //sum为奇数，返回false
    //sum为0，则判断map中0对应的个数是否大于1，大于1则返回true，否则返回false
    public boolean checkEqualTree2(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = getsum(root, map);
        if(sum == 0){
        	int count = 0;
        	if(map.containsKey(sum)){
        		count = map.get(sum);
        	}
        	return count > 1;
        }
        return sum%2 == 0 && map.containsKey(sum/2);
    }
    
    public int getsum(TreeNode root, Map<Integer, Integer> map ){
        if(root == null)return 0;
        int cur = root.val + getsum(root.left, map) + getsum(root.right, map);
        if(!map.containsKey(cur)){
        	map.put(cur, 0);
        }
        map.put(cur, map.get(cur) + 1);
        return cur;
    }
}
