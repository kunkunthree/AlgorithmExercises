package algorithm.leetcode.algorithm;
/*
 * medium
 * 331. Verify Preorder Serialization of a Binary Tree
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, 
 * we record the node's value. If it is a null node, we record using a sentinel value such as #.

		     _9_
		    /     \
		 3       	2
		/ \      	/ \
	   4   1    #  6
	 / \   / \       /  \
   # # # #     #  #

For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", 
where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
 Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, 
for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
 */
import java.util.*;
public class NO331_VerifyPreorderSerializationOfABinaryTree {
	//方法1：
	//O(n)time,O(1)space
	//树所有节点的总入度和总出度相等
	//因为树的除根节点外所有节点的入度都为1（根节点入度为0）
	//非空节点的出度为2，空节点的出度为0,
	//设非空节点的个数为n，则空节点的个数为2×n-(n-1) = n+1
	//总节点个数为2n+1，总出度为2n
	//所以总节点个数2n+1 = outdegrees + 1
	public boolean isValidSerialization(String preorder) {
        //diff = outdegree - indegree   ,因为根节点入度为0，但根节点算在内，所以初始化为1
        int diff = 1;   
        String[] nodes = preorder.split(",");
        for(String node : nodes){
            diff--;
            //在先根遍历中因为先访问根节点，所以非空节点先统计完，所以出度总是大于等于入度
            //如果统计还没结束，而出度小于入度，那么返回false
            if(diff < 0){	
                return false;
            }
            if(!node.equals("#")){
                diff+=2;
            }
        }
        return diff == 0;
    }
	//方法2：
	//利用stack，O(n)time
//	when you iterate through the preorder traversal string, for each char:
//	case 1: you see a number c, means you begin to expand a new tree rooted with c, you push it to stack
//	case 2.1: you see a #, while top of stack is a number, you know this # is a left null child, 
//						put it there as a mark for next coming node k to know it is being the right child.
//	case 2.2: you see a #, while top of stack is #, you know you meet this # as right null child, 
//						you now cancel the sub tree (rooted as t, for example) with these two-# children. 
//						But wait, after the cancellation, you continue to check top of stack is whether # or a number:
//	---- if a number, say u, you know you just cancelled a node t which is left child of u. 
//						You need to leave a # mark to the top of stack. So that the next node know it is a right child.
//	---- if a #, you know you just cancelled a tree whose root, t, is the right child of u. 
//						So you continue to cancel sub tree of u, and the process goes on and on.
	public boolean isValidSerialization2(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] nodes = preorder.split(",");
        for(String node : nodes){
            if(node.equals("#")){
                while(!stack.isEmpty() && stack.peek().equals("#")){
                    stack.pop();
                    if(stack.isEmpty()){
                        return false;
                    }
                    stack.pop();
                }
            }
            stack.push(node);
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }
}
