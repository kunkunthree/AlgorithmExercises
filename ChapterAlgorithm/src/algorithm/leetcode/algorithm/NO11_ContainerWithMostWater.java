package algorithm.leetcode.algorithm;

/*
 * medium
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
 * which together with x-axis forms a container, such that the container contains the most water.

 Note: You may not slant the container and n is at least 2. 
 
 similar problems:
 42. Trapping Rain Water 
 */
public class NO11_ContainerWithMostWater {
	public static void main(String[] args) {
		int[] height = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(maxArea(height));
	}

	// 方法1：两重循环，O(n^2)time，效率太低
	public static int maxArea(int[] height) {
		if (height == null || height.length <= 1) {
			return 0;
		}
		int max = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
			}
		}
		return max;
	}

	// 方法2：O(n)time
	// 用两个指针从两端开始向中间靠拢，如果左端线段短于右端，那么左端右移，反之右端左移，
	// 知道左右两端移到中间重合，记录这个过程中每一次组成木桶的容积，返回其中最大的。
	// 合理性解释：当左端线段L小于右端线段R时，我们把L右移，这时舍弃的是L与右端其他线段（R-1, R-2, ...）
	// 组成的木桶，这些木桶是没必要判断的，因为这些木桶的容积肯定都没有L和R组成的木桶容积大。
	public int maxArea2(int[] height) {
		if (height == null || height.length <= 1) {
			return 0;
		}
		int max = 0;
		int left = 0, right = height.length - 1;
		while (left < right) {
			if (height[left] < height[right]) {
				max = Math.max(max, (right - left) * height[left]);
				left++;
			} else if (height[left] > height[right]) {
				max = Math.max(max, (right - left) * height[right]);
				right--;
			} else {
				max = Math.max(max, (right - left) * height[left]);
				left++;
				right--;
			}
		}
		return max;
	}
}
