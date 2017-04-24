package algorithm.microsoft.beauty.of.programming.chapter4;
/*
 * 4.2	瓷砖覆盖问题：
 * 		用1*2的瓷砖覆盖8*8的地板，有多少种方式？如果是N×M的地板呢？
 */
/*
 * 最上面的为第1行，最下面为第n行
 * 从上到下按行DP
 * 其中一行的状态我们用一个二进制表示，0表示没有被覆盖，1表示被覆盖了
 * 最后得到一个01串，这个串变回十进制就是一个状态
 * 定义状态dp[i][s]，表示前i-1行已经放满，第i行的状态为s的方案数
 * 状态转移方程为 dp[i][s]=sum{ dp[i-1][ss] } ，其中状态s与状态ss兼容
 * 这个状态转移方程的内涵在于理解s和ss何为兼容
 * 首先我们约定一个放置方法，就是竖着放的时候，我们暂且将其称为“上凸型摆放”
 * 因为竖放必然占据第i-1行和第i行，我们约定这个方块是属于第i行的，也就是说它凸上去了
 * 那么要在第i行的第j列竖放一个方块的话，第i-1行第j列必须没有方块
 * 也就是说，第i行的放置是受到第i-1行的限制的，反过来说在第i行竖放了方块，也会影响第i-1行的状态
 * 所以这样就可以讲解一下状态转移方程了，前i-2行已经放满了，第i-1行的状态为ss(dp[i-1][ss])
 * 此时在第i行开始放一些方块，放的方法不定，可能横放可能竖放，但是按这个方案放完后
 * 第i-1行刚好被填满，且第i行的状态变为了s，所以不难想到第i-1行的状态ss到第i行的状态s这个转移是唯一的
 * 所以有 dp[i][s]=sum{ dp[i-1][ss] }
 * 最后我们详细讨论一下s和ss在什么情况下是兼容的
 * 1.第i行的第j列为1，第i-1行的第j列为1，这样的话，说明第i行的第j列一定不是竖放而是横放否则会与第i-1行的第j列冲突
 * 	 所以马上紧接着判断第i行第j+1列，如果是1，那么满足横放的规则，同时也要第i-1行第j+1列也要为1，
 * 否则的话这个格子没办法填充，成立后向左移动两格
 * 不满足上述条件的，就是两个不兼容或者不合法的状态
 * 2.第i行第j列为1，第i-1行第j列为0，那么说明第i行第j列应该竖放并填充第i-1行第j列，成立后向左移动一格
 * 3.第i行第j列为0，说明不放方块，那么第i-1行第j列必须为1，否则没法填充这个格子。若第i-1行第j列也为0，
 * 不兼容不合法  (至于第i行第j列这个格子空着干什么，其实就是留出来给第i+1行竖放的时候插进来的)	
 * 那么目标状态是什么，就是dp[n][maxs]，maxs表示全部是1的串，即第n-1行以上全部覆盖满，第n行的状态为maxs，
 * 即没有空着的格子，也全部覆盖满了， 即整个矩形全部被覆盖满了的状态
 * 最后是第1行的初始化问题，因为约定了“上凸型摆放”，所以第1行是不能竖放方格的，只能横放方格，
 * 每横放一个必定占据两个格子，所以在判断一个状态（那个01串）的时候，连着的1的个数必定为偶数，
 * 如果出现了单独的1，说明不合法
*/
import java.util.*;
public class Mondriaan_s_Dream {
	//判断第一行是否合理：
	//第一行只能出现连续偶数个1，因为第一行不能竖放
	public static boolean judgeFirstLine(long st,int m){
		int tmp = 0;
		for(int i = 0 ; i < m ; i++){
			if( (st & (1<<i) )  != 0){
				tmp++;
			}else{
				if(tmp%2 == 1){	//出现奇数个1，则不合理，返回false
					return false;
				}
				tmp = 0;		//重置tmp
			}
		}
		if(tmp % 2 == 1){
			return false;
		}
		return true;
	}
	//相邻两行的比较，判断上一行up状态达到当前行cur状态是否合理
	public static boolean compareTwoLine(int cur,int up,int m){
		for(int i = 0 ; i < m ; i++){
			if((cur & ( 1 << i )) != 0){	//如果当前行第i列为1
				if((up & ( 1 << i )) != 0){	//如果上一行行第i列为1
					//如果同一列都为1，那当前行第i列必然不可以是竖放，只能是横放，
					//如果是横放，则当前行第i+1列也为1，而且上一行第i列不能为0，因为如果为0，则会出现缺口，无法填补
					if(i+1 == m || (cur & ( 1 << (i+1) )) == 0 || (up & ( 1 << (i+1) )) == 0){
						return false;
					}else{
						i++;	//跳两列
					}
				}	//如果当前行第i列为1，上一行第i列为0，那么只能是竖放了
			}	else{
				//如果当前行第i列为0，那么只能是竖放
				if((up & ( 1 << i )) == 0){
					return false;
				}
			}
		}
		return true;
	}
	public static int getNumberOfFilling(int N,int M){
		if(N > M){
			int tmp = N;
			M = N;
			N = tmp;
		}
		int length = 1 << M;
		int[][] dp = new int[N+1][1<<M];
		for(int i = 0 ; i < N+1 ; i++){
			for(int j = 0 ;  j < length ; j++){
				dp[i][j] = 0;
			}
		}
		for(int i = 0 ; i < length ; i++){
			if(judgeFirstLine(i, M)){
				dp[1][i] = 1;
			}
		}
		for(int i = 2; i <= N ; i++){
			for(int cur = 0 ; cur < length ; cur++){
				for(int up = 0 ; up < length ; up++){
					if(compareTwoLine(cur, up, M)){
						dp[i][cur]+=dp[i-1][up];
					}
				}
			}
		}
//		System.out.println(dp[N][length-1]);
		return dp[N][length-1];
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		System.out.println(getNumberOfFilling(N, M));
	}
}
