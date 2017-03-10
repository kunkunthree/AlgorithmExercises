package algorithm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;
/*
 * 优雅的点：
 * 小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
例如：半径的平方如果为25
优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
输入描述:
	输入为一个整数，即为圆半径的平方,范围在32位int范围内。
输出描述:
	输出为一个整数，即为优雅的点的个数
输入例子:
	25
输出例子:
	12 
 */
public class ElegantPoint {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		System.out.println(getElegantPointNum(num));
	}
	public static int getElegantPointNum(int rs){
		int num = 0;
		int radius = (int)Math.sqrt((double)rs);
		if(radius*radius == rs){
			num+=4;
		}
		int left = 1;
		int right = radius;
		while(left < right){
			while(left < right && left * left + right * right < rs){
				left++;
			}
			while(left < right && left * left + right * right > rs){
				right--;
			}
			if(left * left + right * right == rs){
				if(left == right){
					num+=4;
				}
				else{
					num+=8;
				}
				left++;
				right--;
			}
		}
		return num;
	}
}
