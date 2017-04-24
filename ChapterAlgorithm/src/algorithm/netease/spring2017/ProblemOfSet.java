package algorithm.netease.spring2017;
/*
 * 7
 * [编程题] 集合:
 * 		小易最近在数学课上学习到了集合的概念,集合有三个特征：1.确定性 2.互异性 3.无序性.
 * 小易的老师给了小易这样一个集合：
 * S = { p/q | w ≤ p ≤ x, y ≤ q ≤ z }
 * 需要根据给定的w，x，y，z,求出集合中一共有多少个元素。
 * 小易才学习了集合还解决不了这个复杂的问题,需要你来帮助他。
输入描述:
		输入包括一行：
		一共4个整数分别是w(1 ≤ w ≤ x)，x(1 ≤ x ≤ 100)，y(1 ≤ y ≤ z)，z(1 ≤ z ≤ 100).以空格分隔
输出描述:
		输出集合中元素的个数
输入例子:
1 10 1 1
输出例子:
10
 */
import java.util.*;
public class ProblemOfSet {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int w = in.nextInt();
		int x = in.nextInt();
		int y = in.nextInt();
		int z = in.nextInt();
		System.out.println(getElementsNumber(w, x, y, z));
	}
	public static int getElementsNumber(int w,int x,int y,int z ){
		int sum = 0;
		Map<Integer,Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		int m,n,gcd;
		Set<Integer> set;
		int count = 0;
		for(int i = w ; i <= x ; i++){
			for(int j = y ; j <= z ; j++){
				m = i;
				n = j;
				gcd = getGCD(i, j);
				m/=gcd;
				n/=gcd;
				if(map.containsKey(m)){
					set = map.get(m);
					if(!set.contains(n)){
						sum++;
						set.add(n);
					}
				}else{
					set = new HashSet<Integer>();
					set.add(n);
					map.put(m, set);
					sum++;
				}
			}
		}
		return sum;
	}
	public static int getGCD(int x,int y){
		if(y == 0){
			return x;
		}else if (x < y){
			return getGCD(y, x);
		}else if((x&1) == 0 && (y&1) == 0){
			return getGCD(x>>1, y>>1)<<1;
		}else if((x&1) == 0 && (y&1) == 1){
			return getGCD(x>>1, y);
		}else if((x&1) == 1 && (y&1) == 0){
			return getGCD(x, y>>1);
		}else{
			return getGCD(x-y, y);
		}
	}
}
