package algorithm.tencent;
/*
 * 算法基础-字符移位:
 * 		小Q最近遇到了一个难题：把一个字符串的大写字母放到字符串的后面，各个字符的相对位置不变，
 * 且不能申请额外的空间。
你能帮帮小Q吗？
输入描述:
		输入数据有多组，每组包含一个字符串s，且保证:1<=s.length<=1000.
输出描述:
		对于每组数据，输出移位后的字符串。
输入例子:
		AkleBiCeilD
输出例子:
		kleieilABCD

 */
import java.util.*;
public class CharacterShift {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			char[] array = in.nextLine().toCharArray();
			int num = 0;
			for(int i = 0 ; i < array.length ; i++){
				if(array[i] >= 'A' && array[i] <= 'Z'){
					num++;
				}
			}
	//		System.out.println(num);
			int tmp = num;
			while(tmp > 0){
				for(int i = array.length + tmp - num -1 ; i >= 0 ; i--){
					if(array[i] >= 'A' && array[i] <= 'Z'){
						for(; i < array.length + tmp -num -1 ; i++){
							array[i] = (char) (array[i] + array[i+1]);
							array[i+1] = (char)(array[i] - array[i+1]);
							array[i] = (char)((array[i] - array[i+1]));
						}
						tmp--;
						break;
					}
				}
			}
			for(int i = 0 ; i < array.length ; i++){
				System.out.print(array[i]);
			}
			System.out.println();
	//		System.out.println(Arrays.toString(array));
		}
	}
}
