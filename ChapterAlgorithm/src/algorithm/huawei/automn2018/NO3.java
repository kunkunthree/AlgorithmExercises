package algorithm.huawei.automn2018;
/*
 * [编程|300分] 2~62进制转换
时间限制：3秒
空间限制：32768K
题目描述
将一个处于Integer类型取值范围内的整数从指定源进制转换为指定目标进制； 可指定的进制值范围为[2,62]； 每个数字位的可取值范围为[0-9a-zA-Z]； 输出字符串的每一个都须为有效值；反例："012"的百位字符即为无效值。 实现时无需考虑非法输入。
输入描述:
输入为：
源进制 目标进制 待转换的整数值

例子：8 16 12345670
输出描述:
整数转换为目标进制后得到的值
示例1
输入

8 16 12345670
输出

29cbb8
 */
import java.util.*;
public class NO3 {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] ss = s.split(" ");
        int src = Integer.valueOf(ss[0]);
        int des = Integer.valueOf(ss[1]);
        int value = getInt(ss[2],src);
        System.out.println(toString(value, des));
    }
    public static String toString(int value,int radix){
        String s = "",flag = "";
        if(value < 0){
            value = -value;
            flag="-";
        }
        while(value > 0){
            int a = value/radix;
            int b = value%radix;
            s=getNumToChar(b)+s;
            value = a;
        }
        return flag+s;
    }
	public static int getInt(String s, int radix) {
        int result = 0, flag = 1,add = 0;
        int index = 0;
        if(s.charAt(index) == '-'){
            flag = -1;
            index++;
        }
        for(int i = index ; i < s.length() ; i++){
            result= result * radix + getCharToNum(s.charAt(i));
        }
        return result * flag;
    }
    public static int getCharToNum(char c){
        if(c >= '0' && c <= '9'){
            return c-'0';
        }else if(c >= 'a' && c <= 'z'){
            return c-'a'+10;
        }else if(c >= 'A' && c <= 'Z'){
            return c-'A'+36;
        }
        return -1;
    }
    public static char getNumToChar(int value){
        if(value < 10){
            return (char)(value+'0');
        }else if(value < 36){
            return (char)(value-10+'a');
        }else if(value < 62){
            return (char)(value-36+'A');
        }
        return '*';
    }
}
