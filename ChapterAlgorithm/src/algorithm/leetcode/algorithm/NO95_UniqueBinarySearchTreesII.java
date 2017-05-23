package algorithm.leetcode.algorithm;
/*
 * medium
 * 95. Unique Binary Search Trees II 
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */
import java.util.*;
public class NO95_UniqueBinarySearchTreesII {
	public static void main(String[] args) {
		System.out.println(generateTrees3(4));
	}
	//方法1：
	//递归
	public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<TreeNode>();
        }
        return generateTreesHelper(1,n);
    }
    public List<TreeNode> generateTreesHelper(int start,int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(start > end){
            list.add(null);
            return list;
        }
        if(start == end){
            TreeNode node = new TreeNode(start);
            list.add(node);
            return list;
        }
        for(int i = start ; i <= end ; i++){
            for(TreeNode left : generateTreesHelper(start,i-1)){
                for(TreeNode right : generateTreesHelper(i+1,end)){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
    
    //方法2：
    //动态规划，dp
    public List<TreeNode> generateTrees2(int n) {
        List<TreeNode>[] list = new List[n+1];
        list[0] = new ArrayList<TreeNode>();
        if(n == 0){
            return list[0];
        }
        list[0].add(null);
        for(int i = 1 ; i <= n ; i++){
            list[i] = new ArrayList<TreeNode>();
            for(int j = 1 ; j <= i ; j++){
                for(TreeNode left : list[j-1]){
                    for(TreeNode right : list[i-j]){
                        TreeNode root = new TreeNode(j);
                        // root.left = copy(left,0);
                        root.left = left;   //左子树可以重复利用，因为当前节点的左子树都是确定的
                        root.right = copy(right,j);
                        list[i].add(root);
                    }
                }
            }
        }
        return list[n];
    }
    //复制链表，加上节点的偏移值
    public TreeNode copy(TreeNode root,int offset){
        if(root == null){
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val+offset);
        newRoot.left = copy(root.left,offset);
        newRoot.right = copy(root.right,offset);
        return newRoot;
    }
    //方法3：
    //dp，从n到1
    public static List<TreeNode> generateTrees3(int n) {
        List<TreeNode> res = new ArrayList<>();
        res.add(null);
        for(; n > 0; n--) {
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode node: res) {
                //the special case when Node(n) is root of new tree
                TreeNode root = new TreeNode(n); 
                root.right = node;
                next.add(root);
               //while loop inserts new value to every possible position into the left tree side
                while(node != null) {
                    TreeNode cRoot = new TreeNode(root.right.val);
                    //clone left subtree
                    cRoot.left = copyTree(root.right.left);
                    //reusing - point new root.right to the original right subtree
                    cRoot.right = root.right.right;
                    //curr is the cutoff node whose right child will be replaced by the new n 
                    TreeNode curr = getValNode(cRoot, node.val); 
                    //place n as curr's right child, make curr's original right child as the left child of n.
                    TreeNode tmp = curr.left;
                    curr.left = new TreeNode(n);
                    curr.left.right = tmp;

                    next.add(cRoot);
                    node = node.left;
                }
            }
            res = next;
        }
        return res;
    }
    private static TreeNode getValNode(TreeNode n, int val) { //find the cutoff node in the new tree
        while(n != null) {
            if(n.val == val) break;
            n = n.left;
        }
        return n;
    }

    private static TreeNode copyTree(TreeNode root) { //clone the right subtree
        if(root == null) return null;
        TreeNode cRoot = new TreeNode(root.val);
        cRoot.left = copyTree(root.left);
        cRoot.right = copyTree(root.right);
        return cRoot;
    }
}
