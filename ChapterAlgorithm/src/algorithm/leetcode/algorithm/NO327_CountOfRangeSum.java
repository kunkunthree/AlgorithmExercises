package algorithm.leetcode.algorithm;

/*
 * hard
 * 327. Count of Range Sum 
 *  Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

 Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that.

 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2. 
 */
import java.util.*;

public class NO327_CountOfRangeSum {
	public static void main(String[] args) {
		NO327_CountOfRangeSum test = new NO327_CountOfRangeSum();
		int[] nums = new int[] {-2, 5, -1 };
		int lower = -2;
		int upper = 2;
		System.out.println(test.countRangeSum2(nums, lower, upper));
	}

	// 方法1：
	// 利用线段树，把sum(0,i)，0 <= i <= n-1，建立一个线段树
	// 然后做一个n-1~0的遍历，从sum(0,i)更新到线段树中进行计数，
	// 以sum(0,i-1)作为base，统计[lower+base,upper+base]的个数间接统计sum(i,j)，
	// i<=j<=n-1，在[lower,upper]中的计数
	class SegmentTreeNode {
		long count;
		long min, max;
		SegmentTreeNode left, right;

		SegmentTreeNode(long min, long max) {
			this.min = min;
			this.max = max;
		}
	}

	public int countRangeSum(int[] nums, int lower, int upper) {
		// 防止溢出
		long sum = 0;
		int result = 0;
		// 避免建立线段树时有重复相同的节点
		Set<Long> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			sum += (long) nums[i];
			set.add(sum);
		}
		Long[] sums = set.toArray(new Long[0]);
		Arrays.sort(sums);

		SegmentTreeNode root = buildTree(sums, 0, sums.length - 1);

		for (int i = nums.length - 1; i >= 0; i--) {
			// 更新sum(0,i)到线段树中
			update(root, sum);
			sum -= nums[i];
			// 以sum(0,i-1)为base，求base+sum(i,j)在[base+lower , base+upper]中出现的次数
			result += getCount(root, (long) lower + sum, (long) upper + sum);
		}
		return result;
	}

	// 建立线段树
	private SegmentTreeNode buildTree(Long[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode node = new SegmentTreeNode(nums[start], nums[end]);
		if (start == end) {
			return node;
		}
		int mid = start + (end - start) / 2;
		node.left = buildTree(nums, start, mid);
		node.right = buildTree(nums, mid + 1, end);
		return node;
	}

	// 更新val在线段树中出现的次数
	private void update(SegmentTreeNode root, Long val) {
		if (root == null) {
			return;
		}
		if (root.min <= val && root.max >= val) {
			root.count++;
			update(root.left, val);
			update(root.right, val);
		}
	}

	private long getCount(SegmentTreeNode root, long lower, long upper) {
		if (root == null || root.min > upper || root.max < lower) {
			return 0;
		}
		if (root.min >= lower && root.max <= upper) {
			return root.count;
		}
		return getCount(root.left, lower, upper)
				+ getCount(root.right, lower, upper);
	}

	// 方法2：
	// 利用归并,将sum数组进行归并排序，先对sum[left,mid]和sum[mid+1,right]分别进行归并排序
	//此时sum[left,mid]和sum[mid+1,right]都已经排好序了，然后对sum[left,mid]和sum[mid+1,right]进行归并
	//归并过程中统计以sum[i]为base，left<= i <=mid，在右边sum[mid+1,right]中统计在sum[j]-base 在[lower,upper]中的个数
	//即sum[j]-sum[i]=sum(i,j)，left<= i <=mid，mid+1 <= j <= right在[lower,upper]中的个数
	//将所有统计的个数相加就是所求的结果
	int count = 0;
	int lower;
	int upper;

	public int countRangeSum2(int[] nums, int lower, int upper) {
		long[] sum = new long[nums.length + 1];
		long[] helper = new long[nums.length + 1];
		sum[0] = 0;
		this.lower = lower;
		this.upper = upper;
		// debug <=
		for (int i = 1; i <= nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
		mergesort(sum, helper, 0, sum.length - 1);
		return count;
	}
	private void mergesort(long[] sum, long[] helper, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergesort(sum, helper, low, mid);
			mergesort(sum, helper, mid + 1, high);
			merge(sum, helper, low, mid, high);
		}
	}
	private void merge(long[] sum, long[] helper, int low, int mid, int high) {
		int right = mid + 1;
		int cur = low;
		int left = low;
		int k = mid + 1;
		int j = mid + 1;

		for (left = low; left <= mid; left++) {
			while (k <= high && sum[k] - sum[left] < lower)
				k++;
			while (j <= high && sum[j] - sum[left] <= upper)
				j++;
			while (right <= high && sum[right] < sum[left]) {
				helper[cur++] = sum[right++];
			}
			helper[cur++] = sum[left];
			count += j - k;
		}
		/*
		 * while (right <= high) { helper[cur++] = sum[right++]; }
		 * 
		 * for (int i = low; i <= high; i++) { sum[i] = helper[i]; }
		 */
		// since the [right, high] dont change
		for (int i = low; i < right; i++) {
			sum[i] = helper[i];
		}

	}
}
