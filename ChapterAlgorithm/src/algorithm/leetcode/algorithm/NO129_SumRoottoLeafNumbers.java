package algorithm.leetcode.algorithm;
/*
 * medium
 * 129. Sum Root to Leaf Numbers
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3

The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25. 
 */
import java.util.*;
public class NO129_SumRoottoLeafNumbers {
	//方法1：
	//递归实现
	public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root,0);
    }
    public int sumNumbersHelper(TreeNode root,int sum){
        if(root == null){
            return 0;
        }
        sum = sum*10 + root.val;
        if(root.left == null && root.right == null){
            return sum;
        }
        return sumNumbersHelper(root.left,sum)+sumNumbersHelper(root.right,sum);
    }
    //方法2：
    //迭代形式，把每个路径的值叠加到节点的值上，节省空间
    public int sumNumbers2(TreeNode root) {
        if(root == null){
            return 0;
        }
        int sum = 0,length = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        TreeNode node;
        while(!queue.isEmpty()){
            length = queue.size();
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                if(node.left == null && node.right == null){
                    sum+=node.val;
                }
                if(node.left != null){
                    node.left.val+=node.val*10;
                    queue.offer(node.left);
                }
                if(node.right != null){
                    node.right.val+=node.val*10;
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }
}
