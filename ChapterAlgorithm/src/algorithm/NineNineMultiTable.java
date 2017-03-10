package algorithm;
import static xie.util.Print.*;
/*
 * 打印九九乘法口诀表
 */
public class NineNineMultiTable {
	public static void main(String[] args) {
		for(int i = 1,j = 1; j<=9;i++){
			print(i + "*" + j + " = " + i*j + " ");
			if(i == j){
				i = 0;
				j++;
				println();
			}
		}
	}
}
