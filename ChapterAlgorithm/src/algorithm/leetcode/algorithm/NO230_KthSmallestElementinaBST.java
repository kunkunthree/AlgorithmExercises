package algorithm.leetcode.algorithm;
/*
 * medium
 * 230. Kth Smallest Element in a BST
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ? k ? BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?
 */
import java.util.*;
public class NO230_KthSmallestElementinaBST {
	//如果BST经常被修改，那么可以给TreeNode添加一个域表示整棵树节点的个数，建立BST时，节点数已经设置好
	//而且搜索的时候只需要搜索个数即可，而插入或删除时，更新个数即可
	//方法1：
	//中序遍历，中根遍历，迭代实现
	public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                k--;
                if(k == 0){
                    return root.val;
                }
                root = root.right;
            }
        }
        return 0;
    }
	//方法2：
	//递归实现
	private int count = 0;
    private int result = Integer.MAX_VALUE;
    public int kthSmallest2(TreeNode root, int k) {
        kthSmallestHelper(root,k);
        return result;
    }
    public void kthSmallestHelper(TreeNode root,int k){
        if(count > k || root == null){
            return;
        }
        kthSmallestHelper(root.left,k);
        count++;
        if(count == k){
            result = root.val;
        }
        kthSmallestHelper(root.right,k);
    }
    //方法3：
    //创建新的节点类型，添加一个表示整棵树节点个数的域
    public int kthSmallest3(TreeNode root, int k) {
        TreeNodeWithCount rootWithCount = buildTreeWithCount(root);
        return kthSmallest(rootWithCount, k);
    }
    
    private TreeNodeWithCount buildTreeWithCount(TreeNode root) {
        if (root == null) return null;
        TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
        rootWithCount.left = buildTreeWithCount(root.left);
        rootWithCount.right = buildTreeWithCount(root.right);
        if (rootWithCount.left != null) rootWithCount.count += rootWithCount.left.count;
        if (rootWithCount.right != null) rootWithCount.count += rootWithCount.right.count;
        return rootWithCount;
    }
    
    private int kthSmallest(TreeNodeWithCount rootWithCount, int k) {
        if (k <= 0 || k > rootWithCount.count) return -1;
        if (rootWithCount.left != null) {
            if (rootWithCount.left.count >= k) return kthSmallest(rootWithCount.left, k);
            if (rootWithCount.left.count == k-1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k-1-rootWithCount.left.count);
        } else {
            if (k == 1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k-1);
        }
    }
    
    class TreeNodeWithCount {
        int val;
        int count;
        TreeNodeWithCount left;
        TreeNodeWithCount right;
        TreeNodeWithCount(int x) {val = x; count = 1;};
    }
}
