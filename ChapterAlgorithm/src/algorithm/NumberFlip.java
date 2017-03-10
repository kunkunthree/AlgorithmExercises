package algorithm;
/*
 * 数字翻转：
 * 		对于一个整数X，定义操作rev(X)为将X按数位翻转过来，并且去除掉前导0。例如:
	如果 X = 123，则rev(X) = 321;
	如果 X = 100，则rev(X) = 1.
	现在给出整数x和y,要求rev(rev(x) + rev(y))为多少？
输入描述:
		输入为一行，x、y(1 ≤ x、y ≤ 1000)，以空格隔开。
输出描述:
		输出rev(rev(x) + rev(y))的值
输入例子:
		123 100
输出例子:
		223
 */
import java.io.*;
public class NumberFlip {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] s = in.readLine().split(" ");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		System.out.println(reverse(reverse(x)+reverse(y)));
	}
	public static int reverse(int num){
		int result = 0;
		int tmp = num;
		while(tmp > 0){
			result = result *10 + tmp%10;
			tmp/=10;
		}
		return result;
	}
}
