package algorithm.leetcode.algorithm;
/*
 * medium
 * 655. Print Binary Tree 
 * Print a binary tree in an m*n 2D string array following these rules:
    1.		The row number m should be equal to the height of the given binary tree.
    2.		The column number n should always be an odd number.
    3.		The root node's value (in string format) should be put in the exactly middle of the first row it can be put. 
    The column and the row where the root node belongs will separate the rest space into two parts 
    (left-bottom part and right-bottom part). 
    You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. 
    The left-bottom part and the right-bottom part should have the same size. 
    Even if one subtree is none while the other is not, you don't need to print anything for the none subtree 
    	but still need to leave the space as large as that for the other subtree. 
    However, if two subtrees are none, then you don't need to leave space for both of them.
    4.		Each unused space should contain an empty string "".
    5.		Print the subtrees following the same rules.

Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]

Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]

Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:
[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]

Note: The height of binary tree is in the range of [1, 10]. 
 */
import java.util.*;
public class NO655_PrintBinaryTree {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < 10 ; i++){
			list.add(i);
		}
		list.add(1, 12);
		System.out.println(list);
	}
	//方法1：
	//O(n)time,O(h^3)space,h是树的高度
	//递归，先求出树的高度，然后初始化矩阵，
	//然后利用递归，得到每一层的遍历的长度都不断减半
	public List<List<String>> printTree(TreeNode root) {
        int m = getHeight(root);
        int n = (int)Math.pow(2,m)-1;
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            list.add("");
        }
        for(int i = 0 ; i < m ; i++){
            result.add(new ArrayList<>(list));
        }
        traverse(result,root,0,n/2,(n+1)/2);
        return result;
    }
    private void traverse(List<List<String>> result,TreeNode root,int depth,int start,int length){
        if(root == null ||  depth == result.size()){
            return;
        }
        result.get(depth).set(start,root.val+"");
        traverse(result,root.left,depth+1,start-length/2,length/2);
        traverse(result,root.right,depth+1,start+length/2,length/2);
    }
    private int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(getHeight(root.left),getHeight(root.right));
    }
}
