package algorithm.leetcode.algorithm;

/*
 * hard
 * 410. Split Array Largest Sum 
 * Given an array which consists of non-negative integers and an integer m, 
 * you can split the array into m non-empty continuous subarrays. 
 * Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:
 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)

 Examples:
 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.

 */
public class NO410_SplitArrayLargestSum {
	// 方法1：
	// 二分搜索，在[max,sum]中二分搜索
	// 以low = max，high = sum为初始边界，mid为中点，
	// 查看mid是否能将数组分成大于等于m组的连续子数组，且子数组的和小于mid
	// 如果可以，则说明mid太大，可以将mid缩小，即，将上界缩小，high = mid -1;
	// 如果不可以，则说明mid太小，可以将mid扩大，即，将下界扩大，low = mid + 1;
	// 直到low > high，最后返回low
	public int splitArray(int[] nums, int m) {
		long sum = 0;
		int max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
			sum += num;
		}
		return (int) binarySearch(nums, m, max, sum);
	}

	private long binarySearch(int[] nums, int m, long low, long high) {
		long mid = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (valid(nums, m, mid)) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	private boolean valid(int[] nums, int m, long max) {
		int count = 1;
		int total = 0;
		for (int num : nums) {
			total += num;
			if (total > max) {
				count++;
				total = num;
				if (count > m) {
					return true;
				}
			}
		}
		return false;
	}

	// 方法2：
	// dp，动态规划
	// DP solution. This is obviously not as good as the binary search
	// solutions; but it did pass OJ.
	// dp[s,j] is the solution for splitting subarray n[j]...n[L-1] into s
	// parts.
	// dp[s+1,i] = min{ max(dp[s,j], n[i]+...+n[j-1]) }, i+1 <= j <= L-s
	// This solution does not take advantage of the fact that the numbers are
	// non-negative
	// (except to break the inner loop early). That is a loss.
	// (On the other hand, it can be used for the problem containing arbitrary
	// numbers)
	public int splitArray2(int[] nums, int m) {
		int n = nums.length;
		int[] sum = new int[n + 1];
		sum[0] = 0;
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = sum[n] - sum[i];
		}

		for (int s = 1; s < m; s++) {
			for (int i = 0; i < n - s; i++) {
				dp[i] = Integer.MAX_VALUE;
				for (int j = i + 1; j <= n - s; j++) {
					int t = Math.max(sum[j] - sum[i], dp[j]);
					if (t <= dp[i]) {
						dp[i] = t;
					} else {
						break;
					}
				}
			}
		}
		return dp[0];
	}
}
