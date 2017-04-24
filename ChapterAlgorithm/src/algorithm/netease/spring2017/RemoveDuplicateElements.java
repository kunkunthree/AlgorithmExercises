package algorithm.netease.spring2017;
/*
 * 4.
 * [编程题] 消除重复元素:
 * 		小易有一个长度为n序列，小易想移除掉里面的重复元素，但是小易想是对于每种元素保留最后出现的那个。
 * 小易遇到了困难,希望你来帮助他。
输入描述:
		输入包括两行：
		第一行为序列长度n(1 ≤ n ≤ 50)
		第二行为n个数sequence[i](1 ≤ sequence[i] ≤ 1000)，以空格分隔
输出描述:
		输出消除重复元素之后的序列，以空格分隔，行末无空格
输入例子:
9
100 100 100 99 99 99 100 100 100
输出例子:
99 100
 */
import java.util.*;
public class RemoveDuplicateElements {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < n ; i++){
			list.add(in.nextInt());
		}
		removeDuplicateElements(list);
		int length = list.size();
		for(int i = 0 ; i < length-1 ; i++){
			System.out.print(list.get(i) + " ");
		}
		System.out.println(list.get(length-1));
	}
	public static void removeDuplicateElements(ArrayList<Integer> list){
		Set<Integer> set = new HashSet();
		int length = list.size();
		int index = length-1;
		for(int i = length -1 ; i >= 0; i--){
			Integer tmp = list.get(i);
			if(set.contains(tmp)){
				list.remove(tmp);
			}else{
				set.add(tmp);
			}
		}
	}
}
