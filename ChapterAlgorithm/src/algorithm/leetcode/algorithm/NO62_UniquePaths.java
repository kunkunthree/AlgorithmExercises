package algorithm.leetcode.algorithm;

/*
 * medium
 * 62. Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. 
 The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 NO62_UniquePaths.png

 Above is a 3 x 7 grid. How many possible unique paths are there?

 Note: m and n will be at most 100.
 */
import java.util.*;

public class NO62_UniquePaths {
	public static void main(String[] args) {
		System.out.println(uniquePaths3(2, 2));
		// Set<Integer> set = new HashSet<Integer>();
		// set.add(1);
		// Integer[] x = (Integer[])set.toArray();
	}

	// 方法1：
	// 利用递归，做了过多的重复计算，效率太低
	public static int uniquePaths(int m, int n) {
		return uniquePathsHelper(1, 1, m, n);
	}

	public static int uniquePathsHelper(int i, int j, int m, int n) {
		if (i == m && j == n) {
			return 1;
		}
		if (i > m || j > n) {
			return 0;
		}
		return uniquePathsHelper(i + 1, j, m, n)
				+ uniquePathsHelper(i, j + 1, m, n);
	}

	// 方法2：
	// 方法1的改善，用HashMap和HashSet把m*n(m>n)的结果保存起来，重复利用
	public int uniquePaths2(int m, int n) {
		Map<Set<Integer>, Integer> map = new HashMap<Set<Integer>, Integer>();
		return uniquePathsHelper(m, n, map);
	}

	public int uniquePathsHelper(int m, int n, Map<Set<Integer>, Integer> map) {
		if (1 == m && 1 == n) {
			return 1;
		}
		if (1 > m || 1 > n) {
			return 0;
		}
		int result = 0;
		Set<Integer> set = new HashSet<Integer>();
		set.add(m);
		set.add(n);
		if (map.containsKey(set)) {
			result = map.get(set);
		} else {
			result = uniquePathsHelper(m - 1, n, map)
					+ uniquePathsHelper(m, n - 1, map);
			map.put(set, result);
		}
		return result;
	}
	//方法3：
	//直接迭代，用队列把每一个点存起来
	public static int uniquePaths3(int m, int n) {
		Map<Set<Integer>, Integer> map = new HashMap<Set<Integer>, Integer>();
		Stack<Set<Integer>> stack = new Stack<Set<Integer>>();
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		map.put(set, 1);
		set = new HashSet<Integer>();
		set.add(m);
		set.add(n);
		stack.push(set);
		int count = 0;
		while (!stack.isEmpty()) {
			set = stack.pop();
			if (map.containsKey(set)) {
				count += map.get(set);
			} else {
				Integer[] x = new Integer[2];
				set.toArray(x);
				if (set.size() == 1) {
					x[1] = x[0];
				}
				if (x[0] == 1 || x[1] == 1) {
					count++;
				} else if (x[0] >= 1 && x[1] >= 1) {
					Set<Integer> set1, set2;
					set1 = new HashSet<Integer>();
					set1.add(x[0] - 1);
					set1.add(x[1]);
					set2 = new HashSet<Integer>();
					set2.add(x[0]);
					set2.add(x[1] - 1);
					if (map.containsKey(set1) && map.containsKey(set2)) {
						count += map.get(set1) + map.get(set2);
						map.put(set, map.get(set1) + map.get(set2));
					} else {
						stack.push(set1);
						stack.push(set2);
					}
				}
				{
					map.put(set, 0);
				}
			}
		}
		return count;
	}
	//方法4：
	//公式法：因为线路可以看做是m-1个下移操作和n-1和右移操作的排列组合，限制条件是不能有相同的线路
	//如果不考虑操作的不同，排列组合有(m+n-2)!个，
	//但是右移操作是相同的，且有n-1个，右移操作的排列组合有(n-1)!个
	//同理，下移操作的排列组合有(m-1)!个
	//因为要考虑到相同右移操作和相同左移操作的无序性，而且右移操作和左移操作的无关性
	//所以最后不同的路径数为(m+n-2)!/((n-1)!*(m-1)!)
    public int uniquePaths4(int m, int n) {
        double result = 1;
        for(int i = 1 ; i <=  n - 1 ; i++){
            result = result * (double)(i+m-1)/(double)i;
        }
        return (int)result;
    }
    //方法5：
    //动态规划
    public int uniquePaths5(int m, int n) {
        Integer[][] map = new Integer[m][n];
        for(int i = 0; i<m;i++){
            map[i][0] = 1;
        }
        for(int j= 0;j<n;j++){
            map[0][j]=1;
        }
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                map[i][j] = map[i-1][j]+map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }
}
