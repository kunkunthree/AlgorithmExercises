package algorithm.microsoft.beauty.of.programming.chapter1;
/*
 * 假设有两堆石头，有两个玩家会根据如下的规则轮流取石头：
每人每次可以从两堆石头中各取出数量相等的石头，或者仅从一堆石头中取出
任意数量的石头；最后把剩下的石头一次拿光的人获胜。请问在哪些局面（依
据两堆石头中的石头个数）下，先取石头的玩家有必胜的策略。
 */
import java.util.ArrayList;
import java.util.*;
public class Nim3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		boolean isWin = nim(x,y);
		if(isWin){
			System.out.println("win");
		}else{
			System.out.println("lose");
		}
	}
	//b(n)=a(n)+n,b(n)>a(n)
	//先取者必胜则返回true，否则返回false
	public static boolean nim(int x,int y){
		if(x == y){
			return true;
		}
		if(x > y){
			int t = x;
			x = y;
			y = x;
		}
		if(x == 1 && y == 2){
			return false;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();//保存不安全局面的b的值
		int n = 1;
		list.add(2);
		int delta = 1;			//b(n) = a(n) + n, 这里的delta就是n
		int addition = 0;	//统计列表增加次数，当大于100时，则删减不必要的不安全局面的值，即小于a(n)的值
		while(x > n){
			//a == b时是安全局面，list保存不安全局面b的值，所以n！=b
			while(list.indexOf(++n) != -1);//过滤a==b的情况
//			System.out.println(n);
			delta++;
			list.add(n+delta);
			addition++;
			if(list.size() > 2 && addition > 100){
				shrinkList(list,n);
				addition = 0;
			}
		}
//		System.out.println(list.toString() + " ");
		if( x!=n || list.indexOf(y) == -1){
			return true;
		}else{
			return false;
		}
	}
	public static void shrinkList(ArrayList<Integer> list,int n){
		for(int i = 0 ; i < list.size() ; i++){
			if(list.get(i) < n){
				list.remove(list.get(i));
			}else{
				break;
			}
		}
	}
}
