package algorithm;
import static xie.util.Print.*;
/*
 * 打印10000以内的回文数字
 */
public class CircleNumber {
	public static void main(String[] args) {
		for(int i = 10 ; i <= 10000 ; i ++){
			if(isCircleNumber(i)){
				println(i);
			}
		}
	}
	public static boolean isCircleNumber(int n){
		int oldValue = n;
		int tmp = 0;
		while(n > 0){
			tmp = tmp * 10 + n % 10;
			n /= 10;
		}
		return tmp == oldValue;
	}
}
