package algorithm.leetcode.algorithm;
/*
 * easy
 * 108. Convert Sorted Array to Binary Search Tree 
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class NO108_ConvertSortedArraytoBinarySearchTree {
	public static void main(String[] args) {
//		TreeNode root = new TreeNode(-1);
//		root.left = new TreeNode(0);
//		root.right = new TreeNode(1);
//		root.left.left = new TreeNode(2);
		TreeNode node = sortedArrayToBST(new int[]{-1,0,1,2});
		
	}
    public static TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        return getBST(nums,0,nums.length-1);
    }
    public static TreeNode getBST(int[] nums,int start,int end){
        if(start > end){
            return null;
        }
        int mid = (start+end)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = getBST(nums,start,mid-1);
        root.right = getBST(nums,mid+1,end);
        return root;
    }
	//把数据转换为二叉树
//    public static  TreeNode sortedArrayToBST(int[] nums) {
//        if(nums == null || nums.length == 0){
//            return null;
//        }
//        TreeNode node = null,root = null;
//        TreeNode[] array = new TreeNode[nums.length];
//        array[0] = new TreeNode(nums[0]);
//        for(int i = 1 ; i < nums.length ; i++){
//            if(i%2 == 1){
//                array[i/2].left = new TreeNode(nums[i]);
//                array[i] = array[i/2].left;
//            }else{
//                array[(i-1)/2].right = new TreeNode(nums[i]);
//            }
//        }
//        return array[0];
//    }
}
