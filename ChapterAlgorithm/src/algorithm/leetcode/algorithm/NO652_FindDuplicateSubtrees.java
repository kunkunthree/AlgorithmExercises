package algorithm.leetcode.algorithm;
/*
 * medium
 * 652. Find Duplicate Subtrees 
 *  Given a binary tree, return all duplicate subtrees. 
 *  For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.
Example 1:
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4

The following are two duplicate subtrees:
      2
     /
    4
and
    4

Therefore, you need to return above trees' root in the form of a list. 
 */
import java.util.*;
public class NO652_FindDuplicateSubtrees {
	//方法1:
	//O(n)time,O(n)space
	//递归，先根遍历
	//利用先根遍历构造一个子树的字符串，左右子树用“#”分割，每个节点用“，”分割，
	//使每一个唯一结构的子树有唯一的字符串与之对应，用一个Map对该字符串进行计数，
	//当计数为2时，将该节点加入结果集
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String,Integer> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        preorder(root,map,result);
        return result;
    }
    private String preorder(TreeNode root,Map<String,Integer> map,List<TreeNode> result){
        if(root == null){
            return "#";
        }
        String path = root.val + "," + preorder(root.left,map,result) + "," + preorder(root.right,map,result);
        if(!map.containsKey(path)){
            map.put(path,0);
        }
        map.put(path,map.get(path)+1);
        if(map.get(path) == 2){
            result.add(root);
        }
        return path;
    }
}
