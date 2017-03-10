package algorithm;
/*
 * 分田地:
 * 		牛牛和 15 个朋友来玩打土豪分田地的游戏，牛牛决定让你来分田地，地主的田地可以看成是一个矩形，
 * 每个位置有一个价值。分割田地的方法是横竖各切三刀，分成 16 份，作为领导干部，
 * 牛牛总是会选择其中总价值最小的一份田地， 作为牛牛最好的朋友，你希望牛牛取得的田地的价值和尽可能大，
 * 你知道这个值最大可以是多少吗？
输入描述:
		每个输入包含 1 个测试用例。每个测试用例的第一行包含两个整数 n 和 m（1 <= n, m <= 75），
		表示田地的大小，接下来的 n 行，每行包含 m 个 0-9 之间的数字，表示每块位置的价值。
输出描述:
		输出一行表示牛牛所能取得的最大的价值。
输入例子:
4 4
3332
3233
3332
2323
输出例子:
		2
		
分析：二分 + 枚举。二分答案判断可行性。

可行性判断：假定二分值为mid。暴力枚举竖切的位置，然后看横切能切多少刀。
枚举横切时，当这部分的4个矩形的价值都大于等于mid，说明这一刀切得合理，从这个位置开始继续往下枚举横切。
如果最终横切的刀数大于等于4，那么说明这个值mid是合理的，否则不合理。通过这样的不断压缩区间，
最终必然能够得到答案。

其中如何巧妙计算每个小矩形的和，也是可以通过预处理然后得到的。
 */
import java.util.*;
public class DivideField {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] s = in.nextLine().split(" ");
		int n = Integer.valueOf(s[0]);
		int m = Integer.valueOf(s[1]);
		int[][] array = new int[n+1][m+1];
		int[][] sum = new int[n+1][m+1];
		for(int i = 1 ; i <= n ; i++){
			String str = in.nextLine();
			for(int j = 0 ; j < str.length() ; j++){
				array[i][j+1] = str.charAt(j) - '0';
			}
		}
		 // sum[i][j]表示坐标(i,j)左上方价值总和
		for(int i = 1 ; i <= n ; i++){
			for(int j = 1 ; j <= m ; j++){
				sum[i][j] =  sum[i][j-1]+sum[i-1][j] - sum[i-1][j-1] + array[i][j];
			}
		}
		int left = 0 , right = sum[n][m],result = 0;
//		通过二分法从0到整块田的总和进行不断分割，从而得到最后符合的最小块的最大值
//		如果left到right的中间值mid符合比某一种分割方式的最小块的值要小于等于，则说明最小块的最大值比mid要大，
//		那么就从区间[mid+1,right]中取mid，再次验证；
//		如果left到right的中间值mid不符合，即任何一种分割方式中不满足任意块大于等于mid的值，
//		则说明最小块的最大值比mid要小
//		那么就从区间[mid+1,right]中取mid，再次验证；
		while(left <= right){		//就算right==left，也必须满足judge才能把left或者right赋给result
			int mid = (left + right) >> 1;
			if(judge(mid, m, n, sum)){
				left = mid+1;
				result = mid;
			}else{
				right = mid-1;
			}
		}
//		for(int i = 0 ; i <= n ; i++){
//			System.out.println(Arrays.toString(array[i]));
//		}
		System.out.println(result);
	}
	public static int getSum(int i , int j , int x , int y , int[][] sum){		//返回点(i,j)和点(x,y)为对顶点的矩形的和
		return sum[i][j] + sum[x][y] - sum[i][y] - sum[x][j];
	}
	public static boolean judge(int tmp,int m,int n,int[][] sum){
		for(int i = 1 ; i <= m-3 ; i++){
			for(int j = i+1 ; j <= m-2 ; j++){
				for(int k = j+1 ; k <= m-1 ; k++){
					int last = 0;
					int cutNum = 0;
					for(int r = 1 ; r <= n ; r++){
						int s1 = getSum(r, i, last, 0, sum);
						int s2 = getSum(r, j, last, i, sum);
						int s3 = getSum(r, k, last, j, sum);
						int s4 = getSum(r, m, last, k, sum);
						if(s1 >= tmp && s2 >= tmp && s3 >= tmp && s4 >= tmp){
							last = r;
							cutNum++;
						}
					}
					if(cutNum >= 4){
						return true;
					}
				}
			}
		}
		return false;
	}
}
