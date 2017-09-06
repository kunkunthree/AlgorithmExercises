package algorithm.huawei.automn2018;
/*
 * [编程|100分] 提取偶数下标的子字符串
时间限制：3秒
空间限制：32768K
题目描述
请编写一个转换字符串的函数：将输入字符串中下标为偶数的字符连成一个新的字符串输出。
注意:
1）如果输入字符串的长度超过20， 则转换失败，返回”ERROR!”字符串。
2）输入字符串只能由0-9数字、小写a-z和大写A-Z组成，如果包含其它字符，则转换失败，返回”ERROR!”字符串。
输入描述:
函数原型：void ConvertStr (char *str,  char* output); 

输入：

输入str参数为一个ASCII字符串（C/C++中为0结尾的标准C字符串）。
输出描述:
输出：

若判断输入为合法的字符串，则输出处理后的字符串；

若输入不合法，则输出字符串”ERROR!”；

C/C++通过output参数输出结果，可以假定已经为output分配了足够存放结果的内存；

（请注意添加字符串结尾的0）；

Java函数请直接通过返回值输出结果。
示例1
输入

cdefg22es
输出

ceg2s
 */
import java.util.*;
public class NO1 {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if(s.length() > 20){
            System.out.println("ERROR!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++){
            if((i&1) == 0){
                if(isValid(s.charAt(i))){
                    sb.append(s.charAt(i));
                }else{
                    System.out.println("ERROR!");
                    return;
                }
            }
        }
        System.out.println(sb.toString());
    }
    public static boolean isValid(char c){
        if(c >= '0' && c <= '9' || 
           c >= 'a' && c <= 'z' ||
           c >= 'A' && c <= 'Z'){
            return true;
        }
        return false;
    }
}
