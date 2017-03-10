package algorithm;
import static xie.util.Print.*;
/*
 * 求2-100里的素数
 */
import java.util.Arrays;
public class PrimeNumber {
	public static final int  MaxNum = 100;
	public static void main(String[] args) {
		boolean[] buffer = new boolean[MaxNum+1];
		buffer[0] = true;
		buffer[1] = true;
//		println(Arrays.toString(buffer));
		for(int i = 2 ; i < MaxNum +1; i++){
//			println(i + " " + buffer[i]);
			if(buffer[i] == false){
				int tmp = i;
				for(int j = 2 ; tmp * j < MaxNum+1; j++){
					buffer[tmp * j] = true;
				}
			}
		}
//		println(Arrays.toString(buffer));
		for(int i = 2 ; i < MaxNum +1; i++){
//			println(i + " " + buffer[i]);
			if(buffer[i] == false){
				print(i+" ");
			}
		}
	}
}
