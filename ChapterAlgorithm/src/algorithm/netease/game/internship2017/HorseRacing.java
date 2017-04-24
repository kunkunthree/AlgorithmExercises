package algorithm.netease.game.internship2017;
/*
 * 4.
 * [编程题] 赛马:
 * 		在一条无限长的跑道上，有N匹马在不同的位置上出发开始赛马。当开始赛马比赛后，
 * 所有的马开始以自己的速度一直匀速前进。每匹马的速度都不一样，且全部是同样的均匀随机分布。
 * 在比赛中当某匹马追上了前面的某匹马时，被追上的马就出局。 请问按以上的规则比赛无限长的时间后，
 * 赛道上剩余的马匹数量的数学期望是多少
输入描述:
		每个测试输入包含1个测试用例
		输入只有一行，一个正整数N
		1 <= N <= 1000
输出描述:
		输出一个浮点数，精确到小数点后四位数字，表示剩余马匹数量的数学期望
输入例子:
1
2
输出例子:
1.0000
1.5000

解题思路：
		假设有n只马，速度从大到小排列，a1>a2>...>an，那么a1在任何位置都存活，存活概率为1，
		a2在a1后面才能存活，a2与a1的相对顺序只有两种，存活概率为1/2，即A(1,1)/A(2,2)
		a3必须在a1和a2后面，a3与a1和a2后面，即a3在最后的所有顺序组合除以所有顺序组合，即A(2,2)/A(3,3),即1/3
		同理，an存活概率为1/n
		所以剩余马匹数量的数学期望为1+1/2+1/3+....+1/n
		
 */
import java.util.*;
public class HorseRacing {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		while(in.hasNext()){
			n = in.nextInt();
			System.out.println(String.format("%.4f", getFinalExpectedNumber(n)));
		}
	}
	public static double getFinalExpectedNumber(int n){
		double result = 0;
		for(int i = 1 ; i <=n ; i++){
			result+=(1.0d)/(double)i;
		}
		return result;
	}
}
