package algorithm;
/*
 * 不要二：
 * 		二货小易有一个W*H的网格盒子，网格的行编号为0~H-1，网格的列编号为0~W-1。
 * 每个格子至多可以放一块蛋糕，任意两块蛋糕的欧几里得距离不能等于2。
 * 对于两个格子坐标(x1,y1),(x2,y2)的欧几里得距离为:
 * 		( (x1-x2) * (x1-x2) + (y1-y2) * (y1-y2) ) 的算术平方根
 * 小易想知道最多可以放多少块蛋糕在网格盒子里。
输入描述:
		每组数组包含网格长宽W,H，用空格分割.(1 ≤ W、H ≤ 1000)
输出描述:
		输出一个最多可以放的蛋糕数
输入例子:
		3 2
输出例子:
		4
		解析：
				其实就是一个数学问题，分整除4，整除2，奇数等几种情况讨论即可。
				蛋糕位置就是间隔每个2*2的正方形的区域。
 */
import java.util.*;
public class NoTwo {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int w = in.nextInt();
		int h = in.nextInt();
		System.out.println(getCakeNum(w, h) + "");
	}
	public static int getCakeNum(int W,int H){
		int count = 0;
		if(W%4 == 0 || H % 4 == 0){
			count = W * H /2;
		}else if (W % 2 == 0 && H % 2 == 0){
			count = (W * H /4 + 1) * 2;
		}else{
			count = W * H / 2 + 1;
		}
		return count;
	}
}
