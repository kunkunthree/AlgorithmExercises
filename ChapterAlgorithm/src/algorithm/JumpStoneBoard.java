package algorithm;
/*
 * 跳石板:
 * 小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3.......
 * 这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，
 * 小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。 
 * 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
例如：
		N = 4，M = 24：
		4->6->8->12->18->24
		于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板
输入描述:
		输入为一行，有两个整数N，M，以空格隔开。
		(4 ≤ N ≤ 100000)
		(N ≤ M ≤ 100000)
输出描述:
		输出小易最少需要跳跃的步数,如果不能到达输出-1
输入例子:
		4 24
输出例子:
		5
		
		首先，很明显的一道的dp题，到达某一位置的次数依赖于到达前面石板的步数，只不过增加了条件，
所走步数必须为前面石板的约数。

		刚开始的思路是，计算能到达当前石板的所以之前的位置，选择一个步数最少的，后来发现这个思路不可行，
因为有的位置是无法到达的，并且约数的选取很复杂。从后往前不行，那就从前往后吧，如果当前位置是不能到达的，
直接返回，如果可到达，计算当前位置能去的所有位置，如果到达当前位置所需次数+1小于到达位置的当前最小次数，
替换它那么状态转移方程就是
dp[i+yueshu] = min{ dp[i]+1，dp[i+yueshu]}
 */
import java.io.*;
import java.math.*;
import java.util.*;
public class JumpStoneBoard {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().split(" ");
		int small = Integer.parseInt(str[0]);
		int big = Integer.parseInt(str[1]);
		System.out.println(getLeastJumpTime(small, big));
//		Random rand = new Random();
//		while(true){
//			int small = rand.nextInt(99996)+4;
//			int big = rand.nextInt(100000 - small) + small;
//			System.out.println(small + " " + big);
//			System.out.println(getLeastJumpTime(small, big));
//		}
	}
	//方法1：
	public static int getLeastJumpTime(int small ,int big){
		if(small > big)return -1;
		if(small == big)return 0;
		int len = big - small + 1;
		int[] array = new int[len];
		for(int i = 1 ; i < len ; i++){
			array[i] = Integer.MAX_VALUE;
		}
		array[0] = 0;
		ArrayList<Integer> list = null;
		for(int i = 0 ; i < len ; i++){
			if(array[i] == Integer.MAX_VALUE)continue;
			list = getDivisors(small + i);
			for(int j = 0 ; j < list.size() ; j++){
				int tmpNum = list.get(j);
				if(small + i + tmpNum <= big && array[i] +1 < array[i + tmpNum]){
					array[i + tmpNum] = array[i] + 1;
				}
			}
		}
		if(array[len-1] != Integer.MAX_VALUE)return array[len-1];
		else return -1;
	}
	//获得约数列表：
	public static ArrayList<Integer> getDivisors(int n){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 2 ; i <= Math.sqrt(n) ;i++){
			if(n % i == 0){				//约数是成双成对的
				list.add(i);
				if(n/i != i){
					list.add(n/i);
				}
			}
		}
//		System.out.println(n + "		" + list);
		return list;
	}
	//方法2：
	public static int getJumpTime(int small,int big){
		if(big < small){
			return -1;
		}
		if(big == small){
			return 0;
		}
		int tmp,x,time = 0;
		while(small < big){
			tmp = big - small;
			x = small/2;
			while(x > 1){
				if(tmp % x == 0 && small % x == 0){
					break;
				}
				x--;
			}
			if(x>1){
				time++;
				small += x;
			}else{
				return -1;
			}
		}
		if(small != big){
			return -1;
		}
		return time;
	}
}
