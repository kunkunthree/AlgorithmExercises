package algorithm.leetcode.algorithm;
/*
 * easy
 * 501. Find Mode in Binary Search Tree
 * Given a binary search tree (BST) with duplicates, 
 * find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than or equal to the node's key.
    The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
    Both the left and right subtrees must also be binary search trees.

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2

return [2].

Note: If a tree has more than one mode, you can return them in any order.
Follow up: Could you do that without using any extra space?
 (Assume that the implicit stack space incurred due to recursion does not count). 
 */
import java.util.*;
public class NO501_FindModeinBinarySearchTree {
	public static void main(String[] args) {
//		TreeNode root = TreeNode.getTree(new Object[]{1,1,2,null,null,1,2,null,null,null,null,null,null,null,2});
		TreeNode root = TreeNode.getTree(new Object[]{1,1,2,0,1,1,2,0,0});
		System.out.println(Arrays.toString(findMode2(root)));
	}
	//方法1，利用map，把所有数和其出现频率都记录下来
    public static int[] findMode(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int max = findModeHelper(root,map,0);
        System.out.println(max);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int key : map.keySet()){
            if(map.get(key) == max){
                list.add(key);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0 ; i < result.length ; i++){
            result[i] = list.get(i);
        }
        return result;
        
    }
    //记录频率，返回最大频率
    public static int findModeHelper(TreeNode root,Map<Integer,Integer> map,int max){
        if(root == null){
            return 0;
        }
        int tmp = 0;
        if(map.containsKey(root.val)){
        	tmp = map.get(root.val)+1;
        }else{
        	tmp = 1;
        }
        map.put(root.val,tmp);
        max = Math.max(max,tmp);
        max = Math.max(max,findModeHelper(root.left,map,max));
        max = Math.max(max,findModeHelper(root.right,map,max));
        return max;
    }
	static Integer prev = null;
    static int count = 1;
    static int max = 0;
    //方法2，O(1)space，O(n)time，据观察，通过中根遍历，所有值相等的节点都是连续访问的
    //二叉查找树通过中根遍历得到的遍历顺序就是一个非递减（递增）的序列
    //所以只需要每次记录每个数出现的频率，保存最大频率的数，
    //如果有更大频率的数，则丢弃之前存储的数，更新新的数
    public static int[] findMode2(TreeNode root) {
        if (root == null) return new int[0];
        
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;
    }
    
    private static void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);
        if (prev != null) {
            if (root.val == prev)
                count++;
            else
                count = 1;
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }
        prev = root.val;
        traverse(root.right, list);
    }
}
