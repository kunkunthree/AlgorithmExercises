package algorithm.leetcode.algorithm;
/*
 * medium
 * 307. Range Sum Query - Mutable
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:

    1.The array is only modifiable by the update function.
    2.You may assume the number of calls to update and sumRange function is distributed evenly.

 */
public class NO307_RangeSumQuery_Mutable {
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,5};
		NO307_RangeSumQuery_Mutable rsqm = new NO307_RangeSumQuery_Mutable();
		NumArray na = rsqm.new NumArray(nums);
		System.out.println(na.sumRange(0, 2));
		na.update(1, 2);
		System.out.println(na.sumRange(0, 2));
	}
	//方法1：
	//利用dp存储前i个数的和，更改某个数num[i]时，把后面所有dp[i+1]...dp[n]都更新了
	//更新频率大的 话效率太低，要更新的数据太多，太慢
	class NumArray {
		private int[] nums;
		private int[] dp;
		public NumArray(int[] nums) {
			this.nums = nums;
			int n = nums.length;
			dp = new int[n+1];
			for(int i = 1 ; i <= n ; i++){
				dp[i] = dp[i-1] + nums[i-1];
			}
		}
		
		public void update(int i, int val) {
			int add = val - nums[i];
	        nums[i] = val;
	        for(int j = i+1 ; j < dp.length ; j++){
	            dp[j]+=add;
	        }
		}
		
		public int sumRange(int i, int j) {
			return dp[j+1]-dp[i];
		}
	}
	//方法2：
	//利用SegmentTree线段树把整个数组的和二分保存起来
	class NumArray2 {
	    class SegmentTreeNode{
	        int sum,start,end;
	        SegmentTreeNode left,right;
	        SegmentTreeNode(int start,int end){
	            this.start = start;
	            this.end = end;
	            sum = 0;
	            left = null;
	            right = null;
	        }
	    }
	    private SegmentTreeNode buildTree(int[] nums,int start,int end){
	        if(start > end){
	            return null;
	        }else{
	            SegmentTreeNode node = new SegmentTreeNode(start,end);
	            if(start == end){
	                node.sum = nums[start];
	            }else{
	                int mid = start + (end - start)/2;
	                node.left = buildTree(nums,start,mid);
	                node.right = buildTree(nums,mid+1,end);
	                node.sum = node.left.sum+node.right.sum;
	            }
	            return node;
	        }
	    }
	    private SegmentTreeNode root;
	    public NumArray2(int[] nums) {
	        root = buildTree(nums,0,nums.length-1);
	    }
	    
	    public void update(int i, int val) {
	        update(root,i,val);
	    }
	    private void update(SegmentTreeNode root,int i,int val){
	        if(i == root.start && i == root.end){
	            root.sum = val;
	        }else{
	            int mid = root.start + (root.end - root.start)/2;
	            if(i <= mid){
	                update(root.left,i,val);
	            }else{
	                update(root.right,i,val);
	            }
	            root.sum = root.left.sum + root.right.sum;
	        }
	    }
	    
	    public int sumRange(int i, int j) {
	        return sumRange(root,i,j);
	    }
	    private int sumRange(SegmentTreeNode root,int i, int j) {
	        if(root.start == i && root.end == j){
	            return root.sum;
	        }
	        int mid = root.start + (root.end - root.start)/2;
	        if(i >= mid+1){
	            return sumRange(root.right,i,j);
	        }else if(j <= mid){
	            return sumRange(root.left,i,j);
	        }else{
	            return sumRange(root.left,i,mid) + sumRange(root.right,mid+1,j);
	        }
	    }
	}
	//方法3：
	//树状数组：
