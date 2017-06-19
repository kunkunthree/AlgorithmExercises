package algorithm.leetcode.algorithm;

/*
 * hard
 * 391. Perfect Rectangle 
 *   Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

 Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).

 Example 1:
 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [3,2,4,4],
 [1,3,2,4],
 [2,3,3,4]
 ]
 Return true. All 5 rectangles together form an exact cover of a rectangular region.
 see NO391_PerfectRectangle_1.png

 Example 2:
 rectangles = [
 [1,1,2,3],
 [1,3,2,4],
 [3,1,4,2],
 [3,2,4,4]
 ]
 Return false. Because there is a gap between the two rectangular regions.
 see NO391_PerfectRectangle_2.png

 Example 3:
 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [1,3,2,4],
 [3,2,4,4]
 ]
 Return false. Because there is a gap in the top center.
 see NO391_PerfectRectangle_3.png

 Example 4:
 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [1,3,2,4],
 [2,2,4,4]
 ]
 Return false. Because two of the rectangles overlap with each other.
 see NO391_PerfectRectangle_4.png

 */
import java.util.*;

public class NO391_PerfectRectangle {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] rectangles = new int[n][4];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 4; j++) {
				rectangles[i][j] = in.nextInt();
			}
		}
		System.out.println(isRectangleCover(rectangles));
	}
	//方法1：
	//得到左下角最小的横纵坐标，和右上角最大的横纵坐标
	//计算所有矩形总面积，通过set保存四个角的信息判断是否有重叠，如果有重叠的点，则删除，
	//最后判断set中是否含有四个角，以及set的长度是否为4，
	//如果都符合的话，那就判断左下角和右上角组成的矩形面积是否等于累加的举行面积
	public static boolean isRectangleCover(int[][] rectangles) {
		int sumArea = 0;
		Set<String> set = new HashSet<>();
		String s1, s2, s3, s4;// 四个角
		int[] result = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE,
				Integer.MIN_VALUE, Integer.MIN_VALUE };
		for (int[] rectangle : rectangles) {
			sumArea += (rectangle[3] - rectangle[1])
					* (rectangle[2] - rectangle[0]);
			result[0] = Math.min(result[0], rectangle[0]);
			result[1] = Math.min(result[1], rectangle[1]);
			result[2] = Math.max(result[2], rectangle[2]);
			result[3] = Math.max(result[3], rectangle[3]);

			s1 = rectangle[0] + " " + rectangle[1];
			s2 = rectangle[0] + " " + rectangle[3];
			s3 = rectangle[2] + " " + rectangle[1];
			s4 = rectangle[2] + " " + rectangle[3];

			if (!set.add(s1))
				set.remove(s1);
			if (!set.add(s2))
				set.remove(s2);
			if (!set.add(s3))
				set.remove(s3);
			if (!set.add(s4))
				set.remove(s4);
		}
		s1 = result[0] + " " + result[1];
		s2 = result[0] + " " + result[3];
		s3 = result[2] + " " + result[1];
		s4 = result[2] + " " + result[3];
		if (!set.contains(s1) || !set.contains(s2) || !set.contains(s3)
				|| !set.contains(s4) || set.size() != 4) {
			return false;
		}
		int width = result[2] - result[0], height = result[3] - result[1];
		return width * height == sumArea;
	}
}
