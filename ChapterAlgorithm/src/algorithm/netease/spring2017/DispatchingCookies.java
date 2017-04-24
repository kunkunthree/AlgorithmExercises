package algorithm.netease.spring2017;
/*
 * 12.
 * [编程题] 分饼干:
 * 		易老师购买了一盒饼干，盒子中一共有k块饼干，但是数字k有些数位变得模糊了，看不清楚数字具体是多少了。
 * 易老师需要你帮忙把这k块饼干平分给n个小朋友，易老师保证这盒饼干能平分给n个小朋友。
 * 现在你需要计算出k有多少种可能的数值
输入描述:
		输入包括两行：
		第一行为盒子上的数值k，模糊的数位用X表示，长度小于18(可能有多个模糊的数位)
		第二行为小朋友的人数n
输出描述:
		输出k可能的数值种数，保证至少为1
输入例子:
9999999999999X
3
输出例子:
4
 */
import java.util.*;
public class DispatchingCookies {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		int n = in.nextInt();
		System.out.println(getPosibilityNum(s, n));
	}
	public static long getPosibilityNum(String s,int n){
		char[] array = s.toCharArray();
		int length = array.length;
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		for(int i = 0 ; i < length ; i++){
			if(array[i] == 'X'){
				list.add(i);
			}
		}
		int tmpSum = 0;
		for(int i = 0 ; i < list.get(0) ; i++){
			tmpSum = tmpSum * 10 + array[i] - '0';
		}
		return get2(array, 0,list, tmpSum%n, n);
	}
	public static long get2(char[] array,int index,ArrayList<Integer> list,long sum,int n){
		int result = 0;
		long tmpSum;
		if(list.size()-1 == index){
				for(int i = 0 ; i <= 9 ; i++){
					tmpSum = sum*10+i;
					for(int j = list.get(index)+1 ; j < array.length ; j++){
						tmpSum = tmpSum * 10 + array[j] - '0';
					}
					if(tmpSum % n == 0){
						result++;
					}
				}
		}else{
				for(int i = 0 ; i <= 9 ; i++){
					tmpSum = (sum * 10 + i)%n;
					for(int j = list.get(index)+1 ; j < list.get(index+1) ; j++){
						tmpSum = tmpSum * 10 + array[j] - '0';
					}
					result+=get2(array, index+1, list,tmpSum%n, n);
				}
		}
		return result;
	}
//	public static long get(char[] array,int index,long sum,int n){
//		int result = 0;
//		if(array.length-1 == index){
//			if(array[index] == 'X'){
//				for(int i = 0 ; i <= 9 ; i++){
//					if((sum*10+i)%n == 0){
//						result++;
//					}
//				}
//			}else if((sum*10 + array[index] - '0')%n == 0){
//				result++;
//			}
//		}else{
//			if(array[index] == 'X'){
//				for(int i = 0 ; i <= 9 ; i++){
//					result+=get(array, index+1, sum*10+i, n);
//				}
//			}else{
//				result+=get(array, index+1, sum*10 + array[index] - '0', n);
//			}
//		}
//		return result;
//	}
}
