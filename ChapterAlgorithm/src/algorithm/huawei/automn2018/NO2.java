package algorithm.huawei.automn2018;
/*
 * [编程|200分] 消除重复数字
时间限制：3秒
空间限制：32768K
题目描述
给定一个正整数，给出消除重复数字以后最大的整数
输入描述:
正整数，注意考虑长整数
输出描述:
消除重复数字以后的最大整数
示例1
输入

423234
输出

432
 */
import java.util.*;
public class NO2 {
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
