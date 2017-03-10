package algorithm;
import java.io.InputStreamReader;
/*
 * 数字游戏:
 * 		小易邀请你玩一个数字游戏，小易给你一系列的整数。你们俩使用这些整数玩游戏。
 * 每次小易会任意说一个数字出来，然后你需要从这一系列数字中选取一部分出来让它们的和等于小易所说的数字。
 *  例如： 如果{2,1,2,7}是你有的一系列数，小易说的数字是11.你可以得到方案2+2+7 = 11.
 *  如果顽皮的小易想坑你，他说的数字是6，那么你没有办法拼凑出和为6 
 *  现在小易给你n个数，让你找出无法从n个数中选取部分求和的数字中的最小数。
输入描述:
		输入第一行为数字个数n (n ≤ 20)
		第二行为n个数xi (1 ≤ xi ≤ 100000)
输出描述:
		输出最小不能由n个数选取求和组成的数
输入例子:
		3
		5 1 2
输出例子:
		4
		
		解析：
				将所得序列排序，如果A0...An可以得到[1, A0+A1+...+An]间所有的数，此时再引入A(n+1)，
				可以得到的新的一段区间是[A(n+1),A(n+1)+1,...,A(n+1)+A0+A1+...+An]。
				这个区间和原来的区间之间不能有gap，否则就会有求不到的和。
				所以A(n+1)不能大于 A0+A1+...+An+1，注意最后还有一个加1，即
				当A(n+1)>sum(n)+1时，存在gap，存在最小的不能通过求和得到的数为sum(n)+1
 */
import java.util.*;
public class NumberGame {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < num ; i++){
			list.add(in.nextInt());
		}
		System.out.println(getInavailableNum(list));
	}
	public static int getInavailableNum(ArrayList<Integer> list){
		Collections.sort(list);
		System.out.println(list);
		int sum = list.get(0);
		if(sum != 1){
			return 1;
		}
		for(int i = 1 ; i < list.size() ; i ++){
			System.out.println(sum);
			if(list.get(i) <= sum + 1){
				sum+=list.get(i);
			}else{
				break;
			}
		}
		return sum+1;
	}
}
