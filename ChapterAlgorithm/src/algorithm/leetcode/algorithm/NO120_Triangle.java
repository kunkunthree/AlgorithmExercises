package algorithm.leetcode.algorithm;

/*
 * medium
 * 120. Triangle 
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]

 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, 
 where n is the total number of rows in the triangle. 
 */
import java.util.*;

public class NO120_Triangle {
	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		triangle.add(l);
		l = new ArrayList<Integer>();
		l.add(2);
		l.add(3);
		triangle.add(l);
		System.out.println(minimumTotal3(triangle));
	}

	// 方法1：
	// 利用递归，容易超时
	private int min = Integer.MAX_VALUE;

	public int minimumTotal(List<List<Integer>> triangle) {
		minimumTotal(triangle, 0, 0, 0);
		return min;
	}

	public void minimumTotal(List<List<Integer>> triangle, int level,
			int index, int sum) {
		if (level <= triangle.size() - 1) {
			if (index >= 0 && index < triangle.get(level).size()) {
				sum += triangle.get(level).get(index);
				if (level == triangle.size() - 1) {
					if (sum < min) {
						min = sum;
					}
					return;
				}
				minimumTotal(triangle, level + 1, index, sum);
				minimumTotal(triangle, level + 1, index + 1, sum);
			}
		}
	}

	// 方法2：
	// 动态规划，dp，O(n)space
	// 从上往下，得出每个终点到达的最小值
	public int minimumTotal2(List<List<Integer>> triangle) {
		int length = triangle.size();
		int[] dp = new int[length];
		for (int i = 1; i < length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < length; i++) {
			for (int j = i; j >= 0; j--) {
				if (j == 0) {
					dp[j] += triangle.get(i).get(j);
				} else {
					dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
				}
			}
		}
		int min = dp[0];
		for (int i = 1; i < length; i++) {
			if (dp[i] < min) {
				min = dp[i];
			}
		}
		return min;
	}

	// 方法3：
	// dp，O(n)space,从下往上，直接得到到达顶点的最小值
	public static int minimumTotal3(List<List<Integer>> triangle) {
		int length = triangle.size();
		int[] dp = new int[length + 1];
		for (int i = length - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
			}
		}
		return dp[0];
	}

	// 方法4：
	// dp，O(1)space,从下往上，直接得到到达顶点的最小值
	// 直接利用原来的数组，节省额外空间，不过会改变原来数组的值
	public int minimumTotal4(List<List<Integer>> triangle) {
		int length = triangle.size();
		int[] dp = new int[length + 1];
		for (int i = length - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				triangle.get(i).set(
						j,
						Math.min(triangle.get(i + 1).get(j), triangle
								.get(i + 1).get(j + 1))
								+ triangle.get(i).get(j));
			}
		}
		return triangle.get(0).get(0);
	}
}
