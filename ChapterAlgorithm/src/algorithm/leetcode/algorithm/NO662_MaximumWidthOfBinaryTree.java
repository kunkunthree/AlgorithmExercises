package algorithm.leetcode.algorithm;
/*
 * medium
 * 662. Maximum Width of Binary Tree 
 * Given a binary tree, write a function to get the maximum width of the given tree. 
 * The width of a tree is the maximum width among all levels. 
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 * 
 * The width of one level is defined as the length between the end-nodes (the leftmost 
 * and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted
 *  into the length calculation.

Example 1:
Input: 
           1
         /   \
        3     2
       / \     \  
      5   3     9 
Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input: 
          1
         /  
        3    
       / \       
      5   3     
Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 
          1
         / \
        3   2 
       /        
      5      
Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input: 
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Note: Answer will in the range of 32-bit signed integer. 
 */
import java.util.*;
public class NO662_MaximumWidthOfBinaryTree {
	//方法1：
	//O(n)time,O(n)space
	//迭代
	//为每一个节点定义一个坐标，按照队列的结构排序
	//用一个nodeQueue保存节点，一个indexQueue保存坐标，
	//分层遍历每一层的节点，得到最左边节点坐标和最右边节点坐标，计算差值，得到最大差值
	public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<Integer> indexQueue = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        indexQueue.offer(1);
        nodeQueue.offer(root);
        int max = 1,curIndex,count,minIndex,maxIndex;
        while(!nodeQueue.isEmpty()){
            count = nodeQueue.size();
            minIndex = Integer.MAX_VALUE;
            maxIndex = Integer.MIN_VALUE;
            for(int i = 0 ; i < count ; i++){
                root = nodeQueue.poll();
                curIndex = indexQueue.poll();
                minIndex = Math.min(minIndex,curIndex);
                maxIndex = Math.max(maxIndex,curIndex);
                if(root.left != null){
                    nodeQueue.offer(root.left);
                    indexQueue.offer(curIndex*2);
                }
                if(root.right != null){
                    nodeQueue.offer(root.right);
                    indexQueue.offer(curIndex*2+1);
                }
            }
            max = Math.max(max,maxIndex-minIndex+1);
        }
        return max;
    }
	
	//方法2：
	//递归，用两个list保存当前已遍历的每层节点的下标的最大值和最小值
	public int widthOfBinaryTree2(TreeNode root) {
        return widthOfBinaryTree(root,0,1,new ArrayList<Integer>(),new ArrayList<Integer>());
    }
    public int widthOfBinaryTree(TreeNode root,int level,int curIndex,List<Integer> start,List<Integer> end){
        if(root == null){
            return 0;
        }
        if(level == start.size()){
            start.add(curIndex);
            end.add(curIndex);
        }else{
            end.set(level,curIndex);
        }
        int curWidth = end.get(level)-start.get(level)+1;
        int left = widthOfBinaryTree(root.left,level+1,curIndex*2,start,end);
        int right = widthOfBinaryTree(root.right,level+1,curIndex*2+1,start,end);
        return Math.max(curWidth,Math.max(left,right));
    }
}
