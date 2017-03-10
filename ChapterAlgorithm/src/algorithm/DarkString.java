package algorithm;
/*
 * 暗黑的字符串:
* 		一个只包含'A'、'B'和'C'的字符串，如果存在某一段长度为3的连续子串中恰好'A'、'B'和'C'各有一个，那么这个字符串就是纯净的，否则这个字符串就是暗黑的。例如：
	BAACAACCBAAA 连续子串"CBA"中包含了'A','B','C'各一个，所以是纯净的字符串
	AABBCCAABB 不存在一个长度为3的连续子串包含'A','B','C',所以是暗黑的字符串
	你的任务就是计算出长度为n的字符串(只包含'A'、'B'和'C')，有多少个是暗黑的字符串。
输入描述:
		输入一个整数n，表示字符串长度(1 ≤ n ≤ 30)
输出描述:
		输出一个整数表示有多少个暗黑字符串
输入例子:
		2
		3
输出例子:
		9
		21
		
		很显然f(n)和f(n-1)有关系。所以可以考虑推导递推公式。
考虑，已知f(n-1)，怎么在后面添加元素使字符串满足暗黑呢？显然，和f(n-1)字符串的后两位的情况有关。

（1）f(n-1)字符串后两位为相同的字母，如AA、BB、CC。

    假设这部分数目为m，显然添加一位可产生的暗黑字符串为m*3. 那么m又等于什么呢？
    想想m是如何得到的？（在f(n-2)字符串后面添加与其最后一位相同的字符，这个数目为f（n-2）个）
    因此，m = f(n-2).

（2）f(n-1)字符串后两位为不同的字母，如AB、BA、AC、CA、BC、CB。

    假设这部分数目为s，显然每个这样的字符串后面可以添加两种字符满足暗黑条件。（如末尾为AB，则可添加A或B）
    因而产生的暗黑字符串为s*2
    那么，s又如何得到呢？
    显然s = f(n-1) - m = f(n-1) - f(n-2)。

综上， f(n) = m*3 + s*2 = 3*f(n-2)+2*（f(n-1) - f(n-2)）=2*f(n-1) +f(n-2)。
 */
import java.io.*;
public class DarkString {
	public static void main(String[] args) throws Exception {
		char x = 'A';
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int length = Integer.parseInt(in.readLine());
		System.out.println(calDarkStringNum(length));
	}
	public static void printStr(String str,int time){
		if(time == 0){
			System.out.println(str);
			return;
		}
		if(time < 0){
			return;
		}
		printStr(str+"A" ,time-1);
		printStr(str+"B" ,time-1);
		printStr(str+"C" ,time-1);
	}
	//方法1：
	public static long calDarkStringNum(long num){
		if(num <= 0){
			return -1;
		}
		if(num == 1){
			return 3;
		}
		if(num == 2){
			return 9;
		}
		return calDarkStringNum(num-1)*2+calDarkStringNum(num-2);
		
	}
	//方法2：
	public static int calString(String str,int time){
		if(time == 0){
			return containsABC(str) ? 0 : 1;
		}
		if(time < 0){
			return -1;
		}
		int num = 0;
		num+=calString(str+"A", time-1);
		num+=calString(str+"B", time-1);
		num+=calString(str+"C", time-1);
		return num;
	}
	public static boolean containsABC(String s){
		if(s.contains("ABC") ||
				s.contains("ACB") ||
				s.contains("BAC") ||
				s.contains("BCA") ||
				s.contains("CAB") ||
				s.contains("CBA")){
			return true;
		}
		return false;
	}
}
