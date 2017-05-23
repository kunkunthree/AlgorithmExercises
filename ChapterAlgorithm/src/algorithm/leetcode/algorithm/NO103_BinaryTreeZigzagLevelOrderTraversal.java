package algorithm.leetcode.algorithm;
/*
 * medium
 * 103. Binary Tree Zigzag Level Order Traversal 
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
      /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]

 */
import java.util.*;
public class NO103_BinaryTreeZigzagLevelOrderTraversal {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean order = false;
		System.out.println(order);
		order^=true;
		System.out.println(order);
	}
	//方法1：
	//用两个栈来分别存储从左到右和从右到左的子节点
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        if(root == null){
            return ll;
        }
        List<Integer> l;
        stack1.push(root);
        int length;
        TreeNode node;
        while(!stack1.isEmpty()){
            l = new ArrayList<Integer>();
            length = stack1.size();
            while(!stack1.isEmpty()){
                node = stack1.pop();
                l.add(node.val);
                if(node.left != null){
                    stack2.push(node.left);
                }
                if(node.right != null){
                    stack2.push(node.right);
                }
            }
            ll.add(l);
            if(!stack2.isEmpty()){
                l = new ArrayList<Integer>();
                length = stack2.size();
                while(!stack2.isEmpty()){
                    node = stack2.pop();
                    l.add(node.val);
                    if(node.right != null){
                        stack1.push(node.right);
                    }
                    if(node.left != null){
                        stack1.push(node.left);
                    }
                }
                ll.add(l);
            }
        }
        return ll;
    }
	//方法2：
	//和分层顺序扫描一样，只是list添加方法的参数不一样，从左到右时，直接add(val)，从右到左时，调用add(0,val)
	//用一个boolean型变量表示方向
	public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null){
            return ll;
        }
        List<Integer> l;
        queue.offer(root);
        int length;
        TreeNode node;
        boolean order = false; //false表示从右到左，true表示从左到右
        while(!queue.isEmpty()){
            l = new ArrayList<Integer>();
            length = queue.size();
            for(int i = 0 ; i < length ; i++){
                node = queue.poll();
                if(order){
                    l.add(0,node.val);
                }else{
                    l.add(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            order^=true;
            ll.add(l);
        }
        return ll;
    }
	
	//方法3：
	//DFS，通过高度的奇偶性，当为偶数时调用add(val)，当为偶数时调用add(0,val)
	public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        List<List<Integer>> ll = new ArrayList<List<Integer>>();
        zigzagLevelOrderHelper(ll,root,0);
        return ll;
        
    }
    public void zigzagLevelOrderHelper(List<List<Integer>> ll,TreeNode root,int height){
        if(root == null){
            return;
        }
        List<Integer> l;
        if(ll.size() <= height){
            l = new ArrayList<Integer>();
            ll.add(l);
        }else{
            l = ll.get(height);
        }
        if((height&1) == 1){
            l.add(0,root.val);
        }else{
            l.add(root.val);
        }
        zigzagLevelOrderHelper(ll,root.left,height+1);
        zigzagLevelOrderHelper(ll,root.right,height+1);
    }
}
