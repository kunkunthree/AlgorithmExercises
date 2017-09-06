package algorithm.pinduoduo.automn20170801;
/*
 * [编程题] 大整数相乘
时间限制：1秒
空间限制：32768K
有两个用字符串表示的非常大的大整数,算出他们的乘积，也是用字符串表示。不能用系统自带的大整数类型。
输入描述:
空格分隔的两个字符串，代表输入的两个大整数

输出描述:
输入的乘积，用字符串表示

输入例子1:
72106547548473106236 982161082972751393

输出例子1:
70820244829634538040848656466105986748
 */
import java.util.*;
public class MultiplicationOfLargeIntegers {
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        int flag1 = 0,flag2 = 0;
        if(a.charAt(0) == '-'){
            flag1 = 1;
        }
        if(b.charAt(0) == '-'){
            flag2 = 1;
        }
        int n = a.length()+b.length()-flag1-flag2;
        int[] bit = new int[n];
        for(int i = 0 ; i < a.length()-flag1 ; i++){
            for(int j = 0 ; j < b.length()-flag2 ; j++){
                bit[i+j+1]+=(a.charAt(i+flag1)-'0')*(b.charAt(j+flag2)-'0');
            }
        }
        int add = 0;
        for(int i = n-1 ; i >= 0 ; i--){
            bit[i]+=add;
            add = bit[i]/10;
            bit[i]%=10;
        }
        StringBuilder sb = new StringBuilder();
        if((flag1^flag2) == 1){
            sb.append("-");
        }
        int index = 0;
        while(bit[index] == 0){
            index++;
        }
        while(index < n){
            sb.append(bit[index++]);
        }
        System.out.println(sb.toString());
    }
}
