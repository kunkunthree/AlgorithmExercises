package algorithm.leetcode.algorithm;
/*
 * easy
 * 572. Subtree of Another Tree 
 *  Given two non-empty binary trees s and t, check whether tree t has exactly the same structure 
 *  and node values with a subtree of s. A subtree of s is a tree consists of a node in s 
 *  and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:
     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.

Example 2:
Given tree s:
     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2

Return false. 
 */
public class NO572_SubtreeofAnotherTree {
	//方法1：
	//递归，每个节点判断是否其子树结构与t相同
	public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null){
            return false;
        }
        return equal(s,t) || isSubtree(s.left,t) || isSubtree(s.right,t);
    }
    public boolean equal(TreeNode s, TreeNode t){
        if(s == null && t == null){
            return true;
        }
        if(s != null && t != null && s.val == t.val){
            return equal(s.left,t.left) && equal(s.right,t.right);
        }
        return false;
    }
    //方法2：
    //树结构序列化成字符串，树s得到字符串ss，树t得到字符串ts，判断ts是否时ss的子字符串
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        return serialize(s).contains(serialize(t)); // Java use a naive contains algorithm so to ensure linear time, 
                                                    // replace with KMP algorithm
    }
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        serialize(root, res);
        return res.toString();
    }
    private void serialize(TreeNode cur, StringBuilder res) {
        if (cur == null) {res.append(",#"); return;}
        res.append("," + cur.val);
        serialize(cur.left, res);
        serialize(cur.right, res);
    }
}
