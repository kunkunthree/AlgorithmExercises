package algorithm.netease.spring2017;
/*
 * 3.
 * [编程题] 调整队形:
 * 在幼儿园有n个小朋友排列为一个队伍，从左到右一个挨着一个编号为(0~n-1)。
 * 其中有一些是男生，有一些是女生，男生用'B'表示，女生用'G'表示。小朋友们都很顽皮，
 * 当一个男生挨着的是女生的时候就会发生矛盾。
 * 作为幼儿园的老师，你需要让男生挨着女生或者女生挨着男生的情况最少。你只能在原队形上进行调整，
 * 每次调整只能让相邻的两个小朋友交换位置，现在需要尽快完成队伍调整，你需要计算出
 * 最少需要调整多少次可以让上述情况最少。
例如：
		GGBBG -> GGBGB -> GGGBB
		这样就使之前的两处男女相邻变为一处相邻，需要调整队形2次
输入描述:
		输入数据包括一个长度为n且只包含G和B的字符串.n不超过50.
输出描述:
		输出一个整数，表示最少需要的调整队伍的次数
输入例子:
GGBBG
输出例子:
2
 */
import java.util.*;
public class AdjustFormation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		System.out.println(getAdjustingTime(s));
	}
	public static int getAdjustingTime(String s){
		char[] array = s.toCharArray();
		int len = array.length;
		int minTime = Integer.MAX_VALUE;
		int tmpTimeG = 0,tmpTimeB = 0;	//countTimeB表示当前移动B时所需要的次数，countTimeG表示当前移动G时所需要的次数
		int countB = 0,countG=0;	//countB表示遍历时所经过的B的个数，countG表示遍历时所经过的G的个数
		for(int i = 0 ; i < len ; i++){
			if(array[i] == 'G'){
				tmpTimeG+=countB;
				countG++;
			}else{
				tmpTimeB+=countG;
				countB++;
			}
		}
		minTime = minTime < tmpTimeB ? minTime : tmpTimeB;
		minTime = minTime < tmpTimeG ? minTime : tmpTimeG;
		tmpTimeB = 0;
		tmpTimeG = 0;
		countB = 0;
		countG = 0;
		for(int i = len-1 ; i >= 0 ; i--){
			if(array[i] == 'G'){
				tmpTimeG+=countB;
				countG++;
			}else{
				tmpTimeB+=countG;
				countB++;
			}
		}
		minTime = minTime < tmpTimeB ? minTime : tmpTimeB;
		minTime = minTime < tmpTimeG ? minTime : tmpTimeG;
		return minTime;
	}
}