//	“树状数组”数据结构的一种应用
//	　　对含有n个元素的数组(a[1],...,a[k],...,a[n])：
//	　　(1)求出第i个到第j个元素的和，sum=a[i]+...+a[j]。
//	　　　　进行j-i+1次加法，复杂度为O(j-i+1)
//	　　(2)任意修改其中某个元素的值。
//	　　　　使用数组下标可以直接定位修改，时间复杂度为O(1)
//	核心思想：
//　　　　(1)树状数组中的每个元素是原数组中一个或者多个连续元素的和。
//　　　　(2)在进行连续求和操作a[1]+...+a[n]时，只需要将树状数组中某几个元素的和即可。时间复杂度为O(lgn)
//　　　　(3)在进行修改某个元素a[i]时，只需要修改树状数组中某几个元素的和即可。时间复杂度为O(lgn)
//　　下图就是一个树状数组的示意图：
//			see NO307_RangeSumQuery_Mutable.png
//			BIT[] as a binary tree:
//								     ______________*
//				                     ______*
//				                      __*       __*
//				                      *    *    *    *
//				  indices: 0 1 2 3 4 5 6 7 8
//	1)　a[]: 保存原始数据的数组。(操作(1)求其中连续多个数的和，操作(2)任意修改其中一个元素)
//　　　　e[]: 树状数组，其中的任意一个元素e[i]可能是一个或者多个a数组中元素的和。
//					如e[2]=a[1]+a[2]; e[3]=a[3]; e[4]=a[1]+a[2]+a[3]+a[4]。 
//　　2） e[i]是几个a数组中的元素的和？
//　　　　如果数字 i 的二进制表示中末尾有k个连续的0，则e[i]是a数组中2^k个元素的和，
//					则e[i]=a[i-2^k+1]+a[i-2^k+2]+...+a[i-1]+a[i]。
//　　　　如：4=100(2)　　e[4]=a[1]+a[2]+a[3]+a[4];
//　　　　　　6=110(2)　　e[6]=a[5]+a[6]
//　　　　　　7=111(2)　　e[7]=a[7]
//　　3)　后继：可以理解为节点的父亲节点。是离它最近的，且编号末位连续0比它多的就是它的父亲,
//							如e[2]是e[1]的后继；e[4]是e[2]的后继。
//　　　　　　如e[4] = e[2]+e[3]+a[4] = a[1]+a[2]+a[3]+a[4] ，e[2]、e[3]的后继就是e[4]。
//　　　　　　后继主要是用来计算e数组，将当前已经计算出的e[i]添加到他们后继中。
//　　　　前驱：节点前驱的编号即为比自己小的，最近的，最末连续0比自己多的节点。
//							如e[7]的前驱是e[6],e[6]的前驱是e[4]。
// 　　　　　  前驱主要是在计算连续和时，避免重复添加元素。
//　　　　　　如：Sum(7)=a[1]+...+a[7]=e[7]+e[6]+e[4]。(e[7]的前驱是e[6], e[6]的前驱是e[4])
//　　　　计算前驱与后继：
//　　　　　　lowbit(i) = ( (i-1) ^ i) & i ;
//　　　　　　节点e[i]的前驱为 e[ i - lowbit(i) ]；
//　　　　　　节点e[i]的后继为 e[ i + lowbit(i) ]
	class NumArray3 {
	    int[] nums;
	    int[] BIT;
	    int n;
	    public NumArray3(int[] nums) {
	        n = nums.length;
	        this.nums = nums;
	        BIT = new int[n+1];
	        for(int i = 0 ; i < n ; i++){
	            add(i,nums[i]);
	        }
	    }
	    private int lowbit(int n){
	        return n&(-n);
	    }
	    private void add(int i,int val){
	        i++;
	        while(i <= n){
	            BIT[i] += val;
	            i = i+lowbit(i);
	        }
	    }
	    public void update(int i, int val) {
	        int diff = val - nums[i];
	        nums[i] = val;
	        add(i,diff);
	    }
	    private int getSum(int i){
	        i++;
	        int sum = 0;
	        while(i > 0){
	            sum+=BIT[i];
	            i -= lowbit(i);
	        }
	        return sum;
	    }
	    public int sumRange(int i, int j) {
	        int max = Math.max(i,j);
	        int min = Math.min(i,j);
	        return getSum(max)-getSum(min-1);
	    }
	}
}
