package algorithm;
/*
 * 合唱团：
 * 		有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，
 * 要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
输入描述:
		每个输入包含 1 个测试用例。每个测试数据的第一行包含一个整数 n (1 <= n <= 50)，表示学生的个数，
		接下来的一行，包含 n 个整数，按顺序表示每个学生的能力值 ai（-50 <= ai <= 50）。
		接下来的一行包含两个整数，k 和 d (1 <= k <= 10, 1 <= d <= 50)。
输出描述:
		输出一行表示最大的乘积。
输入例子:
		3
		7 4 7
		2 50
输出例子:
		49
		
解析：
		f [ i ] [ j ] [ 最大 / 最小 ]分别表示，以第i个人为最后一个（也是必选的）人，加上这个人，已经选了 j 个人，
		最大可能的乘积和最小可能的乘积。
——为什么不是只记录最大的，还要记录最小的？
——因为最小的，很可能是一个负数，有着极大的绝对值，再乘一个负数，就变成最大的正数，也就是最优解了。
然后考虑，这个状态由哪些状态转移过来？
j 人，明显是从j-1个人的状态，最后加1个人（当前考虑的 i ）而来。
第 i 人，根据题目要求，编号差不能大于d。那我们就往前观察最多d个人，从i-d到i-1，选了j-1个人中，
选择和自己相乘，最大/最小的。
注意考虑边界条件：只选了一个人，就是 i 自己。
最后，解很大，请使用long long（C++）/ long (Java、C#)来保存中间计算结果。
 */
import java.util.*;
public class Chorus {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] array = new int[n+1];
		for(int i = 1 ; i <=n ; i++){
			array[i] = in.nextInt();
		}
		int k = in.nextInt();
		int d = in.nextInt();
		long[][][] f = new long[n+1][k+1][2];
		long result = 0;
		for(int i = 1 ; i <= n ; i++){
			f[i][1][0] = array[i];
			for(int j = 2 ; j <= k ; j++){
				for(int m = i-1; m >= max(i-d,1) ; m--){
					f[i][j][0] = max(f[i][j][0],max(f[m][j-1][0]*array[i],f[m][j-1][1]*array[i]));
					f[i][j][1] = min(f[i][j][1],min(f[m][j-1][0]*array[i],f[m][j-1][1]*array[i]));
				}
			}
			result = max(result,f[i][k][0]);
		}
//		System.out.println(Arrays.deepToString(f));
		System.out.println(result);
	}
	public static long max(long x, long y){
		return x > y? x:y;
	}
	public static long min(long x, long y){
		return x < y? x:y;
	}
}
