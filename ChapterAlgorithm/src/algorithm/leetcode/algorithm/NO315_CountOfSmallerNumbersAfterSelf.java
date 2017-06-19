package algorithm.leetcode.algorithm;
/*
 * hard
 * 315. Count of Smaller Numbers After Self 
 *  You are given an integer array nums and you have to return a new counts array. 
 *  The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Return the array [2, 1, 1, 0]. 
 */
import java.util.*;
public class NO315_CountOfSmallerNumbersAfterSelf {
	public static void main(String[] args) {
		NO315_CountOfSmallerNumbersAfterSelf test = new NO315_CountOfSmallerNumbersAfterSelf();
		int[] nums = new int[]{5,2,6,1};
		System.out.println(test.countSmaller2(nums));
	}
	//方法1：
	//利用树，树的左子节点（不是左子树）的值比当前值小，右子节点（不是右子树）的值比当前值大，
	//当前节点包含当前节点出现的次数，和左子树中所有比当前值小的出现次数
	//只要每次添加新节点时，进行累加比当前值小的次数即可
	class TreeNode{
        int leftCount;
        int curCount;
        int val;
        TreeNode left,right;
        TreeNode(int val){
            this.val = val;
            this.leftCount = 0;
            this.curCount = 1;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return list;
        }
        TreeNode root = new TreeNode(nums[nums.length-1]);
        list.add(0,0);
        for(int i = nums.length-2 ; i >= 0 ; i--){
            list.add(0,getSmallCountAndInsert(root,nums[i]));
        }
        return list;
    }
    private int getSmallCountAndInsert(TreeNode root,int num){
        if(root == null){
            return 0;
        }
        if(num < root.val){
            root.leftCount++;
            if(root.left == null){
                root.left = new TreeNode(num);
                return 0;
            }else{
                return getSmallCountAndInsert(root.left,num);
            }
        }else if(num > root.val){
            if(root.right == null){
                root.right = new TreeNode(num);
                return root.leftCount + root.curCount;
            }else{
                return root.leftCount +  root.curCount + getSmallCountAndInsert(root.right,num);
            }
        }else{
            root.curCount++;
            return root.leftCount;
        }
    }
    //方法2：
    //归并排序
    //用一个数组count表示最后结果数组
    //用一个数组记录指向原数组的下标，以下标指向的原数组元素的大小为顺序，对下标进行排序
    //归并时，创建一个临时数组，保存临时排好序的结果
    //			将数组一分为二，分别进行归并排序，在合并过程中，左右两半部分数组已经是排好序的
    //			用两个指针分别从两个数组的最左边开始右移，用一个变量right_count记录右指针移动的次数
    //			当右指针指向元素比左指针指向元素小时，则right_count增加1，该元素放到临时数组中
    //			当右指针指向元素大于等于左指针指向元素时，把该元素放到临时数组中
    //			，并对count[indexes[left_index]]增加right_count，说明此时右边有right_count个元素
    //				放到该元素前面（比该元素小）
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return list;
        }
        int[] indexes = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++){
            indexes[i] = i;
        }
        int[] count = new int[nums.length];
        mergeSort(nums,indexes,0,nums.length-1,count);
        for(int num : count){
            list.add(num);
        }
        return list;
    }
    private void mergeSort(int[] nums,int[] indexes,int start,int end,int[] count){
        if(start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums,indexes,start,mid,count);
        mergeSort(nums,indexes,mid+1,end,count);
        
        merge(nums,indexes,start,end,count);
    }
    private void merge(int[] nums,int[] indexes,int start,int end,int[] count){
        int mid = start + (end - start) / 2;
        int right_count = 0;
        int left_index = start;
        int right_index = mid+1;
        int[] new_indexes = new int[end - start + 1];
        int sort_index = 0;
        while(left_index <= mid && right_index <= end){
            if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                new_indexes[sort_index] = indexes[right_index++];
                right_count++;
            }else{
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += right_count;
                left_index++;
            }
            sort_index++;
        }
        while(left_index <= mid){
            new_indexes[sort_index++] = indexes[left_index];
            count[indexes[left_index]] += right_count;
            left_index++;
        }
        while(right_index <= end){
            new_indexes[sort_index++] = indexes[right_index++];
        }
        for(int i = start ; i <= end ; i++){
            indexes[i] = new_indexes[i-start];
        }
    }
}
